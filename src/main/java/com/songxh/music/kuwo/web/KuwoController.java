package com.songxh.music.kuwo.web;

import com.alibaba.fastjson.JSON;
import com.songxh.music.core.model.Page;
import com.songxh.music.core.model.Song;
import com.songxh.music.core.web.AbstractMusicController;
import com.songxh.music.kuwo.model.KuwoSong;
import com.songxh.music.kuwo.model.SearchResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/11 下午2:50.
 */
@RestController
@RequestMapping("/kuwo")
public class KuwoController extends AbstractMusicController {
	private static final String SEARCH_URL_FORMAT = "http://search.kuwo.cn/r.s?all=%s&ft=music&itemset=web_2013&client=kt&pn=%d&rn=10&rformat=json&encoding=utf8";
	private static final String MP3_URL_FORMAT = "http://antiserver.kuwo.cn/anti.s?type=convert_url&rid=%s&format=mp3&response=res";

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Page search(String keywords, @RequestParam("page") Integer pn) {
		Page<Song> page = new Page<>();
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format(SEARCH_URL_FORMAT, keywords, pn-1);
		String result = restTemplate.getForObject(url, String.class);
		SearchResponse response = JSON.parseObject(result, SearchResponse.class);
		page.setTotal(response.getTOTAL());
		List<Song> songs = new LinkedList<>();
		for(KuwoSong ns : response.getAbslist()){
			Song song = new Song();
			song.setId(ns.getMUSICRID());
			if(ns.getSONGNAME().startsWith("%")) {
				try {
					song.setName(URLDecoder.decode(ns.getSONGNAME(), "UTF-8"));
				} catch (Exception e) {}
			}
			if(StringUtils.isEmpty(song.getName())){
				song.setName(ns.getSONGNAME());
			}
			song.setAuthor(ns.getARTIST());
			song.setAlbum(ns.getALBUM());
			song.setUrl(String.format(MP3_URL_FORMAT, ns.getMUSICRID()));
			songs.add(song);
		}
		page.setData(songs);
		return page;
	}

	@Override
	protected String getMusicUrlFormat() {
		return MP3_URL_FORMAT;
	}
}

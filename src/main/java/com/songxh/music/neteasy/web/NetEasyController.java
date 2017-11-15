package com.songxh.music.neteasy.web;

import com.songxh.music.core.model.Page;
import com.songxh.music.core.model.Song;
import com.songxh.music.core.web.AbstractMusicController;
import com.songxh.music.neteasy.model.NetEasySong;
import com.songxh.music.neteasy.model.SearchResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/9 下午8:41.
 */
@RestController
@RequestMapping("/163")
public class NetEasyController extends AbstractMusicController {

	private static final String SEARCH_URL = "http://music.163.com/api/search/get/";
	private static final String SEARCH_REQUEST_FORMAT = "s=%s&limit=20&type=1&offset=%d";
	private static final String MP3_URL_FORMAT = "http://music.163.com/song/media/outer/url?id=%s.mp3";

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Page search(String keywords, Integer start) throws UnsupportedEncodingException {
		Page<Song> page = new Page<>();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded");
		requestHeaders.set("Cookie", "appver=1.5.2");
		requestHeaders.set("Referer", "http://music.163.com");
		HttpEntity<String> httpEntity = new HttpEntity<>(String.format(SEARCH_REQUEST_FORMAT, URLEncoder.encode(keywords, "UTF-8"), start), requestHeaders);
		SearchResponse response = restTemplate.postForObject(SEARCH_URL, httpEntity, SearchResponse.class);
		if(response.getCode() != 200){
			return page;
		}
		page.setTotal(response.getResult().getSongCount());
		List<Song> songs = new LinkedList<>();
		for(NetEasySong ns : response.getResult().getSongs()){
			Song song = new Song();
			song.setId(ns.getId().toString());
			song.setName(ns.getName());
			song.setAuthor(ns.getArtists().get(0).getName());
			song.setAlbum(ns.getAlbum().getName());
			song.setUrl(String.format(MP3_URL_FORMAT, ns.getId()));
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

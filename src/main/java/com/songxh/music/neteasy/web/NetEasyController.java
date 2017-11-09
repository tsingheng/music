package com.songxh.music.neteasy.web;

import com.songxh.music.core.model.Page;
import com.songxh.music.core.model.Song;
import com.songxh.music.neteasy.model.NetEasySong;
import com.songxh.music.neteasy.model.SearchResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/9 下午8:41.
 */
@RestController
@RequestMapping("/163")
public class NetEasyController {

	private static final String SEARCH_URL = "http://music.163.com/api/search/get/";
	private static final String SEARCH_REQUEST_FORMAT = "s=%s&limit=20&type=1&offset=%d";
	private static final String MP3_URL_FORMAT = "http://music.163.com/song/media/outer/url?id=%d.mp3";

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Page search(String keywords, Integer start){
		Page<Song> page = new Page<>();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded");
		requestHeaders.set("Cookie", "appver=1.5.2");
		requestHeaders.set("Referer", "http://music.163.com");
		HttpEntity<String> httpEntity = new HttpEntity<>(String.format(SEARCH_REQUEST_FORMAT, keywords, start), requestHeaders);
		SearchResponse response = restTemplate.postForObject(SEARCH_URL, httpEntity, SearchResponse.class);
		if(response.getCode() != 200){
			return page;
		}
		page.setTotal(response.getResult().getSongCount());
		List<Song> songs = new LinkedList<>();
		for(NetEasySong ns : response.getResult().getSongs()){
			Song song = new Song();
			song.setId(ns.getId());
			song.setName(ns.getName());
			song.setAuthor(ns.getArtists().get(0).getName());
			song.setAlbum(ns.getAlbum().getName());
			song.setUrl(String.format(MP3_URL_FORMAT, ns.getId()));
			songs.add(song);
		}
		page.setData(songs);
		return page;
	}

	@RequestMapping("/download")
	public void download(Integer id, String name, HttpServletResponse response) throws Exception {
//		response.setContentType("application/force-download");
		response.addHeader("Content-Disposition", "attachment;fileName=" + new String(name.getBytes("utf-8"),"iso-8859-1"));
		URL url = new URL("http://m10.music.126.net/20171109230226/8db0bd59489a489c139ae11b872fd287/ymusic/e8b0/4d43/9594/e02e7036c2d8c042aaee6e5dce5c1e7e.mp3");
		URLConnection connection = url.openConnection();
		IOUtils.copy(connection.getInputStream(), response.getOutputStream());
	}

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://m10.music.126.net/20171109230226/8db0bd59489a489c139ae11b872fd287/ymusic/e8b0/4d43/9594/e02e7036c2d8c042aaee6e5dce5c1e7e.mp3");
		URLConnection connection = url.openConnection();
		File file = new File("//Volumes/MacHD/test.mp3");
		IOUtils.copy(connection.getInputStream(), new FileOutputStream(file));
	}

}

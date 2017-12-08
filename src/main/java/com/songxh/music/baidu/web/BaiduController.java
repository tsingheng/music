package com.songxh.music.baidu.web;

import com.alibaba.fastjson.JSON;
import com.songxh.music.baidu.model.BaiduSong;
import com.songxh.music.baidu.model.PlayResponse;
import com.songxh.music.baidu.model.SearchResponse;
import com.songxh.music.core.model.Page;
import com.songxh.music.core.model.Song;
import com.songxh.music.core.web.AbstractMusicController;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * Created by xiangheng.song on 2017/12/8 9:21.
 */
@Controller
@RequestMapping("/baidu")
public class BaiduController extends AbstractMusicController {

    private static final String SEARCH_URL_FORMAT = "http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.search.merge&format=json&query=%s&page_no=%d&page_size=10&type=-1&data_source=0&use_cluster=1";
    private static final String MP3_URL_FORMAT = "/baidu/play/%s.mp3";
    private static final String PLAYER_URL_FORMAT = "http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.song.play&songid=%s&bit=24&_t=%d";

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Page search(String keywords, @RequestParam("page") Integer pn) {
        Page<Song> page = new Page<>();
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(SEARCH_URL_FORMAT, keywords, pn);
        String result = restTemplate.getForObject(url, String.class);
        SearchResponse response = JSON.parseObject(result, SearchResponse.class);
        page.setTotal(response.getResult().getSong_info().getTotal());
        List<Song> songs = new LinkedList<>();
        for(BaiduSong bs : response.getResult().getSong_info().getSong_list()){
            Song song = new Song();
            song.setId(bs.getSong_id());
            song.setName(bs.getTitle());
            song.setAuthor(bs.getAuthor());
            song.setAlbum(bs.getAlbum_title());
            song.setUrl(String.format(MP3_URL_FORMAT, bs.getSong_id()));
            songs.add(song);
        }
        page.setData(songs);
        return page;
    }

    @RequestMapping("/play/{id}.mp3")
    public void play(@PathVariable String id, HttpServletResponse response) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(String.format(PLAYER_URL_FORMAT, id, System.currentTimeMillis()), String.class);
        PlayResponse playResponse = JSON.parseObject(result, PlayResponse.class);

        URL url = new URL(playResponse.getBitrate().getFile_link());
        URLConnection connection = url.openConnection();
        response.setContentType("audio/mpeg");
        response.addHeader("Content-Length", String.valueOf(connection.getContentLength()));
        response.addHeader("Content-Range", "bytes 0-" + (playResponse.getBitrate().getFile_size()-1) + "/" + playResponse.getBitrate().getFile_size());
        IOUtils.copy(connection.getInputStream(), response.getOutputStream());
    }

    @RequestMapping("/download")
    public void download(String id, String name, HttpServletResponse response) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(String.format(PLAYER_URL_FORMAT, id, System.currentTimeMillis()), String.class);
        PlayResponse playResponse = JSON.parseObject(result, PlayResponse.class);

        URL url = new URL(playResponse.getBitrate().getFile_link());
        URLConnection connection = url.openConnection();
        response.setContentType("application/force-download");
        String filename = new String(name.getBytes("utf-8"),"iso-8859-1");
        response.addHeader("Content-Disposition", "attachment;fileName=" + filename + ".mp3");
        response.addHeader("Content-Length", playResponse.getBitrate().getFile_size().toString());
        IOUtils.copy(connection.getInputStream(), response.getOutputStream());
    }

    @Override
    protected String getMusicUrlFormat() {
        return MP3_URL_FORMAT;
    }
}

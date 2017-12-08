package com.songxh.music.baidu.model;

import java.util.List;

/**
 * Description:
 * Created by xiangheng.song on 2017/12/8 23:14.
 */
public class SongInfo {

    private List<BaiduSong> song_list;

    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<BaiduSong> getSong_list() {
        return song_list;
    }

    public void setSong_list(List<BaiduSong> song_list) {
        this.song_list = song_list;
    }
}

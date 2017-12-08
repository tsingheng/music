package com.songxh.music.baidu.model;

/**
 * Description:
 * Created by xiangheng.song on 2017/12/8 23:28.
 */
public class BitRate {

    private String file_link;

    private Long song_file_id;

    private Long file_size;

    private Integer free;

    public String getFile_link() {
        return file_link;
    }

    public void setFile_link(String file_link) {
        this.file_link = file_link;
    }

    public Long getSong_file_id() {
        return song_file_id;
    }

    public void setSong_file_id(Long song_file_id) {
        this.song_file_id = song_file_id;
    }

    public Long getFile_size() {
        return file_size;
    }

    public void setFile_size(Long file_size) {
        this.file_size = file_size;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }
}

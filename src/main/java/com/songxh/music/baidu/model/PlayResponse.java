package com.songxh.music.baidu.model;

/**
 * Description:
 * Created by xiangheng.song on 2017/12/8 23:28.
 */
public class PlayResponse {

    private BitRate bitrate;

    private Integer error_code;

    public BitRate getBitrate() {
        return bitrate;
    }

    public void setBitrate(BitRate bitrate) {
        this.bitrate = bitrate;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }
}

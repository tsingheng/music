package com.songxh.music.baidu.model;

/**
 * Description:
 * Created by xiangheng.song on 2017/12/8 9:14.
 */
public class SearchResponse {

    private Integer error_code;

    private Result result;

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}

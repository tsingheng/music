package com.songxh.music.neteasy.model;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/9 下午8:55.
 */
public class SearchResponse {

	private Integer code;

	private SearchResult result;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public SearchResult getResult() {
		return result;
	}

	public void setResult(SearchResult result) {
		this.result = result;
	}
}

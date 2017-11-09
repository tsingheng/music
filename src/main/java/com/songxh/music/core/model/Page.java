package com.songxh.music.core.model;

import java.util.List;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/9 下午8:43.
 */
public class Page<T> {

	private Integer total;

	private List<T> data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}

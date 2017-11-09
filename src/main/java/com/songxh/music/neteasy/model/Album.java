package com.songxh.music.neteasy.model;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/9 下午8:59.
 */
public class Album {

	private Integer id;

	private String name;

	private Long publishTime;

	private Integer size;

	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

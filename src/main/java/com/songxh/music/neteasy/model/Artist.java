package com.songxh.music.neteasy.model;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/9 下午8:58.
 */
public class Artist {

	private Integer id;

	private String name;

	private String picUrl;

	private Integer albumSize;

	private Integer picId;

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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getAlbumSize() {
		return albumSize;
	}

	public void setAlbumSize(Integer albumSize) {
		this.albumSize = albumSize;
	}

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}
}

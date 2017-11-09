package com.songxh.music.neteasy.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/9 下午8:57.
 */
public class NetEasySong {

	private Integer id;

	private String name;

	private List<Artist> artists;

	private Album album;

	private Integer duration;

	private Integer status;

	private BigDecimal fee;

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

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
}

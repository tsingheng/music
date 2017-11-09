package com.songxh.music.neteasy.model;

import java.util.List;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/9 下午8:57.
 */
public class SearchResult {

	private Integer songCount;

	private List<NetEasySong> songs;

	public Integer getSongCount() {
		return songCount;
	}

	public void setSongCount(Integer songCount) {
		this.songCount = songCount;
	}

	public List<NetEasySong> getSongs() {
		return songs;
	}

	public void setSongs(List<NetEasySong> songs) {
		this.songs = songs;
	}
}

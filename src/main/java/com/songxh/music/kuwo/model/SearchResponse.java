package com.songxh.music.kuwo.model;

import java.util.List;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/11 下午2:46.
 */
public class SearchResponse {

	private Integer TOTAL;

	private Integer PN;

	private Integer RN;

	private List<KuwoSong> abslist;

	public Integer getTOTAL() {
		return TOTAL;
	}

	public void setTOTAL(Integer TOTAL) {
		this.TOTAL = TOTAL;
	}

	public Integer getPN() {
		return PN;
	}

	public void setPN(Integer PN) {
		this.PN = PN;
	}

	public Integer getRN() {
		return RN;
	}

	public void setRN(Integer RN) {
		this.RN = RN;
	}

	public List<KuwoSong> getAbslist() {
		return abslist;
	}

	public void setAbslist(List<KuwoSong> abslist) {
		this.abslist = abslist;
	}
}

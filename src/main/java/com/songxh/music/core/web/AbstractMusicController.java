package com.songxh.music.core.web;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.net.URLConnection;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/11 下午3:00.
 */
public abstract class AbstractMusicController {

	protected String getMusicUrlFormat(){
		return "";
	}

	@RequestMapping("/download")
	public void download(String id, String name, HttpServletResponse response) throws Exception {
		URL url = new URL(String.format(getMusicUrlFormat(), id));
		URLConnection connection = url.openConnection();
		response.setContentType("application/force-download");
		String filename = new String(name.getBytes("utf-8"),"iso-8859-1");
		response.addHeader("Content-Disposition", "attachment;fileName=" + filename + ".mp3");
		IOUtils.copy(connection.getInputStream(), response.getOutputStream());
	}
}

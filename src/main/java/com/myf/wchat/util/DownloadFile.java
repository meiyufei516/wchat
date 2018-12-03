package com.myf.wchat.util;


import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author MeiYF
 * @time 2018/11/30 13:52
 **/
public class DownloadFile {
	private ResourceLoader resourceLoader;
	/**
	 * 下载直播视频所需的插件
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/pub/downloadVideoPlug")
	public void downloadVideoPlug(HttpServletRequest request, HttpServletResponse response) {
		FileInputStream inputStream = null;
		OutputStream out = null;
		try {
			//获取客户端浏览器和操作系统信息
			String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
			// path是指欲下载的文件的路径。
			String filePath = resourceLoader.getResource("/static/media/").getURI().getPath();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download;charset=UTF-8");
			//要下载的文件名这个是对页面呈现，让用户看到的名字，可以自己更改,对这个名字进行处理
			String fileName = "Activex.rar";
			if (UserAgent.indexOf("firefox") >= 0) {
				// 针对火狐浏览器处理方式不一样了
				fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			} else {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			}
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			//此处的Activex.rar就是本地存在的文件的真实名字
			File file = new File(filePath + "Activex.rar");
			inputStream = new FileInputStream(file);
			out = response.getOutputStream();
			int b;
			byte[] buffer = new byte[1024];
			while ((b = inputStream.read(buffer)) > 0) {
				//4.写到输出流(out)中
				out.write(buffer, 0, b);
			}
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
				out.close();
			}catch (Exception e){

			}
		}
	}
}

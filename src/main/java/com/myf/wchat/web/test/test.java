package com.myf.wchat.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MeiYF
 * 2018/11/30 15:50
 * 杂项测试
 **/
@Controller
public class test {


	@RequestMapping("/wordPictureDiv")
	public String wordPictureDiv(){
		return "/test/wordPictureDiv";
	}
	/**
	 * checkBox全选反选
	 * @return 返回
	 */
	@RequestMapping("/checkBoxTest")
	public String checkBoxTest(){
		return "/test/checkBoxTest";
	}

	/**
	 * 获取验证码
	 * @param code
	 * @return
	 */
	@RequestMapping("/getCheckCode/{code}")
	public String getCheckCode(@PathVariable("code") String code){
		if (code.equals("getCheckCode_one")) {
			//验证码
	      return "/test/checkCode/checkCode";
		}
		//60s获取验证码
		return "/test/checkCode/checkCode_Two";
	}


	/**
	 * 上传图片立刻显示
	 * @return
	 */
	@RequestMapping("/picturePre")
	public String picturePre(){
		return "/test/picturePre";
	}


	/**
	 *二维码测试
	 * @return
	 */
	@RequestMapping("/code")
	public String code(){
		return "/test/code";
	}


	/**
	 * main方法
	 */
	public static void main(String[] args) {
		String a="bbc";
		final String b="b";
		final String c="bc";
		String d=b+c;
		System.out.println(d==a);
	}
}

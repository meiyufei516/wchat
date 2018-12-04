package com.myf.wchat.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MeiYF
 * 2018/11/30 15:50
 * 杂项测试
 **/
@Controller
public class test {

	/**
	 * checkBox全选反选
	 * @return 返回
	 */
	@RequestMapping("/checkBoxTest")
	public String checkBoxTest(){
		return "/test/checkBoxTest";
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
	 * 60s获取验证码
	 * @return
	 */
	@RequestMapping("/picturePre")
	public String picturePre(){
		return "/test/picturePre";
	}

}

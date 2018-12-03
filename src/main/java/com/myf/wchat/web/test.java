package com.myf.wchat.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MeiYF
 * @time 2018/11/30 15:50
 **/
@Controller
public class test {

	@RequestMapping("/checkBoxTest")
	public String checkBoxTest(){
		return "/test/picturePre";
	}
}

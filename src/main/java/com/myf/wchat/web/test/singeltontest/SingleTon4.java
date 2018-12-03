package com.myf.wchat.web.test.singeltontest;

/**
 * @author MeiYF
 * @time 2018/12/3 15:37
 * 饿汉，变种
 * 表面上看起来差别挺大，其实更第三种方式差不多，都是在类初始化即实例化instance
 **/
public class SingleTon4 {

	private SingleTon4(){};

	private static SingleTon4 singleTon4=null;

	static {
		singleTon4=new SingleTon4();
	}

	public static SingleTon4 getSingleTon4(){
		return singleTon4;
	}
}

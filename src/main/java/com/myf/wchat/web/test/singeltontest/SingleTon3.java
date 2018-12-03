package com.myf.wchat.web.test.singeltontest;

/**
 * @author MeiYF
 * @time 2018/12/3 15:17
 * 饿汉
 * 这种方式基于classloder机制避免了多线程的同步问题，
 * 不过，instance在类装载时就实例化，这时候初始化instance显然没有达到lazy loading的效果。
 **/
public class SingleTon3 {

	private SingleTon3(){};

	private static SingleTon3 singleTon3=new SingleTon3();

	public static SingleTon3 getSingleTon3(){
		return singleTon3;
	}
}

package com.myf.wchat.web.test.singeltontest;

/**
 * @author MeiYF
 * 2018/12/3 14:53
 * 懒汉，线程不安全
 * 这种写法lazy loading很明显，但是致命的是在多线程不能正常工作。
 **/
public class SingleTon1 {

	private SingleTon1() {
	}

	private static SingleTon1 singleTon1;

	public static SingleTon1 getSingleTon1() {
		if (singleTon1 == null) {
			singleTon1 = new SingleTon1();
		}
		return singleTon1;
	}

}

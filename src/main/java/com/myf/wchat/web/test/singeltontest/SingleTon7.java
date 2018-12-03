package com.myf.wchat.web.test.singeltontest;

/**
 * @author MeiYF
 * @time 2018/12/3 15:52
 * 双重校验锁
 **/
public class SingleTon7 {

	private SingleTon7(){}

	private volatile static SingleTon7 singleTon7;

	public static SingleTon7 getSingleTon7(){
		if (singleTon7 == null) {
			synchronized (SingleTon7.class) {
				if (singleTon7 == null){
					singleTon7 = new SingleTon7();
				}
			}
		}
		return singleTon7;
	}
}

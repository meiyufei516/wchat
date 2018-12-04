package com.myf.wchat.web.test.singeltontest;

/**
 * @author MeiYF
 * 2018/12/3 15:00
 * 懒汉，线程安全
 * 这种写法在getInstance()方法中加入了synchronized锁。能够在多线程中很好的工作，
 * 而且看起来它也具备很好的lazy loading，但是效率很低（因为锁），并且大多数情况下不需要同步。
 **/
public class SingleTon2 {

	private SingleTon2() { };

	private static SingleTon2 singleTon2;

	public static SingleTon2 getSingleTon2() {
		if (singleTon2 == null) {
			synchronized (SingleTon2.class){
				singleTon2 = new SingleTon2();
			}
		}
		return singleTon2;
	}
}

package com.myf.wchat.web.test.singeltontest;

/**
 * @author MeiYF
 * @time 2018/12/3 15:12
 **/
public class SingeTonTest2 extends Thread {

	@Override
	public void run() {
		super.run();
		SingleTon1 singleTon1=SingleTon1.getSingleTon1();
		System.out.println("SingleTon1的hashcode:"+singleTon1.hashCode());
	}

	public static void main(String[] args) {
		for (int i = 0; i <5 ; i++) {
			new SingeTonTest2().run();
		}
	}
}

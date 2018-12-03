package com.myf.wchat.web.test.singeltontest;

/**
 * @author MeiYF
 * @time 2018/12/3 14:58
 **/
public class SingelTonTest implements Runnable {

	@Override
	public void run() {
		/*SingleTon1 singleTon1=SingleTon1.getSingleTon1();
		System.out.println("SingleTon1的hashcode:"+singleTon1.hashCode());*/

		/*SingleTon2 singleTon2=SingleTon2.getSingleTon2();
		System.out.println("SingleTon2的hashcode:"+singleTon2.hashCode());*/

		System.out.println("SingleTon3的hashcode:"+SingleTon3.getSingleTon3().hashCode());
	}

	public static void main(String[] args) {
		Thread t1=new Thread(new SingelTonTest());
		Thread t2=new Thread(new SingelTonTest());
		Thread t3=new Thread(new SingelTonTest());
		Thread t4=new Thread(new SingelTonTest());
		t1.run();
		t2.run();
		t3.run();
		t4.run();
	}
}

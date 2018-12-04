package com.myf.wchat.web.test.singeltontest;

/**
 * @author MeiYF
 * @time 2018/12/3 15:52
 * 双重校验锁
 *     volatile关键字，考虑的是，new关键字在虚拟机中执行时其实分为很多步骤，
 * 具体原因可以参考深入理解java虚拟机一书（考虑的是这个new关键字字节码执行时是非原子性的），
 * 而volatile关键字可以防止指令重排。
 *
 *     假如有A、B、C三个线程，A、B比C快，A和B都通过了0处到达1处，判断了对象为null后，
 * A和B到达2处，A进入锁，到达3处再进行一次null判断，如果是null,就创建对象。
 *     当A运行完后释放锁，B进入锁到达3处，判断此时的对象不为空，就直接返回对象。
 *     此时C通过0处到达1处，判断对象不为Null,就直接返回对象。
 **/
public class SingleTon7 {

	private SingleTon7(){}

	private volatile static SingleTon7 singleTon7;

	public static SingleTon7 getSingleTon7(){//0
		if (singleTon7 == null) {//1
			synchronized (SingleTon7.class) {//2
				if (singleTon7 == null){//3
					singleTon7 = new SingleTon7();
				}
			}
		}
		return singleTon7;
	}
}

package com.myf.wchat.web.test.singeltontest;

/**
 * @author MeiYF
 * @time 2018/12/3 15:46
 * 枚举
 * 这种方式不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象
 **/
public class SingelTon6 {

	private SingelTon6() { }

	public static SingelTon6 getsingelTon6() {
		return SingelTon.INSTANCE.getsingelTon6();
	}


	private enum SingelTon {
		/**
		 * INSTANCE
		 */
		INSTANCE;
		private SingelTon6 singelTon6;

		//JVM会保证此方法绝对只调用一次
		private SingelTon() {
			singelTon6 = new SingelTon6();
		}

		public SingelTon6 getsingelTon6() {
			return singelTon6;
		}
	}

	/**
	 * main测试
	 * @param args
	 */
	public static void main(String[] args) {
		SingelTon6 s1=SingelTon6.getsingelTon6();
		SingelTon6 s2=SingelTon6.getsingelTon6();
		System.out.println((s1.hashCode())==(s2.hashCode()));
	}
}

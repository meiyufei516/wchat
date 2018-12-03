package com.myf.wchat.util;

/**
 * 所有的静态常量
 * @author MeiYF
 * @time 2018/11/23 15:28
 **/
public class StaticContent {
	/**
	 * 接收到的信息是文本
	 */
	public static final String MSGTYPE_TEXT="text";
	/**
	 * 接收到的信息是图片
	 */
	public static final String MSGTYPE_IMAGE="image";
	/**
	 * 接收到的信息是语音
	 */
	public static final String MSGTYPE_VOICE="voice";
	/**
	 * 接收到的信息是视频
	 */
	public static final String MSGTYPE_VIDEO="video";
	/**
	 * 接收到的信息是小视频
	 */
	public static final String MSGTYPE_SHORTVIDEO="shortvideo";
	/**
	 * 接收到的信息是链接
	 */
	public static final String MSGTYPE_LINK="link";
	/**
	 * 接收到的信息是地理位置
	 */
	public static final String MSGTYPE_LOCATION="location";
	/**
	 * 图文信息
	 */
	public static final String MSGTYPE_IMAGE_TEXT="图文";
	/**
	 * 返回消息类型：音乐
	 */
	public static final String MSGTYPE_MUSIC = "music";

	/**
	 * 请求消息类型：推送
	 */
	public static final String MSGTYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String MSGTYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String MSGTYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String MSGTYPE_CLICK = "CLICK";
	/**
	 * boolean值
	 */
	public static boolean FLAGE;
	/**
	 * 返回的xml
	 */
	public static String RETURNXML;
}

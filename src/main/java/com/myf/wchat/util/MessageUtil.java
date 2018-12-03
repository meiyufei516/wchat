package com.myf.wchat.util;

import com.myf.wchat.domain.messageDomain.request.Etvideomessagereq;
import com.myf.wchat.domain.messageDomain.response.Article;
import com.myf.wchat.domain.messageDomain.response.Etrespmessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MeiYF
 * @time 2018/11/23 14:30
 * 消息工具类
 */
public class MessageUtil {

	/**
	 * 解析微信发来的请求（XML）
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}

	/**
	 * 文本消息对象转换成xml
	 * @param etrespmessage 文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(Etrespmessage etrespmessage) {
		xstream.alias("xml", etrespmessage.getClass());
		return xstream.toXML(etrespmessage);
	}
	/**
	 * 视频消息对象转换成xml
	 * @param etvideomessagereq 视频消息对象
	 * @return xml
	 */
	public static String videoMessageToXml(Etvideomessagereq etvideomessagereq) {
		xstream.alias("xml", etvideomessagereq.getClass());
		String videoXml=xstream.toXML(etvideomessagereq);
		String videoXml1=videoXml.replace("<MediaId>","<Video><MediaId>");
		String videoXml2=videoXml1.replace("</Description>","</Description></Video>");
		return videoXml2;
	}
	/**
	 * 语音消息对象转换成xml
	 * @param etrespmessage 语音消息对象
	 * @return xml
	 */
	public static String voiceMessageToXml(Etrespmessage etrespmessage) {
		xstream.alias("xml", etrespmessage.getClass());
		String voiceXml=xstream.toXML(etrespmessage);
		String voiceXml1=voiceXml.replace("<MediaId>","<Voice><MediaId>");
		String voiceXml2=voiceXml1.replace("</MediaId>","</MediaId></Voice>");
		return voiceXml2;
	}
	/**
	 * 图片消息对象转换成xml
	 * @param etrespmessage 图片消息对象
	 * @return xml
	 */
	public static String imageMessageToXml(Etrespmessage etrespmessage) {
		xstream.alias("xml", etrespmessage.getClass());
		String imgXml=xstream.toXML(etrespmessage);
		String imgXml1=imgXml.replace("<MediaId>","<Image><MediaId>");
		String imgXml2=imgXml1.replace("</MediaId>","</MediaId></Image>");
		return imgXml2;
	}

	/**
	 * 音乐消息对象转换成xml
	 * @param musicMessage 音乐消息对象
	 * @return xml
	 */
	/*public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}*/

	/**
	 * 图文消息对象转换成xml
	 * @param etrespmessage 图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(Etrespmessage etrespmessage) {
		xstream.alias("xml", etrespmessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(etrespmessage);
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 * @date
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;
				@Override
				public void startNode(String name, Class clazz) {
					if(name.equals("ArticleCount")||name.equals("CreateTime")){
						cdata = false;
					}
					super.startNode(name, clazz);
				}
				@Override
				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
						cdata = true;
					}
				}
			};
		}
	});
}

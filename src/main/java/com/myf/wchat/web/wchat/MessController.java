package com.myf.wchat.web.wchat;

import com.myf.wchat.config.SysConfig;
import com.myf.wchat.domain.messageDomain.request.*;
import com.myf.wchat.domain.messageDomain.response.Article;
import com.myf.wchat.domain.messageDomain.response.Etrespmessage;
import com.myf.wchat.service.MessageService;
import com.myf.wchat.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author MeiYF
 * @time 2018/11/26 17:13
 * 封装的写法
 **/
@Controller
public class MessController {
	private static final Logger logger= LoggerFactory.getLogger(MessController.class);
	@Autowired
	private SysConfig sysConfig;
	@Autowired
	private MessageService messageService;

	/**
	 * 微信的接口配置的URL地址
	 * @param request
	 * @return
	 */
	@RequestMapping("/wchat")
	@ResponseBody
	public void wChatContoller(HttpServletRequest request, HttpServletResponse response) throws AesException, IOException {
		StaticContent.FLAGE=request.getMethod().toLowerCase().equals("get");
		if (StaticContent.FLAGE){
			//get请求，验证token
			this.checkToken(request,response);
		}else{
			//post请求，消息
			this.accessMess(request,response);
		}

	}

	/**
	 * 接受消息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void accessMess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			//xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			if(StaticContent.MSGTYPE_TEXT.equals(requestMap.get("MsgType"))){
				if(StaticContent.MSGTYPE_IMAGE_TEXT.equals(requestMap.get("Content"))){
					//图文消息
					this.textImageMess(requestMap,response);
				}else{
					//文本信息
					this.accessTextMess(requestMap,response);
				}
			}else if(StaticContent.MSGTYPE_IMAGE.equals(requestMap.get("MsgType"))){
				//图片信息
				this.accessImageMess(requestMap,response);
			}else if(StaticContent.MSGTYPE_VOICE.equals(requestMap.get("MsgType"))){
				//语音信息
				this.accessVoiceMess(requestMap,response);
			}else if(StaticContent.MSGTYPE_VIDEO.equals(requestMap.get("MsgType"))||StaticContent.MSGTYPE_SHORTVIDEO.equals(requestMap.get("MsgType"))){
				//视频、小视频信息
				this.accessVideoMess(requestMap,response);
			}else if(StaticContent.MSGTYPE_LOCATION.equals(requestMap.get("MsgType"))){
				//地理信息
				this.accessLocationMess(requestMap,response);
			}else if(StaticContent.MSGTYPE_LINK.equals(requestMap.get("MsgType"))){
				//链接信息
				this.accessLinkMess(requestMap,response);
			}else if(StaticContent.MSGTYPE_EVENT.equals(requestMap.get("MsgType"))){
				//Event事件
				this.accessEvent(requestMap,response);
			}
		}catch (Exception e){
			logger.error("【"+DataUtil.getLogTime()+"】解析发送的数据失败："+e.getMessage());
		}
	}


	/**
	 * 点击事件
	 * @param requestMap
	 * @param response
	 * @throws IOException
	 */
	public void accessClick (Map<String, String> requestMap,HttpServletResponse response) throws IOException {

        //接收到的消息
		Etclickreq etclickreq=new Etclickreq();
		etclickreq.setFid(UUID.randomUUID().toString());
		etclickreq.setEtmsgtype(requestMap.get("MsgType"));
		etclickreq.setEttousername(requestMap.get("ToUserName"));
		etclickreq.setEtfromusername(requestMap.get("FromUserName"));
		etclickreq.setEtcreatetime(requestMap.get("CreateTime"));
		etclickreq.setEtevent(requestMap.get("Event"));
		etclickreq.setEteventkey(requestMap.get("EventKey"));
		logger.info("【"+DataUtil.getLogTime()+"】点击了菜单。");

		//返回消息
		if(requestMap.get("EventKey").equals("123")){
			Etrespmessage etrespmessage=new Etrespmessage();
			etrespmessage.setToUserName(requestMap.get("FromUserName"));
			etrespmessage.setFromUserName(requestMap.get("ToUserName"));
			etrespmessage.setCreateTime(System.currentTimeMillis());
			etrespmessage.setMsgType(StaticContent.MSGTYPE_TEXT);
			etrespmessage.setContent("谢谢您的点击1");
			StaticContent.RETURNXML=MessageUtil.textMessageToXml(etrespmessage);
			logger.info("【"+DataUtil.getLogTime()+"】用户点击按钮后回复的是"+etrespmessage.getContent());
		}else{
			Etrespmessage etrespmessage=new Etrespmessage();
			etrespmessage.setToUserName(requestMap.get("FromUserName"));
			etrespmessage.setFromUserName(requestMap.get("ToUserName"));
			etrespmessage.setCreateTime(System.currentTimeMillis());
			etrespmessage.setMsgType(StaticContent.MSGTYPE_TEXT);
			etrespmessage.setContent("谢谢您的点击2");
			StaticContent.RETURNXML=MessageUtil.textMessageToXml(etrespmessage);
			logger.info("【"+DataUtil.getLogTime()+"】用户点击按钮后回复的是"+etrespmessage.getContent());
		}
		PrintWriter out = response.getWriter();
		out.print(StaticContent.RETURNXML);
		out.close();

		//存库

	}

	/**
	 * 事件判断
	 * @param requestMap
	 * @param response
	 * @throws IOException
	 */
	public void accessEvent (Map<String, String> requestMap,HttpServletResponse response) throws IOException {
		if(StaticContent.MSGTYPE_CLICK.equals(requestMap.get("Event"))){
			//点击事件
			this.accessClick(requestMap,response);
		}else if(StaticContent.MSGTYPE_SUBSCRIBE.equals(requestMap.get("Event"))){
			//订阅
			this.accessSubscribe(requestMap,response);
		}
	}

	/**
	 *订阅
	 * @param requestMap
	 * @param response
	 * @throws IOException
	 */
	public void accessSubscribe (Map<String, String> requestMap,HttpServletResponse response) throws IOException {
        //接收到的消息
		Etsubscribe etsubscribe=new Etsubscribe();
		etsubscribe.setFid(UUID.randomUUID().toString());
		etsubscribe.setEtmsgtype(requestMap.get("MsgType"));
		etsubscribe.setEttousername(requestMap.get("ToUserName"));
		etsubscribe.setEtfromusername(requestMap.get("FromUserName"));
		etsubscribe.setEtcreatetime(requestMap.get("CreateTime"));
		etsubscribe.setEtevent(requestMap.get("Event"));
		etsubscribe.setEteventkey(requestMap.get("EventKey"));
		logger.info("【"+DataUtil.getLogTime()+"】用户订阅了我的测试号。");

		//返回消息
		Etrespmessage etrespmessage=new Etrespmessage();
		etrespmessage.setToUserName(requestMap.get("FromUserName"));
		etrespmessage.setFromUserName(requestMap.get("ToUserName"));
		etrespmessage.setCreateTime(System.currentTimeMillis());
		etrespmessage.setMsgType(StaticContent.MSGTYPE_TEXT);
		//有换行符+a标签
		etrespmessage.setContent("欢迎您的订阅：\n<a href=\"http://www.baidu.com\">百度（双引号）</a>\n<a href='http://www.toutiao.com'>头条（单引号）</a>");
		StaticContent.RETURNXML=MessageUtil.textMessageToXml(etrespmessage);
		logger.info("【"+DataUtil.getLogTime()+"】回复给用户的【文本消息】xml内容是【"+StaticContent.RETURNXML+"】");
		PrintWriter out = response.getWriter();
		out.print(StaticContent.RETURNXML);
		out.close();

		//存库
		//存返回消息的对象
		/*etrespmessage.setFid(UUID.randomUUID().toString());
		etrespmessage.setEttousername(etrespmessage.getFromUserName());
		etrespmessage.setEtfromusername(etrespmessage.getToUserName());
		etrespmessage.setEtcreatetime(String.valueOf(etrespmessage.getCreateTime()));
		etrespmessage.setEtmsgtype(etrespmessage.getMsgType());
		etrespmessage.setEtcontent(etrespmessage.getContent());
		etrespmessage.setEtreqid(etsubscribe.getFid());
		if(messageService.checkEttextmessagereqMsgId(ettextmessagereq.getEtmsgid())==null){
			try {
				messageService.saveEttextmessagereq(ettextmessagereq);
				logger.info("【"+DataUtil.getLogTime()+"】成功保存一条用户发送的文本信息。");
				try {
					messageService.saveEtrespmessage(etrespmessage);
					logger.info("【"+DataUtil.getLogTime()+"】成功保存一条回复的信息。");
				}catch (Exception e){
					logger.error("【"+DataUtil.getLogTime()+"】存储回复的信息时出错："+e.getMessage());
				}
			}catch (Exception e){
				logger.error("【"+DataUtil.getLogTime()+"】存储用户发送的文本信息时出错："+e.getMessage());
			}
		}*/
	}
	/**
	 * 接收链接信息并回复，存库
	 * @param requestMap
	 * @param response
	 * @throws IOException
	 */
	public void accessLinkMess (Map<String, String> requestMap,HttpServletResponse response) throws IOException {

		//接受到的消息
		Etlinkmessagereq etlinkmessagereq=new Etlinkmessagereq();
		etlinkmessagereq.setFid(UUID.randomUUID().toString());
		etlinkmessagereq.setEttousername(requestMap.get("ToUserName"));
		etlinkmessagereq.setEtfromusername(requestMap.get("FromUserName"));
		etlinkmessagereq.setEtcreatetime(requestMap.get("CreateTime"));
		etlinkmessagereq.setEtmsgtype(requestMap.get("MsgType"));
		etlinkmessagereq.setEtmsgid(Long.parseLong(requestMap.get("MsgId")));
		etlinkmessagereq.setEttitle(requestMap.get("Title"));
		etlinkmessagereq.setEtdescription(requestMap.get("Description"));
		etlinkmessagereq.setEturl(requestMap.get("Url"));
		logger.info("【"+DataUtil.getLogTime()+"】用户发送过来的【链接消息】消息标题【"+requestMap.get("Title")+"】，消息描述【"+requestMap.get("Description")+"】，消息链接【"+requestMap.get("Url")+"】");

		//返回消息
		Etrespmessage etrespmessage=new Etrespmessage();
		etrespmessage.setToUserName(requestMap.get("FromUserName"));
		etrespmessage.setFromUserName(requestMap.get("ToUserName"));
		etrespmessage.setCreateTime(System.currentTimeMillis());
		etrespmessage.setMsgType(StaticContent.MSGTYPE_TEXT);
		etrespmessage.setContent("收到您的消息链接Url："+requestMap.get("Url"));
		StaticContent.RETURNXML=MessageUtil.textMessageToXml(etrespmessage);
		logger.info("【"+DataUtil.getLogTime()+"】回复给用户发送过来的【链接消息】的文本内容是【"+"收到您的消息链接Url："+requestMap.get("Url"));
		PrintWriter out = response.getWriter();
		out.print(StaticContent.RETURNXML);
		out.close();

		//存库
		etrespmessage.setFid(UUID.randomUUID().toString());
		etrespmessage.setEttousername(requestMap.get("FromUserName"));
		etrespmessage.setEtfromusername(requestMap.get("ToUserName"));
		etrespmessage.setEtcreatetime(String.valueOf(etrespmessage.getCreateTime()));
		etrespmessage.setEtcontent("收到您的消息链接Url："+requestMap.get("Url"));
		etrespmessage.setEtreqid(etlinkmessagereq.getFid());
		if(messageService.checkEtlinkmessagereqMsgId(etlinkmessagereq.getEtmsgid())==null){
			try {
				messageService.saveEtlinkmessagereq(etlinkmessagereq);
				logger.info("【"+DataUtil.getLogTime()+"】成功保存一条用户发送的链接信息。");
				try {
					messageService.saveEtrespmessage(etrespmessage);
					logger.info("【"+DataUtil.getLogTime()+"】成功保存一条回复的信息。");
				}catch (Exception e){
					logger.error("【"+DataUtil.getLogTime()+"】存储回复的信息时出错："+e.getMessage());
				}
			}catch (Exception e){
				logger.error("【"+DataUtil.getLogTime()+"】存储用户发送的链接信息时出错："+e.getMessage());
			}
		}

	}

	/**
	 * 接收地理信息并回复，存库
	 * @param requestMap
	 * @param response
	 * @throws IOException
	 */
	public void accessLocationMess (Map<String, String> requestMap,HttpServletResponse response) throws IOException {

		//接受到的消息
		Etlocationmessagereq etlocationmessagereq=new Etlocationmessagereq();
		etlocationmessagereq.setFid(UUID.randomUUID().toString());
		etlocationmessagereq.setEttousername(requestMap.get("ToUserName"));
		etlocationmessagereq.setEtfromusername(requestMap.get("FromUserName"));
		etlocationmessagereq.setEtcreatetime(requestMap.get("CreateTime"));
		etlocationmessagereq.setEtmsgtype(requestMap.get("MsgType"));
		etlocationmessagereq.setEtmsgid(Long.parseLong(requestMap.get("MsgId")));
		etlocationmessagereq.setEtlocationx(requestMap.get("Location_X"));
		etlocationmessagereq.setEtlocationy(requestMap.get("Location_Y"));
		etlocationmessagereq.setEtscale(requestMap.get("Scale"));
		etlocationmessagereq.setEtlabel(requestMap.get("Label"));
		logger.info("【"+DataUtil.getLogTime()+"】用户发送过来的【地理位置消息】的地理位置维度【"+requestMap.get("Location_X")+"】，地理位置经度【"+requestMap.get("Location_Y")+"】，地图缩放大小【"+requestMap.get("Scale")+"】，地理位置信息【"+requestMap.get("Label")+"】");

		//返回消息
		Etrespmessage etrespmessage=new Etrespmessage();
		etrespmessage.setToUserName(requestMap.get("FromUserName"));
		etrespmessage.setFromUserName(requestMap.get("ToUserName"));
		etrespmessage.setCreateTime(System.currentTimeMillis());
		etrespmessage.setMsgType(StaticContent.MSGTYPE_TEXT);
		etrespmessage.setContent("收到您的地理位置："+requestMap.get("Label"));
		StaticContent.RETURNXML=MessageUtil.textMessageToXml(etrespmessage);
		logger.info("【"+DataUtil.getLogTime()+"】回复给用户发送过来的【地理位置消息】的xml内容是【"+StaticContent.RETURNXML+"】");
		PrintWriter out = response.getWriter();
		out.print(StaticContent.RETURNXML);
		out.close();

		//存库
		etrespmessage.setFid(UUID.randomUUID().toString());
		etrespmessage.setEttousername(requestMap.get("FromUserName"));
		etrespmessage.setEtfromusername(requestMap.get("ToUserName"));
		etrespmessage.setEtcreatetime(String.valueOf(etrespmessage.getCreateTime()));
		etrespmessage.setEtmsgtype(StaticContent.MSGTYPE_TEXT);
		etrespmessage.setEtcontent("收到您的地理位置："+requestMap.get("Label"));
		etrespmessage.setEtreqid(etlocationmessagereq.getFid());
		if(messageService.checkEtlocationmessagereqMsgId(etlocationmessagereq.getEtmsgid())==null){
			try {
				messageService.saveEtlocationmessagereq(etlocationmessagereq);
				logger.info("【"+DataUtil.getLogTime()+"】成功保存一条用户发送的地理位置信息。");
				try {
					messageService.saveEtrespmessage(etrespmessage);
					logger.info("【"+DataUtil.getLogTime()+"】成功保存一条回复的信息。");
				}catch (Exception e){
					logger.error("【"+DataUtil.getLogTime()+"】存储回复的信息时出错："+e.getMessage());
				}
			}catch (Exception e){
				logger.error("【"+DataUtil.getLogTime()+"】存储用户发送的地理位置信息时出错："+e.getMessage());
			}
		}
	}


	/**
	 * 接收视频、小视频消息并回复，存库
	 * @param requestMap
	 * @param response
	 * @throws IOException
	 */
	public void accessVideoMess (Map<String, String> requestMap,HttpServletResponse response) throws IOException {

		//接受到的消息
		Etvideomessagereq etvideomessagereq=new Etvideomessagereq();
		etvideomessagereq.setFid(UUID.randomUUID().toString());
		etvideomessagereq.setEttousername(requestMap.get("ToUserName"));
		etvideomessagereq.setEtfromusername(requestMap.get("FromUserName"));
		etvideomessagereq.setEtcreatetime(requestMap.get("CreateTime"));
		etvideomessagereq.setEtmsgtype(requestMap.get("MsgType"));
		etvideomessagereq.setEtmsgid(Long.parseLong(requestMap.get("MsgId")));
		etvideomessagereq.setEtmediaid(requestMap.get("MediaId"));
		etvideomessagereq.setEtthumbmediaid(requestMap.get("ThumbMediaId"));
		logger.info("【"+DataUtil.getLogTime()+"】用户发送过来的【"+((StaticContent.MSGTYPE_VIDEO.equals(requestMap.get("MsgType")))?"视频消息":"小视频消息")+"】视频消息媒体id【"+requestMap.get("MediaId")+"】，视频消息缩略图的媒体id【"+requestMap.get("ThumbMediaId")+"】");

		//返回消息
		Etrespmessage etrespmessage=new Etrespmessage();
		etrespmessage.setToUserName(requestMap.get("FromUserName"));
		etrespmessage.setFromUserName(requestMap.get("ToUserName"));
		etrespmessage.setCreateTime(System.currentTimeMillis());
		etrespmessage.setMsgType(StaticContent.MSGTYPE_VIDEO);
		etrespmessage.setMediaId(requestMap.get("MediaId"));
		etrespmessage.setTitle("视屏消息");
		etrespmessage.setDescription("就是测试用的描述，你喜欢吗！？");
		StaticContent.RETURNXML=MessageUtil.videoMessageToXml(etvideomessagereq);
		logger.info("【"+DataUtil.getLogTime()+"】回复给用户的【"+((StaticContent.MSGTYPE_VIDEO.equals(requestMap.get("MsgType")))?"视频消息":"小视频消息")+"】xml内容是【"+StaticContent.RETURNXML+"】");
		PrintWriter out = response.getWriter();
		out.print(StaticContent.RETURNXML);
		out.close();

		//存库
		etrespmessage.setFid(UUID.randomUUID().toString());
		etrespmessage.setEttousername(requestMap.get("FromUserName"));
		etrespmessage.setEtfromusername(requestMap.get("ToUserName"));
		etrespmessage.setEtcreatetime(String.valueOf(etrespmessage.getCreateTime()));
		etrespmessage.setEtmsgtype(StaticContent.MSGTYPE_VIDEO);
		etrespmessage.setEtmediaid(requestMap.get("MediaId"));
		etrespmessage.setEttitle("视屏消息");
		etrespmessage.setEtdescription("就是测试用的描述，你喜欢吗！？");
		etrespmessage.setEtreqid(etvideomessagereq.getFid());
		if(messageService.checkEtvideomessagereqMsgId(etvideomessagereq.getEtmsgid())==null){
			try {
				messageService.saveEtvideomessagereq(etvideomessagereq);
				logger.info("【"+DataUtil.getLogTime()+"】成功保存一条用户发送的【"+((StaticContent.MSGTYPE_VIDEO.equals(requestMap.get("MsgType")))?"视频消息":"小视频消息")+"】信息。");
				try {
					messageService.saveEtrespmessage(etrespmessage);
					logger.info("【"+DataUtil.getLogTime()+"】成功保存一条回复的信息。");
				}catch (Exception e){
					logger.error("【"+DataUtil.getLogTime()+"】存储回复的信息时出错："+e.getMessage());
				}
			}catch (Exception e){
				logger.error("【"+DataUtil.getLogTime()+"】存储用户发送的【"+((StaticContent.MSGTYPE_VIDEO.equals(requestMap.get("MsgType")))?"视频消息":"小视频消息")+"】信息时出错："+e.getMessage());
			}
		}
	}
	/**
	 * 接收语音消息并回复，存库
	 * @param requestMap
	 * @param response
	 * @throws IOException
	 */
	public void accessVoiceMess (Map<String, String> requestMap,HttpServletResponse response) throws IOException {

		//接受到的消息
		Etvoicemessagereq etvoicemessagereq=new Etvoicemessagereq();
		etvoicemessagereq.setFid(UUID.randomUUID().toString());
		etvoicemessagereq.setEttousername(requestMap.get("ToUserName"));
		etvoicemessagereq.setEtfromusername(requestMap.get("FromUserName"));
		etvoicemessagereq.setEtcreatetime(requestMap.get("CreateTime"));
		etvoicemessagereq.setEtmsgtype(requestMap.get("MsgType"));
		etvoicemessagereq.setEtmsgid(Long.parseLong(requestMap.get("MsgId")));
		etvoicemessagereq.setEtmediaid(requestMap.get("MediaId"));
		etvoicemessagereq.setEtformat(requestMap.get("Format"));
		etvoicemessagereq.setEtrecognition(requestMap.get("Recognition"));
		logger.info("【"+DataUtil.getLogTime()+"】用户发送过来的【语音消息】语音识别结果【"+requestMap.get("Recognition")+"】，媒体ID【"+requestMap.get("MediaId")+"】，语音格式【"+requestMap.get("Format")+"】");

		//返回消息
		Etrespmessage etrespmessage=new Etrespmessage();
		etrespmessage.setToUserName(requestMap.get("FromUserName"));
		etrespmessage.setFromUserName(requestMap.get("ToUserName"));
		etrespmessage.setCreateTime(System.currentTimeMillis());
		etrespmessage.setMsgType(StaticContent.MSGTYPE_VOICE);
		etrespmessage.setMediaId(requestMap.get("MediaId"));
		StaticContent.RETURNXML=MessageUtil.voiceMessageToXml(etrespmessage);
		logger.info("【"+DataUtil.getLogTime()+"】回复给用户的【语音消息】xml内容是【"+StaticContent.RETURNXML+"】");
		PrintWriter out = response.getWriter();
		out.print(StaticContent.RETURNXML);
		out.close();

		//存库
		etrespmessage.setFid(UUID.randomUUID().toString());
		etrespmessage.setEttousername(requestMap.get("FromUserName"));
		etrespmessage.setEtfromusername(requestMap.get("ToUserName"));
		etrespmessage.setEtcreatetime(String.valueOf(etrespmessage.getCreateTime()));
		etrespmessage.setEtmsgtype(StaticContent.MSGTYPE_VOICE);
		etrespmessage.setEtmediaid(requestMap.get("MediaId"));
		etrespmessage.setEtreqid(etvoicemessagereq.getFid());
		if(messageService.checkEtvoicemessagereqMsgId(etvoicemessagereq.getEtmsgid())==null){
			try {
				messageService.saveEtvoicemessagereq(etvoicemessagereq);
				logger.info("【"+DataUtil.getLogTime()+"】成功保存一条用户发送的语音信息。");
				try {
					messageService.saveEtrespmessage(etrespmessage);
					logger.info("【"+DataUtil.getLogTime()+"】成功保存一条回复的信息。");
				}catch (Exception e){
					logger.error("【"+DataUtil.getLogTime()+"】存储回复的信息时出错："+e.getMessage());
				}
			}catch (Exception e){
				logger.error("【"+DataUtil.getLogTime()+"】存储用户发送的语音信息时出错："+e.getMessage());
			}
		}
	}

	/**
	 * 接收图片消息并回复，存库
	 * @param requestMap
	 * @param response
	 * @throws IOException
	 */
	public void accessImageMess(Map<String, String> requestMap,HttpServletResponse response) throws IOException {

		//接收到的消息
		Etimagemessagereq etimagemessagereq=new Etimagemessagereq();
		etimagemessagereq.setFid(UUID.randomUUID().toString());
		etimagemessagereq.setEtmsgtype(requestMap.get("MsgType"));
		etimagemessagereq.setEttousername(requestMap.get("ToUserName"));
		etimagemessagereq.setEtfromusername(requestMap.get("FromUserName"));
		etimagemessagereq.setEtcreatetime(requestMap.get("CreateTime"));
		etimagemessagereq.setEtmsgid(Long.parseLong(requestMap.get("MsgId")));
		etimagemessagereq.setEtpicurl(requestMap.get("PicUrl"));
		etimagemessagereq.setEtmediaid(requestMap.get("MediaId"));
		logger.info("【"+DataUtil.getLogTime()+"】用户发送过来的【图片消息】的图片链接【"+requestMap.get("PicUrl")+"】，图片消息媒体id【"+requestMap.get("MediaId")+"】");

		//返回消息
		Etrespmessage etrespmessage=new Etrespmessage();
		etrespmessage.setToUserName(requestMap.get("FromUserName"));
		etrespmessage.setFromUserName(requestMap.get("ToUserName"));
		etrespmessage.setCreateTime(System.currentTimeMillis());
		etrespmessage.setMsgType(StaticContent.MSGTYPE_IMAGE);
		etrespmessage.setMediaId(requestMap.get("MediaId"));
		StaticContent.RETURNXML=MessageUtil.imageMessageToXml(etrespmessage);
		logger.info("【"+DataUtil.getLogTime()+"】回复给用户的【图片消息】xml内容是【"+StaticContent.RETURNXML+"】");
		PrintWriter out = response.getWriter();
		out.print(StaticContent.RETURNXML);
		out.close();

		//存库
		etrespmessage.setFid(UUID.randomUUID().toString());
		etrespmessage.setEttousername(requestMap.get("FromUserName"));
		etrespmessage.setEtfromusername(requestMap.get("ToUserName"));
		etrespmessage.setEtcreatetime(String.valueOf(etrespmessage.getCreateTime()));
		etrespmessage.setEtmsgtype(StaticContent.MSGTYPE_IMAGE);
		etrespmessage.setEtmediaid(requestMap.get("MediaId"));
		etrespmessage.setEtreqid(etimagemessagereq.getFid());
		if(messageService.checkEtimagemessagereqMsgId(etimagemessagereq.getEtmsgid())==null){
			try{
				messageService.saveEtimagemessagereq(etimagemessagereq);
				logger.info("【"+DataUtil.getLogTime()+"】成功保存一条用户发送的图片信息。");
				try {
					messageService.saveEtrespmessage(etrespmessage);
					logger.info("【"+DataUtil.getLogTime()+"】成功保存一条回复的信息。");
				}catch (Exception e){
					logger.error("【"+DataUtil.getLogTime()+"】存储回复的信息时出错："+e.getMessage());
				}
			}catch (Exception e){
				logger.error("【"+DataUtil.getLogTime()+"】存储用户发送的图片信息时出错："+e.getMessage());
			}
		}
	}

	/**
	 * 接收文本消息并回复图文，存库
	 * @param requestMap
	 * @param response
	 * @throws IOException
	 */
	public void textImageMess(Map<String, String> requestMap,HttpServletResponse response) throws IOException {
		//接收到的消息
		Ettextmessagereq ettextmessagereq=new Ettextmessagereq();
		ettextmessagereq.setFid(UUID.randomUUID().toString());
		ettextmessagereq.setEtmsgtype(requestMap.get("MsgType"));
		ettextmessagereq.setEttousername(requestMap.get("ToUserName"));
		ettextmessagereq.setEtfromusername(requestMap.get("FromUserName"));
		ettextmessagereq.setEtcreatetime(requestMap.get("CreateTime"));
		ettextmessagereq.setEtmsgid(Long.parseLong(requestMap.get("MsgId")));
		ettextmessagereq.setEtcontent(requestMap.get("Content"));
		logger.info("【"+DataUtil.getLogTime()+"】用户发送过来的【文本消息】内容是【"+requestMap.get("Content")+"】");

		//返回消息
		List<Article> Articles=new ArrayList<>();
		Article article1=new Article();
		article1.setTitle("这是第一个图文标题");
		article1.setDescription("这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述");
		article1.setPicUrl("http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg");
		article1.setUrl("https://baijiahao.baidu.com/s?id=1617911349814046774&wfr=spider&for=pc");
		Articles.add(article1);

		Article article2=new Article();
		article2.setTitle("这是第二个图文标题");
		article2.setDescription("这是第一个图文标题的描述");
		article2.setPicUrl("http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg");
		article2.setUrl("https://baijiahao.baidu.com/s?id=1617911349814046774&wfr=spider&for=pc");
		Articles.add(article2);

		Article article3=new Article();
		article3.setTitle("这是第三个图文标题");
		article3.setDescription("这是第一个图文标题的描述");
		article3.setPicUrl("http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg");
		article3.setUrl("https://baijiahao.baidu.com/s?id=1617911349814046774&wfr=spider&for=pc");
		Articles.add(article3);

		Article article4=new Article();
		article4.setTitle("这是第四个图文标题");
		article4.setDescription("这是第一个图文标题的描述");
		article4.setPicUrl("http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg");
		article4.setUrl("https://baijiahao.baidu.com/s?id=1617911349814046774&wfr=spider&for=pc");
		Articles.add(article4);

		Etrespmessage etrespmessage=new Etrespmessage();
		etrespmessage.setToUserName(requestMap.get("FromUserName"));
		etrespmessage.setFromUserName(requestMap.get("ToUserName"));
		etrespmessage.setCreateTime(System.currentTimeMillis());
		etrespmessage.setMsgType("news");
		etrespmessage.setArticleCount(4);
		etrespmessage.setArticles(Articles);
		StaticContent.RETURNXML=MessageUtil.newsMessageToXml(etrespmessage);
		logger.info("【"+DataUtil.getLogTime()+"】回复给用户的【图文消息】xml内容是【"+StaticContent.RETURNXML+"】");
		PrintWriter out = response.getWriter();
		out.print(StaticContent.RETURNXML);
		out.close();

		//存库
		etrespmessage.setFid(UUID.randomUUID().toString());
		etrespmessage.setEttousername(requestMap.get("FromUserName"));
		etrespmessage.setEtfromusername(requestMap.get("ToUserName"));
		etrespmessage.setEtcreatetime(String.valueOf(etrespmessage.getCreateTime()));
		etrespmessage.setEtmsgtype("news");
		etrespmessage.setEtarticlecount(4);
		etrespmessage.setEtreqid(ettextmessagereq.getFid());

		List<Article> Article=new ArrayList<>();
		Article article1_1=new Article();
		article1_1.setFid(UUID.randomUUID().toString());
		article1_1.setEttitle("这是第一个图文标题");
		article1_1.setEtdescription("这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述这是第一个图文标题的描述");
		article1_1.setEtpicurl("http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg");
		article1_1.setEturl("https://baijiahao.baidu.com/s?id=1617911349814046774&wfr=spider&for=pc");
		article1_1.setEtrespmessageid(ettextmessagereq.getFid());
		Article.add(article1_1);

		Article article2_2=new Article();
		article2_2.setFid(UUID.randomUUID().toString());
		article2_2.setEttitle("这是第二个图文标题");
		article2_2.setEtdescription("这是第一个图文标题的描述");
		article2_2.setEtpicurl("http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg");
		article2_2.setEturl("https://baijiahao.baidu.com/s?id=1617911349814046774&wfr=spider&for=pc");
		article2_2.setEtrespmessageid(ettextmessagereq.getFid());
		Article.add(article2_2);

		Article article3_3=new Article();
		article3_3.setFid(UUID.randomUUID().toString());
		article3_3.setEttitle("这是第三个图文标题");
		article3_3.setEtdescription("这是第一个图文标题的描述");
		article3_3.setEtpicurl("http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg");
		article3_3.setEturl("https://baijiahao.baidu.com/s?id=1617911349814046774&wfr=spider&for=pc");
		article3_3.setEtrespmessageid(ettextmessagereq.getFid());
		Article.add(article3_3);

		Article article4_4=new Article();
		article4_4.setFid(UUID.randomUUID().toString());
		article4_4.setEttitle("这是第四个图文标题");
		article4_4.setEtdescription("这是第一个图文标题的描述");
		article4_4.setEtpicurl("http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg");
		article4_4.setEturl("https://baijiahao.baidu.com/s?id=1617911349814046774&wfr=spider&for=pc");
		article4_4.setEtrespmessageid(ettextmessagereq.getFid());
		Article.add(article4_4);

		if(messageService.checkEttextmessagereqMsgId(ettextmessagereq.getEtmsgid())==null){
			try {
				messageService.saveEttextmessagereq(ettextmessagereq);
				logger.info("【"+DataUtil.getLogTime()+"】成功保存一条用户发送的文本信息。");
				try {
					messageService.saveEtrespmessage(etrespmessage);
					messageService.saveArticles(Article);
					logger.info("【"+DataUtil.getLogTime()+"】成功保存一条回复的信息。");
				}catch (Exception e){
					logger.error("【"+DataUtil.getLogTime()+"】存储回复的信息时出错："+e.getMessage());
				}
			}catch (Exception e){
				logger.error("【"+DataUtil.getLogTime()+"】存储用户发送的文本信息时出错："+e.getMessage());
			}
		}
	}

	/**
	 * 接收文本消息并回复，存库
	 * @param requestMap
	 * @param response
	 * @throws IOException
	 */
	public void accessTextMess(Map<String, String> requestMap,HttpServletResponse response) throws IOException {
		//接收到的消息
		Ettextmessagereq ettextmessagereq=new Ettextmessagereq();
		ettextmessagereq.setFid(UUID.randomUUID().toString());
		ettextmessagereq.setEtmsgtype(requestMap.get("MsgType"));
		ettextmessagereq.setEttousername(requestMap.get("ToUserName"));
		ettextmessagereq.setEtfromusername(requestMap.get("FromUserName"));
		ettextmessagereq.setEtcreatetime(requestMap.get("CreateTime"));
		ettextmessagereq.setEtmsgid(Long.parseLong(requestMap.get("MsgId")));
		ettextmessagereq.setEtcontent(requestMap.get("Content"));
		logger.info("【"+DataUtil.getLogTime()+"】用户发送过来的【文本消息】内容是【"+requestMap.get("Content")+"】");

		//返回消息
		Etrespmessage etrespmessage=new Etrespmessage();
		etrespmessage.setToUserName(requestMap.get("FromUserName"));
		etrespmessage.setFromUserName(requestMap.get("ToUserName"));
		etrespmessage.setCreateTime(System.currentTimeMillis());
		etrespmessage.setMsgType(StaticContent.MSGTYPE_TEXT);
		//没有换行符
		//etrespmessage.setContent("您发送的是："+requestMap.get("Content"));
		//有换行符
		//etrespmessage.setContent("您发送的是：\n"+requestMap.get("Content"));
		//有换行符+a标签
		etrespmessage.setContent("欢迎您的订阅：\n<a href=\"http://www.baidu.com\">百度（双引号）</a>\n<a href='http://www.toutiao.com'>头条（单引号）</a>");
		StaticContent.RETURNXML=MessageUtil.textMessageToXml(etrespmessage);
		logger.info("【"+DataUtil.getLogTime()+"】回复给用户的【文本消息】xml内容是【"+StaticContent.RETURNXML+"】");
		PrintWriter out = response.getWriter();
		out.print(StaticContent.RETURNXML);
		out.close();

		//存库
		//存返回消息的对象
		etrespmessage.setFid(UUID.randomUUID().toString());
		etrespmessage.setEttousername(etrespmessage.getFromUserName());
		etrespmessage.setEtfromusername(etrespmessage.getToUserName());
		etrespmessage.setEtcreatetime(String.valueOf(etrespmessage.getCreateTime()));
		etrespmessage.setEtmsgtype(etrespmessage.getMsgType());
		etrespmessage.setEtcontent(etrespmessage.getContent());
		etrespmessage.setEtreqid(ettextmessagereq.getFid());
		if(messageService.checkEttextmessagereqMsgId(ettextmessagereq.getEtmsgid())==null){
			try {
				messageService.saveEttextmessagereq(ettextmessagereq);
				logger.info("【"+DataUtil.getLogTime()+"】成功保存一条用户发送的文本信息。");
				try {
					messageService.saveEtrespmessage(etrespmessage);
					logger.info("【"+DataUtil.getLogTime()+"】成功保存一条回复的信息。");
				}catch (Exception e){
					logger.error("【"+DataUtil.getLogTime()+"】存储回复的信息时出错："+e.getMessage());
				}
			}catch (Exception e){
				logger.error("【"+DataUtil.getLogTime()+"】存储用户发送的文本信息时出错："+e.getMessage());
			}
		}
	}


	/**
	 * 验证token
	 * @param request
	 * @param response
	 * @return
	 * @throws AesException
	 */
	public String checkToken(HttpServletRequest request, HttpServletResponse response) throws AesException {
		Map<String, String[]> paremeterMap=request.getParameterMap();
		//校验地址
		String signature=paremeterMap.get("signature")[0];
		String timestamp=paremeterMap.get("timestamp")[0];
		String nonce=paremeterMap.get("nonce")[0];
		String echostr= paremeterMap.get("echostr")[0];
		String newSignature = SHA1.getSHA2(sysConfig.getWxToken(),timestamp, nonce);
		if (!signature.equals(newSignature)) {
			logger.info("【"+DataUtil.getLogTime()+"】signature验证失败！url校验不成功："+AesException.ValidateSignatureError);
			throw new AesException(AesException.ValidateSignatureError);
		}
		try {
			response.getWriter().write(echostr);
			logger.info("【"+DataUtil.getLogTime()+"】成功返回 echostr：" + echostr);
		} catch (IOException e) {
			logger.info("【"+DataUtil.getLogTime()+"】返回失败："+e.getMessage());
			return null;
		}
		return echostr;
	}
}

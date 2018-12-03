package com.myf.wchat.domain.messageDomain.request;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author MeiYF
 * @time 2018/11/29 9:43
 **/
@Entity
public class Etvideomessagereq {
	private String fid;
	private String etmsgtype;
	private String ettousername;
	private String etfromusername;
	private String etcreatetime;
	private Long etmsgid;
	private String etmediaid;
	private String etthumbmediaid;

	//冗余字段
	private String MsgType;
	private String ToUserName;
	private String FromUserName;
	private Long CreateTime;
	private Long MsgId;
	private String MediaId;
	private String ThumbMediaId;

	@Transient
	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	@Transient
	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	@Transient
	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	@Transient
	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	@Transient
	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
	@Transient
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	@Transient
	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	@Id
	@Column(name = "fid", nullable = false, length = 50)
	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	@Basic
	@Column(name = "etmsgtype", nullable = true, length = 50)
	public String getEtmsgtype() {
		return etmsgtype;
	}

	public void setEtmsgtype(String etmsgtype) {
		this.etmsgtype = etmsgtype;
	}

	@Basic
	@Column(name = "ettousername", nullable = true, length = 50)
	public String getEttousername() {
		return ettousername;
	}

	public void setEttousername(String ettousername) {
		this.ettousername = ettousername;
	}

	@Basic
	@Column(name = "etfromusername", nullable = true, length = 50)
	public String getEtfromusername() {
		return etfromusername;
	}

	public void setEtfromusername(String etfromusername) {
		this.etfromusername = etfromusername;
	}

	@Basic
	@Column(name = "etcreatetime", nullable = true, length = 100)
	public String getEtcreatetime() {
		return etcreatetime;
	}

	public void setEtcreatetime(String etcreatetime) {
		this.etcreatetime = etcreatetime;
	}

	@Basic
	@Column(name = "etmsgid", nullable = true)
	public Long getEtmsgid() {
		return etmsgid;
	}

	public void setEtmsgid(Long etmsgid) {
		this.etmsgid = etmsgid;
	}

	@Basic
	@Column(name = "etmediaid", nullable = true, length = 100)
	public String getEtmediaid() {
		return etmediaid;
	}

	public void setEtmediaid(String etmediaid) {
		this.etmediaid = etmediaid;
	}

	@Basic
	@Column(name = "etthumbmediaid", nullable = true, length = 100)
	public String getEtthumbmediaid() {
		return etthumbmediaid;
	}

	public void setEtthumbmediaid(String etthumbmediaid) {
		this.etthumbmediaid = etthumbmediaid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Etvideomessagereq that = (Etvideomessagereq) o;
		return Objects.equals(fid, that.fid) &&
				Objects.equals(etmsgtype, that.etmsgtype) &&
				Objects.equals(ettousername, that.ettousername) &&
				Objects.equals(etfromusername, that.etfromusername) &&
				Objects.equals(etcreatetime, that.etcreatetime) &&
				Objects.equals(etmsgid, that.etmsgid) &&
				Objects.equals(etmediaid, that.etmediaid) &&
				Objects.equals(etthumbmediaid, that.etthumbmediaid);
	}

	@Override
	public int hashCode() {

		return Objects.hash(fid, etmsgtype, ettousername, etfromusername, etcreatetime, etmsgid, etmediaid, etthumbmediaid);
	}
}

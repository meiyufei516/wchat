package com.myf.wchat.domain.messageDomain.request;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author MeiYF
 * @time 2018/11/29 9:43
 **/
@Entity
public class Etlocationmessagereq {
	private String fid;
	private String etmsgtype;
	private String ettousername;
	private String etfromusername;
	private String etcreatetime;
	private Long etmsgid;
	private String etlocationx;
	private String etlocationy;
	private String etscale;
	private String etlabel;

	//冗余字段
	private String MsgType;
	private String ToUserName;
	private String FromUserName;
	private Long CreateTime;
	private Long MsgId;
	private String Location_X;
	private String Location_Y;
	private String Scale;
	private String Label;


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
	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	@Transient
	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	@Transient
	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}
	@Transient
	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
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
	@Column(name = "etlocationx", nullable = true, length = 50)
	public String getEtlocationx() {
		return etlocationx;
	}

	public void setEtlocationx(String etlocationx) {
		this.etlocationx = etlocationx;
	}

	@Basic
	@Column(name = "etlocationy", nullable = true, length = 50)
	public String getEtlocationy() {
		return etlocationy;
	}

	public void setEtlocationy(String etlocationy) {
		this.etlocationy = etlocationy;
	}

	@Basic
	@Column(name = "etscale", nullable = true, length = 50)
	public String getEtscale() {
		return etscale;
	}

	public void setEtscale(String etscale) {
		this.etscale = etscale;
	}

	@Basic
	@Column(name = "etlabel", nullable = true, length = 250)
	public String getEtlabel() {
		return etlabel;
	}

	public void setEtlabel(String etlabel) {
		this.etlabel = etlabel;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Etlocationmessagereq that = (Etlocationmessagereq) o;
		return Objects.equals(fid, that.fid) &&
				Objects.equals(etmsgtype, that.etmsgtype) &&
				Objects.equals(ettousername, that.ettousername) &&
				Objects.equals(etfromusername, that.etfromusername) &&
				Objects.equals(etcreatetime, that.etcreatetime) &&
				Objects.equals(etmsgid, that.etmsgid) &&
				Objects.equals(etlocationx, that.etlocationx) &&
				Objects.equals(etlocationy, that.etlocationy) &&
				Objects.equals(etscale, that.etscale) &&
				Objects.equals(etlabel, that.etlabel);
	}

	@Override
	public int hashCode() {

		return Objects.hash(fid, etmsgtype, ettousername, etfromusername, etcreatetime, etmsgid, etlocationx, etlocationy, etscale, etlabel);
	}
}

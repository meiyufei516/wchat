package com.myf.wchat.domain.messageDomain.request;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author MeiYF
 * @time 2018/11/29 9:43
 **/
@Entity
public class Ettextmessagereq {
	private String fid;
	private String etmsgtype;
	private String ettousername;
	private String etfromusername;
	private String etcreatetime;
	private Long etmsgid;
	private String etcontent;

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
	@Column(name = "etcontent", nullable = true, length = 250)
	public String getEtcontent() {
		return etcontent;
	}

	public void setEtcontent(String etcontent) {
		this.etcontent = etcontent;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ettextmessagereq that = (Ettextmessagereq) o;
		return Objects.equals(fid, that.fid) &&
				Objects.equals(etmsgtype, that.etmsgtype) &&
				Objects.equals(ettousername, that.ettousername) &&
				Objects.equals(etfromusername, that.etfromusername) &&
				Objects.equals(etcreatetime, that.etcreatetime) &&
				Objects.equals(etmsgid, that.etmsgid) &&
				Objects.equals(etcontent, that.etcontent);
	}

	@Override
	public int hashCode() {

		return Objects.hash(fid, etmsgtype, ettousername, etfromusername, etcreatetime, etmsgid, etcontent);
	}
}

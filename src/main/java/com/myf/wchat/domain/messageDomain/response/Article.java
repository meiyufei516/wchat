package com.myf.wchat.domain.messageDomain.response;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author MeiYF
 * @time 2018/11/29 9:43
 **/
@Entity
@Table(name="etarticle")
public class Article {
	private String fid;
	private String ettitle;
	private String etdescription;
	private String etpicurl;
	private String eturl;
	private String etrespmessageid;


	//冗余字段
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;

	@Transient
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}
	@Transient
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	@Transient
	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	@Transient
	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
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
	@Column(name = "ettitle", nullable = true, length = 250)
	public String getEttitle() {
		return ettitle;
	}

	public void setEttitle(String ettitle) {
		this.ettitle = ettitle;
	}

	@Basic
	@Column(name = "etdescription", nullable = true, length = 250)
	public String getEtdescription() {
		return etdescription;
	}

	public void setEtdescription(String etdescription) {
		this.etdescription = etdescription;
	}

	@Basic
	@Column(name = "etpicurl", nullable = true, length = 250)
	public String getEtpicurl() {
		return etpicurl;
	}

	public void setEtpicurl(String etpicurl) {
		this.etpicurl = etpicurl;
	}

	@Basic
	@Column(name = "eturl", nullable = true, length = 250)
	public String getEturl() {
		return eturl;
	}

	public void setEturl(String eturl) {
		this.eturl = eturl;
	}

	@Basic
	@Column(name = "etrespmessageid", nullable = true, length = 50)
	public String getEtrespmessageid() {
		return etrespmessageid;
	}

	public void setEtrespmessageid(String etrespmessageid) {
		this.etrespmessageid = etrespmessageid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Article etarticle = (Article) o;
		return Objects.equals(fid, etarticle.fid) &&
				Objects.equals(ettitle, etarticle.ettitle) &&
				Objects.equals(etdescription, etarticle.etdescription) &&
				Objects.equals(etpicurl, etarticle.etpicurl) &&
				Objects.equals(eturl, etarticle.eturl) &&
				Objects.equals(etrespmessageid, etarticle.etrespmessageid);
	}

	@Override
	public int hashCode() {

		return Objects.hash(fid, ettitle, etdescription, etpicurl, eturl, etrespmessageid);
	}
}

package com.myf.wchat.domain.messageDomain.response;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author MeiYF
 * @time 2018/11/29 9:43
 **/
@Entity
public class Etrespmessage {
	private String fid;
	private String etmsgtype;
	private String ettousername;
	private String etfromusername;
	private String etcreatetime;
	private String etcontent;
	private String etmediaid;
	private String ettitle;
	private String etdescription;
	private String etmusicurl;
	private String ethqmusicurl;
	private String etthumbmediaid;
	private Integer etarticlecount;
	private String etreqid;

	//冗余字段
	private String MsgType;
	private String ToUserName;
	private String FromUserName;
	private Long CreateTime;
	private String Content;
	private Long MsgId;
	private String Title;
	private String Description;
	private String MusicURL;
	private String HQMusicUrl;
	private String ThumbMediaId;
	private Integer ArticleCount;
	private String MediaId;
	private List<Article> Articles;

	@Transient
	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

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
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	@Transient
	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
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
	public String getMusicURL() {
		return MusicURL;
	}

	public void setMusicURL(String musicURL) {
		MusicURL = musicURL;
	}
	@Transient
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String HQMusicUrl) {
		this.HQMusicUrl = HQMusicUrl;
	}
	@Transient
	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	@Transient
	public Integer getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(Integer articleCount) {
		ArticleCount = articleCount;
	}
	@Transient
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
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
	@Column(name = "etcontent", nullable = true, length = 250)
	public String getEtcontent() {
		return etcontent;
	}

	public void setEtcontent(String etcontent) {
		this.etcontent = etcontent;
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
	@Column(name = "etmusicurl", nullable = true, length = 250)
	public String getEtmusicurl() {
		return etmusicurl;
	}

	public void setEtmusicurl(String etmusicurl) {
		this.etmusicurl = etmusicurl;
	}

	@Basic
	@Column(name = "ethqmusicurl", nullable = true, length = 250)
	public String getEthqmusicurl() {
		return ethqmusicurl;
	}

	public void setEthqmusicurl(String ethqmusicurl) {
		this.ethqmusicurl = ethqmusicurl;
	}

	@Basic
	@Column(name = "etthumbmediaid", nullable = true, length = 50)
	public String getEtthumbmediaid() {
		return etthumbmediaid;
	}

	public void setEtthumbmediaid(String etthumbmediaid) {
		this.etthumbmediaid = etthumbmediaid;
	}

	@Basic
	@Column(name = "etarticlecount", nullable = true)
	public Integer getEtarticlecount() {
		return etarticlecount;
	}

	public void setEtarticlecount(Integer etarticlecount) {
		this.etarticlecount = etarticlecount;
	}

	@Basic
	@Column(name = "etreqid", nullable = true, length = 50)
	public String getEtreqid() {
		return etreqid;
	}

	public void setEtreqid(String etreqid) {
		this.etreqid = etreqid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Etrespmessage that = (Etrespmessage) o;
		return Objects.equals(fid, that.fid) &&
				Objects.equals(etmsgtype, that.etmsgtype) &&
				Objects.equals(ettousername, that.ettousername) &&
				Objects.equals(etfromusername, that.etfromusername) &&
				Objects.equals(etcreatetime, that.etcreatetime) &&
				Objects.equals(etcontent, that.etcontent) &&
				Objects.equals(etmediaid, that.etmediaid) &&
				Objects.equals(ettitle, that.ettitle) &&
				Objects.equals(etdescription, that.etdescription) &&
				Objects.equals(etmusicurl, that.etmusicurl) &&
				Objects.equals(ethqmusicurl, that.ethqmusicurl) &&
				Objects.equals(etthumbmediaid, that.etthumbmediaid) &&
				Objects.equals(etarticlecount, that.etarticlecount) &&
				Objects.equals(etreqid, that.etreqid);
	}

	@Override
	public int hashCode() {

		return Objects.hash(fid, etmsgtype, ettousername, etfromusername, etcreatetime, etcontent, etmediaid, ettitle, etdescription, etmusicurl, ethqmusicurl, etthumbmediaid, etarticlecount, etreqid);
	}
}

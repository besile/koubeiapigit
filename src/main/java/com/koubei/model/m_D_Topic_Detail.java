package com.koubei.model;

import com.koubei.annotation.Column;
import com.koubei.annotation.RelationKey;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class m_D_Topic_Detail implements Serializable {
	@Column(name = "Id")
	private long detailId;
	@Column(name = "Topicid")
	@RelationKey
	private int detailTopicid;
	@Column(name = "Title")
	private String detailTitle;
	@Column(name = "Pinyin")
	private String detailPinyin;

	@Column(name = "Content")
	private String detailContent;
	@Column(name = "Rating")
	private int detailRating;
	/**
	 * 分项ID
	 */
	@Column(name = "CategoryId")
	private int categoryId;
	@XmlElement(name = "detailId")
	public long getDetailId() {
		return detailId;
	}

	public void setDetailId(long detailId) {
		this.detailId = detailId;
	}
	@XmlElement(name = "detailTopicid")
	public int getDetailTopicid() {
		return detailTopicid;
	}

	public void setDetailTopicid(int detailTopicid) {
		this.detailTopicid = detailTopicid;
	}
	@XmlElement(name = "detailTitle")
	public String getDetailTitle() {
		return detailTitle;
	}

	public void setDetailTitle(String detailTitle) {
		this.detailTitle = detailTitle;
	}
	@XmlElement(name = "detailPinyin")
	public String getDetailPinyin() {
		return detailPinyin;
	}

	public void setDetailPinyin(String detailPinyin) {
		this.detailPinyin = detailPinyin;
	}
	@XmlElement(name = "detailContent")
	public String getDetailContent() {
		return detailContent;
	}

	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}
	@XmlElement(name = "detailRating")
	public int getDetailRating() {
		return detailRating;
	}

	public void setDetailRating(int detailRating) {
		this.detailRating = detailRating;
	}
	@XmlElement(name = "categoryId")
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}

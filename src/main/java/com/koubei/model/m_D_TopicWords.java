package com.koubei.model;

import com.koubei.annotation.Column;
import com.koubei.annotation.RelationKey;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class m_D_TopicWords implements Serializable {
	@Column(name = "Id")
	private int id;
	// / <summary>
	// / 口碑ID
	// / </summary>
	@Column(name = "topicId")
	@RelationKey
	private int topicId;
	// / <summary>
	// / 分词ID
	// / </summary>
	@Column(name = "wordId")
	private int wordId;
	// / <summary>
	// / 状态
	// / </summary>
	@Column(name = "status")
	private int status;
	@XmlElement(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@XmlElement(name = "topicId")
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	@XmlElement(name = "wordId")
	public int getWordId() {
		return wordId;
	}

	public void setWordId(int wordId) {
		this.wordId = wordId;
	}
	@XmlElement(name = "status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

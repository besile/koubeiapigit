package com.koubei.model;

import com.koubei.annotation.Column;
import com.koubei.annotation.RelationKey;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class m_D_Topic_Image implements Serializable{
	@Column(name = "Id")
	private long imageId;
	@Column(name = "Topicid")
	@RelationKey
	private int imageTopicid;
	@Column(name = "ImagePath")
	private String imagePath;
	@Column(name = "ImageName")
	private String imageName;
	@XmlElement(name = "imageId")
	public long getImageId() {
		return imageId;
	}
	public void setImageId(long imageId) {
		this.imageId = imageId;
	}
	public int getImageTopicid() {
		return imageTopicid;
	}
	public void setImageTopicid(int imageTopicid) {
		this.imageTopicid = imageTopicid;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}

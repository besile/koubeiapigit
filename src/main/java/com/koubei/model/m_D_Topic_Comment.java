package com.koubei.model;

import com.koubei.annotation.Column;
import com.koubei.annotation.RelationKey;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class m_D_Topic_Comment implements Serializable {
    @Column(name = "Id")
    private long commentId;
    @Column(name = "Topicid")
    @RelationKey
    private int commentTopicid;
    @Column(name = "Content")
    private String commentContent;
    @Column(name = "GoodContent")
    private String goodContent;
    @Column(name = "BadContent")
    private String badContent;

    @XmlElement(name = "commentId")
    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    @XmlElement(name = "commentTopicid")
    public int getCommentTopicid() {
        return commentTopicid;
    }

    public void setCommentTopicid(int commentTopicid) {
        this.commentTopicid = commentTopicid;
    }

    @XmlElement(name = "commentContent")
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @XmlElement(name = "goodContent")
    public String getGoodContent() {
        return goodContent;
    }

    public void setGoodContent(String goodContent) {
        this.goodContent = goodContent;
    }

    @XmlElement(name = "badContent")
    public String getBadContent() {
        return badContent;
    }

    public void setBadContent(String badContent) {
        this.badContent = badContent;
    }

}

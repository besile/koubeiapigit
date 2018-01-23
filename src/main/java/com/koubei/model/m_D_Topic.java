package com.koubei.model;

import com.koubei.annotation.Column;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "topic")
public class m_D_Topic implements Serializable{
	private static final long serialVersionUID = -3371451210123762490L;
	/// <summary>
    /// 购车价格
    /// </summary>
   @Column(name="purchase_price")
    private BigDecimal purchasePrice ;
	/**
	 * 口碑ID
	 */
    @Column(name="id",unique=true)
    private Integer topicId;

    @Column(name="guid")
    private String uinonkey;
    @Column(name="username")
    private String TopicUserName ;
    @Column(name="title")
    private String TopicTitle ;

    @Column(name="model_id")
    private Integer topicModelId ;
    @Column(name="trim_id")
    private Integer topicTrimId ;
    @Column(name="trimName")
    private String TopicTrimName ;
    @Column(name="postcount")
    private Integer topicPostsCount ;
    @Column(name="created_at")
    private Date topicCreatedAt ;

    
    @Column(name="user_id")
    private Integer topicUserId;
    @Column(name="Rating")
    private Integer topicRating ;
    @Column(name="positive_votes")
    private Integer topicVotedCount ;
    @Column(name="quality")
    private Integer topicQuality ;

    @Column(name="trimYear")
    private Integer topicTrimYear ;

    /// <summary>
    /// 油耗值
    /// </summary>
    @Column(name="fuel_value")
    private double topicFuel;
    /// <summary>
    /// 里程
    /// </summary>
    @Column(name="mileage")
    private Integer mileage ;



   @Column(name="is_active")
    private boolean topicStatus ;
    /// <summary>
    /// 口碑类型
    /// </summary>
   @Column(name="category")
    private Short categoryId ;

    /// <summary>
    /// 口碑类型
    /// </summary>
   @Column(name="post_type")
    private Integer postType ;
    /// <summary>
    /// 购买时间
    /// </summary>
   @Column(name="purchase_date")
    private Date purchaseDate ;

    /// <summary>
    /// 口碑区分（0：是完整口碑 1：是微口碑）
    /// </summary>
   @Column(name="datatype")
    private Integer dataType ;

    /// <summary>
    /// 养成费用
    /// </summary>
   @Column(name="fee")
    private Integer fee ;
    /// <summary>
    /// 供应商省市Id
    /// </summary>
   @Column(name="ParentCityId")
    private Integer dealerParentCityId ;
    /// <summary>
    /// 供应商城市Id
    /// </summary>
   @Column(name="image_id")
    private Integer dealerCityId ;
    /// <summary>
    /// 供应商Id
    /// </summary>
   @Column(name="dealerId")
    private Integer dealerId ;
    /// <summary>
    /// 总评分
    /// </summary>
   @Column(name="rating_value")
    private Integer ratingValue ;
    


    private List<m_D_Topic_Detail> detailList ;

    private m_D_Topic_Comment comment ;

    private List<m_D_Topic_Image> imageList ;

    /// <summary>
    /// 分词列表
    /// </summary>
    private List<m_D_TopicWords> topicWordsList;

	@XmlElement(name = "topicId")
	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	@XmlElement(name = "guid")
	public String getUinonkey() {
		return uinonkey;
	}

	public void setUinonkey(String uinonkey) {
		this.uinonkey = uinonkey;
	}
	@XmlElement(name = "topicUserName")
	public String getTopicUserName() {
		return TopicUserName;
	}

	public void setTopicUserName(String topicUserName) {
		TopicUserName = topicUserName;
	}
	@XmlElement(name = "topicTitle")
	public String getTopicTitle() {
		return TopicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		TopicTitle = topicTitle;
	}

	public Integer getTopicModelId() {
		return topicModelId;
	}

	public void setTopicModelId(Integer topicModelId) {
		this.topicModelId = topicModelId;
	}
	@XmlElement(name = "topicTrimId")
	public Integer getTopicTrimId() {
		return topicTrimId;
	}

	public void setTopicTrimId(Integer topicTrimId) {
		this.topicTrimId = topicTrimId;
	}
	@XmlElement(name = "topicTrimName")
	public String getTopicTrimName() {
		return TopicTrimName;
	}

	public void setTopicTrimName(String topicTrimName) {
		TopicTrimName = topicTrimName;
	}
	@XmlElement(name = "topicPostsCount")
	public Integer getTopicPostsCount() {
		return topicPostsCount;
	}

	public void setTopicPostsCount(Integer topicPostsCount) {
		this.topicPostsCount = topicPostsCount;
	}
	@XmlElement(name = "topicCreatedAt")
	public Date getTopicCreatedAt() {
		return topicCreatedAt;
	}

	public void setTopicCreatedAt(Date topicCreatedAt) {
		this.topicCreatedAt = topicCreatedAt;
	}


	@XmlElement(name = "topicUserId")
	public Integer getTopicUserId() {
		return topicUserId;
	}

	public void setTopicUserId(Integer topicUserId) {
		this.topicUserId = topicUserId;
	}

	public Integer getTopicRating() {
		return topicRating;
	}

	public void setTopicRating(Integer topicRating) {
		this.topicRating = topicRating;
	}

	public Integer getTopicVotedCount() {
		return topicVotedCount;
	}

	public void setTopicVotedCount(Integer topicVotedCount) {
		this.topicVotedCount = topicVotedCount;
	}

	public Integer getTopicQuality() {
		return topicQuality;
	}

	public void setTopicQuality(Integer topicQuality) {
		this.topicQuality = topicQuality;
	}

	public Integer getTopicTrimYear() {
		return topicTrimYear;
	}

	public void setTopicTrimYear(Integer topicTrimYear) {
		this.topicTrimYear = topicTrimYear;
	}

	public double getTopicFuel() {
		return topicFuel;
	}

	public void setTopicFuel(double topicFuel) {
		this.topicFuel = topicFuel;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public boolean getTopicStatus() {
		return topicStatus;
	}

	public void setTopicStatus(Boolean topicStatus) {
		this.topicStatus = topicStatus;
	}

	public Short getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Short categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getPostType() {
		return postType;
	}

	public void setPostType(Integer postType) {
		this.postType = postType;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getDealerParentCityId() {
		return dealerParentCityId;
	}

	public void setDealerParentCityId(Integer dealerParentCityId) {
		this.dealerParentCityId = dealerParentCityId;
	}

	public Integer getDealerCityId() {
		return dealerCityId;
	}

	public void setDealerCityId(Integer dealerCityId) {
		this.dealerCityId = dealerCityId;
	}

	public int getDealerId() {
		return dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public Integer getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(Integer ratingValue) {
		this.ratingValue = ratingValue;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	@XmlElement(name = "detailList")
	public List<m_D_Topic_Detail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<m_D_Topic_Detail> detailList) {
		this.detailList = detailList;
	}
	@XmlElement(name = "comment")
	public m_D_Topic_Comment getComment() {
		return comment;
	}

	public void setComment(m_D_Topic_Comment comment) {
		this.comment = comment;
	}
	@XmlElement(name = "imageList")
	public List<m_D_Topic_Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<m_D_Topic_Image> imageList) {
		this.imageList = imageList;
	}
	@XmlElement(name = "topicWordsList")
	public List<m_D_TopicWords> getTopicWordsList() {
		return topicWordsList;
	}

	public void setTopicWordsList(List<m_D_TopicWords> topicWordsList) {
		this.topicWordsList = topicWordsList;
	}

}

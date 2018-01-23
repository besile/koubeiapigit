package com.koubei.model;

import com.koubei.annotation.Column;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class m_D_CarSerial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 车型ID
	 */
	@Column(name = "cs_Id",unique=true)
	private Integer serialId;
	// 车型名称
	@Column(name = "cs_ShowName")
	private String carName;
	// 车型全拼
	@Column(name = "csAllSpell")
	private String carAllSpell;
	//车型级别
	@Column(name = "cs_CarLevel")
	private String carLevel;
	//品牌Id
	@Column(name = "cb_Id")
	private Integer cbId;
	//品牌名称
	@Column(name = "cb_Name")
	private String cbName;
	@Column(name = "cbAllSpell")
	private String cbAllSpell;
	@Column(name = "cbSeoName")
    private String cbSeoName;
	@Column(name = "bs_Id")
    private Integer masterId;
	@Column(name = "bs_Name")
    private String masterName;
	@Column(name = "bsallSpell")
    private String masterAllSpell;
	@Column(name = "msrp")
    private String msrp;
    private double rating;
    private Integer totalCount;
    private Integer levelRank;
    //同级别评分
    private double levelRating;
    //同级别评分偏移量
    private double ratingVariance;
    //白底图
    private String modelImageUrl;
    private String serialRadar;
    private double minFuelValue;
    private double maxFuelValue;
    private List<String> reportUrlList;
    //分项评分
    private Map<String,Double> ratingItem;
    private String saleState;
    @Column(name = "PowerType")
    private Integer powerType;
    private List<m_D_CarTrim> trimList;
	public Integer getSerialId() {
		return serialId;
	}
	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarAllSpell() {
		return carAllSpell;
	}
	public void setCarAllSpell(String carAllSpell) {
		this.carAllSpell = carAllSpell;
	}
	public String getCarLevel() {
		return carLevel;
	}
	public void setCarLevel(String carLevel) {
		this.carLevel = carLevel;
	}
	public Integer getCbId() {
		return cbId;
	}
	public void setCbId(Integer cbId) {
		this.cbId = cbId;
	}
	public String getCbName() {
		return cbName;
	}
	public void setCbName(String cbName) {
		this.cbName = cbName;
	}
	public String getCbAllSpell() {
		return cbAllSpell;
	}
	public void setCbAllSpell(String cbAllSpell) {
		this.cbAllSpell = cbAllSpell;
	}
	public String getCbSeoName() {
		return cbSeoName;
	}
	public void setCbSeoName(String cbSeoName) {
		this.cbSeoName = cbSeoName;
	}
	public Integer getMasterId() {
		return masterId;
	}
	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	public String getMasterAllSpell() {
		return masterAllSpell;
	}
	public void setMasterAllSpell(String masterAllSpell) {
		this.masterAllSpell = masterAllSpell;
	}
	public String getMsrp() {
		return msrp;
	}
	public void setMsrp(String msrp) {
		this.msrp = msrp;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getLevelRank() {
		return levelRank;
	}
	public void setLevelRank(Integer levelRank) {
		this.levelRank = levelRank;
	}
	public double getLevelRating() {
		return levelRating;
	}
	public void setLevelRating(double levelRating) {
		this.levelRating = levelRating;
	}
	public double getRatingVariance() {
		return ratingVariance;
	}
	public void setRatingVariance(double ratingVariance) {
		this.ratingVariance = ratingVariance;
	}
	public String getModelImageUrl() {
		return modelImageUrl;
	}
	public void setModelImageUrl(String modelImageUrl) {
		this.modelImageUrl = modelImageUrl;
	}
	public String getSerialRadar() {
		return serialRadar;
	}
	public void setSerialRadar(String serialRadar) {
		this.serialRadar = serialRadar;
	}
	public double getMinFuelValue() {
		return minFuelValue;
	}
	public void setMinFuelValue(double minFuelValue) {
		this.minFuelValue = minFuelValue;
	}
	public double getMaxFuelValue() {
		return maxFuelValue;
	}
	public void setMaxFuelValue(double maxFuelValue) {
		this.maxFuelValue = maxFuelValue;
	}
	public List<String> getReportUrlList() {
		return reportUrlList;
	}
	public void setReportUrlList(List<String> reportUrlList) {
		this.reportUrlList = reportUrlList;
	}
	public Map<String, Double> getRatingItem() {
		return ratingItem;
	}
	public void setRatingItem(Map<String, Double> ratingItem) {
		this.ratingItem = ratingItem;
	}
	public String getSaleState() {
		return saleState;
	}
	public void setSaleState(String saleState) {
		this.saleState = saleState;
	}
	public Integer getPowerType() {
		return powerType;
	}
	public void setPowerType(Integer powerType) {
		this.powerType = powerType;
	}
	public List<m_D_CarTrim> getTrimList() {
		return trimList;
	}
	public void setTrimList(List<m_D_CarTrim> trimList) {
		this.trimList = trimList;
	}
    
    
    
}

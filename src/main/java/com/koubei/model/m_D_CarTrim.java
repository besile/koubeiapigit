package com.koubei.model;

import com.koubei.annotation.Column;

import java.io.Serializable;
import java.util.Map;

public class m_D_CarTrim implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "Car_Id")
	private Integer trimId;
	@Column(name = "Car_Name")
	private String trimName;
	@Column(name = "car_ReferPrice")
	private double trimMsrp;
	@Column(name = "Car_YearType")
    private String trimYearType;
	@Column(name = "Cs_Id")
    private Integer modelId;
	@Column(name = "fuel_type")
    private String fuelType;
    //排量
	@Column(name = "displacement")
    private String displacement;
	@Column(name = "transmission")
    private String transmission;
	@Column(name = "engine_type")
    private String engineType;
    private double rating;
    private double minFuel;
    private double maxFuel;
    private double avgFuel;
    //分项评分
    private Map<String,Double> ratingItem;
	public Integer getTrimId() {
		return trimId;
	}
	public void setTrimId(Integer trimId) {
		this.trimId = trimId;
	}
	public String getTrimName() {
		return trimName;
	}
	public void setTrimName(String trimName) {
		this.trimName = trimName;
	}
	public double getTrimMsrp() {
		return trimMsrp;
	}
	public void setTrimMsrp(double trimMsrp) {
		this.trimMsrp = trimMsrp;
	}
	public String getTrimYearType() {
		return trimYearType;
	}
	public void setTrimYearType(String trimYearType) {
		this.trimYearType = trimYearType;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getDisplacement() {
		return displacement;
	}
	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getMinFuel() {
		return minFuel;
	}
	public void setMinFuel(double minFuel) {
		this.minFuel = minFuel;
	}
	public double getMaxFuel() {
		return maxFuel;
	}
	public void setMaxFuel(double maxFuel) {
		this.maxFuel = maxFuel;
	}
	public double getAvgFuel() {
		return avgFuel;
	}
	public void setAvgFuel(double avgFuel) {
		this.avgFuel = avgFuel;
	}
	public Map<String, Double> getRatingItem() {
		return ratingItem;
	}
	public void setRatingItem(Map<String, Double> ratingItem) {
		this.ratingItem = ratingItem;
	}
    
}

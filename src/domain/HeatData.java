package domain;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class HeatData implements Serializable{

	// data: [{lat: 35.682845, lng:140.192977, value: 1},{lat: 35.687818,
	// lng:140.241222, value: 2}]

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double lat;

	private Double lng;

	private Integer value;
	
	@JSONField(serialize=false) 
	private Integer speed;
	
	@JSONField(serialize=false) 
	private Integer longitude;
	
	@JSONField(serialize=false) 
	private Integer latitude;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}
	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

}

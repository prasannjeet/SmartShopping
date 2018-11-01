package org.store.domain.service.dao;

import java.util.List;

public class PriceList {
	private String userId;
	private String storeName;
	private double distanceFromUser;
	private List<PriceTagDao> pricesTags; //barcode + price
	
	public PriceList(String userId, String storeName, double distanceFromUser,
			List<PriceTagDao> pricesTags) {
		super();
		this.userId = userId;
		this.storeName = storeName;
		this.distanceFromUser = distanceFromUser;
		this.pricesTags = pricesTags;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public double getDistanceFromUser() {
		return distanceFromUser;
	}
	public void setDistanceFromUser(double distanceFromUser) {
		this.distanceFromUser = distanceFromUser;
	}
	public List<PriceTagDao> getPricesTags() {
		return pricesTags;
	}
	public void setPricesTags(List<PriceTagDao> pricesTags) {
		this.pricesTags = pricesTags;
	}
	
	
}

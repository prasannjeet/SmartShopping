package org.store.domain.service.dao;

public class ProductInfo {
	private String barcode;
	private String productName;
	private boolean weightable;
	
	public ProductInfo(String barcode, String productName, boolean weightable) {
		super();
		this.barcode = barcode;
		this.productName = productName;
		this.weightable = weightable;
	}
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public boolean isWeightable() {
		return weightable;
	}
	public void setWeightable(boolean weightable) {
		this.weightable = weightable;
	}
}

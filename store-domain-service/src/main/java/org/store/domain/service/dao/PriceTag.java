package org.store.domain.service.dao;

public class PriceTag {
	private String barcode;
	private double price;
	
	public PriceTag(String barcode, double price) {
		this.barcode = barcode;
		this.price = price;
	}	
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}

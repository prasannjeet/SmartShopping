package org.store.domain.service.dao;

import org.store.domain.service.model.PriceTag;

public class PriceTagDao {
	private String barcode;
	private double price;
	
	public PriceTagDao(String barcode, double price) {
		this.barcode = barcode;
		this.price = price;
	}	
	
	public PriceTagDao(PriceTag priceTag) {
		this(priceTag.getBarcode(), priceTag.getPrice());
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

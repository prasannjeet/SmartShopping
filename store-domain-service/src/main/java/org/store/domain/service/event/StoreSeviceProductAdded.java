package org.store.domain.service.event;

import io.eventuate.Event;

import org.store.domain.service.dao.ProductInfo;

public class StoreSeviceProductAdded implements Event {
	private ProductInfo productInfo;

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
}

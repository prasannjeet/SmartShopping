package org.store.domain.service.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "price_tag")
public class PriceTag {

    @Id
    @NotBlank
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @NotBlank
    @Column(name = "barcode", nullable = false, unique = true)
    private String barcode;
    
    @NotBlank
    @Column(name = "price", nullable = false)
    private double price;
    
    public PriceTag() {

    }

    public PriceTag(String id, String barcode, double price) {
        this.setId(id);
        this.setBarcode(barcode);
        this.setPrice(price);
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = (id == null) ? this.id : (id.isEmpty() ? this.id : id);
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = (barcode == null) ? this.barcode : (barcode.isEmpty() ? this.barcode : barcode);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

package org.product.domain.service.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@NotBlank
	@Column(name = "barcode", nullable = false, unique = true)
	private String barcode;
	
	@NotBlank
	@Column(name = "name", nullable = false, unique = false)
	private String name;
	
	@Column(name = "weightable", unique = false)
	private boolean weightable;
	
	public Product() {
		
	}
	
	public Product(String barcode, String name, boolean weightable) {
		this.setBarcode(barcode);
		this.setName(name);
		this.setWeightable(weightable);
	}
	
    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
    		this.barcode = (barcode == null) ? this.barcode : (barcode.matches("[0-9]+") ? barcode : this.barcode);
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
    		this.name = (name == null) ? this.name : (name.isEmpty() ? this.name : name);
    }
    
    public boolean getWeightable() {
        return this.weightable;
    }

    public void setWeightable(boolean weightable) {
    		this.weightable = weightable;
    }
}

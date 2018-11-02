package org.product.domain.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String barcode;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(name = "has_weight", nullable = false)
    private boolean hasWeight;

    @NotBlank
    @Column(name = "store_id", nullable = false)
    private String storeId;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = (barcode == null || barcode.isEmpty()) ? this.barcode : barcode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = (name == null || name.isEmpty()) ? this.name : name;
    }

    public boolean getHasWeight() {
        return this.hasWeight;
    }

    public void setHasWeight(boolean hasWeight) {
        this.hasWeight = hasWeight;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = (storeId == null || storeId.isEmpty()) ? this.storeId : storeId;
    }
}
package org.cart.domain.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    @Column(nullable = false)
    private String quantity;

    @NotNull
    @Column(name = "has_weight", nullable = false)
    private Boolean hasWeight;

    @NotBlank
    @Column(name = "user_id", nullable = false)
    private String userId;

    public Product() {

    }

    public Product(String id, Product product) throws Exception {
        this.setId(id);
        this.setBarcode(product.barcode);
        this.setName(product.name);
        this.setHasWeight(product.hasWeight);
        this.setQuantity(product.quantity);
        this.setUserId(product.userId);
    }

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

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) throws Exception {
        if (Double.parseDouble(quantity) <= 0) {
            throw new Exception("Quantity value must be > 0");
        }
        this.quantity = quantity;
    }

    public void verifyQuantity(Product product) throws Exception {
        if (Double.parseDouble(product.quantity) <= 0) {
            throw new Exception("Quantity value must be > 0");
        }
        try {
            product.setQuantity(product.hasWeight ? product.quantity : "" + Integer.parseInt(product.quantity));
        } catch (Exception e) {
            throw new Exception("Quantity value must be integer");
        }
    }

    public Boolean getHasWeight() {
        return this.hasWeight;
    }

    public void setHasWeight(Boolean hasWeight) {
        this.hasWeight = hasWeight;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = (userId == null || userId.isEmpty()) ? this.userId : userId;
    }
}

package org.cart.domain.service.model;

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
    @Column(nullable = false)
    private String barcode;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String brand;

    @NotBlank
    @Column(nullable = false)
    private String quantity;

    @NotBlank
    @Column(name = "user_id", nullable = false)
    private String userId;

    public Product() {

    }

    public Product(String barcode, Product product) throws Exception {
        this.setBarcode(barcode);
        this.setName(product.name);
        this.setBrand(product.brand);
        this.setQuantity(product.quantity);
        this.setUserId(product.userId);
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = (barcode == null) ? this.barcode : (barcode.isEmpty() ? this.barcode : barcode);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = (name == null) ? this.name : (name.isEmpty() ? this.name : name);
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = (brand == null) ? this.brand : (brand.isEmpty() ? this.brand : brand);
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) throws Exception {
        if (Double.parseDouble(quantity) <= 0) {
            throw new Exception("Quantity value must be > 0");
        }
        this.quantity = (quantity == null) ? this.quantity : (quantity.isEmpty() ? this.quantity : quantity);
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = (userId == null) ? this.userId : (userId.isEmpty() ? this.userId : userId);
    }
}

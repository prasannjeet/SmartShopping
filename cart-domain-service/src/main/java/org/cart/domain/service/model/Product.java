package org.cart.domain.service.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    private String id;

    @NotBlank
    @Column(nullable = false, unique = true)
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

    public Product(String id, String barcode, String name, String brand, String quantity, String userId) throws Exception {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.brand = brand;
        this.setQuantity(quantity);
        this.userId = userId;
    }

    public Product(Product product) throws Exception {
        this.barcode = product.barcode;
        this.name = product.name;
        this.brand = product.brand;
        this.setQuantity(product.getQuantity());
        this.userId = product.userId;
    }

    public Product(String barcode, String name, String brand, String quantity, String userId) throws Exception {
        this.barcode = barcode;
        this.name = name;
        this.brand = brand;
        this.setQuantity(quantity);
        this.userId = userId;
    }

    public Product(String id, Product product) {
        this.id = id;
        this.barcode = product.barcode;
        this.name = product.name;
        this.brand = product.brand;
        this.quantity = product.quantity;
        this.userId = product.userId;
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
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}

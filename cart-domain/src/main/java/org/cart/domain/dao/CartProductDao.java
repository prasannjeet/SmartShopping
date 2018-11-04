package org.cart.domain.dao;

public class CartProductDao {

    private String name;
    private String price;
    private String quantity;
    private String total;

    public CartProductDao() {
        this.name = "";
        this.price = "";
        this.quantity = "";
        this.total = "";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

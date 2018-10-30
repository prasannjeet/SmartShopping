package org.store.domain.service.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pricetag")
public class Pricetag {

    @Id
    private String id;

    @NotBlank
    @Column(name = "barcode", nullable = false)
    private String barcode;

    @NotBlank
    @Column(name = "price", nullable = false)
    private String price;

    @NotBlank
    @Column(name = "store", nullable = false)
    private String store;

    public Pricetag() {

    }

    public Pricetag(String id, String barcode, String price, String store) throws Exception {
        this.setId(id);
        this.setBarcode(barcode);
        this.setPrice(price);
        this.setStore(store);
    }

    public Pricetag(Pricetag pricetag) throws Exception{
        this.setBarcode(pricetag.barcode);
        this.setPrice(pricetag.price);
        this.setStore(pricetag.store);
    }

    public Pricetag(String barcode, String price, String store) throws Exception {
        this.setBarcode(barcode);
        this.setPrice(price);
        this.setStore(store);
    }


    public Pricetag (String id, Pricetag pricetag) throws Exception{
        this.setId(id);
        this.setBarcode(pricetag.barcode);
        this.setPrice(pricetag.price);
        this.setStore(pricetag.store);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = (id == null) ? this.id : (id.isEmpty() ? this.id : id);
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = (barcode == null) ? this.barcode : (barcode.isEmpty() ? this.barcode : barcode);
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) throws Exception {
        if (Double.parseDouble(price) <= 0) {
            throw new Exception("Price value must be > 0");
        }
        this.price = price.isEmpty() ? this.price : price;
    }

    public String getStore() {
        return this.store;
    }

    public void setStore(String store) {
        this.store = (store == null) ? this.store : (store.isEmpty() ? this.store : store);
    }
}

package org.store.domain.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "price_tag")
public class PriceTag {

    @Id
    private String id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String barcode;

    @NotBlank
    @Column(nullable = false)
    private String price;

    public PriceTag() {

    }

    public PriceTag(String id, PriceTag priceTag) throws Exception {
        this.setId(id);
        this.setBarcode(priceTag.barcode);
        this.setPrice(priceTag.price);
    }

    public PriceTag(String id, Product product) throws Exception {
        this.setId(id);
        this.setBarcode(product.getBarcode());
        this.setPrice(product.getPrice());
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

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) throws Exception {
        if (Double.parseDouble(price) <= 0) {
            throw new Exception("Price must be > 0");
        }
        this.price = price;
    }
}

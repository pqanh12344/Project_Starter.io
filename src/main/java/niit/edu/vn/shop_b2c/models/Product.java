package niit.edu.vn.shop_b2c.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    Double price;

    @Column(name = "quantity")
    Long quantity;

    @Column(name = "description")
    String description;

    @Column(name = "content")
    String content;

    @Column(name = "discount_price")
    String discount_price;

    @Column(name = "sku")
    String sku;

    @Column(name = "status")
    String status;

    @Column(name = "category_id")
    Long category_id;


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public String getSku() {
        return sku;
    }

    public String getStatus() {
        return status;
    }
}

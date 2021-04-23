package niit.edu.vn.shop_b2c.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id")
    Long user_id;

    @Column(name = "product_id")
    Long product_id;

    @Column(name = "quantity")
    Long quantity;

    @Column(name = "filter_value_id")
    Long filter_value_id;

    @Column(name = "store_id")
    Long store_id;

    public Long getId() {
        return id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFilter_value_id() {
        return filter_value_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setFilter_value_id(Long filter_value_id) {
        this.filter_value_id = filter_value_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getStore_id() {
        return store_id;
    }

    public void setStore_id(Long store_id) {
        this.store_id = store_id;
    }
}

package niit.edu.vn.shop_b2c.models;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "parent_id")
    Long parent_id;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private Category parent;

    @OneToOne(mappedBy = "parent", cascade = {CascadeType.DETACH})
    private Category child;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = {CascadeType.ALL})
    private List<Product> listProduct = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Category getChild() {
        return child;
    }

    public Category getParent() {
        return parent;
    }

    public void setChild(Category child) {
        this.child = child;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Long getParent_id() {
        return parent_id;
    }
}

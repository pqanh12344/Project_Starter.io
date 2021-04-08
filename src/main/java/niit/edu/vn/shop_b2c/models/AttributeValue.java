package niit.edu.vn.shop_b2c.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "attribute_values")
public class AttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "value")
    String value;

    @Column(name = "attribute_id")
    Long attribute_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttribute_id() {
        return attribute_id;
    }

    public String getValue() {
        return value;
    }

    public void setAttribute_id(Long attribute_id) {
        this.attribute_id = attribute_id;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package niit.edu.vn.shop_b2c.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "path")
    String path;

    @Column(name = "is_preview")
    String is_preview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public String getIs_preview() {
        return is_preview;
    }

    public void setIs_preview(String is_preview) {
        this.is_preview = is_preview;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

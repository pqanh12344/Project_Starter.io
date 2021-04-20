package niit.edu.vn.shop_b2c.services;

import niit.edu.vn.shop_b2c.models.Category;
import niit.edu.vn.shop_b2c.models.Image;
import niit.edu.vn.shop_b2c.models.Product;
import niit.edu.vn.shop_b2c.models.Role;
import niit.edu.vn.shop_b2c.respository.CategoryRepo;
import niit.edu.vn.shop_b2c.respository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ImageService imageService;

    public AbService<Product, ProductRepo> getService() {
        return new AbService<>(this.productRepo);
    }

    public boolean save(Product product) {
        try {
            Product saveProduct  = new Product();
            saveProduct.setName(product.getName());
            saveProduct.setPrice(product.getPrice());
            saveProduct.setQuantity(product.getQuantity());
            saveProduct.setDescription(product.getDescription());
            saveProduct.setContent(product.getContent());
            saveProduct.setDiscount_price(product.getDiscount_price());
            saveProduct.setSku(product.getSku());
            saveProduct.setStatus(product.getStatus());

            Category category = categoryService.getService().getById(product.getCategory_id());
            saveProduct.setCategory(category);
            saveProduct.setCategory_id(category.getId());

            Image image = imageService.getService().getById(product.getImage_id());
            saveProduct.setImage(image);
            saveProduct.setImage_id(image.getId());
            productRepo.save(saveProduct);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }
}

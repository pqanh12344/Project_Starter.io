package niit.edu.vn.shop_b2c.services;

import niit.edu.vn.shop_b2c.models.Category;
import niit.edu.vn.shop_b2c.respository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public AbService<Category, CategoryRepo> getService() {
        return new AbService<>(this.categoryRepo);
    }

    public boolean save(Category category) {
        try {
            Category saveCategory  = new Category();
            saveCategory.setName(category.getName());
            saveCategory.setParent_id(category.getParent_id());

            categoryRepo.save(saveCategory);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void deleteCategory(Long id){
        categoryRepo.deleteById(id);
    }
}

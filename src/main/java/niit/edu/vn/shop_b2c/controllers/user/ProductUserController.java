package niit.edu.vn.shop_b2c.controllers.user;

import niit.edu.vn.shop_b2c.models.Attribute;
import niit.edu.vn.shop_b2c.models.AttributeValue;
import niit.edu.vn.shop_b2c.models.Category;
import niit.edu.vn.shop_b2c.models.Image;
import niit.edu.vn.shop_b2c.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/user/product")
public class ProductUserController {
    @Value("${config.upload_folder}")
    String UPLOADED_FOLDER;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ImageService imageService;

    @Autowired
    AttributeService attributeService;

    @Autowired
    AttributeValueService attributeValueService;

    @GetMapping("")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        AbService.PagingResult pageResult = productService.getService().getPaginate(page);
        model.addAttribute("pageResult", pageResult);
        ArrayList<Category> categories = categoryService.getService().getAll();
        model.addAttribute("listCategory",categories);
        ArrayList<Image> images = imageService.getService().getAll();
        model.addAttribute("listImage",images);
        ArrayList<Attribute> attributes = attributeService.getService().getAll();
        model.addAttribute("listAttribute",attributes);
        ArrayList<AttributeValue> attributeValues = attributeValueService.getService().getAll();
        model.addAttribute("listValue",attributeValues);
        return "user/product/list";
    }
}

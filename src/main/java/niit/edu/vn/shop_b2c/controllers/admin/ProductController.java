package niit.edu.vn.shop_b2c.controllers.admin;

import niit.edu.vn.shop_b2c.models.*;
import niit.edu.vn.shop_b2c.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


@Controller
@RequestMapping("/admin/product")
public class ProductController implements IIAdminController<Product> {
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

    @Override
    @GetMapping("")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        AbService.PagingResult pageResult = productService.getService().getPaginate(page);
        model.addAttribute("pageResult", pageResult);
        ArrayList<Category> categories = categoryService.getService().getAll();
        model.addAttribute("listCategory",categories);
        //ArrayList<Product> products = pageResult.getList();
        ArrayList<Image> images = imageService.getService().getAll();
        model.addAttribute("listImage",images);
        ArrayList<Attribute> attributes = attributeService.getService().getAll();
        model.addAttribute("listAttribute",attributes);
        ArrayList<AttributeValue> attributeValues = attributeValueService.getService().getAll();
        model.addAttribute("listValue",attributeValues);
        return "admin/product/list";
    }

    @Override
    @RequestMapping("/add")
    public String add(Model model) {
        ArrayList<Category> categories = categoryService.getService().getAll();
        model.addAttribute("listCategory",categories);
        model.addAttribute("obj", new Product());
        model.addAttribute("image",new Image());
        return "admin/product/add";
    }

    @Override
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("obj", productService.getService().getById(id));
        ArrayList<Category> categories = categoryService.getService().getAll();
        model.addAttribute("listCategory",categories);
        return "admin/product/edit";
    }

    @Override
    @PostMapping("/do-add")
    public String doAdd(Product product, RedirectAttributes redirectAttributes, @RequestParam(name = "image1") MultipartFile file) {
        try {
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year = localDate.getYear();
            int month = localDate.getMonthValue();
            String saveFolder = UPLOADED_FOLDER + month + "_" + year + "/";
            redirectAttributes.addFlashAttribute("success", "Done");

            File dir = new File(saveFolder);
            if (dir.isFile() || !dir.exists()) {
                dir.mkdir();
            }

            String filename = System.currentTimeMillis() + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(dir.getAbsolutePath() + "/" + filename);
            Files.write(path, bytes);
            Image image = new Image();
            image.setPath(saveFolder.replace(UPLOADED_FOLDER, "")+ filename);
            imageService.getService().save(image);
            product.setImage_id(image.getId());
            if (productService.getService().save(product)) {
                redirectAttributes.addFlashAttribute("success", "Thêm thành công");
            } else {
                redirectAttributes.addFlashAttribute("error", "Thêm thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thêm thất bại");
        }
        return "redirect:/admin/product/add";
    }

    @Override
    @PostMapping("/do-edit")
    public String doEdit(Product product, RedirectAttributes redirectAttributes) {
        if (productService.getService().save(product)) {
            redirectAttributes.addFlashAttribute("success", "Sửa thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", "Sửa thất bại");
        }

        return "redirect:/admin/product/";
    }

    @Override
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        Product product = productService.getService().getById(id);
        productService.deleteProduct(id);
        Image image = imageService.getService().getById(product.getImage_id());
        //User user = userService.getUser(id);
        try {
            File file = new File(UPLOADED_FOLDER + image.getPath());
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageService.deleteImage(product.getImage_id());
        //userService.deleteUser(id);
        return "redirect:/admin/product/";
    }
}

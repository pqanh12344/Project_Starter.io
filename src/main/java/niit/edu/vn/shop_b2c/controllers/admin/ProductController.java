package niit.edu.vn.shop_b2c.controllers.admin;

import niit.edu.vn.shop_b2c.models.Product;
import niit.edu.vn.shop_b2c.models.Role;
import niit.edu.vn.shop_b2c.models.User;
import niit.edu.vn.shop_b2c.services.AbService;
import niit.edu.vn.shop_b2c.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin/product")
public class ProductController implements IAdminController<Product> {
    @Autowired
    ProductService productService;

    @Override
    @GetMapping("")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        AbService.PagingResult pageResult = productService.getService().getPaginate(page);
        model.addAttribute("pageResult", pageResult);
        return "admin/product/list";
    }

    @Override
    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("obj", new Product());
        return "admin/product/add";
    }

    @Override
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("obj", productService.getService().getById(id));
        return "admin/product/edit";
    }

    @Override
    @PostMapping("/do-add")
    public String doAdd(Product product, RedirectAttributes redirectAttributes) {
        if (productService.getService().save(product)) {
            redirectAttributes.addFlashAttribute("success", "Thêm thành công");
        } else {
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
        productService.deleteProduct(id);
        return "redirect:/admin/product/";
    }
}

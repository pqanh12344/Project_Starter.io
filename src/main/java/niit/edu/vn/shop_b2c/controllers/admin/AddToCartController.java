package niit.edu.vn.shop_b2c.controllers.admin;

import niit.edu.vn.shop_b2c.models.*;
import niit.edu.vn.shop_b2c.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/cart")
public class AddToCartController {
    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ImageService imageService;

    @GetMapping("")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        AbService.PagingResult pageResult = cartService.getService().getPaginate(page);
        model.addAttribute("pageResult",pageResult);
        ArrayList<Cart> carts = pageResult.getList();
        ArrayList<Product> products = productService.getService().getAll();
        ArrayList<Product> listProduct = new ArrayList<>();
        ArrayList<Image> listImage = new ArrayList<>();
        for (int i = 0; i < carts.size(); i++) {
            if(carts.get(i).getUser_id() == 1){
                //Product product = new Product();
                //product.setId(carts.get(i).getProduct_id());
                //for (int j = 0; j < products.size(); j++) {
                    //if(product.getId() == products.get(i).getId()){
                        //listProduct.add(products.get(i));
                    //}
                //}
                Product product = productService.getService().getById(carts.get(i).getProduct_id());
                Image image = imageService.getService().getById(product.getImage_id());
                listImage.add(image);
                listProduct.add(product);
            }
        }
        model.addAttribute("listProduct",listProduct);
        //ArrayList<Product> products = pageResult.getList();
        //ArrayList<Image> images = imageService.getService().getAll();
        model.addAttribute("listImage",listImage);
        return "admin/cart/list";
    }

    @RequestMapping("/add")
    public String add(Model model, @RequestParam("id") Long id) {
        Product product = productService.getService().getById(id);
        model.addAttribute("product",product);
        model.addAttribute("obj", new Cart());
        return "admin/cart/add";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("obj", cartService.getService().getById(id));
        return "admin/cart/edit";
    }

    @PostMapping("/do-add")
    public String doAdd(Cart cart, RedirectAttributes redirectAttributes, @RequestParam("id") Long id) {
        Product product = productService.getService().getById(id);
        cart.setProduct_id(product.getId());
        cart.setUser_id(1L);
        if (cartService.getService().save(cart)) {
            redirectAttributes.addFlashAttribute("success", "Thêm thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", "Thêm thất bại");
        }

        return "redirect:/admin/cart/";
    }

    @PostMapping("/do-edit")
    public String doEdit(Cart cart, RedirectAttributes redirectAttributes) {
        if (cartService.getService().save(cart)) {
            redirectAttributes.addFlashAttribute("success", "Sửa thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", "Sửa thất bại");
        }

        return "redirect:/admin/cart/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        cartService.deleteCart(id);
        return "redirect:/admin/cart/";
    }
}

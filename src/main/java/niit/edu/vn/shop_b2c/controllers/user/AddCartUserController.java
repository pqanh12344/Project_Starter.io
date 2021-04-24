package niit.edu.vn.shop_b2c.controllers.user;

import niit.edu.vn.shop_b2c.models.Cart;
import niit.edu.vn.shop_b2c.models.Image;
import niit.edu.vn.shop_b2c.models.Product;
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
@RequestMapping("/user/cart")
public class AddCartUserController {
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
        //Cart cart1 = cartService.getService().getById();
        ArrayList<Cart> carts = pageResult.getList();
        ArrayList<Product> products = productService.getService().getAll();
        ArrayList<Product> listProduct = new ArrayList<>();
        ArrayList<Image> listImage = new ArrayList<>();
        for (int i = 0; i < carts.size(); i++) {
            if(carts.get(i).getUser_id() == null){
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
        return "user/cart/list";
    }

    @RequestMapping("/add")
    public String add(Model model, @RequestParam("id") Long id) {
        Product product = productService.getService().getById(id);
        model.addAttribute("product",product);
        model.addAttribute("obj", new Cart());
        return "user/cart/add";
    }

    @PostMapping("/do-add")
    public String doAdd(Cart cart, RedirectAttributes redirectAttributes, @RequestParam("id") Long id) {
        Product product = productService.getService().getById(id);
        cart.setProduct_id(product.getId());
        if (cartService.getService().save(cart)) {
            redirectAttributes.addFlashAttribute("success", "Thêm thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", "Thêm thất bại");
        }

        return "redirect:/user/cart/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        cartService.deleteCart(id);
        return "redirect:/user/cart/";
    }
}

package niit.edu.vn.shop_b2c.controllers.user;

import niit.edu.vn.shop_b2c.models.*;
import niit.edu.vn.shop_b2c.respository.CartRepo;
import niit.edu.vn.shop_b2c.services.AbService;
import niit.edu.vn.shop_b2c.services.CartService;
import niit.edu.vn.shop_b2c.services.RoleService;
import niit.edu.vn.shop_b2c.services.UserService;
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
@RequestMapping("/user/user")
public class UserUController {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    private CartRepo cartRepo;

    @GetMapping("/add")
    public String add(Model model) {
        ArrayList<Role> roles = roleService.getService().getAll();
        model.addAttribute("listRole", roles);
        model.addAttribute("obj", new User());
        return "user/user/add";
    }

    @PostMapping("/do-add")
    public String doAdd(User user, RedirectAttributes redirectAttributes) {
        //user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (userService.save(user)) {
            redirectAttributes.addFlashAttribute("success", "Thêm thành công");
            ArrayList<Cart> carts = cartService.getService().getAll();
            for (int i = 0; i < carts.size(); i++) {
                if(carts.get(i).getUser_id() == null){
                    carts.get(i).setUser_id(user.getId());
                    cartRepo.save(carts.get(i));
                }
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Thêm thất bại");
        }

        return "redirect:/user/user/add";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user/user/";
    }
}

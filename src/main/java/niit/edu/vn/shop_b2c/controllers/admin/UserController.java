package niit.edu.vn.shop_b2c.controllers.admin;

import niit.edu.vn.shop_b2c.models.Role;
import niit.edu.vn.shop_b2c.models.User;
import niit.edu.vn.shop_b2c.services.AbService;
import niit.edu.vn.shop_b2c.services.RoleService;
import niit.edu.vn.shop_b2c.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/user")
public class UserController implements IAdminController<User> {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Override
    @GetMapping("")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        AbService.PagingResult pageResult = userService.getService().getPaginate(page);
        model.addAttribute("pageResult", pageResult);
        return "admin/user/list";
    }

    @Override
    @GetMapping("/add")
    public String add(Model model) {
        ArrayList<Role> roles = roleService.getService().getAll();

        model.addAttribute("listRole", roles);
        model.addAttribute("obj", new User());
        return "admin/user/add";
    }

    @Override
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("obj", userService.getService().getById(id));
        return "admin/user/edit";
    }

    @Override
    @PostMapping("/do-add")
    public String doAdd(User user, RedirectAttributes redirectAttributes) {
        //user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        if (userService.save(user)) {
            redirectAttributes.addFlashAttribute("success", "Thêm thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", "Thêm thất bại");
        }

        return "redirect:/admin/user/add";
    }

    @Override
    @PostMapping("/do-edit")
    public String doEdit(User user, RedirectAttributes redirectAttributes) {
        if (userService.getService().save(user)) {
            redirectAttributes.addFlashAttribute("success", "Sửa thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", "Sửa thất bại");
        }

        return "redirect:/admin/user/";
    }

    @Override
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/user/";
    }
}

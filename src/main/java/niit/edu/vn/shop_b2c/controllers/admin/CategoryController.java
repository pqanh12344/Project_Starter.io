package niit.edu.vn.shop_b2c.controllers.admin;

import niit.edu.vn.shop_b2c.models.Category;
import niit.edu.vn.shop_b2c.services.AbService;
import niit.edu.vn.shop_b2c.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController implements IAdminController<Category>{
    @Autowired
    CategoryService categoryService;

    @Override
    @GetMapping("")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        AbService.PagingResult pageResult = categoryService.getService().getPaginate(page);
        model.addAttribute("pageResult", pageResult);
        return "admin/category/list";
    }

    @Override
    @RequestMapping("/add")
    public String add(Model model) {
        List<Category> listParentCategory = categoryService.getService().getAll();
        model.addAttribute("parents", listParentCategory);
        model.addAttribute("obj", new Category());
        return "admin/category/add";
}

    @Override
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Long id) {
        List<Category> listParentCategory = categoryService.getService().getAll();
        model.addAttribute("parents", listParentCategory);
        Category category = categoryService.getService().getById(id);
        model.addAttribute("obj", category);
        return "admin/category/edit";
    }

    @Override
    @PostMapping("/do-add")
    public String doAdd(Category category, RedirectAttributes redirectAttributes) {
        if (categoryService.getService().save(category)) {
            redirectAttributes.addFlashAttribute("success", "Th??m th??nh c??ng");
        } else {
            redirectAttributes.addFlashAttribute("error", "Th??m th???t b???i");
        }

        return "redirect:/admin/category/add";
    }

    @Override
    @PostMapping("/do-edit")
    public String doEdit(Category category, RedirectAttributes redirectAttributes) {
        if (categoryService.getService().save(category)) {
            redirectAttributes.addFlashAttribute("success", "S???a th??nh c??ng");
        } else {
            redirectAttributes.addFlashAttribute("error", "S???a th???t b???i");
        }

        return "redirect:/admin/category/";
    }

    @Override
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/category/";
    }
}

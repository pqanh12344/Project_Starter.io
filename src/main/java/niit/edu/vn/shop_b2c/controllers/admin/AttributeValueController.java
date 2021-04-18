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
@RequestMapping("/admin/attribute-value")
public class AttributeValueController implements IAdminController<AttributeValue>{
    @Autowired
    AttributeService attributeService;

    @Autowired
    AttributeValueService attributeValueService;

    @Override
    @GetMapping("")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        AbService.PagingResult pageResult = attributeValueService.getService().getPaginate(page);
        model.addAttribute("pageResult", pageResult);
        ArrayList<Attribute> attributes = attributeService.getService().getAll();
        model.addAttribute("listAttribute", attributes);
        return "admin/attribute-value/list";
    }

    @Override
    @GetMapping("/add")
    public String add(Model model) {
        ArrayList<Attribute> attributes = attributeService.getService().getAll();
        model.addAttribute("listAttribute", attributes);
        model.addAttribute("obj", new AttributeValue());
        return "admin/attribute-value/add";
    }

    @Override
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("obj", attributeValueService.getService().getById(id));
        ArrayList<Attribute> attributes = attributeService.getService().getAll();
        model.addAttribute("listAttribute", attributes);
        return "admin/attribute-value/edit";
    }

    @Override
    @PostMapping("/do-add")
    public String doAdd(AttributeValue attributeValue, RedirectAttributes redirectAttributes) {
        //user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        if (attributeValueService.save(attributeValue)) {
            redirectAttributes.addFlashAttribute("success", "Thêm thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", "Thêm thất bại");
        }

        return "redirect:/admin/attribute-value/add";
    }

    @Override
    @PostMapping("/do-edit")
    public String doEdit(AttributeValue attributeValue, RedirectAttributes redirectAttributes) {
        if (attributeValueService.getService().save(attributeValue)) {
            redirectAttributes.addFlashAttribute("success", "Sửa thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", "Sửa thất bại");
        }

        return "redirect:/admin/attribute-value/";
    }

    @Override
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        attributeService.deleteAttribute(id);
        return "redirect:/admin/attribute-value/";
    }
}

package niit.edu.vn.shop_b2c.controllers.admin;

import niit.edu.vn.shop_b2c.models.Attribute;
import niit.edu.vn.shop_b2c.services.AbService;
import niit.edu.vn.shop_b2c.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/attribute")
public class AttributeController implements IAdminController<Attribute>{
    @Autowired
    AttributeService attributeService;

    @Override
    @GetMapping("")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        AbService.PagingResult pageResult = attributeService.getService().getPaginate(page);
        model.addAttribute("pageResult", pageResult);
        return "admin/attribute/list";
    }

    @Override
    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("obj", new Attribute());
        return "admin/attribute/add";
    }

    @Override
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("obj", attributeService.getService().getById(id));
        return "admin/attribute/edit";
    }

    @Override
    @PostMapping("/do-add")
    public String doAdd(Attribute attribute, RedirectAttributes redirectAttributes) {
        if (attributeService.getService().save(attribute)) {
            redirectAttributes.addFlashAttribute("success", "Thêm thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", "Thêm thất bại");
        }

        return "redirect:/admin/attribute/add";
    }

    @Override
    @PostMapping("/do-edit")
    public String doEdit(Attribute attribute, RedirectAttributes redirectAttributes) {
        if (attributeService.getService().save(attribute)) {
            redirectAttributes.addFlashAttribute("success", "Sửa thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", "Sửa thất bại");
        }

        return "redirect:/admin/attribute/";
    }

    @Override
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        attributeService.deleteAttribute(id);
        return "redirect:/admin/attribute/";
    }
}

package jp.albrecht1999.sisiwaka_spring.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/updates")
public class UpdatesController {

    private final UpdatesService service;

    public UpdatesController(UpdatesService service) {
        this.service = service;
    }

    @GetMapping
    public String page(Model model,
                       @ModelAttribute("statusMessage") String statusMessage,
                       @ModelAttribute("statusOk") Boolean statusOk) {

        var items = service.listForAdmin();
        model.addAttribute("items", items);

        // 既存行も編集できるようにフォーム行を同数作る
        UpdatesForm form = new UpdatesForm();
        for (var it : items) {
            AdminUpdateFormDto formItem = new AdminUpdateFormDto();
            // createdAt は template 側で埋める（OffsetDateTime→datetime-local整形）
            formItem.setArticle(it.getArticle());
            formItem.setValid(it.isValid());
            form.getRows().add(formItem);
        }
        model.addAttribute("form", form);

        model.addAttribute("statusMessage", statusMessage == null ? "" : statusMessage);
        model.addAttribute("statusOk", statusOk != null && statusOk);

        return "admin/updates";
    }

    @PostMapping
    public String update(@ModelAttribute UpdatesForm form, RedirectAttributes ra) {
        try {
            int n = service.saveAll(form);
            ra.addFlashAttribute("statusOk", true);
            ra.addFlashAttribute("statusMessage", "更新しました（" + n + " 行）");
        } catch (Exception e) {
            ra.addFlashAttribute("statusOk", false);
            ra.addFlashAttribute("statusMessage", "更新に失敗しました: " + e.getMessage());
        }
        return "redirect:/admin/updates";
    }
}

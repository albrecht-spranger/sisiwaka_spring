package jp.albrecht1999.sisiwaka_spring.controller;

import jp.albrecht1999.sisiwaka_spring.service.WorksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WorksController {

	private final WorksService worksService;

	public WorksController(WorksService worksService) {
		this.worksService = worksService;
	}

	// =========================
	// 作品一覧
	// =========================
	@GetMapping("/works")
	public String works(Model model) {
		model.addAttribute("allProducts", worksService.getAllProductsForList());
		model.addAttribute("categories", worksService.getCategoriesForFilter());
		model.addAttribute("techniques", worksService.getTechniquesForFilter());
		model.addAttribute("colorings", worksService.getColoringsForFilter());
		return "works";
	}

	// =========================
	// 作品詳細
	// =========================
	@GetMapping("/works/{id}")
	public String workDetail(
			@PathVariable int id,
			Model model) {
		model.addAttribute("work", worksService.findById(id));
		model.addAttribute("mediaList", worksService.findMediaByWorkId(id));
		model.addAttribute("techniques", worksService.findTechniquesByWorkId(id));
		return "work_detail"; // templates/work_detail.html
	}
}

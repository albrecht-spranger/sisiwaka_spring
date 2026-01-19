package jp.albrecht1999.sisiwaka_spring.controller;

import jp.albrecht1999.sisiwaka_spring.service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("updatesList", homeService.latestUpdatesOrFallback());

        // var list = homeService.latestUpdatesOrFallback();
        // System.out.println("updatesList size = " + (list == null ? "null" : list.size()));
        // model.addAttribute("updatesList", list);

        return "index"; // templates/index.html
    }
}

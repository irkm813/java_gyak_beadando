package com.example.java_gyak_beadando;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Főoldal");
        return "index"; // Az index.html-t tölti be a templates mappából
    }

    @GetMapping("/left-sidebar")
    public String leftSidebar(Model model) {
        model.addAttribute("title", "left");
        return "left-sidebar"; // Az about.html-t tölti be a templates mappából
    }

    @GetMapping("/right-sidebar")
    public String rightSidebar(Model model) {
        model.addAttribute("title", "right");
        return "right-sidebar"; // Az about.html-t tölti be a templates mappából
    }
    @GetMapping("/no-sidebar")
    public String noSidebar(Model model) {
        model.addAttribute("title", "nope");
        return "no-sidebar"; // Az about.html-t tölti be a templates mappából
    }
}

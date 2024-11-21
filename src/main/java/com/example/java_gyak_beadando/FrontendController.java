package com.example.java_gyak_beadando;

import com.example.java_gyak_beadando.Lotto_Query.LottoService;
import com.example.java_gyak_beadando.Messages.MessageDto;
import com.example.java_gyak_beadando.Messages.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FrontendController {

    private final LottoService huzasService;
    private final MessageService messageService;

    @Autowired
    public FrontendController(LottoService huzasService, MessageService messageService) {
        this.messageService = messageService;
        this.huzasService = huzasService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Főoldal");
        return "index"; // Az index.html-t tölti be a templates mappából
    }

    @GetMapping("/left-sidebar")
    public String leftSidebar(Model model) {
        model.addAttribute("title", "left");
        return "left-sidebar"; // Az left-sidebar.html-t tölti be a templates mappából
    }

    @GetMapping("/right-sidebar")
    public String rightSidebar(Model model) {
        model.addAttribute("title", "right");
        return "right-sidebar"; // Az right-sidebar.html-t tölti be a templates mappából
    }

    @GetMapping("/no-sidebar")
    public String noSidebar(Model model) {
        model.addAttribute("title", "nope");
        return "no-sidebar"; // Az no-sidebar.html-t tölti be a templates mappából
    }

    @GetMapping("/lotto-query")
    public String lotto(Model model) {
        model.addAttribute("title", "Lekérdezés");
        List<Integer> years = huzasService.getDistinctYears(); // Évek lekérdezése
        model.addAttribute("years", years); // Az évek átadása a sablonnak
        return "lotto-query"; // Az lotto.html-t tölti be a templates mappából
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Kapcsolat");
        model.addAttribute("authentication", null);
        return "contact";
    }

    @GetMapping("/messages")
    public String viewMessages(Model model) {
        model.addAttribute("title", "Üzenetek");
        List<MessageDto> messages = messageService.getLatestMessages(); // Csak a legfrissebb 10
        model.addAttribute("messages", messages);
        return "messages";
    }

}

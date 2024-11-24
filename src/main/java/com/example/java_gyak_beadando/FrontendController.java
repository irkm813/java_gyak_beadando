package com.example.java_gyak_beadando;

import com.example.java_gyak_beadando.Lotto_Query.LottoService;
import com.example.java_gyak_beadando.Messages.MessageDto;
import com.example.java_gyak_beadando.Messages.MessageService;
import com.example.java_gyak_beadando.login.User;
import com.example.java_gyak_beadando.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FrontendController {

    private final LottoService huzasService;
    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public FrontendController(LottoService huzasService, MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.huzasService = huzasService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Főoldal");
        return "index"; // Az index.html-t tölti be a templates mappából
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

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user.getUsername(), user.getEmail(),user.getPassword(), user.getConfirmPassword());
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/messages")
    public String viewMessages(Model model) {
        model.addAttribute("title", "Üzenetek");
        List<MessageDto> messages = messageService.getLatestMessages(); // Csak a legfrissebb 10
        model.addAttribute("messages", messages);
        return "messages";
    }
}

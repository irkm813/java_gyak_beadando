package com.example.java_gyak_beadando.Contact;

import com.example.java_gyak_beadando.login.User;
import com.example.java_gyak_beadando.login.UserRepository;
import com.example.java_gyak_beadando.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.Optional;

@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired private UserRepository userRepo;
    @PostMapping("/contact/submit")
    public String submitContactForm(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam("message") String message,
            Model model,
            Principal principal // Bejelentkezett felhasználó információi
    ) {
        // Szerveroldali validáció
        if ((email == null || email.isBlank()) && principal == null) {
            model.addAttribute("error", "Email cím kitöltése kötelező vendégként.");
            return "contact";
        }
        if (message == null || message.isBlank()) {
            model.addAttribute("error", "Az üzenet mező nem lehet üres.");
            return "contact";
        }
        if (message.length() > 1000) {
            model.addAttribute("error", "Az üzenet nem lehet hosszabb, mint 1000 karakter.");
            return "contact";
        }

        // Usernév meghatározása
        Integer userId = null; // Vendég alapértelmezés
        Optional<User> user = null;

        if (principal != null) {
            UserService userService = applicationContext.getBean(UserService.class);
            email = principal.getName();
            user = userRepo.findByEmail(email);
            userId = user.get().getId();
        }

        // Adatbázis mentés
        boolean success = contactService.saveMessage(email, message, userId);

        if (success) {
            model.addAttribute("success", "Az üzenet sikeresen elküldve.");
        } else {
            model.addAttribute("error", "Hiba történt az üzenet mentésekor.");
        }

        return "contact";
    }
}

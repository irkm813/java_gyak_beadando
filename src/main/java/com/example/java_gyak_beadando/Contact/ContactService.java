package com.example.java_gyak_beadando.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public boolean saveMessage(String email, String message, Integer userId) {
        try {
            // Új ContactEntity példány létrehozása
            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setEmail(email);
            contactEntity.setMessage(message);
            contactEntity.setUserId(userId);
            contactEntity.setCreatedAt(LocalDateTime.now());

            // Adat mentése az adatbázisba
            contactRepository.save(contactEntity);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

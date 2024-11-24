package com.example.java_gyak_beadando.Messages;

import com.example.java_gyak_beadando.login.User;
import com.example.java_gyak_beadando.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired private UserRepository userRepo;
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageDto> getLatestMessages() {
        // Legfrissebb 10 üzenet lekérdezése

        Optional<User> user = null;

        return messageRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(0, 10))
                .stream()
                .map(message -> new MessageDto(
                        message.getUserId() != null ? userRepo.findByEmail(message.getEmail()).get().getUsername() : "Vendég",
                        message.getMessage(),
                        message.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}

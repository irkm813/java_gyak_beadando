package com.example.java_gyak_beadando.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageDto> getLatestMessages() {
        // Legfrissebb 10 üzenet lekérdezése
        return messageRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(0, 10))
                .stream()
                .map(message -> new MessageDto(
                        message.getUserId() != null ? "Felhasználó #" + message.getUserId() : "Vendég",
                        message.getMessage(),
                        message.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}

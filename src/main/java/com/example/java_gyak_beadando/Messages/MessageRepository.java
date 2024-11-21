package com.example.java_gyak_beadando.Messages;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    // Legfrissebb 10 üzenet lekérdezése
    List<MessageEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
}

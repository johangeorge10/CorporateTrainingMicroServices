package com.capstone.chat.repository;

import com.capstone.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findBySenderIdAndReceiverId(UUID senderId, UUID receiverId);
    List<Chat> findByReceiverIdAndSenderId(UUID receiverId, UUID senderId);
}

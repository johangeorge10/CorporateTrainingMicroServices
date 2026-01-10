package com.capstone.chat.service;

import com.capstone.chat.dto.ChatDTO;
import com.capstone.chat.entity.Chat;
import com.capstone.chat.repository.ChatRepository;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatService(ChatRepository chatRepository, SimpMessagingTemplate messagingTemplate) {
        this.chatRepository = chatRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage (ChatDTO message, Principal principal){
        // 1. Save to Database
        Chat chat = new Chat(message);
        chatRepository.save(chat);

        // 2. Send to the specific user via WebSocket
        // Destination: /user/{receiverId}/queue/messages
        messagingTemplate.convertAndSendToUser(
                message.getReceiverId().toString(),
                "/queue/messages",
                message
        );
    }

    public List<Chat> getChatHistory(
            UUID senderId,
            UUID receiverId) {
        return chatRepository.findAll().stream()
                .filter(msg ->
                        (msg.getSenderId().equals(senderId) && msg.getReceiverId().equals(receiverId)) ||
                                (msg.getSenderId().equals(receiverId) && msg.getReceiverId().equals(senderId)))
                .collect(Collectors.toList());
    }
}

package com.capstone.chat.controller;

import com.capstone.chat.dto.ChatDTO;
import com.capstone.chat.entity.Chat;
import com.capstone.chat.repository.ChatRepository;
import com.capstone.chat.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/chat")
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService service;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatService service) {
        this.messagingTemplate = messagingTemplate;
        this.service = service;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatDTO message, Principal principal) {
        service.sendMessage(message, principal);
    }

    @GetMapping("/history/{senderId}/{receiverId}")
    public List<Chat> getChatHistory(
            @PathVariable UUID senderId,
            @PathVariable UUID receiverId) {
        return service.getChatHistory(senderId, receiverId);
    }
}

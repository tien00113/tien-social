package com.tien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.tien.models.Message;

@RestController
public class RealTimeChat {

	@Autowired
	private SimpMessagingTemplate simpMessageTemplate;

	@MessageMapping("/chat/{groupId}")
	public Message sendToUser(@Payload Message message, @DestinationVariable String groupId) {
		
		simpMessageTemplate.convertAndSendToUser(groupId, "/private", message);
		

		return message;

	}
}

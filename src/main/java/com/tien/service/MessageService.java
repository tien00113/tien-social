package com.tien.service;

import java.util.List;

import com.tien.models.Chat;
import com.tien.models.Message;
import com.tien.models.User;

public interface MessageService {

	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
	
	
	
	
}

package com.tien.service;

import java.util.List;

import com.tien.models.Chat;
import com.tien.models.User;

public interface ChatService {

	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
}

package com.tien.request;

import com.tien.models.Chat;
import com.tien.models.User;

public class CreateChatRequest {
	
	private Integer userId;
	
	public CreateChatRequest() {
		// TODO Auto-generated constructor stub
	}

	public CreateChatRequest(Integer userId) {
		super();
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}

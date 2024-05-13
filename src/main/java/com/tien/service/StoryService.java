package com.tien.service;

import java.util.List;

import com.tien.models.Story;
import com.tien.models.User;

public interface StoryService {

	public Story createStory (Story story,User  user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;
}

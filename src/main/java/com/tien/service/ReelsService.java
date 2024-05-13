package com.tien.service;

import java.util.List;

import com.tien.models.Reels;
import com.tien.models.User;

public interface ReelsService {

	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReel(Integer userId) throws Exception;
}

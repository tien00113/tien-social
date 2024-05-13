package com.tien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tien.models.Reels;
import com.tien.models.User;
import com.tien.repository.ReelsRepository;

@Service
public class ReelsServiceImplementation implements ReelsService{
	
	@Autowired
	private ReelsRepository reelsRepository;

	@Autowired
	private UserService userService;
	
	@Override
	public Reels createReel(Reels reel, User user) {
		Reels createReel = new Reels();
		
		createReel.setTitle(reel.getTitle());
		createReel.setUser(user);
		createReel.setVideo(reel.getVideo());
		
		
		return reelsRepository.save(createReel);
	}

	@Override
	public List<Reels> findAllReels() {
		// TODO Auto-generated method stub
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUsersReel(Integer userId) throws Exception {
		userService.findUserById(userId);
		
		
		return reelsRepository.findByUserId(userId);
	}

}

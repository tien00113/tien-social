package com.tien.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tien.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels, Integer>{

	public List<Reels> findByUserId(Integer userId);
	
}

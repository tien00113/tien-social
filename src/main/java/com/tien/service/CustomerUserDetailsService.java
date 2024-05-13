package com.tien.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tien.models.User;
import com.tien.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("không tìm thấy người dùng có email"+username);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authorities);
	}

}

package com.tien.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tien.config.JwtProvider;
import com.tien.exceptions.UserException;
import com.tien.models.User;
import com.tien.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());

		User savedUser = userRepository.save(newUser);
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get();
		}

		throw new UserException("người dùng không tồn tại với id " + userId);
	}

	@Override
	public User findUserByEmail(String email) {

		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws UserException {

		User reqUser = findUserById(reqUserId);

		User user2 = findUserById(userId2);

		if (user2.getFollowers().contains(reqUser.getId())) {
			user2.getFollowers().remove(reqUser.getId());
			reqUser.getFollowings().remove(user2.getId());
		} else {
			user2.getFollowers().add(reqUser.getId());
			reqUser.getFollowings().add(user2.getId());
		}
		
//		user2.getFollowers().add(reqUser.getId());
//		reqUser.getFollowings().add(user2.getId());

		userRepository.save(reqUser);
		userRepository.save(user2);

		return reqUser;
		
	}

	@Override
	public User updateUser(User user, Integer userId) throws UserException {
		Optional<User> user1 = userRepository.findById(userId);

		if (user1.isEmpty()) {
			throw new UserException("người dùng không tồn tại với id " + userId);
		}

		User oldUser = user1.get();

		if (user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			oldUser.setLastName(user.getLastName());
		}
		if (user.getEmail() != null) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getGender()!=null) {
			oldUser.setGender(user.getGender());
		}

		User updateUser = userRepository.save(oldUser);
		return updateUser;
	}
	
	@Override
	public User patchUpdateUserImage(User user, Integer userId) throws UserException {
		Optional<User> newUser = userRepository.findById(userId);

		if (newUser.isEmpty()) {
			throw new UserException("người dùng không tồn tại với id " + userId);
		}
		
		User oldUser = newUser.get();
		if (user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			oldUser.setLastName(user.getLastName());
		}
		
		if(user.getAvatarImage() != null) {
			oldUser.setAvatarImage(user.getAvatarImage());
		}
		
		if(user.getCoverImage() != null) {
			oldUser.setCoverImage(user.getCoverImage());
		}
		
		User updateUser = userRepository.save(oldUser);
		
		return updateUser;
	}

	@Override
	public List<User> searchUser(String query) {
		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		
		return user;
	}

}

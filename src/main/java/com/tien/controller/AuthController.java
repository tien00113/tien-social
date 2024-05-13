package com.tien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tien.config.JwtProvider;
import com.tien.models.User;
import com.tien.repository.UserRepository;
import com.tien.request.LoginRequest;
import com.tien.response.AuthResponse;
import com.tien.service.CustomerUserDetailsService;
import com.tien.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;

	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {

		User isExist = userRepository.findByEmail(user.getEmail());

		if (isExist != null) {
			throw new Exception("Email này đã được sử dụng với tài khoản khác");
		}

		User newUser = new User();

		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userRepository.save(newUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());

		String token = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse(token, "Đăng kí thành công");

		return res;
	}

	//auth/signin
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		String token = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse(token, "Đăng nhập thành công");

		return res;
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

		if (userDetails == null) {
			throw new BadCredentialsException("Tên sử dụng không hợp lệ");
		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Sai mật khẩu");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

	@GetMapping("/logout")
	public String logout(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null){
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/signin";
	}

}

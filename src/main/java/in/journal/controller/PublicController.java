package in.journal.controller;

import in.journal.service.CustomUserDetailServiceImp;
import in.journal.utils.JwtUtils;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.journal.entity.User;
import in.journal.service.UserService;

@RestController
@RequestMapping("/public")

public class PublicController {

	@Autowired
	private UserService userservice;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailServiceImp userDetailServiceImp;
	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping("/health-check")
	public String healthCheck() {
		return "OK";
	}

	@PostMapping("/create-user")
	public User signUp(@RequestBody User user) {
		return userservice._saveUser(user);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
 		try{
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

			UserDetails userDetails = userDetailServiceImp.loadUserByUsername(user.getUsername());
			String jwt = jwtUtils.generateToken(userDetails.getUsername());
			return ResponseEntity.ok().body(jwt);
		}catch (Exception e) {
			e.printStackTrace(); // <-- add this
			return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
		}
//		return userservice._saveUser(user);
	}

}

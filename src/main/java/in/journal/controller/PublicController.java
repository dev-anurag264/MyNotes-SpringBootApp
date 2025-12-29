package in.journal.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/health-check")
	public String healthCheck() {
		return "OK";
	}
	
	@PostMapping("/create-user")
	public User createUser(@RequestBody User user) {
		return userservice._saveUser(user);
	}
	
}

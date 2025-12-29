package in.journal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.journal.entity.User;
import in.journal.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/allusers")
	public ResponseEntity<?> getAllUser(){
		List<User> all= userservice.getAllUser();
		
		if(all!=null && !all.isEmpty()) {
			return ResponseEntity.ok().body(all);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/createadmin")
	public void  createAdmin(@RequestBody User user){
		userservice._saveAdmin(user);
	}
	

}

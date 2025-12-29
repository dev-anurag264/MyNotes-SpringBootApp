package in.journal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.journal.entity.User;
import in.journal.repository.UserRepository;
import in.journal.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userservice;

	@Autowired
	private  UserRepository userrepository;
	
//	@PostMapping("/user")
//	public User createUser(@RequestBody User user) {
//		return userservice.createUser(user);
//	}
//		
	
	@PostMapping("/newUser")
	public void registerUser(@RequestBody User user) {
		userservice.saveExisting(user);
	}
	
	
	@GetMapping("/alluser")
	public List<User> getUser() {
		return userservice.getAllUser();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		 User user=userservice.getUserById(id).orElse(null);
		 if(user!=null) {
			 return ResponseEntity.ok().body(user);
		 }
		 else {
			 return ResponseEntity.notFound().build();
		 }
	}
	//Edit here -> TODO
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user){
		// Since user is already logged in, we hit the put request with  the users name and password" using ->
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String username=authentication.getName();
		User userinDb=userservice.findByUsername(username);
		
			userinDb.setUsername(user.getUsername());
			userinDb.setPassword(user.getPassword());
			userservice.saveExisting(userinDb);
			return ResponseEntity.ok().build();
	}
		
//	@DeleteMapping("/user/{id}")
//	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
//		userservice.removeUser(id);
//		return ResponseEntity.noContent().build();
//		
//	}
//	
	@DeleteMapping
	public ResponseEntity<Void> deleteUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userrepository.deleteByUsername(auth.getName());
		return ResponseEntity.noContent().build();
		
	}
	
}

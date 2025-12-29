package in.journal.service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.journal.entity.User;
import in.journal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userrepo;
	
	private static final PasswordEncoder passwordencoder = new BCryptPasswordEncoder();
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User _saveUser(User user) {
		try {
			user.setPassword(passwordencoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList("USER"));
			return userrepo.save(user);
		}
		catch(Exception e) {
			logger.info("Logger says info not saved for the user");
			return null;
		}
		
	}
	
	@Override
	public User _saveAdmin(User user) {
		user.setPassword(passwordencoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER","ADMIN"));
		return userrepo.save(user);
	}
	

	@Override
	public User saveExisting(User user) {
		return userrepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userrepo.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userrepo.findById(id);
	}

//	@Override
//	public void removeUser(Long id) {
//		userrepo.deleteById(id);
//		
//	}

	@Override
	
	public User findByUsername(String username) {
		return userrepo.findByUsername(username);
	}

	
	@Override
	@Transactional
	public void deletebyUsername(String username) {
		userrepo.deleteByUsername(username);
		
	}
	
}

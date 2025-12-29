package in.journal.service;

import java.util.List;
import java.util.Optional;

import in.journal.entity.User;

public interface UserService {
	public User _saveUser(User user);
	public User _saveAdmin(User user);
	public User saveExisting(User user);
	public List<User> getAllUser();
	public Optional<User> getUserById(Long id);
	public User findByUsername(String username);
//	public void removeUser(Long id);
	public void deletebyUsername(String username);
}

package in.journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.journal.entity.User;
import in.journal.repository.UserRepository;

@Service
public class CustomUserDetailServiceImp implements UserDetailsService {
	
    private final UserRepository userrepo;

    public CustomUserDetailServiceImp(UserRepository userrepo) {
        this.userrepo = userrepo;
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userrepo.findByUsername(username);
		if(user!=null) {
			UserDetails userdetails = org.springframework.security.core.userdetails.User.builder().
			username(user.getUsername()).
			password(user.getPassword()).
			roles(user.getRoles().toArray(new String[0])).
			build();
			
			return userdetails;
		}else {
			throw new UsernameNotFoundException("Username not found with the name"+username);
		}

	}
	
}

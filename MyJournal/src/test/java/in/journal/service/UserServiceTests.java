package in.journal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import in.journal.entity.User;
import in.journal.repository.UserRepository;
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
	
	 @Mock
	    private UserRepository userRepo;

	    @InjectMocks
	    private CustomUserDetailServiceImp userService;

	    @Test
	    void loadUserByUsername_whenUserExists_shouldReturnUserDetails() {

	        // Arrange (Given)
	        User user = new User();
	        user.setUsername("anurag");
	        user.setPassword("encodedPassword");
	        user.setRoles(List.of("USER"));

	        when(userRepo.findByUsername("anurag"))
	                .thenReturn(user);

	        // Act (When)
	        UserDetails userDetails =
	                userService.loadUserByUsername("anurag");

	        // Assert (Then)
	        assertNotNull(userDetails);
	        assertEquals("anurag", userDetails.getUsername());
	        assertEquals("encodedPassword", userDetails.getPassword());

	        assertTrue(
	            userDetails.getAuthorities()
	                .stream()
	                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"))
	        );

	        verify(userRepo).findByUsername("anurag");
	    }


	
	
}

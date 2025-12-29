package in.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.journal.entity.User;



public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	
    void deleteByUsername( String username);
}

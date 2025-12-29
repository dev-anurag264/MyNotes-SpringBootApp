package in.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.journal.entity.Journal;
import in.journal.entity.User;

import java.util.List;
import java.util.Optional;


public interface JournalRepository extends JpaRepository<Journal, Integer>{
	Optional<Journal> findByIdAndUserUsername(int id, String username);
}

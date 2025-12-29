package in.journal.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.journal.entity.Journal;
import in.journal.entity.User;
import in.journal.repository.JournalRepository;

@Service
public class JournalService implements JournalServices{
	
	@Autowired
	private JournalRepository jrepository;
	@Autowired
	private UserService userservice;
	
	@Override
	@Transactional
	public void createEntry(Journal journal, String userName) {
	    User user = userservice.findByUsername(userName);
	    if (user == null) {
	        throw new RuntimeException("User not found");
	    }
	    journal.setUser(user);

	    user.getJournal().add(journal);

	    jrepository.save(journal); 
	 
		
	}
	@Override
	public void createEntry(Journal journal) {
		jrepository.save(journal);
	}

	@Override
	public List<Journal> getAllEntries() {
		return jrepository.findAll();
	}

	@Override
	public Optional<Journal> getEntryByID(int id) {
		return jrepository.findById(id);
	}

	@Transactional
	@Override
	public void removeEntry(int id, String username) {
		boolean removed=false;
		try {
		    User user = userservice.findByUsername(username);
		    if (user == null) {
		        throw new RuntimeException("User not found");
		    }

		    Journal journal = jrepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Journal not found"));

		    // Ownership check (CRITICAL)
		    if (!journal.getUser().getUsername().equals(username)) {
		        throw new RuntimeException("Unauthorized delete attempt");
		    }
		    removed = true;
		    jrepository.delete(journal);
		}catch(RuntimeException e) {
			System.out.println("Deletion Failed..!!");
			throw e;
		}
	
	}


	@Override
	public Journal updateEntry(int id, Journal journal, String username) {
		 Journal updatedJournal=jrepository.findById(id).orElseThrow(
				 ()->new RuntimeException("No Entry found with the id:"+id));
		
		 if (!updatedJournal.getUser().getUsername().equals(username)) {
		        throw new RuntimeException("You are not allowed to update this journal");
		    }
			updatedJournal.setTitle(journal.getTitle());
			updatedJournal.setContent(journal.getContent());
			
			return jrepository.save(updatedJournal);
	}
	
	
	
	@Override
	public Optional<Journal> getJournalByIdAndUsername(int id, String username) {
	    return jrepository.findByIdAndUserUsername(id, username);
	}
	
	
}

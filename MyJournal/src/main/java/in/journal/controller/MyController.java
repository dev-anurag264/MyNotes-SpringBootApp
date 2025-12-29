package in.journal.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.journal.entity.Journal;
import in.journal.entity.User;
import in.journal.service.JournalService;
import in.journal.service.UserService;

@RestController
@RequestMapping("/myjournal")
public class MyController {

	@Autowired
	private JournalService jservice;

	@Autowired
	private UserService userservice;

	@PostMapping
	public ResponseEntity<Journal> createEntries(@RequestBody Journal journal) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			jservice.createEntry(journal, username);
			return ResponseEntity.ok().body(journal);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<?> getEntryofUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userservice.findByUsername(username);
		List<Journal> entries = user.getJournal();

		if (entries != null && !entries.isEmpty()) {
			return ResponseEntity.ok().body(entries);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Journal> getEntrybyId(@PathVariable int id) {
//		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//		String username=authentication.getName();
//		User user =userservice.findByUsername(username);
//		
//		List<Journal> userEntries = user.getJournal().stream().filter(x -> Objects.equals(x.getId(), id)).collect(Collectors.toList());
//		
//		if(userEntries!=null) {
//			Journal journal=jservice.getEntryByID(id).orElse(null);
//			 
//			 if(journal!=null) {
//				 return ResponseEntity.ok().body(journal);
//			 }else {
//				 return ResponseEntity.notFound().build();
//			 }
//		}
//		return ResponseEntity.badRequest().build();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		return jservice.getJournalByIdAndUsername(id, username).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Journal> updateEntry(@PathVariable int id, @RequestBody Journal journal) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			Journal newEntry = jservice.updateEntry(id, journal, name);
			return ResponseEntity.ok(newEntry);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEntry(@PathVariable int id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		jservice.removeEntry(id, username);
		return ResponseEntity.noContent().build();
	}

}

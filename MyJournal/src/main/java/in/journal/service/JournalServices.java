package in.journal.service;

import java.util.List;
import java.util.Optional;

import in.journal.entity.Journal;

public interface JournalServices {
	public void createEntry(Journal journal, String username);
	public void createEntry(Journal journal);
	public List<Journal> getAllEntries();
	public Optional<Journal> getEntryByID(int id);
	public void removeEntry(int id, String username);
	public Journal updateEntry(int id, Journal journal,String username);
	public Optional<Journal> getJournalByIdAndUsername(int id, String username);
	
}

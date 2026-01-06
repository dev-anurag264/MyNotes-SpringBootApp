package in.journal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.journal.config.App_Config;

public interface ConfigApp extends JpaRepository<App_Config, Long>{
	 List<App_Config> findAll();
}

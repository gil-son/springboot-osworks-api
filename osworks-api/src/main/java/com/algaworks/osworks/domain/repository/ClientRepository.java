package com.algaworks.osworks.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.algaworks.osworks.domain.model.Client;

@Repository // Component from Spring
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	List<Client> findByName(String name);
	List<Client> findByNameContaining(String name);
}

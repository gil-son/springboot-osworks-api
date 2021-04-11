package com.algaworks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.repository.ClientRepository;




@RestController
@RequestMapping("/clients")
public class ClientController {

	
	@Autowired // Instantiate the ClientRepository here 
	private ClientRepository clientRepository;
	
	@GetMapping // @GetMapping("clients") // RequestMapping care about it
	public List<Client> list(){    // JPQL from JPA
		   return clientRepository.findAll(); // A method that comes from JPA
		// return clientRepository.findByName("Aaa");
		// return clientRepository.findByNameContaining("B");
	}
	
	@GetMapping("/{clientId}") // @GetMapping("/clients/{clientId}")
	public ResponseEntity<Client> search(@PathVariable Long clientId) {
		Optional<Client> client = clientRepository.findById(clientId);
		
		if(client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client add(@Valid @RequestBody Client client) { // @Valid to able Validation from Client extends JPA @RequestBody say that is an object client
		return clientRepository.save(client);
	}
	
	
	
	
	@PutMapping("/{clientId}")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Client> update(@PathVariable Long clientId,
			@RequestBody Client client) {
		
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		client.setId(clientId); // This ensure that is update and not create another user
		// clientRepository.save(client);
		
		client = clientRepository.save(client);
		
		return ResponseEntity.ok(client);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> remover(@PathVariable Long clientId){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		clientRepository.deleteById(clientId);
		
		return ResponseEntity.noContent().build();
	}
	
	/*
		@GetMapping("clients")
		public List<Client> list() {
		var c1 = new Client(); c1.setId(1L); c1.setName("Aaa");c1.setEmail("aaa@gmail.com");c1.setTelephone("3333-3333");
		var c2 = new Client(); c2.setId(2L); c2.setName("Bbb");c2.setEmail("bbb@gmail.com");c2.setTelephone("4444-4444");
		return Arrays.asList(c1,c2);
		}
	*/
}

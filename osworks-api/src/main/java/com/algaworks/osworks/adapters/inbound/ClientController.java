package com.algaworks.osworks.adapters.inbound;


import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.adapters.outbound.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<Client> add(@Valid @RequestBody Client client) { // @Valid to able Validation from Client extends JPA @RequestBody say that is an object client
		return ResponseEntity.ok(clientRepository.save(client));
	}

	@PutMapping("/{clientId}")
	public ResponseEntity<Client> update(@PathVariable Long clientId,
			@RequestBody Client client) {
		
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		client.setId(clientId); // This ensure that is updated and not created another user
		
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

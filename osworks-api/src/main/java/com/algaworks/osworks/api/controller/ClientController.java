package com.algaworks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Client;




@RestController
public class ClientController {
	
	@GetMapping("clients")
	public List<Client> list() {
		var c1 = new Client(); c1.setId(1L); c1.setName("Aaa");c1.setEmail("aaa@gmail.com");c1.setTelephone("3333-3333");
		var c2 = new Client(); c2.setId(2L); c2.setName("Bbb");c2.setEmail("bbb@gmail.com");c2.setTelephone("4444-4444");

		return Arrays.asList(c1,c2);
	}
}

package com.algawoks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algawoks.osworks.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Alex");
		cliente1.setTelefone("16 99999-9999");
		cliente1.setEmail("alex@alex.com.br");
			
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Felipe");
		cliente2.setTelefone("16 99229-9599");
		cliente2.setEmail("felipe@felipe.com.br");		
		
		return Arrays.asList(cliente1, cliente2);
	}

}

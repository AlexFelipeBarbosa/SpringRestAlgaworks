package com.algawoks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.algawoks.osworks.domain.model.Cliente;
import com.algawoks.osworks.domain.repository.ClienteRepository;
import com.algawoks.osworks.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroCliente;

	// Busca todos os clientes
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
		// return clienteRepository.findByNome("João da Silva"); // nome completo
		// return clienteRepository.findByNomeContaining("a"); // utilizando o like
	}

	// Busca o cliente pelo codigo
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		// Verifica se existe o cliente
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get()); // retorna 200 OK
		}

		return ResponseEntity.notFound().build(); // retorna 404 NOT FOUND
	}

	// Cadastro de Cliente
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return cadastroCliente.salvar(cliente);
	}

	// Atualizar os dados do Cliente
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId); // Para setar o ID que não foi passado na requisição
		cliente = cadastroCliente.salvar(cliente);

		return ResponseEntity.ok(cliente);
	}

	// Excluir um Cliente
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		cadastroCliente.excluir(clienteId);

		return ResponseEntity.noContent().build();
	}

}

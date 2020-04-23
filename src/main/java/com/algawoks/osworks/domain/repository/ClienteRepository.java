package com.algawoks.osworks.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algawoks.osworks.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome); // busca por nome completo
	List<Cliente> findByNomeContaining(String nome); // mesma coisa do Like do BD
	Cliente findByEmail(String email);
	
}

package br.unisul.aula.mscliente.dto;

import br.unisul.aula.mscliente.modelo.Cliente;

public class ClientePorCidadeDTO {

	public Long id;
	public String nome;
	
	public ClientePorCidadeDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}

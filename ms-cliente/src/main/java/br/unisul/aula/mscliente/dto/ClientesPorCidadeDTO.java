package br.unisul.aula.mscliente.dto;

import java.util.List;

public class ClientesPorCidadeDTO {
	
	public String cidade;
	public String uf;
	public List<ClientePorCidadeDTO> clientes;
	
	public ClientesPorCidadeDTO(EnderecoDTO endereco, List<ClientePorCidadeDTO> clientes) {
		this.cidade = endereco.getCidade();
		this.uf = endereco.getUf();
		this.clientes = clientes;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<ClientePorCidadeDTO> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClientePorCidadeDTO> clientes) {
		this.clientes = clientes;
	}
	
	

}

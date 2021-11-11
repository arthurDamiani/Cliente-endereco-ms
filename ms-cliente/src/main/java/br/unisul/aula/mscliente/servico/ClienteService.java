package br.unisul.aula.mscliente.servico;

import br.unisul.aula.mscliente.dto.ClienteDTO;
import br.unisul.aula.mscliente.dto.ClientePorCidadeDTO;
import br.unisul.aula.mscliente.dto.ClientesPorCidadeDTO;
import br.unisul.aula.mscliente.dto.EnderecoDTO;
import br.unisul.aula.mscliente.modelo.Cliente;
import br.unisul.aula.mscliente.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
/*

    @Autowired
    private RestTemplate clienteRaimbow;
*/

    @Autowired
    private EnderecoClient enderecoClient;

    public List<ClienteDTO> listarTodos() {
        List<ClienteDTO> dtos = new ArrayList<>();
        for (Cliente cliente: clienteRepository.findAll()){
            EnderecoDTO enderecoDTO = buscarEnderecoPorID(cliente.getEndereco_id());
            ClienteDTO clienteDTO = new ClienteDTO(cliente, enderecoDTO);
            dtos.add(clienteDTO);
        }
        return dtos;
    }
    public ClientesPorCidadeDTO listarClientesPorCidade(String cidade) {
    	List<ClientePorCidadeDTO> clientes = new ArrayList<>();
    	EnderecoDTO endereco = buscarEnderecoPorCidade(cidade);
    	for(Cliente cliente: clienteRepository.findAll()) {
    		if(cliente.getEndereco_id() == endereco.getId()) {
    			ClientePorCidadeDTO dto = new ClientePorCidadeDTO(cliente);
    			clientes.add(dto);
    		}
    	}
    	return new ClientesPorCidadeDTO(endereco, clientes);
    }
    private EnderecoDTO buscarEnderecoPorID(Long id){
        return enderecoClient.buscarPorId(id);
    }
    private EnderecoDTO buscarEnderecoPorCep(Integer cep){
        return enderecoClient.buscarPorCep(cep);
    }
    private EnderecoDTO buscarEnderecoPorCidade(String cidade){
        return enderecoClient.buscarPorCidade(cidade);
    }
/*

    private EnderecoDTO buscarEnderecoPorID(Long id) {
        ResponseEntity<EnderecoDTO> exchange = clienteRaimbow.exchange("http://endereco/end/id/" + id,
                HttpMethod.GET, null, EnderecoDTO.class);
        return exchange.getBody();
    }
    private EnderecoDTO buscarEnderecoPorCep(Integer cep) {
        ResponseEntity<EnderecoDTO> exchange = clienteRaimbow.exchange("http://endereco/end/" + cep,
                HttpMethod.GET, null, EnderecoDTO.class);
        return exchange.getBody();
    }
*/

    public void registrarCliente(ClienteDTO dto) {
        dto.setEndereco(buscarEnderecoPorCep(dto.getCep()));
        Cliente cliente = dto.converterParaCliente();
        clienteRepository.save(cliente);
    }
}

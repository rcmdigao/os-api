package br.com.os.services;

import br.com.os.domain.Cliente;
import br.com.os.domain.Pessoa;
import br.com.os.dtos.ClienteDTO;
import br.com.os.exceptions.DataIntegratyViolationException;
import br.com.os.exceptions.ObjectNotFoundException;
import br.com.os.repositories.ClienteRepository;
import br.com.os.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    ///Todo Listar por ID
    public Cliente findById(Integer id){
        // Procurar um Cliente na base de dados
        Optional<Cliente> obj = repository.findById(id);
        // Retornando o objeto, caso não exista nada lança a excecao customizada atraves da classe ObjectNotFoundException
        return obj.orElseThrow(()-> new ObjectNotFoundException(
                "Objeto não encontrado!, id: " + id + ", Tipo:" + Cliente.class.getName()));
    }

    ///Todo Listar todos
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    ///Todo Novo cadastro
    public Cliente salvar(ClienteDTO dto){

        if (findByCPF(dto) != null){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        return repository.save(new Cliente(null, dto.getNome(), dto.getCpf(), dto.getTelefone()));
    }

    ///Todo Atualizaçao do registro
    public Cliente update(Integer id,  ClienteDTO dto) {

        Cliente cliente = findById(id);

        // Validando se o cpf já existe e verificar se pertence ao id informado
        if (findByCPF(dto) != null && findByCPF(dto).getId() != id){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setTelefone(dto.getTelefone());

        return repository.save(cliente);
    }

    ///Todo Excludao do registro
    public void delete(Integer id) {
        // Verificar se o  id existe
        Cliente cliente = findById(id);

        // Verificando se o Cliente possui OS em seu poder
        if (cliente.getList().size() > 0){
            throw new DataIntegratyViolationException("Cliente que possui Ordens de Serviço em seu nome, não pode ser excluido");
        }
        repository.deleteById(id);
    }

    ///Todo Verificando se já possui CPF na base de dados
    private Pessoa findByCPF(ClienteDTO dto){
        Pessoa cliente = pessoaRepository.findByCPF(dto.getCpf());
        if (cliente != null){
            return cliente;
        }
        return null;
    }

}

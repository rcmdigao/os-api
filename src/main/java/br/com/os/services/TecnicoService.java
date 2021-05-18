package br.com.os.services;

import br.com.os.domain.Pessoa;
import br.com.os.domain.Tecnico;
import br.com.os.dtos.TecnicoDTO;
import br.com.os.exceptions.DataIntegratyViolationException;
import br.com.os.exceptions.ObjectNotFoundException;
import br.com.os.repositories.PessoaRepository;
import br.com.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    ///Todo Listar por ID
    public Tecnico findById(Integer id){
        // Procurar um tecnico na base de dados
        Optional<Tecnico> obj = repository.findById(id);
        // Retornando o objeto, caso não exista nada lança a excecao customizada atraves da classe ObjectNotFoundException
        return obj.orElseThrow(()-> new ObjectNotFoundException(
                "Objeto não encontrado!, id: " + id + ", Tipo:" + Tecnico.class.getName()));
    }

    ///Todo Listar todos
    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    ///Todo Novo cadastro
    public Tecnico salvar(TecnicoDTO dto){

        if (findByCPF(dto) != null){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        return repository.save(new Tecnico(null, dto.getNome(), dto.getCpf(), dto.getTelefone()));
    }

    ///Todo Atualizaçao do registro
    public Tecnico update(Integer id,  TecnicoDTO dto) {

        Tecnico tecnico = findById(id);

        // Validando se o cpf já existe e verificar se pertence ao id informado
        if (findByCPF(dto) != null && findByCPF(dto).getId() != id){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        tecnico.setNome(dto.getNome());
        tecnico.setCpf(dto.getCpf());
        tecnico.setTelefone(dto.getTelefone());

        return repository.save(tecnico);
    }

    ///Todo Excludao do registro
    public void delete(Integer id) {
        // Verificar se o  id existe
        Tecnico tecnico = findById(id);

        // Verificando se o Tecnico possui OS em seu poder
        if (tecnico.getList().size() > 0){
            throw new DataIntegratyViolationException("O técnico possui OS em seu nome, não pode ser excluido");
        }
        repository.deleteById(id);
    }

    ///Todo Verificando se já possui CPF na base de dados
    private Pessoa findByCPF(TecnicoDTO dto){
        Pessoa tecnico = pessoaRepository.findByCPF(dto.getCpf());
        if (tecnico != null){
            return tecnico;
        }
        return null;
    }



}

package br.com.os.services;

import br.com.os.domain.Cliente;
import br.com.os.domain.OS;
import br.com.os.domain.Tecnico;
import br.com.os.domain.enuns.Prioridade;
import br.com.os.domain.enuns.Status;
import br.com.os.dtos.OsDTO;
import br.com.os.exceptions.ObjectNotFoundException;
import br.com.os.repositories.OSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository osRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    ///Todo Busca pelo id
    public OS findById(Integer id){
        Optional<OS> obj = osRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
    }

    ///Todo Listar todos
    public List<OS> findAll() {
        return osRepository.findAll();
    }

    ///Todo Novo Cadastro
    public OS salvar(OsDTO dto) {
        return fromDTO(dto);
    }

    ///Todo Atualizaçao
    public OS update(OsDTO dto) {
        // Validando
        findById(dto.getId());
        // O Save, sabe que tem um ID então, já atualiza
        return fromDTO(dto);
    }

    ///Todo Metodo Post/Put
    private OS fromDTO(OsDTO dto){
        Tecnico tecnico = tecnicoService.findById(dto.getTecnico()); // buscando o tecnico
        Cliente cliente = clienteService.findById(dto.getCliente()); // buscando o cliente

        OS os = new OS();
        os.setId(dto.getId());
        os.setObservacoes(dto.getObservacoes());
        os.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
        os.setStatus(Status.toEnum(dto.getStatus()));
        os.setTecnico(tecnico);
        os.setCliente(cliente);

        // Validando a ordem de servico: Se o status for igual a 2 (Encerrado)
        if (os.getStatus().getCod().equals(2)){
            os.setDataFechamento(LocalDateTime.now());
        }

        return osRepository.save(os);
    }

}

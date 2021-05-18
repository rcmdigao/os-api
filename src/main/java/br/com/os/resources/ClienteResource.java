package br.com.os.resources;

import br.com.os.domain.Cliente;
import br.com.os.dtos.ClienteDTO;
import br.com.os.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    ///Todo Listar todos por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findByID(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);
        ClienteDTO dto = new ClienteDTO(cliente);
        return ResponseEntity.ok().body(dto);
    }

    ///Todo Listando todos
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> dtoList = clienteService.findAll()
                .stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    ///Todo Novo Cadastro
    @PostMapping
    public ResponseEntity<ClienteDTO> salvar(@Valid @RequestBody ClienteDTO dto) {

        // Salvando o objeto tecnico
        Cliente cliente = clienteService.salvar(dto);

        // Criando a URI de acesso
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();

        // retorna a URI
        return ResponseEntity.created(uri).build();
    }

    ///Todo Atualizaçao de registro
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO dto) {
        ClienteDTO cliente = new ClienteDTO(clienteService.update(id, dto));
        return ResponseEntity.ok().body(cliente);
    }
    
    ///Todo Apagar tecnico
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return  ResponseEntity.noContent().build();
    }
}




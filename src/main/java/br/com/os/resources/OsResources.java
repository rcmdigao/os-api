package br.com.os.resources;

import br.com.os.domain.OS;
import br.com.os.dtos.OsDTO;
import br.com.os.services.OsService;
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
@RequestMapping(value = "/ordens")
public class OsResources {

    @Autowired
    private OsService service;

    ///Todo Listar todos por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<OsDTO> findById(@PathVariable Integer id){
        OsDTO dto = new OsDTO(service.findById(id));
        return ResponseEntity.ok().body(dto);
    }

    ///Todo Listar todos
    @GetMapping
    public ResponseEntity<List<OsDTO>> findAll(){
        List<OsDTO> lista = service.findAll().stream()
                .map(obj -> new OsDTO(obj)).collect(Collectors.toList());
            return ResponseEntity.ok().body(lista);

    }

    ///Todo Novo cadastro
    @PostMapping
    public ResponseEntity<OsDTO> salvar(@Valid @RequestBody OsDTO dto){
        dto = new OsDTO(service.salvar(dto));

        // Criando a URI de acesso
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        // retorna a URI
        return ResponseEntity.created(uri).build();
    }

    ///Todo Atualizacao
    // Atualizacao de outra maneira, sem mandar o id.
    @PutMapping
    public ResponseEntity<OsDTO> update(@Valid @RequestBody OsDTO dto){
        dto = new OsDTO(service.update(dto));
        return ResponseEntity.ok().body(dto);
    }


}

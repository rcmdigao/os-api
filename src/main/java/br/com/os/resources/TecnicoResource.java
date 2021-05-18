package br.com.os.resources;

import br.com.os.domain.Tecnico;
import br.com.os.dtos.TecnicoDTO;
import br.com.os.services.TecnicoService;
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
@RequestMapping(value = "/tecnicos")

public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    ///Todo Listar todos por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findByID(@PathVariable Integer id) {
        Tecnico obj = tecnicoService.findById(id);
        TecnicoDTO objDto = new TecnicoDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    ///Todo Listando todos
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {

        List<TecnicoDTO> dtoList = tecnicoService.findAll()
                .stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    ///Todo Novo Cadastro
    @PostMapping
    public ResponseEntity<TecnicoDTO> salvar(@Valid @RequestBody TecnicoDTO dto) {

        // Salvando o objeto tecnico
        Tecnico tecnico = tecnicoService.salvar(dto);

        // Criando a URI de acesso
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tecnico.getId()).toUri();

        // retorna a URI
        return ResponseEntity.created(uri).build();
    }

    ///Todo Atualizaçao de registro
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO dto) {
        TecnicoDTO tecnico = new TecnicoDTO(tecnicoService.update(id, dto));
        return ResponseEntity.ok().body(tecnico);
    }

    ///Todo Apagar tecnico
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        tecnicoService.delete(id);
        return  ResponseEntity.noContent().build();
    }



}




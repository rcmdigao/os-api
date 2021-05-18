package br.com.os.services;

import br.com.os.domain.Cliente;
import br.com.os.domain.OS;
import br.com.os.domain.Tecnico;
import br.com.os.domain.enuns.Prioridade;
import br.com.os.domain.enuns.Status;
import br.com.os.repositories.ClienteRepository;
import br.com.os.repositories.OSRepository;
import br.com.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OSRepository osRepository;

    public void instanciaDB() {
        Tecnico t1 = new Tecnico(null, "Rodrigo", "929.857.691-91", "(61) 3303-2013");
        Tecnico t2 = new Tecnico(null, "Marina", "961.298.780-79", "(61) 3303-2014");

        Cliente c1 = new Cliente(null, "Raimundo", "598.508.200-80", "(61) 3303-2314");

        OS os1 = new OS(null, Prioridade.ALTA, "Teste Cliente Os", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1, t2));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }

}

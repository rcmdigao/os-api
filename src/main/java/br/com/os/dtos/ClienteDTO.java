package br.com.os.dtos;

import br.com.os.domain.Cliente;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "O campo NOME é obrigatório")
    private String nome;

    @CPF
    @NotEmpty(message = "O campo CPF é obrigatório")
    private String cpf;

    @NotEmpty(message = "O campo TELEFONE é obrigatório")
    private String telefone;

    public ClienteDTO() {
        super();
    }

    public ClienteDTO(Cliente obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}

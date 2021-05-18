package br.com.os.dtos;

import br.com.os.domain.Tecnico;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@Getter
@Setter
public class TecnicoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "O campo NOME é obrigatório")
    private String nome;

    @CPF
    @NotEmpty(message = "O campo CPF é obrigatório")
    private String cpf;

    @NotEmpty(message = "O campo TELEFONE é obrigatório")
    private String telefone;

    public TecnicoDTO() {
        super();
    }

    public TecnicoDTO(Tecnico obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}

package br.com.os.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationError extends StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<FieldMessade> errors = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    // Metodo para adicionarmos os erros
    public void addError(String fieldName, String message){
        this.errors.add(new FieldMessade(fieldName, message));
    }

}

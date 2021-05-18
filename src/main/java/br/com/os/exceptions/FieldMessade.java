package br.com.os.exceptions;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FieldMessade implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldMane;
    private String message;

    public FieldMessade() {
        super();
    }

    public FieldMessade(String fieldMane, String message) {
        super();
        this.fieldMane = fieldMane;
        this.message = message;
    }
}

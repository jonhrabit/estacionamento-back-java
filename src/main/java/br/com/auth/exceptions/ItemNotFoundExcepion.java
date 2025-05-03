package br.com.auth.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemNotFoundExcepion extends RuntimeException {

    private String classeName;
    private long id;

    public ItemNotFoundExcepion(long id, String classe) {
        super(String.format("%s com id %d não foi encontrado.", classe, id));
        this.classeName = classe;
        this.id = id;
    }
}

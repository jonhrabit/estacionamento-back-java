package br.com.auth.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemNotFoundExcepion extends RuntimeException {

    private String classeName;
    private long id;

    public ItemNotFoundExcepion(long id, String classe) {
        super("%s com id %d não foi encontrado.".formatted(classe, id));
        this.classeName = classe;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClasseName() {
        return classeName;
    }

    public void setClasseName(String classeName) {
        this.classeName = classeName;
    }
}

package ex_1;

import java.io.Serializable;

public class Author extends Human implements Serializable {

    private static final long serialVersionUID = 1L;

    Author(String name, String surname) {
        super(name,surname);
    }

    @Override
    public String toString() {
        return "Имя: " + name + " Фамилия: " + surname;
    }

}

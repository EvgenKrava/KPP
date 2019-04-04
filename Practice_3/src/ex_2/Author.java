package ex_2;

import java.io.Serializable;

public class Author extends Human implements Serializable {
    private static final long serialVersionUID = -1649962461463353566L;

    Author(String name, String surname) {
        super(name,surname);
    }

    public Author() {
        super(null, null);
    }

    @Override
    public String toString() {
        return "Имя: " + name + " Фамилия: " + surname;
    }

}

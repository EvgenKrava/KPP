package ex_2;

import java.io.Serializable;

public abstract class Human implements Serializable {

    private static final long serialVersionUID = -1694422635467717772L;

    protected String name;

    protected String surname;

    Human(String name, String surname) {
        this.name=name;
        this.surname=surname;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Name: "+name+" Surname: "+surname;
    }
}

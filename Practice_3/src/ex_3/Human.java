package ex_3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Human implements Externalizable {

    Human(){
    name = null;
    surname = null;
    }

    private String name;

    private String surname;

    Human(String name, String surname) {
        this.name=name;
        this.surname=surname;
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

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(name);
        objectOutput.writeObject(surname);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        setName((String) objectInput.readObject());
        setSurname((String)objectInput.readObject());
    }
}

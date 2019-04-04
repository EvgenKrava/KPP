package ex_1;

import java.io.Serializable;
import java.util.ArrayList;

public class BookReader extends Human implements Serializable {
    private static final long serialVersionUID = 1L;

    private int registerNumber;

    private ArrayList<Book> books;

    BookReader(String name, String surname){
        super(name, surname);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname);
    }

    public void setRegisterNumber(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

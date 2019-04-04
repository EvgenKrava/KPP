package ex_3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class BookReader extends Human implements Externalizable {

    private int registerNumber;

    private ArrayList<Book> books;

    BookReader(){

    }

    BookReader(String name, String surname){
        super(name, surname);
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

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        super.writeExternal(objectOutput);
        objectOutput.write(registerNumber);
        objectOutput.write(books.size());
        for(Book b: books){
            b.writeExternal(objectOutput);
        }
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        super.readExternal(objectInput);
        registerNumber=objectInput.read();
        int size = objectInput.read();
        books = new ArrayList<>();
        for(int i=0; i < size; i++){
            Book b = new Book();
            b.readExternal(objectInput);
            books.add(b);
        }
    }
}

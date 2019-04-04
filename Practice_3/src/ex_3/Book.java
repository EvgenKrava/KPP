package ex_3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class Book implements Externalizable {

    protected String name;

    private ArrayList<Author> authors;

    private Integer year;

    private Integer number;

    Book(){
        number=0;
        name=null;
        year=1979;
        authors=new ArrayList<>();
    }

    Book(int number, String name, int year, Author... authors){
        this.authors = new ArrayList<>();
        this.number=number;
        this.name=name;
        this.year=year;
        this.authors.addAll(Arrays.asList(authors));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Author a:this.authors) {
            result.append(" ").append(a.toString());
        }
        return "№ " + number + " Название книги: " + name + " год издания: " + year + " авторы: " + result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public int getNumber() {
        return number;
    }

    public int getYear() {
        return year;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(name);
        objectOutput.write(authors.size());
        for (Author a:authors) {
            a.writeExternal(objectOutput);
        }
        objectOutput.writeObject(year);
        objectOutput.writeObject(number);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        name=(String) objectInput.readObject();
        int s = objectInput.read();
        this.authors=new ArrayList<>();
        for (int i = 0; i < s; i++) {
            Author a = new Author();
            a.readExternal(objectInput);
            this.authors.add(a);
        }
        year=(Integer) objectInput.readObject();
        number=(Integer) objectInput.readObject();

    }
}

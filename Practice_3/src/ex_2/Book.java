package ex_2;

import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable {

    private static final long serialVersionUID = -3600018573372609677L;

    protected String name;

    protected ArrayList<Author> authors;

    protected int year;

    protected int number;

    public Book(){
        name = "defaultName";
        authors = null;
        year=0;
        number=0;
        authors= new ArrayList<>();
    }

    public Book(String readObject, int readInt, int year, Author... authors) {
        name=readObject;
        this.year=year;
        number = readInt;

    }

    public String getName(){
        return name;
    }

    public int getNumber(){
        return number;
    }

    public int getYear(){
        return year;
    }

    public ArrayList<Author> getAuthors(){
        return authors;
    }

    Book(int number, String name, int year, Author... authors){
        this.authors = new ArrayList<>();
        this.number=number;
        this.name=name;
        this.year=year;
        for (Author a:authors) {
            this.authors.add(a);
        }
    }

    @Override
    public String toString() {
        String authors = "";
        for (Author a:this.authors) {
            authors+=" "+a.toString();
        }
        return "№ " + number + " Название книги: " + name + " год издания: " + year + " авторы: "+authors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthors(Author... authors) {
        for (Author a:authors) {
            this.authors.add(a);
        }
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

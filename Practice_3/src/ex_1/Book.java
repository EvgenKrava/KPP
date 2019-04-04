package ex_1;

import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String name;

    protected ArrayList<Author> authors;

    protected int year;

    protected int number;

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

}

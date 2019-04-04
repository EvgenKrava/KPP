package ex_1;

import java.io.Serializable;
import java.util.ArrayList;

public class BookStore implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;

    ArrayList<Book> books;

    BookStore(String name){
        this.name=name;
        books = new ArrayList<>();
    }

    public Book getBook(String bookName){
        for (Book b:books) {
            if (b.name.equals(bookName)) return b;
        }
        return null;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        String result="Имя книгохранилища: "+name+"\n";
        for (Book b:books) {
            result+=(b.toString()+"\n");
        }
        return result;
    }
}

package ex_2;

import java.io.Serializable;
import java.util.ArrayList;

public class Library implements Serializable {


    private static final long serialVersionUID = 1364237131488795776L;

    String name;

    ArrayList<BookStore> bookStores;

    ArrayList<BookReader> bookReaders;

    Library(String name){
        bookStores = new ArrayList<>();
        bookReaders = new ArrayList<>();
        this.name=name;
    }

    public void addBookStore(BookStore bookStore) {
        bookStores.add(bookStore);
    }

    public ArrayList<BookStore> getBookStores() {
        return bookStores;
    }

    public void addBook(BookStore bookStore, Book book){
        for (int i = 0; i<bookStores.size(); i++) {
            if(bookStores.get(i).equals(bookStore)){
                bookStores.get(i).addBook(book);
            }else {
                if (!bookStores.contains(bookStore)){
                    bookStores.add(bookStore);
                }
            }
        }
    }

    public void addBookReader(BookReader bookReader){
        this.bookReaders.add(bookReader);
    }

    @Override
    public String toString() {
        String result="Имя библиотеки: "+name+"\n";
        result+="Bookstores:\n";
        for (BookStore b: bookStores) {
            result+=b.toString();
        }
        return result;
    }
}

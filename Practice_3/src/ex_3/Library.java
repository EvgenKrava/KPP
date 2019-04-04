package ex_3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class Library implements Externalizable {

    private String name;

    private ArrayList<BookStore> bookStores;

    private ArrayList<BookReader> bookReaders;

    Library(String name){
        bookStores = new ArrayList<>();
        bookReaders = new ArrayList<>();
        this.name=name;
    }

    public void addBookStore(BookStore bookStore){
        bookStores.add(bookStore);
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<BookStore> getBookStores() {
        return bookStores;
    }

    public ArrayList<BookReader> getBookReaders() {
        return bookReaders;
    }

    public void setBookReaders(ArrayList<BookReader> bookReaders) {
        this.bookReaders = bookReaders;
    }

    public void setBookStores(ArrayList<BookStore> bookStores) {
        this.bookStores = bookStores;
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(name);
        objectOutput.write(bookStores.size());
        for(BookStore b: bookStores){
            b.writeExternal(objectOutput);
        }
        objectOutput.write(bookReaders.size());
        for (BookReader b: bookReaders) {
            b.writeExternal(objectOutput);
        }
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        name=(String)objectInput.readObject();
        int sbs = objectInput.read();
        bookStores = new ArrayList<>();
        for (int i = 0; i < sbs; i++) {
            BookStore b = new BookStore();
            b.readExternal(objectInput);
            bookStores.add(b);
        }
        int sbr = objectInput.read();
        bookReaders = new ArrayList<>();
        for (int i = 0; i < sbr ; i++) {
            BookReader b = new BookReader();
            b.readExternal(objectInput);
            bookReaders.add(b);
        }
    }
}

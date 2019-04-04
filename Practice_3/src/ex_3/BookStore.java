package ex_3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class BookStore implements Externalizable {

    private String name;

    private ArrayList<Book> books;

    BookStore(){
        name="";
        books=new ArrayList<>();
    }

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

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(name);
        objectOutput.write(books.size());
        for(Book b: books){
            b.writeExternal(objectOutput);
        }
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        name = (String) objectInput.readObject();
        int size = objectInput.read();
        books = new ArrayList<>();
        for(int i=0; i < size; i++){
            Book b = new Book();
            b.readExternal(objectInput);
            books.add(b);
        }
    }
}

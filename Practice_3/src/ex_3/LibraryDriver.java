package ex_3;

import java.io.*;

public class LibraryDriver{


    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Library library = new Library("Моя библиотека");
        BookStore bookStore1 = new BookStore("Мое книгохранилище1");
        BookStore bookStore2 = new BookStore("Мое книгохранилище2");
        BookStore bookStore3 = new BookStore("Мое книгохранилище3");
        library.addBookStore(bookStore1);
        library.addBookStore(bookStore2);
        library.addBookStore(bookStore3);
        library.addBook(bookStore1,new Book(1,"Book1", 1999, new Author("AuthorName1","AuthorSurname1")));
        library.addBook(bookStore1,new Book(2,"Book2", 2000, new Author("AuthorName2","AuthorSurname2")));
        library.addBook(bookStore2,new Book(3,"Book3", 1899, new Author("AuthorName3","AuthorSurname3")));
        library.addBook(bookStore2,new Book(4,"Book4", 2009, new Author("AuthorName4","AuthorSurname4")));
        library.addBook(bookStore3,new Book(5,"Book5", 933, new Author("AuthorName5","AuthorSurname5")));

        System.out.println(library);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("file3.bin"));

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("file3.bin"));

        library.writeExternal(objectOutputStream);

        Library o = new Library("");
        o.readExternal(objectInputStream);

        System.out.println(o);
    }
}

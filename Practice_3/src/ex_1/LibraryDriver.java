package ex_1;

import java.io.*;

public class LibraryDriver implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void serializeObject(String fileName, Object obj){
        try {
            ObjectOutputStream os = new ObjectOutputStream(new
                    FileOutputStream(fileName));
            os.writeObject(obj);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerializeObject(String fileName){
        Object obj = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new
                    FileInputStream(fileName));
            obj = is.readObject();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


    public static void main(String[] args) {
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
        serializeObject("file1.bin", library);
        Library o =(Library) deSerializeObject("file1.bin");
        System.out.println(o);
    }
}

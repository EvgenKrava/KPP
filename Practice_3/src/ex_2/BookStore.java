package ex_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BookStore implements Serializable {

    private static final long serialVersionUID = -3501407766241748251L;

    transient String name;

    transient ArrayList<Book> books;

    BookStore(String name) {
        this.name = name;
        books = new ArrayList<>();
    }

    public Book getBook(String bookName) {
        for (Book b : books) {
            if (b.name.equals(bookName)) return b;
        }
        return null;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Имя книгохранилища: " + name + "\n");
        for (Book b : books) {
            result.append(b.toString()).append("\n");
        }
        return result.toString();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(name);
        out.writeInt(books.size()); // вручную сохраняем все что надо
        for (Book b : books) { // для каждой книги сохраняем все
            out.writeInt(b.getNumber());
            out.writeInt(b.getYear());
            out.writeObject(b.getName());
            out.writeInt(b.authors.size()); // и список авторов сохраняем весь
            for (Author a : b.getAuthors()) {
                out.writeObject(a.getName());
                out.writeObject(a.getSurname());
            }
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Book b;
        in.defaultReadObject(); // все что можно считываем по умолчанию
        this.name=(String) in.readObject();
        int s= in.readInt();
        this.books = new ArrayList<>(); // все остальное конструируем и
        for (int i = 0; i < s; i++) { // восстанавливаем книгу
            b = new Book();
            b.setNumber(in.readInt());
            b.setYear(in.readInt());
            b.setName((String) in.readObject());
            //System.out.println(b);

            int as = in.readInt();
            ArrayList<Author> authors = new ArrayList<>();

            for (int j = 0; j < as; j++) {
                Author a = new Author((String)in.readObject(),(String)in.readObject());
                b.authors.add(a);
            }
            this.books.add(b);
        }
    }


}
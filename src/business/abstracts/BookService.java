package business.abstracts;

import entities.Book;
import entities.User;
import enums.BookGenre;
import exceptions.BookNotAvailableException;

public interface BookService {
    void add(Book book);
    void print();
    void delete(Book book);
    void update(Book book, User user);
    void find() throws BookNotAvailableException;
     void displayBooksByAuthor(String authorName);
     void displayBooksByGenre(BookGenre genre);



    }

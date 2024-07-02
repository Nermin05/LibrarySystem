package business.abstracts;

import entities.Book;
import entities.User;
import exceptions.BookNotAvailableException;

public interface ProcessService {
    void borrowBook(User user, Book book) throws BookNotAvailableException;
    void returnBook(User user, Book book);

}

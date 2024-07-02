package business.abstracts;

import entities.Book;
import entities.User;

public interface ReservBooksService {
    void reservBooks(User user, Book book);
    void displayReservedBooks(User user);
    void notifyUsers(Book book);

}

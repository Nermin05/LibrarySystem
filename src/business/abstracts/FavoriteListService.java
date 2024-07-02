package business.abstracts;

import entities.Book;
import entities.User;

public interface FavoriteListService {
    void addBooks(User user, Book book);
    void displayList(User user);

}

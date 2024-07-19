package business.abstracts;

import business.concretes.FavoriteListManager;
import entities.Book;
import entities.User;

@FunctionalInterface
public interface CalculateFavoriteList {
    void calculate(User user, Book book);
}

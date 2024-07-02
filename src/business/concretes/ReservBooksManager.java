package business.concretes;

import business.abstracts.ReservBooksService;
import entities.Book;
import entities.User;

import java.util.LinkedHashSet;
import java.util.Set;

public class ReservBooksManager implements ReservBooksService {
    @Override
    public void reservBooks(User user, Book book) {
if (book.isAvailable()!=true) {
    user.reserveBook(book);
    book.users(user);
}
    }
    @Override
    public void displayReservedBooks(User user) {
        System.out.println(user.getUserId()+"->"+user.getName() + " reserves:");
        int id = 1;
        for (Book reservedBook : user.getReservedBooks()) {
            System.out.println(id + ")" + reservedBook);
            id++;
        }
    }



    @Override
    public void notifyUsers(Book book) {
        if(book.isAvailable()==true) {
            for(User user: book.getUsers()) {
                System.out.println(user.getUserId()+"->"+user.getName()+","+book.getTitle()+" is available now!");
            }
        }
    }
}

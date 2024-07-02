package business.concretes;

import business.abstracts.FavoriteListService;
import entities.Book;
import entities.User;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class FavoriteListManager implements FavoriteListService {
    Set<Book> favBooks;
    @Override
    public void addBooks(User user, Book book) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Do you want to add this book to your favorite list?\n1->Yes\n2->No\nChoose: ");
        int addFav=scanner.nextInt();
        if(addFav==1) {
           favBooks=new LinkedHashSet<>();
            favBooks.add(book);
            System.out.println(user.getName()+" add "+book.getTitle()+" to favorite list");
        }
    }

    @Override
    public void displayList(User user) {
        System.out.println(user.getName()+",your favorite book list:");
for (Book book:favBooks) {
    System.out.println(book.toString());
}
    }


}

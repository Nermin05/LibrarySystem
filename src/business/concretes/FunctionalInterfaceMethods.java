package business.concretes;
import business.abstracts.CalculateFavoriteList;
import entities.Book;
import entities.User;
import enums.BookGenre;
import enums.UserRole;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceMethods  {
private int count=0;
private FavoriteListManager favoriteListManager;

    public FunctionalInterfaceMethods(FavoriteListManager favoriteListManager) {
        this.favoriteListManager = favoriteListManager;
    }

    public void fourFunctionalInterface(Book book1, Book book2, Set<User> users,
                                        Map<Integer, LocalDate> localDates, BookManager bookManager, User user3) {
        Consumer<Book> bookConsumer = book -> System.out.println(book);
        bookConsumer.accept(book1);

        Predicate<Book> bookPredicate = book -> {
            if (book.getBookGenre() == BookGenre.FICTION) {
                System.out.println(book.getTitle() + " is fiction!");
                return true;
            } else {
                System.out.println(book.getTitle() + " is not fiction!");
            }
            return false;
        };
        bookPredicate.test(book2);

        Supplier<String> transactionSupplier = () -> {
            StringBuilder stringBuilder = new StringBuilder();
            localDates.forEach((key, value) -> {
                stringBuilder.append(key + ")" + value + "\n");
            });
            return stringBuilder.toString();
        };
        System.out.println(transactionSupplier.get());

        Function<Book, Set<User>> bookBookFunction = book -> book.getUsers();
        System.out.println(bookBookFunction.apply(book1));

        Consumer<Set<User>> userConsumer = userSet -> {
            for (User user : userSet) {
                System.out.println("Send notification->" + user.getEmail());
            }
        };
        userConsumer.accept(users);

        Supplier<List<Book>> availableFictionBooksSupplier = () -> {
            List<Book> availableBooks = new ArrayList<>();
            bookManager.getBooks().forEach(book -> {
                if (book.isAvailable() && book.getBookGenre() == BookGenre.FICTION) {
                    availableBooks.add(book);
                }
            });
            return availableBooks;
        };
        System.out.println("Available Fiction Books: " + availableFictionBooksSupplier.get());

        Predicate<User> userRolePredicate = user -> {
            if (user.getUserRole() == UserRole.MEMBER) {
                System.out.println(user.getName() + " you are a member!");
                return true;
            } else {
                System.out.println(user.getName() + " you are a librarian");
            }
            return false;
        };
        userRolePredicate.test(user3);
    }

}

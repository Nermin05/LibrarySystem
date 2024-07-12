import business.abstracts.ReservBooksService;
import business.concretes.*;
import entities.Book;
import entities.Transaction;
import entities.User;
import enums.BookGenre;
import enums.TransactionType;
import enums.UserRole;
import exceptions.BookNotAvailableException;
import exceptions.UserNotFoundException;

import java.time.LocalDate;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws IllegalAccessException, UserNotFoundException, BookNotAvailableException {
        LocalDate userDate=LocalDate.of(2023,1,20);
        LocalDate user2Date=LocalDate.of(2023,3,25);
        User user=new User("Nermin","ldjla","380293","nermin@gmail.com","salam123","salam123", UserRole.LIBRARIAN,userDate);
        User user2=new User("Salam","ldjla","380293","nermin@gmail.com","salam123","salam123", UserRole.MEMBER,user2Date);
        User user3=new User("Hello","ldjla","380293","nermin@gmail.com","salam123","salam123", UserRole.MEMBER,user2Date);

        UserManager userManager=new UserManager();
        userManager.register(user);
        userManager.register(user2);
        userManager.register(user3);


        userManager.add(user);
        userManager.add(user2);
        userManager.add(user3);

        Set<User> users= Set.of(new User[]{user, user2,user3});
        userManager.print();
        //userManager.login(users);
        //userManager.delete(user);
        //userManager.print();
//        userManager.update(user);
//        userManager.print();
        LocalDate localDate=LocalDate.of(2023,10,10);
        LocalDate localDate2=LocalDate.of(2022,9,20);

        Book book1=new Book("Cerpeleng ucuran","Xalid Huseyn",
                localDate,false, BookGenre.FICTION);
        Book book2=new Book("Sefiller","Victor Huqo",
                localDate2,false, BookGenre.NONFICTION);
        BookManager bookManager=new BookManager();
        bookManager.add(book1);
        bookManager.add(book2);

        bookManager.print();
//bookManager.update(book1);
        ProcessManager processManager=new ProcessManager(bookManager);
//        processManager.borrowBook(user,book1);
//        bookManager.print();
//        System.out.println("----------------");
//        processManager.returnBook(user,book1);
//        bookManager.print();
        LocalDate localBorrow=LocalDate.of(2023,1,5);
        LocalDate localReturn=LocalDate.of(2023,1,15);
        LocalDate localBorrow2=LocalDate.of(2023,2,28);
        LocalDate localReturn2=LocalDate.of(2023,3,15);

        Transaction transaction=new Transaction(1,1,localBorrow,localReturn, TransactionType.BORROW);
        Transaction transaction2=new Transaction(2,1,localBorrow2,localReturn2, TransactionType.BORROW);

        TransactionManager transactionManager=new TransactionManager(bookManager);
        transactionManager.record(transaction);
        transactionManager.record(transaction2);
        transactionManager.display();
        transactionManager.displayBorrowBooks();
 // bookManager.find();
 // transactionManager.displayBorrowBooks();
        SaveInformationsManager saveInformationsManager=new SaveInformationsManager(bookManager,userManager,transactionManager);
        saveInformationsManager.saveBooks("books.txt");
        saveInformationsManager.saveUsers("users.txt");
saveInformationsManager.saveTransaction("transactions.txt");
//FavoriteListManager favoriteListManager=new FavoriteListManager();
//favoriteListManager.addBooks(user,book1);
//favoriteListManager.displayList(user);
//        favoriteListManager.addBooks(user2,book2);
//        favoriteListManager.displayList(user2);
        ReservBooksManager reservBooksManager=new ReservBooksManager();
        reservBooksManager.reservBooks(user,book1);
        reservBooksManager.reservBooks(user,book2);
        reservBooksManager.reservBooks(user2,book1);
reservBooksManager.displayReservedBooks(user);
        reservBooksManager.displayReservedBooks(user2);
        book1.setAvailable(true);
        book2.setAvailable(true);
        reservBooksManager.notifyUsers(book1);
        reservBooksManager.notifyUsers(book2);
        FeeManager feeManager=new FeeManager(transaction);
        feeManager.calculateFee(user);
        FeeManager feeManager2=new FeeManager(transaction2);
        feeManager2.calculateFee(user2);
     //    bookManager.find();
        // userManager.find();
bookManager.displayBooksByAuthor("Xalid Huseyn");
bookManager.displayBooksByGenre(BookGenre.NONFICTION);
bookManager.find();
    }
}
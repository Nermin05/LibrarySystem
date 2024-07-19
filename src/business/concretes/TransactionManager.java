package business.concretes;

import business.abstracts.TransactionService;
import entities.Book;
import entities.Transaction;
import entities.User;
import enums.TransactionType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TransactionManager implements TransactionService {

    private Set<Transaction> transactions;
   private BookManager bookManager;
private User user;
private boolean ifAllow;
    public TransactionManager(BookManager bookManager) {
        transactions=new LinkedHashSet<>();
       this.bookManager=bookManager;
       this.user=new User();
       ifAllow=false;
    }

    public boolean isIfAllow() {
        return ifAllow;
    }

    public TransactionManager() {
        transactions = new LinkedHashSet<>();
    }
    public Set<Transaction> getTransactions() {
        return transactions;
    }
    @Override
    public void record(Transaction transaction) {
   transactions.add(transaction);
    }

    @Override
    public void display() {
        int index = 1;
        System.out.println("History:");
        for(Transaction transaction1: transactions) {
            System.out.print(index+")ID:"+transaction1.getTransactionID());
            transaction1.displayTransaction();
            index++;
        }
    }
@Override
public void displayBorrowBooks() {
    System.out.println("List of all books currently borrowed:");
    if (transactions.isEmpty()) {
        System.out.println("No transactions found.");
        return;
    }

    for (Transaction transaction : transactions) {
        System.out.println("Checking transaction with ID: " + transaction.getTransactionID());

        if (transaction.getTransactionType() == TransactionType.BORROW) {
            System.out.println("Transaction is of type BORROW.");
            if (bookManager != null) {
                Book borrowedBook = bookManager.findBookById(transaction.getBookID());
                if (borrowedBook != null) {
                    System.out.println("Book ID: " + borrowedBook.getBookID());
                    System.out.println("Title: " + borrowedBook.getTitle());
                    System.out.println("Author: " + borrowedBook.getAuthor());
                    System.out.println("Publication Year: " + borrowedBook.getPublicationDate());
                    System.out.println();
                } else {
                    System.out.println("No book found with ID: " + transaction.getBookID());
                }
            } else {
                System.out.println("BookManager is null.");
            }
        } else {
            System.out.println("Transaction is not of type BORROW.");
        }
    }
}

    @Override
    public void overdueBooks(User user, Transaction transaction) {
        if (transaction.getReturnDate() == null || transaction.getBorrowDate() == null || user.getReturnDate() == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        LocalDate borrowDate = transaction.getBorrowDate();
        LocalDate returnDate = transaction.getReturnDate();
        LocalDate userReturnDate = user.getReturnDate();

        long daysBetweenBorrowAndReturn = ChronoUnit.DAYS.between(borrowDate, returnDate);
        long daysBetweenBorrowAndUserReturn = ChronoUnit.DAYS.between(borrowDate, userReturnDate);

        if (daysBetweenBorrowAndReturn > daysBetweenBorrowAndUserReturn) {
            System.out.println("This book is overdue: " + bookManager.getBooks().toString());
            ifAllow=false;
        }
        else {
            ifAllow=true;
        }
    }


}








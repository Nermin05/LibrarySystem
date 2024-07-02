package entities;

import enums.TransactionType;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private static long idCounter = 0;
    private long transactionID;
    private long userID;
    private long bookID;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private TransactionType transactionType;

    public Transaction(long userID, long bookID, LocalDate borrowDate, LocalDate returnDate, TransactionType transactionType) {
        this.transactionID = generateNumericID();
        this.userID = userID;
        this.bookID = bookID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.transactionType = transactionType;
    }
    public Transaction() {}

    private synchronized static long generateNumericID() {
        return ++idCounter;
    }
    public long getTransactionID() {
        return transactionID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void displayTransaction() {
        System.out.println(" Transaction ID: " + transactionID);
        System.out.println("User ID: " + userID);
        System.out.println("Book ID: " + bookID);
        if (transactionType == TransactionType.BORROW) {
            System.out.println("Borrow Date: " + borrowDate);
        } else if (transactionType == TransactionType.RETURN) {
            System.out.println("Return Date: " + returnDate);
        }
        System.out.println("Transaction Type: " + transactionType);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", userID=" + userID +
                ", bookID=" + bookID +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", transactionType=" + transactionType +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return transactionID == that.transactionID && userID == that.userID && bookID == that.bookID && Objects.equals(borrowDate, that.borrowDate) && Objects.equals(returnDate, that.returnDate) && transactionType == that.transactionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionID, userID, bookID, borrowDate, returnDate, transactionType);
    }
}

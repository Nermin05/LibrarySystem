package business.abstracts;

import entities.Transaction;
import entities.User;

public interface TransactionService {
    void record(Transaction transaction);
    void display();
    void displayBorrowBooks();
  void overdueBooks(User user, Transaction transaction);
}

package business.abstracts;

import entities.Book;
import entities.Transaction;
import entities.User;

public interface SaveInformationsService {
    void saveBooks(String filename);
    void saveUsers( String filename);
    void saveTransaction(String filename);



}

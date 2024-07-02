package business.concretes;

import business.abstracts.SaveInformationsService;
import entities.Book;
import entities.Transaction;
import entities.User;
import enums.BookGenre;

import java.io.*;
import java.time.LocalDate;

public class SaveInformationsManager implements SaveInformationsService {
    private BookManager bookManager;
    private UserManager userManager;
    private TransactionManager transactionManager;

    public SaveInformationsManager(BookManager bookManager, UserManager userManager, TransactionManager transactionManager) {
        this.bookManager = bookManager;
        this.userManager = userManager;
        this.transactionManager = transactionManager;
    }

    @Override
    public void saveBooks(String filename) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write("ID,Title,Author,PublicationDate,BookGenre");
            bufferedWriter.newLine();

            for (Book book1 : bookManager.getBooks()) {
                System.out.println(book1);
                bufferedWriter.write(book1.toString());
                bufferedWriter.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUsers(String filename) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write("ID,Name,Address,Phone,Email,User role");
            bufferedWriter.newLine();

            for (User user1 : userManager.users) {
                System.out.println(user1);
                bufferedWriter.write(user1.toString());
                bufferedWriter.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void saveTransaction(String filename) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write("ID,Name,Address,Phone,Email,User role");
            bufferedWriter.newLine();

            for (Transaction transaction1 : transactionManager.getTransactions()) {
                bufferedWriter.write(transaction1.toString());
                bufferedWriter.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

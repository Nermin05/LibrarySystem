package business.concretes;

import business.abstracts.ProcessService;
import entities.Book;
import entities.User;
import exceptions.BookNotAvailableException;

public class ProcessManager implements ProcessService {
    private BookManager bookManager;

    public ProcessManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    @Override
    public void borrowBook(User user, Book book) throws BookNotAvailableException {
        if (book.isAvailable()) {
            System.out.println(user.getName() + ", take this book: " + book.getTitle());
            bookManager.getBooks().remove(book);

        } else {
            throw new BookNotAvailableException("This book isn't available");
        }
    }

    @Override
    public void returnBook(User user, Book book) {
        bookManager.add(book);
        System.out.println(user.getName() + " returns " + book.getTitle());
    }
}

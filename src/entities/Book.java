package entities;

import enums.BookGenre;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public class Book {
    private static long id = 0;
    private long bookID;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private boolean isAvailable;
    private BookGenre bookGenre;
    private Set<User> users;

    public Book(String title, String author, LocalDate publicationDate, boolean isAvailable, BookGenre bookGenre) {
        this.bookID = generateNumericID();
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.isAvailable = isAvailable;
        this.bookGenre = bookGenre;
        this.users = new LinkedHashSet<>();
    }
public void users(User user) {
    users.add(user);
}
    public Book(String title, String author) {
        this(title, author, null, true, null); // Example default values
    }

    public Book() {
        this(null, null, null, true, null); // Example default values
    }

    private synchronized long generateNumericID() {
        return ++id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setBookGenre(BookGenre bookGenre) {
        this.bookGenre = bookGenre;
    }

    public long getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public BookGenre getBookGenre() {
        return bookGenre;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isAvailable == book.isAvailable &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(publicationDate, book.publicationDate) &&
                bookGenre == book.bookGenre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publicationDate, isAvailable, bookGenre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate=" + publicationDate +
                ", bookGenre=" + bookGenre +
                '}';
    }

}

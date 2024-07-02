package business.concretes;

import business.abstracts.BookService;
import entities.Book;
import entities.User;
import enums.BookGenre;
import enums.UserRole;
import exceptions.BookNotAvailableException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookManager implements BookService {
    private Set<Book> books;
    private Book book;

    public BookManager() {
        books = new LinkedHashSet<>();
        book = new Book();
    }

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public void add(Book book) {
        books.add(book);

    }

    @Override
    public void print() {
        int index = 1;
        System.out.println("Books:");
        for (Book book1 : books) {
            System.out.println(index + ")ID:" + book1.getBookID() + ".Title:" + book1.getTitle() + " Author:" + book1.getAuthor() + " Publication Date:" +
                    book1.getPublicationDate() + " Book Genre:" + book1.getBookGenre());
            index++;
        }
    }

    @Override
    public void delete(Book book) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you sure delete this book?\n 1->Yes\n2->No\nChoose:");
        int sec = scanner.nextInt();
        switch (sec) {
            case 1:
                books.remove(book);
                break;
            case 2:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void update(Book book,User user) {
        Scanner scanner = new Scanner(System.in);
        if (user.getUserRole() == UserRole.LIBRARIAN) {
            try {
                System.out.print("What do you want to update?\n1->Title\n2->Author\n3->PublicationDate\n4->BookGenre\nChoose: ");
                int sec = scanner.nextInt();
                scanner.nextLine();

                switch (sec) {
                    case 1:
                        System.out.print("Enter new title: ");
                        String title = scanner.nextLine();
                        book.setTitle(title);
                        break;
                    case 2:
                        System.out.print("Enter new author: ");
                        String author = scanner.nextLine();
                        book.setAuthor(author);
                        break;
                    case 3:
                        System.out.print("Enter new publication date: ");
                        String dateString = scanner.nextLine();
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        LocalDate date = LocalDate.parse(dateString, dateFormatter);
                        book.setPublicationDate(date);
                        break;
                    case 4:
                        System.out.print("Enter new book genre: ");
                        BookGenre bookGenre = BookGenre.valueOf(scanner.nextLine().toUpperCase());
                        book.setBookGenre(bookGenre);
                        break;
                    default:
                        System.out.println("Invalid option. Exiting.");
                        System.exit(0);
                }
            } finally {
                scanner.close();
            }
        } else {
            System.out.println("Just Librarians can do this!");
        }
    }

    @Override
    public void find() throws BookNotAvailableException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which do you want to find for?\n1->Title\n2->Author\n3->Genre\nChoose: ");
        int sec = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;

        switch (sec) {
            case 1:
                System.out.print("Write title name: ");
                String title = scanner.nextLine();
                for (Book book1 : books) {
                    if (title.equals(book1.getTitle())) {
                        System.out.println(book1.getBookID() + ") Title: " + book1.getTitle() + " Author: " + book1.getAuthor()
                                + " Genre: " + book1.getBookGenre());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("No exact match found. Searching for prefixes...");
                    for (Book book1 : books) {
                        if (book1.getTitle().startsWith(title)) {
                            System.out.println(book1.getBookID() + ") Title: " + book1.getTitle() + " Author: " + book1.getAuthor()
                                    + " Genre: " + book1.getBookGenre());
                            found = true;
                        }
                    }

                    if (!found) {
                        throw new BookNotAvailableException("You have to enter correct information");
                    }
                }
                break;

            case 2:
                System.out.print("Write author name: ");
                String author = scanner.nextLine();
                for (Book book1 : books) {
                    if (author.equals(book1.getAuthor())) {
                        System.out.println(book1.getBookID() + ") Title: " + book1.getTitle() + " Author: " + book1.getAuthor()
                                + " Genre: " + book1.getBookGenre());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("No exact match found. Searching for prefixes...");
                    for (Book book1 : books) {
                        if (book1.getAuthor().startsWith(author)) {
                            System.out.println(book1.getBookID() + ") Title: " + book1.getTitle() + " Author: " + book1.getAuthor()
                                    + " Genre: " + book1.getBookGenre());
                            found = true;
                        }
                    }

                    if (!found) {
                        throw new BookNotAvailableException("You have to enter correct information");
                    }
                }
                break;

            case 3:
                System.out.print("Write genre name: ");
                String genreString = scanner.nextLine().toUpperCase();
                try {
                    BookGenre genre = BookGenre.valueOf(genreString);
                    for (Book book1 : books) {
                        if (genre == book1.getBookGenre()) {
                            System.out.println(book1.getBookID() + ") Title: " + book1.getTitle() + " Author: " + book1.getAuthor()
                                    + " Genre: " + book1.getBookGenre());
                            found = true;
                        }
                    }

                    if (!found) {
                        throw new BookNotAvailableException("You have to enter correct information");
                    }
                } catch (IllegalArgumentException e) {
                    throw new BookNotAvailableException("Invalid genre entered. You have to enter correct information.");
                }
                break;

            default:
                System.exit(0);
        }
    }

    public Book findBookById(Long bookID) {
        for (Book book1 : books) {
            if (Objects.equals(book1.getBookID(), bookID)) {
                return book1;
            }
        }
        return null;
    }

    public void displayBooksByGenre(BookGenre genre) {
        System.out.println("List of books in genre: " + genre);

        boolean foundBooks = false;
        for (Book book : books) {
            if (book.getBookGenre() == genre) {
                System.out.println("Book ID: " + book.getBookID());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Publication Year: " + book.getPublicationDate());
                System.out.println();
                foundBooks = true;
            }
        }

        if (!foundBooks) {
            System.out.println("No books found in genre: " + genre);
        }
    }

    public void displayBooksByAuthor(String authorName) {
        System.out.println("List of books by author: " + authorName);

        boolean foundBooks = false;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                System.out.println("Book ID: " + book.getBookID());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Publication Year: " + book.getPublicationDate());
                System.out.println();
                foundBooks = true;
            }
        }

        if (!foundBooks) {
            System.out.println("No books found by author: " + authorName);
        }

    }
}

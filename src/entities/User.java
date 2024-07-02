package entities;

import enums.UserRole;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class User {
    private static long id=0;
    private long userId=generateNumericID();
    private String name;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String passwordAgain;
    private UserRole userRole;
    private Set<Book> reservedBooks;
    private LocalDate returnDate;

    public User() {
    }

    public Set<Book> getReservedBooks() {
        return reservedBooks;
    }

    public void reserveBook(Book book){
reservedBooks.add(book);
        book.users(this);

    }


    public User(String name, String address, String phone,
                String email, String password, String passwordAgain, UserRole userRole,LocalDate returnDate) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.passwordAgain = passwordAgain;
        this.userRole = userRole;
        this.returnDate=returnDate;
        reservedBooks= new LinkedHashSet<>();
    }

    public User(String name, String address,String phone,String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    private static synchronized long generateNumericID() {
        return ++id;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

  public String getPassword() {
        return password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return userId==user.userId && Objects.equals(name, user.name) && Objects.equals(address, user.address) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(passwordAgain, user.passwordAgain) && userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, address, phone, email, password, passwordAgain, userRole);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                '}';
    }

}

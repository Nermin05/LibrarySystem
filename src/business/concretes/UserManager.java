package business.concretes;

import business.abstracts.UserService;
import entities.Book;
import entities.User;
import enums.UserRole;
import exceptions.BookNotAvailableException;
import exceptions.UserNotFoundException;

import java.util.*;


public class UserManager implements UserService {
    Set<User> users;
    private User user;

    public UserManager() {
        users = new LinkedHashSet<>();
        user = new User();
    }


    @Override
    public boolean register(User user) throws IllegalAccessException {
        if (!(user.getEmail().contains("@") && user.getEmail().contains("."))) {
            throw new IllegalAccessException("Use correctly format");
        }
        if (user.getPassword().length() < 8 && user.getPasswordAgain().length() < 8) {
            throw new IllegalAccessException("Your password is too poor");
        }
        if (user.getPassword() != user.getPasswordAgain()) {
            throw new IllegalAccessException("Enter passswords correctly pls");
        }
        return true;
    }

    @Override
    public void add(User user) throws IllegalAccessException {
        if (register(user) == true) {
            users.add(user);
        } else {
            throw new IllegalAccessException("You have some mistakes");
        }
    }

    @Override
    public void login(Set<User> users) throws UserNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name:");
        String name = scanner.nextLine();
        System.out.print("Enter your email:");
        String email = scanner.nextLine();
        System.out.print("Enter your password:");
        String password = scanner.nextLine();

        User userFound = null;
        for (User user1 : users) {
            if (name.equals(user1.getName()) && email.equals(user1.getEmail()) && password.equals(user1.getPassword())) {
                userFound = user1;
                break;
            }
        }
        if (userFound != null) {
            System.out.println("Hi " + userFound.getName());
        } else {
            throw new UserNotFoundException("You don't have any accaunt,pls register");
        }
    }

    public void print() {
        int index = 1;
        System.out.println("Users:");
        for (User user1 : users) {
            System.out.println(index + ")ID:" + user1.getUserId() + ".Name:" + user1.getName() + " Address:" + user1.getAddress() + " Email:" + user1.getEmail() +
                    " UserRole:" + user1.getUserRole());
            index++;
        }

    }

    @Override
    public void delete(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you sure delete your account?\n 1->Yes\n2->No\nChoose:");
        int sec = scanner.nextInt();
        switch (sec) {
            case 1:
                users.remove(user);
                break;
            case 2:
                System.exit(0);
                break;
            default:
                break;
        }

    }

    @Override
    public void update(User user) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("What do you want to update?\n1->Name\n2->Address\n3->email\n4->UserRole\nChoose: ");
            int sec = scanner.nextInt();
            scanner.nextLine();

            switch (sec) {
                case 1:
                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine();
                    user.setName(name);
                    break;
                case 2:
                    System.out.print("Enter new address: ");
                    String address = scanner.nextLine();
                    user.setAddress(address);
                    break;
                case 3:
                    System.out.print("Enter new email: ");
                    String email = scanner.nextLine();
                    user.setEmail(email);
                    break;
                case 4:
                    System.out.print("Enter new user role: ");
                    UserRole userRole = UserRole.valueOf(scanner.nextLine().toUpperCase());
                    user.setUserRole(userRole);
                    break;
                default:
                    System.out.println("Invalid option. Exiting.");
                    System.exit(0);
            }
        } finally {
            scanner.close();
        }
    }

    @Override
    public void find() throws  UserNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which do you want to find for?\n1->Name\n2->UserId\nChoose:");
        int sec = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;
        switch (sec) {
            case 1:
                System.out.print("Write user name:");
                String name = scanner.nextLine();

                for (User user1 : users) {
                    if (user1.getName() != null && name.equals(user1.getName())) {
                        System.out.println(user.getUserId() + ")Name:" + user1.getName() + " Phone:" + user1.getPhone()
                                + "Email:" + user1.getEmail());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("No exact match found. Searching for prefixes...");
                    for (User user1 : users) {
                        if (user1.getName() != null && user1.getName().startsWith(name)) {
                            System.out.println(user1.getUserId() + ") Name: " + user1.getName() + " Email: " + user1.getEmail()
                                    + " Phone: " + user1.getName());
                            found = true;
                        }
                    }

                    if (!found) {
                        throw new UserNotFoundException("User not found!Enter correctly information");
                    }
                }
                break;
            case 2:
                System.out.print("Write user id:");
                long id = scanner.nextLong();
                for (User user1 : users) {
                    if (id == user1.getUserId()) {
                        System.out.println(user.getUserId() + ")Name:" + user1.getName() + " Phone:" + user1.getPhone()
                                + "Email:" + user1.getEmail());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("No exact match found. Searching for prefixes...");
                    for (User user1 : users) {
                        String idPrefix = scanner.nextLine();
                        if (String.valueOf(user1.getUserId()).startsWith(idPrefix)) {
                            System.out.println(user.getUserId() + ")Name:" + user1.getName() + " Phone:" + user1.getPhone()
                                    + "Email:" + user1.getEmail());
                            found = true;
                        }
                    }

                    if (!found) {
                        throw new UserNotFoundException("You have to enter correct information");
                    }
                    break;
                }

        }
    }
}

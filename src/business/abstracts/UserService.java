package business.abstracts;

import entities.User;
import exceptions.BookNotAvailableException;
import exceptions.UserNotFoundException;
import java.util.Set;

public interface UserService {
    boolean register(User user) throws IllegalAccessException;
     void add(User user) throws IllegalAccessException;
    void login(Set<User> users) throws UserNotFoundException;
    void print();
    void delete(User user);
    void update(User user);
    void find() throws UserNotFoundException;


}

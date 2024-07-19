package business.abstracts;

import entities.Transaction;
import entities.User;

public interface AllowReservation {
    void allowReserv(User user, Transaction transaction);
}

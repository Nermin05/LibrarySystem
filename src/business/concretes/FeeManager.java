package business.concretes;

import business.abstracts.FeeService;
import entities.Transaction;
import entities.User;

public class FeeManager implements FeeService {
    private Transaction transaction;

    public FeeManager(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void calculateFee(User user) {

        if (transaction.getReturnDate()== null || transaction.getBorrowDate() == null || user.getReturnDate() == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if((transaction.getReturnDate().toEpochDay()-transaction.getBorrowDate().toEpochDay())
                <(user.getReturnDate().toEpochDay()-transaction.getBorrowDate().toEpochDay())){
            int late=user.getReturnDate().compareTo(transaction.getReturnDate());
            double result=late*0.20;
            System.out.println(user.getUserId()+"->"+user.getName()+" your late fee:"+result);
        }
        else {
            System.out.println("Successfully process!No problem");
        }
    }
}

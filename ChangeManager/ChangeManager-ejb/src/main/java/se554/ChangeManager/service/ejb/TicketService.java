package se554.ChangeManager.service.ejb;

import se554.ChangeManager.persistence.Ticket;
import se554.ChangeManager.service.*;
import se554.ChangeManager.service.TicketServiceRemote;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jklug3
 */
@Stateless
public class TicketService implements TicketServiceRemote{
    @PersistenceContext(unitName = "changemanagerPU")
    private EntityManager entityManager;
    
   // @TransactionAttribute(TransactionAttributeType.REQUIRED)
        //public etc.........for each method defined in remote interface
        
}





    @PersistenceContext(unitName = "accountPU")
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public long openAccount(String name, float initialBalance) throws NegativeBalanceException {
        checkForPositiveBalance(initialBalance);
        Account account = new Account();
        account.setBalance(initialBalance);
        account.setName(name);
        entityManager.persist(account);
        return account.getId();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public float deposit(long accountNumber, float amount)
            throws NegativeBalanceException, AccountNotFoundException {
        checkForPositiveBalance(amount);
        Account account = entityManager.find(Account.class, accountNumber);
        float newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);

        entityManager.merge(account);

        return newBalance;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public float withdraw(long accountNumber, float amount)
            throws NegativeBalanceException, InsufficientBalanceException, AccountNotFoundException {
        checkForPositiveBalance(amount);
        Account account = entityManager.find(Account.class, accountNumber);

        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException();
        }
        float newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);

        entityManager.merge(account);

        return newBalance;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public float close(long accountNumber)
            throws InsufficientBalanceException, AccountNotFoundException {
        Account account = entityManager.find(Account.class, accountNumber);
        float remainingBalance = account.getBalance();

        entityManager.remove(account);

        return remainingBalance;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void transferFunds(long fromAccountNumber, long toAccountNumber, float amount)
            throws AccountNotFoundException, NegativeBalanceException, InsufficientBalanceException {
        withdraw(fromAccountNumber, amount);
        deposit(toAccountNumber, amount);
    }

    @Override
    public List getAllAccounts() {
         return entityManager.createQuery("select a from Account a").getResultList();
    }
    
    
    private void checkForPositiveBalance(float amount) throws NegativeBalanceException {
        if (amount < 0) {
            throw new NegativeBalanceException();
        }
    }

}

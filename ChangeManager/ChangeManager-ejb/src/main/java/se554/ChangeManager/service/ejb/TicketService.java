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





   
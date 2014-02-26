package se554.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se554.ChangeManager.persistence.Ticket;


@SessionScoped
@Named
public class ChangeManagerBean implements Serializable {
    private static final Logger logger = Logger.getLogger(ChangeManagerBean.class.getName());
    
    @PersistenceContext(unitName = "changemanagerPU")
    private EntityManager entityManager;
    
    public List<Ticket> getTicketList() throws SQLException {
        
        logger.info("Before getting connection");
        
        List<Ticket> list = entityManager.createQuery("select a from Ticket a").getResultList();
        
        logger.log(Level.INFO, "Before returning: {0}", list.size());
        
        return list;
    }
}

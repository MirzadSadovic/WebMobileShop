package business.facade;

import business.model.Purchase;
import business.model.User;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PurchaseFacade extends AbstractFacade<Purchase> implements PurchaseFacadeLocal {

    @PersistenceContext(unitName = "ShopPU")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public PurchaseFacade() {
        super(Purchase.class);
    }

    @Override
    public Purchase createPurchase(String username, String cardNumber, BigDecimal totalPrice) {
        try {
            Query query = entityManager.createNamedQuery("User.findByUsername");
            query.setParameter("username", username);
            User user = (User) query.getSingleResult();
            Purchase purchase = new Purchase();
            purchase.setUserId(user);
            purchase.setName(user.getName());
            purchase.setSurname(user.getSurname());
            purchase.setCardNumber(cardNumber);
            purchase.setTotalPrice(totalPrice);
            entityManager.persist(purchase);
            return purchase;

        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}

package business.facade;

import business.model.PurchaseProduct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PurchaseProductFacade extends AbstractFacade<PurchaseProduct> implements PurchaseProductFacadeLocal {

    @PersistenceContext(unitName = "ShopPU")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public PurchaseProductFacade() {
        super(PurchaseProduct.class);
    }

}

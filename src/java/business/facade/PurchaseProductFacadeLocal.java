package business.facade;

import business.model.PurchaseProduct;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PurchaseProductFacadeLocal {

    void create(PurchaseProduct purchaseProduct);

    void edit(PurchaseProduct purchaseProduct);

    void remove(PurchaseProduct purchaseProduct);

    PurchaseProduct find(Object id);

    List<PurchaseProduct> findAll();

}

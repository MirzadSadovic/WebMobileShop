package beans;

import beans.model.ShoppingCartItem;
import business.facade.PurchaseProductFacadeLocal;
import business.model.Purchase;
import business.model.PurchaseProduct;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "purchaseProductManagedBean")
@RequestScoped
public class PurchaseProductManagedBean {

    @Inject
    PurchaseManagedBean purchaseManagedBean;

    @EJB
    private PurchaseProductFacadeLocal purchaseProductFacadeLocal;

    public PurchaseProductManagedBean() {
    }

    public PurchaseManagedBean getPurchaseManagedBean() {
        return purchaseManagedBean;
    }

    public void setPurchaseManagedBean(PurchaseManagedBean purchaseManagedBean) {
        this.purchaseManagedBean = purchaseManagedBean;
    }

    private List<ShoppingCartItem> getShopingCartItems() {
        return getPurchaseManagedBean().getProductManagedBean().getShoppingCartItems();
    }

    public void savePurchases(String username) {
        if (getPurchaseManagedBean().totalPrice().equals(BigDecimal.ZERO)) {
            return;

        }
        Purchase purchase = getPurchaseManagedBean().savePurchase(username);
        for (ShoppingCartItem item : getShopingCartItems()) {
            PurchaseProduct purchaseProduct = new PurchaseProduct();
            purchaseProduct.setProductId(item.getProduct());
            purchaseProduct.setPurchaseId(purchase);
            purchaseProduct.setQuantity(item.getQuantity());
            purchaseProductFacadeLocal.create(purchaseProduct);
        }
        getPurchaseManagedBean().cleanShoppingCartItems();
    }
}

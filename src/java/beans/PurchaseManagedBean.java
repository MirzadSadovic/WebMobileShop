package beans;

import beans.model.ShoppingCartItem;
import business.facade.PurchaseFacadeLocal;
import business.model.Purchase;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "purchaseManagedBean")
@RequestScoped
public class PurchaseManagedBean implements Serializable {

    @Inject
    ProductManagedBean productManagedBean;

    @EJB
    private PurchaseFacadeLocal purchaseFacadeLocal;

    private String cardNumber;
    private String message;

    public PurchaseManagedBean() {
    }

    public ProductManagedBean getProductManagedBean() {
        return productManagedBean;
    }

    public void setProductManagedBean(ProductManagedBean productManagedBean) {
        this.productManagedBean = productManagedBean;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal totalPrice() {
        try {
            BigDecimal totalPrice = getProductManagedBean()
                    .getShoppingCartItems()
                    .stream()
                    .map(ShoppingCartItem::getTotalPrice)
                    .reduce(BigDecimal::add)
                    .get();
            return totalPrice;
        } catch (NoSuchElementException e) {
            return BigDecimal.ZERO;
        }
    }

    public void cleanShoppingCartItems() {
        getProductManagedBean().getShoppingCartItems().clear();
    }

    public Purchase savePurchase(String username) {
        Purchase purchase = purchaseFacadeLocal.createPurchase(username, cardNumber, totalPrice());
        return purchase;
    }
}

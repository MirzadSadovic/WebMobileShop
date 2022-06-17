package beans;

import beans.model.ShoppingCartItem;
import business.facade.ProductFacadeLocal;
import business.model.Product;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

@Named(value = "productManagedBean")
@SessionScoped
public class ProductManagedBean implements Serializable {

    @EJB
    private ProductFacadeLocal productFacadeLocal;

    private int quantity;
    private final List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductManagedBean() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public List<Product> getAllProducts() {
        return productFacadeLocal.findAll();
    }

    public void addProductToCart(Product product) {
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            if (product.getId().equals(shoppingCartItem.getProduct().getId())) {
                if (shoppingCartItem.getQuantity() + quantity <= 3) {
                    shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + quantity);
                }
                return;
            }
        }
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, quantity);
        shoppingCartItems.add(shoppingCartItem);
    }

    public void removeProductFromCart(ShoppingCartItem shoppingCartItem1) {
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            if (shoppingCartItem1.equals(shoppingCartItem)) {
                if (shoppingCartItem.getQuantity() != 1) {
                    shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() - 1);
                } else {
                    shoppingCartItems.remove(shoppingCartItem);
                }
                return;
            }
        }
    }
}

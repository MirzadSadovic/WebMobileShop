package beans.model;

import business.model.Product;
import java.io.Serializable;
import java.math.BigDecimal;

public class ShoppingCartItem implements Serializable {

    private Product product;
    private int quantity;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}

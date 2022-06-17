package business.facade;

import business.model.Product;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProductFacadeLocal {

    void create(Product product);

    void edit(Product product);

    void remove(Product product);

    Product find(Object id);

    List<Product> findAll();
}

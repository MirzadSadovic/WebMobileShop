package business.facade;

import business.model.User;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserFacadeLocal {

    public User login(String username, String password);
    
    public boolean register(String username, String password, String name, String surname);

    public void remove(User user);

    User find(Object id);

    List<User> findAll();
}

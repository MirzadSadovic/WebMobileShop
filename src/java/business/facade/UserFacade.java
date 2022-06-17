package business.facade;

import business.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "ShopPU")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User login(String username, String password) {
        try {
            Query query = entityManager.createNamedQuery("User.findByUsernameAndPassword");
            query.setParameter("username", username);
            query.setParameter("password", password);
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean register(String username, String password, String name, String surname) {
        try {
            Query query = entityManager.createNamedQuery("User.findByUsername");
            query.setParameter("username", username);
            List<User> users = query.getResultList();
            if (users.isEmpty()) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setName(name);
                user.setSurname(surname);
                entityManager.persist(user);
                return true;
            } else {
                return false;
            }

        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}

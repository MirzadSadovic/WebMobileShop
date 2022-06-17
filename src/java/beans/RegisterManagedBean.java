package beans;

import business.facade.UserFacadeLocal;
import encryption.Encryption;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;

@Named(value = "registerManagedBean")
@ConversationScoped //ViewScoped
public class RegisterManagedBean implements Serializable {

    @EJB
    private UserFacadeLocal userFacadeLocal;

    private String username;
    private String password;
    private String name;
    private String surname;
    private String message;

    public RegisterManagedBean() {
    }

    public String register() {
        boolean userCreated = userFacadeLocal.register(username, password, name, surname);
        if (userCreated) {
            return "login";
        }
        message = "Unijeto korisničko ime je zauzeto";
        return "register";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Encryption.encrypt(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

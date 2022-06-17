package beans;

import business.facade.UserFacadeLocal;
import business.model.User;
import encryption.Encryption;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {

    @EJB
    private UserFacadeLocal userFacadeLocal;

    private String username;
    private String password;
    private String message;
    private boolean loggedIn;
    private String goToPage = "welcome";

    public LoginManagedBean() {
    }

    public String loginFromIndex() {
        User user = userFacadeLocal.login(username, password);
        if (user != null) {
            loggedIn = true;
            return goToPage;
        } else {
            message = "Pogrešno korisničko ime ili lozinka"
                    + " ili možda niste registrovani na naš Shop";
            loggedIn = false;
            return "login";
        }
    }

    public String loginFromKorpa() {
        if (isLoggedIn()) {
            return "kupovina";
        }
        goToPage = "kupovina";
        return "login";
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String logout() {
        loggedIn = false;
        goToPage = "welcome";
        return "index";
    }
}

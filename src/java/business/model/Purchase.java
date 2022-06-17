package business.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "purchase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p")
    , @NamedQuery(name = "Purchase.findById", query = "SELECT p FROM Purchase p WHERE p.id = :id")
    , @NamedQuery(name = "Purchase.findByName", query = "SELECT p FROM Purchase p WHERE p.name = :name")
    , @NamedQuery(name = "Purchase.findBySurname", query = "SELECT p FROM Purchase p WHERE p.surname = :surname")
    , @NamedQuery(name = "Purchase.findByCardNumber", query = "SELECT p FROM Purchase p WHERE p.cardNumber = :cardNumber")
    , @NamedQuery(name = "Purchase.findByTotalPrice", query = "SELECT p FROM Purchase p WHERE p.totalPrice = :totalPrice")})
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "card_number")
    private String cardNumber;
    @Basic(optional = false)
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "purchaseId")
    private List<PurchaseProduct> purchaseProductList;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Purchase() {
    }

    public Purchase(Integer id) {
        this.id = id;
    }

    public Purchase(Integer id, String name, String surname, String cardNumber, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.cardNumber = cardNumber;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @XmlTransient
    public List<PurchaseProduct> getPurchaseProductList() {
        return purchaseProductList;
    }

    public void setPurchaseProductList(List<PurchaseProduct> purchaseProductList) {
        this.purchaseProductList = purchaseProductList;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.model.Purchase[ id=" + id + " ]";
    }
}

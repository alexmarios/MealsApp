/*
Η κλάση MealCategory αναπαριστά μια κατηγορία γευμάτων και περιλαμβάνει πεδία για το ID, 
την περιγραφή, την εικόνα και την περιγραφή της κατηγορίας. Στον κώδικα χρησιμοποιούνται
annotations για την περιγραφή της κλάσης και των πεδίων της, καθώς και named queries για 
τον εντοπισμό δεδομένων στη βάση δεδομένων.
*/
package mealsapp.MealClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import com.google.gson.annotations.SerializedName;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@NamedQueries({
    @NamedQuery(name = "MealCategory.findAll", query = "SELECT m FROM MealCategory m"),
    
    @NamedQuery(name = "MealCategory.findByIdcategory", query = "SELECT m FROM MealCategory m WHERE m.idcategory = :idcategory"),
    
    @NamedQuery(name = "MealCategory.findByStrcategory", query = "SELECT m FROM MealCategory m WHERE m.strcategory = :strcategory"),
    
    @NamedQuery(name = "MealCategory.findByStrcategorythumb", query = "SELECT m FROM MealCategory m WHERE m.strcategorythumb = :strcategorythumb"),
    
    @NamedQuery(name = "MealCategory.findByStrcategorydescription", query = "SELECT m FROM MealCategory m WHERE m.strcategorydescription = :strcategorydescription"),

    @NamedQuery(name = "MealCategory.findBycategoryAndDtype", query = "SELECT m FROM MealCategory m WHERE m.strcategory = :strcategory AND TYPE(m) = MealCategory")

})
public class MealCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "IDCATEGORY")
    @SerializedName(value = "idCategory", alternate = "idcategory")
    private String idcategory;

    @Column(name = "STRCATEGORY")
    @SerializedName(value = "strCategory", alternate = "strcategory")
    private String strcategory;

    @Column(name = "STRCATEGORYTHUMB")
    @SerializedName(value = "strCategoryThumb", alternate = "strcategorythumb")
    private String strcategorythumb;

    @Column(name = "STRCATEGORYDESCRIPTION", length = 6000)
    @SerializedName(value = "strCategoryDescription", alternate = "strcategorydescription")
    private String strcategorydescription;

    public MealCategory() {
    }
    
    public MealCategory(String idcategory, String strcategory, String strcategorythumb, String strcategorydescription) {
    }

    public MealCategory(String idcategory) {
        this.idcategory = idcategory;
    }

    public String getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(String idcategory) {
        this.idcategory = idcategory;
    }

    public String getStrcategory() {
        return strcategory;
    }

    public void setStrcategory(String strcategory) {
        this.strcategory = strcategory;
    }

    public String getStrcategorythumb() {
        return strcategorythumb;
    }

    public void setStrcategorythumb(String strcategorythumb) {
        this.strcategorythumb = strcategorythumb;
    }

    public String getStrcategorydescription() {
        return strcategorydescription;
    }

    public void setStrcategorydescription(String strcategorydescription) {
        this.strcategorydescription = strcategorydescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategory != null ? idcategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MealCategory)) {
            return false;
        }
        MealCategory other = (MealCategory) object;
        if ((this.idcategory == null && other.idcategory != null) || (this.idcategory != null && !this.idcategory.equals(other.idcategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mealsapp.MealCategory[ idcategory=" + idcategory + " ]";
    }

}

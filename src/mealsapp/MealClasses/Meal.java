
package mealsapp.MealClasses;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.google.gson.annotations.SerializedName;


@Entity

@NamedQueries({
    @NamedQuery(name = "Meal.findAll", query = "SELECT m FROM Meal m"),

    @NamedQuery(name = "Meal.findByIdmeal", query = "SELECT m FROM Meal m WHERE m.idmeal = :idmeal"),
    @NamedQuery(name = "Meal.findByStrmeal", query = "SELECT m FROM Meal m WHERE m.strmeal = :strmeal"),
    @NamedQuery(name = "Meal.findByStrarea", query = "SELECT m FROM Meal m WHERE m.strarea = :strarea"),
    @NamedQuery(name = "Meal.findByStrcategory", query = "SELECT m FROM Meal m WHERE m.strcategory = :strcategory"),
    @NamedQuery(name = "Meal.findByStrinstructions", query = "SELECT m FROM Meal m WHERE m.strinstructions = :strinstructions"),
    @NamedQuery(name = "Meal.findByStrmealthumb", query = "SELECT m FROM Meal m WHERE m.strmealthumb = :strmealthumb"),
    @NamedQuery(name = "Meal.findByStryoutube", query = "SELECT m FROM Meal m WHERE m.stryoutube = :stryoutube"),
    @NamedQuery(name = "Meal.findByStringredient1", query = "SELECT m FROM Meal m WHERE m.stringredient1 = :stringredient1"),
    @NamedQuery(name = "Meal.findByStringredient2", query = "SELECT m FROM Meal m WHERE m.stringredient2 = :stringredient2"),
    @NamedQuery(name = "Meal.findByStringredient3", query = "SELECT m FROM Meal m WHERE m.stringredient3 = :stringredient3"),
    @NamedQuery(name = "Meal.findByStringredient4", query = "SELECT m FROM Meal m WHERE m.stringredient4 = :stringredient4"),
    @NamedQuery(name = "Meal.findByStringredient5", query = "SELECT m FROM Meal m WHERE m.stringredient5 = :stringredient5"),
    @NamedQuery(name = "Meal.findByStringredient6", query = "SELECT m FROM Meal m WHERE m.stringredient6 = :stringredient6"),
    @NamedQuery(name = "Meal.findByStringredient7", query = "SELECT m FROM Meal m WHERE m.stringredient7 = :stringredient7"),
    @NamedQuery(name = "Meal.findByStringredient8", query = "SELECT m FROM Meal m WHERE m.stringredient8 = :stringredient8"),
    @NamedQuery(name = "Meal.findByStringredient9", query = "SELECT m FROM Meal m WHERE m.stringredient9 = :stringredient9"),
    @NamedQuery(name = "Meal.findByStringredient10", query = "SELECT m FROM Meal m WHERE m.stringredient10 = :stringredient10"),
    @NamedQuery(name = "Meal.findByStringredient11", query = "SELECT m FROM Meal m WHERE m.stringredient11 = :stringredient11"),
    @NamedQuery(name = "Meal.findByStringredient12", query = "SELECT m FROM Meal m WHERE m.stringredient12 = :stringredient12"),
    @NamedQuery(name = "Meal.findByStringredient13", query = "SELECT m FROM Meal m WHERE m.stringredient13 = :stringredient13"),
    @NamedQuery(name = "Meal.findByStringredient14", query = "SELECT m FROM Meal m WHERE m.stringredient14 = :stringredient14"),
    @NamedQuery(name = "Meal.findByStringredient15", query = "SELECT m FROM Meal m WHERE m.stringredient15 = :stringredient15"),
    @NamedQuery(name = "Meal.findByStringredient16", query = "SELECT m FROM Meal m WHERE m.stringredient16 = :stringredient16"),
    @NamedQuery(name = "Meal.findByStringredient17", query = "SELECT m FROM Meal m WHERE m.stringredient17 = :stringredient17"),
    @NamedQuery(name = "Meal.findByStringredient18", query = "SELECT m FROM Meal m WHERE m.stringredient18 = :stringredient18"),
    @NamedQuery(name = "Meal.findByStringredient19", query = "SELECT m FROM Meal m WHERE m.stringredient19 = :stringredient19"),
    @NamedQuery(name = "Meal.findByStringredient20", query = "SELECT m FROM Meal m WHERE m.stringredient20 = :stringredient20"),
    @NamedQuery(name = "Meal.findByStrmeasure1", query = "SELECT m FROM Meal m WHERE m.strmeasure1 = :strmeasure1"),
    @NamedQuery(name = "Meal.findByStrmeasure2", query = "SELECT m FROM Meal m WHERE m.strmeasure2 = :strmeasure2"),
    @NamedQuery(name = "Meal.findByStrmeasure3", query = "SELECT m FROM Meal m WHERE m.strmeasure3 = :strmeasure3"),
    @NamedQuery(name = "Meal.findByStrmeasure4", query = "SELECT m FROM Meal m WHERE m.strmeasure4 = :strmeasure4"),
    @NamedQuery(name = "Meal.findByStrmeasure5", query = "SELECT m FROM Meal m WHERE m.strmeasure5 = :strmeasure5"),
    @NamedQuery(name = "Meal.findByStrmeasure6", query = "SELECT m FROM Meal m WHERE m.strmeasure6 = :strmeasure6"),
    @NamedQuery(name = "Meal.findByStrmeasure7", query = "SELECT m FROM Meal m WHERE m.strmeasure7 = :strmeasure7"),
    @NamedQuery(name = "Meal.findByStrmeasure8", query = "SELECT m FROM Meal m WHERE m.strmeasure8 = :strmeasure8"),
    @NamedQuery(name = "Meal.findByStrmeasure9", query = "SELECT m FROM Meal m WHERE m.strmeasure9 = :strmeasure9"),
    @NamedQuery(name = "Meal.findByStrmeasure10", query = "SELECT m FROM Meal m WHERE m.strmeasure10 = :strmeasure10"),
    @NamedQuery(name = "Meal.findByStrmeasure11", query = "SELECT m FROM Meal m WHERE m.strmeasure11 = :strmeasure11"),
    @NamedQuery(name = "Meal.findByStrmeasure12", query = "SELECT m FROM Meal m WHERE m.strmeasure12 = :strmeasure12"),
    @NamedQuery(name = "Meal.findByStrmeasure13", query = "SELECT m FROM Meal m WHERE m.strmeasure13 = :strmeasure13"),
    @NamedQuery(name = "Meal.findByStrmeasure14", query = "SELECT m FROM Meal m WHERE m.strmeasure14 = :strmeasure14"),
    @NamedQuery(name = "Meal.findByStrmeasure15", query = "SELECT m FROM Meal m WHERE m.strmeasure15 = :strmeasure15"),
    @NamedQuery(name = "Meal.findByStrmeasure16", query = "SELECT m FROM Meal m WHERE m.strmeasure16 = :strmeasure16"),
    @NamedQuery(name = "Meal.findByStrmeasure17", query = "SELECT m FROM Meal m WHERE m.strmeasure17 = :strmeasure17"),
    @NamedQuery(name = "Meal.findByStrmeasure18", query = "SELECT m FROM Meal m WHERE m.strmeasure18 = :strmeasure18"),
    @NamedQuery(name = "Meal.findByStrmeasure19", query = "SELECT m FROM Meal m WHERE m.strmeasure19 = :strmeasure19"),
    @NamedQuery(name = "Meal.findByStrmeasure20", query = "SELECT m FROM Meal m WHERE m.strmeasure20 = :strmeasure20"),

    @NamedQuery(name = "Meal.findByTimesofvisit", query = "SELECT m FROM Meal m WHERE m.timesofvisit = :timesofvisit"),
    @NamedQuery(name = "Meal.sortByTimesofVisit", query = "SELECT m FROM Meal m ORDER BY m.timesofvisit DESC, m.strmeal"),
    @NamedQuery(name = "Meal.getTimesOfVisit", query = "SELECT m.timesofvisit FROM Meal m WHERE m.strmeal = :strmeal"),
    @NamedQuery(name = "Meal.getIdmeal", query = "SELECT m.idmeal FROM Meal m WHERE m.strmeal = :strmeal"),
    @NamedQuery(name = "Meal.getLikeStrMeal", query = "SELECT m FROM Meal m WHERE m.strmeal LIKE :strmeal ORDER BY m.strmeal ASC"),
    @NamedQuery(name = "Meal.findByAbsoluteStrmeal", query = "SELECT m FROM Meal m WHERE ABS (m.strmeal) = :strmeal"),
    @NamedQuery(name = "Meal.findBygetIdcategory", query = "SELECT m.idcategory FROM Meal m WHERE m.idcategory = :idcategory")
    

        

})
public class Meal extends MealCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "IDMEAL")
    @SerializedName(value = "idMeal", alternate = "idmeal")
    private String idmeal;

    @Column(name = "STRMEAL")
    @SerializedName(value = "strMeal", alternate = "strmeal")
    private String strmeal;

    @Column(name = "STRAREA")
    @SerializedName(value = "strArea", alternate = "strarea")
    private String strarea;

    @Column(name = "STRINSTRUCTIONS", length = 6000)
    @SerializedName(value = "strInstructions", alternate = "strinstructions")
    private String strinstructions;

    @Column(name = "STRMEALTHUMB")
    @SerializedName(value = "strMealThumb", alternate = "strmealthumb")
    private String strmealthumb;

    @Column(name = "STRYOUTUBE")
    @SerializedName(value = "strYoutube", alternate = "stryoutube")
    private String stryoutube;

    @Column(name = "STRINGREDIENT1")
    @SerializedName(value = "strIngredient1", alternate = "stringredient1")
    private String stringredient1;

    @Column(name = "STRINGREDIENT2")
    @SerializedName(value = "strIngredient2", alternate = "stringredient2")
    private String stringredient2;

    @Column(name = "STRINGREDIENT3")
    @SerializedName(value = "strIngredient3", alternate = "stringredient3")
    private String stringredient3;

    @Column(name = "STRINGREDIENT4")
    @SerializedName(value = "strIngredient4", alternate = "stringredient4")
    private String stringredient4;

    @Column(name = "STRINGREDIENT5")
    @SerializedName(value = "strIngredient5", alternate = "stringredient5")
    private String stringredient5;

    @Column(name = "STRINGREDIENT6")
    @SerializedName(value = "strIngredient6", alternate = "stringredient6")
    private String stringredient6;

    @Column(name = "STRINGREDIENT7")
    @SerializedName(value = "strIngredient7", alternate = "stringredient7")
    private String stringredient7;

    @Column(name = "STRINGREDIENT8")
    @SerializedName(value = "strIngredient8", alternate = "stringredient8")
    private String stringredient8;

    @Column(name = "STRINGREDIENT9")
    @SerializedName(value = "strIngredient9", alternate = "stringredient9")
    private String stringredient9;

    @Column(name = "STRINGREDIENT10")
    @SerializedName(value = "strIngredient10", alternate = "stringredient10")
    private String stringredient10;

    @Column(name = "STRINGREDIENT11")
    @SerializedName(value = "strIngredient11", alternate = "stringredient11")
    private String stringredient11;

    @Column(name = "STRINGREDIENT12")
    @SerializedName(value = "strIngredient12", alternate = "stringredient12")
    private String stringredient12;

    @Column(name = "STRINGREDIENT13")
    @SerializedName(value = "strIngredient13", alternate = "stringredient13")
    private String stringredient13;

    @Column(name = "STRINGREDIENT14")
    @SerializedName(value = "strIngredient14", alternate = "stringredient14")
    private String stringredient14;

    @Column(name = "STRINGREDIENT15")
    @SerializedName(value = "strIngredient15", alternate = "stringredient15")
    private String stringredient15;

    @Column(name = "STRINGREDIENT16")
    @SerializedName(value = "strIngredient16", alternate = "stringredient16")
    private String stringredient16;

    @Column(name = "STRINGREDIENT17")
    @SerializedName(value = "strIngredient17", alternate = "stringredient17")
    private String stringredient17;

    @Column(name = "STRINGREDIENT18")
    @SerializedName(value = "strIngredient18", alternate = "stringredient18")
    private String stringredient18;

    @Column(name = "STRINGREDIENT19")
    @SerializedName(value = "strIngredient19", alternate = "stringredient19")
    private String stringredient19;

    @Column(name = "STRINGREDIENT20")
    @SerializedName(value = "strIngredient20", alternate = "stringredient20")
    private String stringredient20;

    @Column(name = "STRMEASURE1")
    @SerializedName(value = "strMeasure1", alternate = "strmeasure1")
    private String strmeasure1;
    @Column(name = "STRMEASURE2")
    @SerializedName(value = "strMeasure2", alternate = "strmeasure2")
    private String strmeasure2;
    @Column(name = "STRMEASURE3")
    @SerializedName(value = "strMeasure3", alternate = "strmeasure3")
    private String strmeasure3;
    @Column(name = "STRMEASURE4")
    @SerializedName(value = "strMeasure4", alternate = "strmeasure4")
    private String strmeasure4;
    @Column(name = "STRMEASURE5")
    @SerializedName(value = "strMeasure5", alternate = "strmeasure5")
    private String strmeasure5;
    @Column(name = "STRMEASURE6")
    @SerializedName(value = "strMeasure6", alternate = "strmeasure6")
    private String strmeasure6;
    @Column(name = "STRMEASURE7")
    @SerializedName(value = "strMeasure7", alternate = "strmeasure7")
    private String strmeasure7;
    @Column(name = "STRMEASURE8")
    @SerializedName(value = "strMeasure8", alternate = "strmeasure8")
    private String strmeasure8;
    @Column(name = "STRMEASURE9")
    @SerializedName(value = "strMeasure9", alternate = "strmeasure9")
    private String strmeasure9;
    @Column(name = "STRMEASURE10")
    @SerializedName(value = "strMeasure10", alternate = "strmeasure10")
    private String strmeasure10;
    @Column(name = "STRMEASURE11")
    @SerializedName(value = "strMeasure11", alternate = "strmeasure11")
    private String strmeasure11;
    @Column(name = "STRMEASURE12")
    @SerializedName(value = "strMeasure12", alternate = "strmeasure12")
    private String strmeasure12;
    @Column(name = "STRMEASURE13")
    @SerializedName(value = "strMeasure13", alternate = "strmeasure13")
    private String strmeasure13;
    @Column(name = "STRMEASURE14")
    @SerializedName(value = "strMeasure14", alternate = "strmeasure14")
    private String strmeasure14;
    @Column(name = "STRMEASURE15")
    @SerializedName(value = "strMeasure15", alternate = "strmeasure15")
    private String strmeasure15;
    @Column(name = "STRMEASURE16")
    @SerializedName(value = "strMeasure16", alternate = "strmeasure16")
    private String strmeasure16;
    @Column(name = "STRMEASURE17")
    @SerializedName(value = "strMeasure17", alternate = "strmeasure17")
    private String strmeasure17;
    @Column(name = "STRMEASURE18")
    @SerializedName(value = "strMeasure18", alternate = "strmeasure18")
    private String strmeasure18;
    @Column(name = "STRMEASURE19")
    @SerializedName(value = "strMeasure19", alternate = "strmeasure19")
    private String strmeasure19;
    @Column(name = "STRMEASURE20")
    @SerializedName(value = "strMeasure20", alternate = "strmeasure20")
    private String strmeasure20;

    @Column(name = "TIMESOFVISIT")
    
    private Integer timesofvisit;

    public Meal() {
        this.timesofvisit = 0;
    }

    public Meal(String strmeal) {
        this.strmeal = strmeal;
        this.timesofvisit = 0;
    }

    public String getIdmeal() {
        return idmeal;
    }

    public void setIdmeal(String idmeal) {
        this.idmeal = idmeal;
    }

    public String getStrmeal() {
        return strmeal;
    }

    public void setStrmeal(String strmeal) {
        this.strmeal = strmeal;
    }

    public String getStrarea() {
        return strarea;
    }

    public void setStrarea(String strarea) {
        this.strarea = strarea;
    }

    public String getStrinstructions() {
        return strinstructions;
    }

    public void setStrinstructions(String strinstructions) {
        this.strinstructions = strinstructions;
    }

    public String getStrmealthumb() {
        return strmealthumb;
    }

    public void setStrmealthumb(String strmealthumb) {
        this.strmealthumb = strmealthumb;
    }

    public String getStryoutube() {
        return stryoutube;
    }

    public void setStryoutube(String stryoutube) {
        this.stryoutube = stryoutube;
    }

    public String getStringredient1() {
        return stringredient1;
    }

    public void setStringredient1(String stringredient1) {
        this.stringredient1 = stringredient1;
    }

    public String getStringredient2() {
        return stringredient2;
    }

    public void setStringredient2(String stringredient2) {
        this.stringredient2 = stringredient2;
    }

    public String getStringredient3() {
        return stringredient3;
    }

    public void setStringredient3(String stringredient3) {
        this.stringredient3 = stringredient3;
    }

    public String getStringredient4() {
        return stringredient4;
    }

    public void setStringredient4(String stringredient4) {
        this.stringredient4 = stringredient4;
    }

    public String getStringredient5() {
        return stringredient5;
    }

    public void setStringredient5(String stringredient5) {
        this.stringredient5 = stringredient5;
    }

    public String getStringredient6() {
        return stringredient6;
    }

    public void setStringredient6(String stringredient6) {
        this.stringredient6 = stringredient6;
    }

    public String getStringredient7() {
        return stringredient7;
    }

    public void setStringredient7(String stringredient7) {
        this.stringredient7 = stringredient7;
    }

    public String getStringredient8() {
        return stringredient8;
    }

    public void setStringredient8(String stringredient8) {
        this.stringredient8 = stringredient8;
    }

    public String getStringredient9() {
        return stringredient9;
    }

    public void setStringredient9(String stringredient9) {
        this.stringredient9 = stringredient9;
    }

    public String getStringredient10() {
        return stringredient10;
    }

    public void setStringredient10(String stringredient10) {
        this.stringredient10 = stringredient10;
    }

    public String getStringredient11() {
        return stringredient11;
    }

    public void setStringredient11(String stringredient11) {
        this.stringredient11 = stringredient11;
    }

    public String getStringredient12() {
        return stringredient12;
    }

    public void setStringredient12(String stringredient12) {
        this.stringredient12 = stringredient12;
    }

    public String getStringredient13() {
        return stringredient13;
    }

    public void setStringredient13(String stringredient13) {
        this.stringredient13 = stringredient13;
    }

    public String getStringredient14() {
        return stringredient14;
    }

    public void setStringredient14(String stringredient14) {
        this.stringredient14 = stringredient14;
    }

    public String getStringredient15() {
        return stringredient15;
    }

    public void setStringredient15(String stringredient15) {
        this.stringredient15 = stringredient15;
    }

    public String getStringredient16() {
        return stringredient16;
    }

    public void setStringredient16(String stringredient16) {
        this.stringredient16 = stringredient16;
    }

    public String getStringredient17() {
        return stringredient17;
    }

    public void setStringredient17(String stringredient17) {
        this.stringredient17 = stringredient17;
    }

    public String getStringredient18() {
        return stringredient18;
    }

    public void setStringredient18(String stringredient18) {
        this.stringredient18 = stringredient18;
    }

    public String getStringredient19() {
        return stringredient19;
    }

    public void setStringredient19(String stringredient19) {
        this.stringredient19 = stringredient19;
    }

    public String getStringredient20() {
        return stringredient20;
    }

    public void setStringredient20(String stringredient20) {
        this.stringredient20 = stringredient20;
    }

    public String getStrmeasure1() {
        return strmeasure1;
    }

    public void setStrmeasure1(String strmeasure1) {
        this.strmeasure1 = strmeasure1;
    }

    public String getStrmeasure2() {
        return strmeasure2;
    }

    public void setStrmeasure2(String strmeasure2) {
        this.strmeasure2 = strmeasure2;
    }

    public String getStrmeasure3() {
        return strmeasure3;
    }

    public void setStrmeasure3(String strmeasure3) {
        this.strmeasure3 = strmeasure3;
    }

    public String getStrmeasure4() {
        return strmeasure4;
    }

    public void setStrmeasure4(String strmeasure4) {
        this.strmeasure4 = strmeasure4;
    }

    public String getStrmeasure5() {
        return strmeasure5;
    }

    public void setStrmeasure5(String strmeasure5) {
        this.strmeasure5 = strmeasure5;
    }

    public String getStrmeasure6() {
        return strmeasure6;
    }

    public void setStrmeasure6(String strmeasure6) {
        this.strmeasure6 = strmeasure6;
    }

    public String getStrmeasure7() {
        return strmeasure7;
    }

    public void setStrmeasure7(String strmeasure7) {
        this.strmeasure7 = strmeasure7;
    }

    public String getStrmeasure8() {
        return strmeasure8;
    }

    public void setStrmeasure8(String strmeasure8) {
        this.strmeasure8 = strmeasure8;
    }

    public String getStrmeasure9() {
        return strmeasure9;
    }

    public void setStrmeasure9(String strmeasure9) {
        this.strmeasure9 = strmeasure9;
    }

    public String getStrmeasure10() {
        return strmeasure10;
    }

    public void setStrmeasure10(String strmeasure10) {
        this.strmeasure10 = strmeasure10;
    }

    public String getStrmeasure11() {
        return strmeasure11;
    }

    public void setStrmeasure11(String strmeasure11) {
        this.strmeasure11 = strmeasure11;
    }

    public String getStrmeasure12() {
        return strmeasure12;
    }

    public void setStrmeasure12(String strmeasure12) {
        this.strmeasure12 = strmeasure12;
    }

    public String getStrmeasure13() {
        return strmeasure13;
    }

    public void setStrmeasure13(String strmeasure13) {
        this.strmeasure13 = strmeasure13;
    }

    public String getStrmeasure14() {
        return strmeasure14;
    }

    public void setStrmeasure14(String strmeasure14) {
        this.strmeasure14 = strmeasure14;
    }

    public String getStrmeasure15() {
        return strmeasure15;
    }

    public void setStrmeasure15(String strmeasure15) {
        this.strmeasure15 = strmeasure15;
    }

    public String getStrmeasure16() {
        return strmeasure16;
    }

    public void setStrmeasure16(String strmeasure16) {
        this.strmeasure16 = strmeasure16;
    }

    public String getStrmeasure17() {
        return strmeasure17;
    }

    public void setStrmeasure17(String strmeasure17) {
        this.strmeasure17 = strmeasure17;
    }

    public String getStrmeasure18() {
        return strmeasure18;
    }

    public void setStrmeasure18(String strmeasure18) {
        this.strmeasure18 = strmeasure18;
    }

    public String getStrmeasure19() {
        return strmeasure19;
    }

    public void setStrmeasure19(String strmeasure19) {
        this.strmeasure19 = strmeasure19;
    }

    public String getStrmeasure20() {
        return strmeasure20;
    }

    public void setStrmeasure20(String strmeasure20) {
        this.strmeasure20 = strmeasure20;
    }

    public Integer getTimesofvisit() {
        return timesofvisit;
    }

    public void setTimesofvisit(Integer timesofvisit) {
        this.timesofvisit = timesofvisit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (strmeal != null ? strmeal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meal)) {
            return false;
        }
        Meal other = (Meal) object;
        if ((this.strmeal == null && other.strmeal != null) || (this.strmeal != null && !this.strmeal.equals(other.strmeal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mealsapp.Meal[ strmeal=" + strmeal + " ]";
    }

}

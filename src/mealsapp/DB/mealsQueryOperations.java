package mealsapp.DB;

import mealsapp.MealClasses.Meal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class mealsQueryOperations {

    //Μέθοδος για την εισαγωγή ενός νέου αντικειμένου Meal στη βάση δεδομένων
    public static void insertNewMeal(Meal newMeal) {
        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Χρήση του named query για την εύρεση ενός αντικειμένου Meal βάσει του strmeal του
            newMeal = em.createNamedQuery("Meal.findByStrmeal", Meal.class)
                    .setParameter("strmeal", newMeal.getStrmeal())
                    .getSingleResult();

            if (newMeal != null) {
                // Αν υπάρχει ήδη αντικείμενο Meal με το ίδιο strmeal, δε γίνεται εισαγωγή στη βάση και επιστρέφεται η συνάρτηση
                return;
            }
        } catch (NoResultException e) {
            // Αν δε βρεθεί κάποιο αντικείμενο Meal με το συγκεκριμένο strmeal, δε γίνεται τίποτα και συνεχίζεται η εισαγωγή στη βάση
        }

        try {
            // Χρήση του named query "Meal.findBygetIdcategory" για την εύρεση όλων των Meal αντικειμένων που έχουν το ίδιο idcategory με το νέο αντικείμενο
            List<Meal> newMea2Meal = em.createNamedQuery("Meal.findBygetIdcategory", Meal.class)
                    .setParameter("idcategory", newMeal.getIdcategory())
                    .getResultList();

            if (newMea2Meal != null) {
                // Αν υπάρχουν ήδη αντικείμενα Meal με το ίδιο idcategory με το νέο αντικείμενο, αυτό σημαίνει ότι το νέο αντικείμενο έχει εισαχθεί από το χρήστη
                // οπότε θέτουμε τα απαραίτητα πεδία (timesofvisit και idmeal) και κάνουμε merge
                em.getTransaction().begin();
                newMeal.setTimesofvisit(0);
                newMeal.setIdmeal("SavedByUser");
                em.merge(newMeal);
                em.getTransaction().commit();
                em.close();
                return;
            }
        } catch (NoResultException e) {
            // Αν δε βρεθεί κάποιο αντικείμενο Meal με το συγκεκριμένο idcategory, δε γίνεται τίποτα και συνεχίζεται η εισαγωγή στη βάση
        }
        // Αν δε βρεθεί ούτε αντικείμενο Meal με το ίδιο strmeal ούτε αντικείμενο Meal με το ίδιο idcategory, εισάγουμε το νέο αντικείμενο Meal στη βάση
        em.getTransaction().begin();
        em.persist(newMeal);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    //Μέθοδος για τη διαγραφή ενός αντικειμένου Meal 
    public static void deleteSingleMeal(String strMeal) {
        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();
        try {
            // Χρήση του named query για την εύρεση του αντικειμένου Meal βάσει του συγκεκριμένου strmeal
            Meal meal = em.createNamedQuery("Meal.findByStrmeal", Meal.class).setParameter("strmeal", strMeal).getSingleResult();

            em.getTransaction().begin();

            // Διαγραφή του αντικειμένου Meal από τη βάση
            em.remove(meal);
            em.getTransaction().commit();

        } catch (NoResultException e) {
            // Αν δε βρεθεί κάποιο αντικείμενο Meal με το συγκεκριμένο strmeal, δε γίνεται τίποτα και συνεχίζεται η συνάρτηση
        } finally {
            // Κλείσιμο του entity manager και του entity manager factory
            em.close();
            emf.close();
        }
    }

    //Μέθοδος για τον έλεγχο ύπαρξης στη βάση δεδομένων ενός αντικειμένου Meal
    public static boolean findMealByStrMeal(String strmeal) {
        // Αρχικά θεωρούμε ότι το αντικείμενο Meal με το συγκεκριμένο strmeal δεν υπάρχει στη βάση
        boolean result = false;

        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Χρήση του named query για την εύρεση του αντικειμένου Meal βάσει του συγκεκριμένου strmeal
            Meal meal = em.createNamedQuery("Meal.findByStrmeal", Meal.class)
                    .setParameter("strmeal", strmeal)
                    .getSingleResult();
            if (meal.getStrmeal().equals(strmeal)) {
                // Αν βρεθεί αντικείμενο Meal με το συγκεκριμένο strmeal, τότε το result γίνεται true
                result = true;
            }
            em.getTransaction().begin();
            em.getTransaction().commit();

        } catch (NoResultException e) {
            // Αν δε βρεθεί κάποιο αντικείμενο Meal με το συγκεκριμένο strmeal, δε γίνεται τίποτα και συνεχίζεται η συνάρτηση
        } finally {
            // Κλείσιμο του entity manager και του entity manager factory και επιστροφή του result
            em.close();
            emf.close();
            return result;
        }
    }

    //Μέθοδος για τον έλεγχο εαν το αντικείμενο Meal έχει αποθηκευτεί από τον χρήστη
    public static boolean findIfMealIsSavedByUser(String meal) {
        // Αρχικά θεωρούμε ότι το αντικείμενο Meal δεν έχει αποθηκευτεί από τον χρήστη
        boolean result = false;

        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Χρήση του named query για την εύρεση του αντικειμένου Meal βάσει του συγκεκριμένου strmeal
            Meal searchMeal = em.createNamedQuery("Meal.findByStrmeal", Meal.class)
                    .setParameter("strmeal", meal)
                    .getSingleResult();
            if (searchMeal.getIdmeal() == null || searchMeal.getIdmeal().equals("SavedByUser")){
                // Αν το idmeal είναι null, τότε το αντικείμενο Meal δεν έχει αποθηκευτεί από τον χρήστη
                result = false;

            } else {
                // Αν το idmeal δεν είναι null, τότε το αντικείμενο Meal έχει αποθηκευτεί από τον χρήστη
                result = true;

            }
        } catch (NoResultException e) {
            // Αν δε βρεθεί κάποιο αντικείμενο Meal με το συγκεκριμένο strmeal, δε γίνεται τίποτα και συνεχίζεται η συνάρτηση
        } finally {
            // Κλείσιμο του entity manager και του entity manager factory και επιστροφή του result
            em.close();
            emf.close();
            return result;
        }
    }

    //Μέθοδος για την αύξηση του αριθμού επισκέψεων ενός γεύματος
    public static void addVisit(String strMeal) {

        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            // Χρήση του named query για την εύρεση του αντικειμένου Meal βάσει του συγκεκριμένου strmeal
            Meal meal = em.createNamedQuery("Meal.findByStrmeal", Meal.class)
                    .setParameter("strmeal", strMeal)
                    .getSingleResult();

            // Αύξηση του αριθμού επισκέψεων του αντικειμένου Meal κατά 1
            meal.setTimesofvisit(meal.getTimesofvisit() + 1);

            em.persist(meal);
            em.getTransaction().commit();


        } catch (NoResultException e) {
            // Αν δε βρεθεί κάποιο αντικείμενο Meal με το συγκεκριμένο strmeal, δε γίνεται τίποτα και συνεχίζεται η συνάρτηση
        } finally {
            // Κλείσιμο του entity manager και του entity manager factory
            em.close();
            emf.close();
        }

    }

    //Μέθοδος για την επιστροφή μιας λίστας με τα αντικείμενα Meal ταξινομημένα κατά φθίνουσα σειρά βάσει του αριθμού επισκέψεων
    public static List<Meal> getbyDecOrder() {

        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Δήλωση μιας μεταβλητής που θα περιέχει τα αντικείμενα Meal που θα επιστραφούν από το query
        List<Meal> meals = null;

        try {
            // Χρήση του named query για την επιστροφή των αντικειμένων Meal ταξινομημένων κατά φθίνουσα σειρά βάσει του αριθμού επισκέψεων
            meals = em.createNamedQuery("Meal.sortByTimesofVisit", Meal.class).getResultList();
        } catch (NoResultException e) {
            // Αν δε βρεθεί κανένα αντικείμενο Meal, δε γίνεται τίποτα και συνεχίζει
        } finally {
            // Commit και κλείσιμο του entity manager και του entity manager factory
            em.getTransaction().commit();
            em.close();
            emf.close();
        }

        // Επιστροφή της λίστας με τα αντικείμενα Meal
        return meals;
    }

    //Μέθοδος για την επιστροφή μιας λίστας παρόμοιων γευμάτων από τη βάση δεδομένων
    public static List<Meal> getLikeMealsFromDB(String strmeal) {

        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();


        List<Meal> meals = null;

        try {
            // Αναζήτηση παρόμοιων γευμάτων με βάση το κείμενο αναζήτησης strmeal, είτε με μικρά γράμματα είτε με πρώτο γράμμα κεφαλαίο
            if (!strmeal.isEmpty()) {
                meals = em.createNamedQuery("Meal.getLikeStrMeal", Meal.class)
                        .setParameter("strmeal", "%" + strmeal.toLowerCase() + "%")
                        .setParameter("strmeal", "%" + strmeal.substring(0, 1).toUpperCase() + strmeal.substring(1).toLowerCase() + "%")
                        .getResultList();
            } else {
                // Αν το κείμενο αναζήτησης είναι κενό, επιστρέφονται όλα τα γεύματα από τη βάση δεδομένων
                meals = em.createNamedQuery("Meal.getLikeStrMeal", Meal.class)
                        .setParameter("strmeal", "%" + strmeal + "%").getResultList();
            }
        } catch (NoResultException e) {
            // Σε περίπτωση που δεν βρεθεί κανένα παρόμοιο γεύμα, δεν γίνεται τίποτα
        } finally {
            // Commit και κλείσιμο του entity manager και του entity manager factory
            em.getTransaction().commit();
            em.close();
            emf.close();
        }

        // Επιστροφή λίστας με τα παρόμοια γεύματα που βρέθηκαν στη βάση δεδομένων
        return meals;

    }

    //Μέθοδος για την επιστροφή ενός γεύματος από τη βάση δεδομένων βάσει του ονόματός του
    public static Meal getMealByStrMeal(String strmeal) {

        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();

        Meal meal = null;

        try {
            // Αναζήτηση του γεύματος με βάση το όνομα του γεύματος 
            meal = em.createNamedQuery("Meal.findByStrmeal", Meal.class)
                    .setParameter("strmeal", strmeal)
                    .getSingleResult();

            em.getTransaction().begin();
            em.getTransaction().commit();

        } catch (NoResultException e) {
            // Σε περίπτωση που δεν βρεθεί κανένα γεύμα, δεν γίνεται τίποτα
        } finally {
            // Κλείσιμο entity manager και entity manager factory
            em.close();
            emf.close();

        }
        // Επιστροφή του γεύματος που βρέθηκε στη βάση δεδομένων
        return meal;
    }

    //Μέθοδος για την αποθήκευση ενός γεύματος στη βάση δεδομένων
    public static void saveMeal(Meal meal) {

        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            // Αναζήτηση του γεύματος στη βάση δεδομένων με βάση το όνομα του γεύματος
            Meal oldmeal = em.createNamedQuery("Meal.findByStrmeal", Meal.class)
                    .setParameter("strmeal", meal.getStrmeal())
                    .getSingleResult();

            // Ενημέρωση του πλήθους επισκέψεων στο γεύμα
            meal.setTimesofvisit(oldmeal.getTimesofvisit());

            // Ενημέρωση των πεδίων του παλιού γεύματος με τα νέα δεδομένα από το νέο γεύμα
            oldmeal.setIdmeal(meal.getIdmeal());
            oldmeal.setStrcategory(meal.getStrcategory());
            oldmeal.setStrmeal(meal.getStrmeal());
            oldmeal.setStrarea(meal.getStrarea());
            oldmeal.setStrinstructions(meal.getStrinstructions());
            oldmeal.setStrmealthumb(meal.getStrmealthumb());
            oldmeal.setStryoutube(meal.getStryoutube());
            oldmeal.setStringredient1(meal.getStringredient1());
            oldmeal.setStringredient2(meal.getStringredient2());
            oldmeal.setStringredient3(meal.getStringredient3());
            oldmeal.setStringredient4(meal.getStringredient4());
            oldmeal.setStringredient5(meal.getStringredient5());
            oldmeal.setStringredient6(meal.getStringredient6());
            oldmeal.setStringredient7(meal.getStringredient7());
            oldmeal.setStringredient8(meal.getStringredient8());
            oldmeal.setStringredient9(meal.getStringredient9());
            oldmeal.setStringredient10(meal.getStringredient10());
            oldmeal.setStringredient11(meal.getStringredient11());
            oldmeal.setStringredient12(meal.getStringredient12());
            oldmeal.setStringredient13(meal.getStringredient13());
            oldmeal.setStringredient14(meal.getStringredient14());
            oldmeal.setStringredient15(meal.getStringredient15());
            oldmeal.setStringredient16(meal.getStringredient16());
            oldmeal.setStringredient17(meal.getStringredient17());
            oldmeal.setStringredient18(meal.getStringredient18());
            oldmeal.setStringredient19(meal.getStringredient19());
            oldmeal.setStringredient20(meal.getStringredient20());
            oldmeal.setStrmeasure1(meal.getStrmeasure1());
            oldmeal.setStrmeasure2(meal.getStrmeasure2());
            oldmeal.setStrmeasure3(meal.getStrmeasure3());
            oldmeal.setStrmeasure4(meal.getStrmeasure4());
            oldmeal.setStrmeasure5(meal.getStrmeasure5());
            oldmeal.setStrmeasure6(meal.getStrmeasure6());
            oldmeal.setStrmeasure7(meal.getStrmeasure7());
            oldmeal.setStrmeasure8(meal.getStrmeasure8());
            oldmeal.setStrmeasure9(meal.getStrmeasure9());
            oldmeal.setStrmeasure10(meal.getStrmeasure10());
            oldmeal.setStrmeasure11(meal.getStrmeasure11());
            oldmeal.setStrmeasure12(meal.getStrmeasure12());
            oldmeal.setStrmeasure13(meal.getStrmeasure13());
            oldmeal.setStrmeasure14(meal.getStrmeasure14());
            oldmeal.setStrmeasure15(meal.getStrmeasure15());
            oldmeal.setStrmeasure16(meal.getStrmeasure16());
            oldmeal.setStrmeasure17(meal.getStrmeasure17());
            oldmeal.setStrmeasure18(meal.getStrmeasure18());
            oldmeal.setStrmeasure19(meal.getStrmeasure19());
            oldmeal.setStrmeasure20(meal.getStrmeasure20());

            // Αποθήκευση των αλλαγών στη βάση δεδομένων
            em.merge(oldmeal);
            em.getTransaction().commit();


        } catch (NoResultException e) {
            // Σε περίπτωση που δεν βρεθεί κανένα γεύμα, δεν γίνεται τίποτα
        } finally {
            // Κλείσιμο entity manager και entity manager factory
            em.close();
            emf.close();
        }
    }
}

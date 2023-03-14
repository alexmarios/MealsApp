package mealsapp.DB;

import mealsapp.MealClasses.MealCategory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class mealsCategoryQueryOperations {

    // Μέθοδος για την επιστροφή μιας ταξινομημένης λίστας MealCategory βάση του id
    public static List<MealCategory> sortByIdcategory() {
        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();

        // Δημιουργία του ερωτήματος για την ταξινόμηση των MealCategory αντικειμένων βάσει του idcategory
        TypedQuery<MealCategory> query = em.createNamedQuery("MealCategory.sortByIdcategory", MealCategory.class);
        List<MealCategory> resultList = query.getResultList();

        // Κλείσιμο του entity manager και του entity manager factory
        em.close();
        emf.close();

        // Επιστροφή της ταξινομημένης λίστας
        return resultList;
    }

    // Μέθοδος για την εισαγωγή της λίστας MealCategory στη βάση δεδομένων
    public static void importMealCategories(List<MealCategory> mealCategories) {
        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();

        // Εισαγωγή κάθε MealCategory αντικειμένου στη βάση δεδομένων (αν δεν υπάρχει ήδη)
        for (int i = 0; i < mealCategories.size(); i++) {

            try {
                // Έλεγχος αν υπάρχει ήδη στη βάση δεδομένων κάποιο MealCategory με το ίδιο category και dtype
                List<MealCategory> existingMealCategories = em.createNamedQuery("MealCategory.findBycategoryAndDtype", MealCategory.class)
                        .setParameter("strcategory", mealCategories.get(i).getStrcategory())
                        .getResultList();
                // Αν υπάρχει ήδη, συνεχίζεται η επανάληψη χωρίς να γίνεται εισαγωγή του στη βάση
                if (!existingMealCategories.isEmpty()) {

                    continue;
                }
            } catch (NoResultException e) {

            }

            // Ξεκινά η διαδικασία για την προσθήκη του MealCategory στη βάση δεδομένων
            em.getTransaction().begin();
            // Εισαγωγή του MealCategory στη βάση δεδομένων
            em.persist(mealCategories.get(i));
            // Ολοκλήρωση της διαδικασίας προσθήκης
            em.getTransaction().commit();

        }

        // Κλείσιμο του entity manager και του entity manager factory
        em.close();
        emf.close();
    }

    // Μέθοδος για την επιστροφή μιας λίστας όλων των MealCategory αντικειμένων από τη βάση δεδομένων
    public static List<MealCategory> getAllMealCategory() {
        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();

        // Δημιουργία ερωτήματος για την επιστροφή όλων των MealCategory αντικειμένων από τη βάση δεδομένων
        TypedQuery<MealCategory> query = em.createNamedQuery("MealCategory.findAll", MealCategory.class);
        List<MealCategory> resultList = query.getResultList();

        // Κλείσιμο του entity manager και του entity manager factory
        em.close();
        emf.close();

        // Επιστροφή της λίστας όλων των MealCategory αντικειμένων από τη βάση δεδομένων
        return resultList;
    }

    // Μέθοδος για την επιστροφή ενός μόνο MealCategory αντικειμένου
    public static MealCategory getSingleCategory(String strCategory) {

        // Δημιουργία entity manager factory και entity manager για την επικοινωνία με τη βάση δεδομένων
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MealsAppPU");
        EntityManager em = emf.createEntityManager();

        MealCategory mealCategory = null;

        try {
            // Χρήση του named query για την εύρεση του αντικειμένου MealCategory βάσει του strCategory του
            mealCategory = em.createNamedQuery("MealCategory.findByStrcategory", MealCategory.class)
                    .setParameter("strcategory", strCategory)
                    .getSingleResult();
            em.getTransaction().begin();
            em.getTransaction().commit();
            System.out.println("Meal Found Successfully");
        } catch (NoResultException e) {

        } finally {
            // Κλείσιμο του entity manager και του entity manager factory
            em.close();
            emf.close();

        }
        // Επιστροφή του αντικειμένου MealCategory που βρέθηκε ή null αν δεν βρέθηκε κάποιο αντικείμενο
        return mealCategory;

    }
}

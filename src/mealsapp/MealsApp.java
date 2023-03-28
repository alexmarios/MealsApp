
package mealsapp;

// Εισαγωγή των απαραίτητων βιβλιοθηκών και κλάσεων για την εκτέλεση του προγράμματος

import mealsapp.GUI.MainForm;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mealsapp.DB.DatabaseCreation;
/**
 * 
 * @author 
 * ΡΕΜΟΥΝΤΑΚΗΣ ΑΛΕΞΑΝΔΡΟΣ ΜΑΡΙΟΣ
 * ΣΤΕΦΑΝΑΤΟΣ ΝΙΚΟΛΑΟΣ
 * ΝΙΚΟΛΤΣΙΟΣ ΜΙΧΑΗΛ 
 * 
 */

// Κλάση για την εκτέλεση του προγράμματος
public class MealsApp {


    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        /*
        Για την λειτουργία του προγράμματος δημιουργείται αυτόματα μια DB με τα στοιχεία,
        όνομα MealsDB user=mealsdb password=mealsdb 
        και το απαραίτητο table
        
        Σε περίπτωση που δεν δημιουργηθεί αυτόματα, μπορείτε να φτιάξτε μία με τα κάτωθι στοιχεία:
        Για driver Java DB (Embedded):
        jdbc:derby:MealsDB;create=true;user=mealsdb;password=mealsdb
        Για driver Java DB (Network):
        jdbc:derby://localhost:1527/MealsDB;create=true;user=mealsdb;password=mealsdb
        */
        try {
             // Ορισμός του συστήματος εμφάνισης του προγράμματος στην εμφάνιση των κουμπιών και των παραθύρων
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        // Δημιουργία της βάσης δεδομένων, αν δεν υπάρχει ήδη
        DatabaseCreation.createDB();
        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        // Εκκίνηση της κεντρικής φόρμας της εφαρμογής
        MainForm mf = new MainForm();
        mf.pack();
        mf.setVisible(true);

    }

}

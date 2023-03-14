
package mealsapp.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseCreation {
    // Μέθοδος για δημιουργία της βάσης δεδομένων
    public static void createDB() {
        // Ορισμός του ονόματος της βάσης δεδομένων και του URL σύνδεσης
        String dbName = "MealsDB";
        String connectionURL = "jdbc:derby:MealsDB;create=true;user=mealsdb;password=mealsdb";

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            // Επιτυχής σύνδεση με τη βάση δεδομένων
            System.out.println("Connected to database " + dbName);
        } catch (SQLException e) {
            if (e.getSQLState().equals("XJ004")) {
                // Η βάση δεδομένων δεν υπάρχει και πρέπει να δημιουργηθεί
                System.out.println("Database " + dbName + " does not exist, creating...");
                createDatabase(connectionURL);
            } else {
                // Σφάλμα κατά τη σύνδεση με τη βάση δεδομένων
                e.printStackTrace();
            }
        }
    }
    // Μέθοδος για δημιουργία της βάσης δεδομένων
    private static void createDatabase(String connectionURL) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {
             // Επιτυχής δημιουργία της βάσης δεδομένων
            System.out.println("Database created successfully");
        } catch (SQLException e) {
            // Σφάλμα κατά τη δημιουργία της βάσης δεδομένων
            e.printStackTrace();
        }
    }

}



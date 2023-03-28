// Πακέτο που περιέχει την κλάση APIRequest
package mealsapp.API;

// Εισαγωγή απαραίτητων βιβλιοθηκών
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Δημιουργία κλάσης APIRequest
public class APIRequest {

/**
 * Μέθοδος για κάλεσμα API και λήψη απόκρισης
 * @param httpLink : μια συμβολοσειρά που χρησιμοποιείται ως ερώτημα για την API
 * @return String : η απόκριση από την API σε μορφή συμβολοσειράς. 
 */
    public static String getData(String httpLink) {
        // Δημιουργία αντικειμένου OkHttpClient
        OkHttpClient client = new OkHttpClient();

         // Δημιουργία αντικειμένου Request με το URL που περιέχεται στη μεταβλητή httpLink
        Request request = new Request.Builder().url(httpLink).build();

       // Προσπάθεια εκτέλεσης του κλήσης API και λήψη απόκρισης
        try (Response response = client.newCall(request).execute()) {
            // Εάν η απόκριση είναι επιτυχής και περιέχει σώμα
            if (response.isSuccessful() && response.body() != null) {

                // Επιστροφή του σώματος απόκρισης ως συμβολοσειρά
                return response.body().string();
            } else {
             // Επιστροφή null εάν η απόκριση δεν είναι επιτυχής ή δεν περιέχει σώμα
                return "null";
            }
        } catch (IOException e) {
           
            return "null";
        }
    }
}

package mealsapp.API;

import mealsapp.MealClasses.MealCategory;
import mealsapp.MealClasses.Meal;
import com.google.gson.JsonParser;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import java.util.ArrayList;

public class JsonParsers {

    // Ορισμός σταθερών για τα links της API
    static String apiLink = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    static String apiIDLink = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=";
    static String apiCategoriesLink = "https://www.themealdb.com/api/json/v1/1/categories.php";
    static String apiMealOfCategories = "https://www.themealdb.com/api/json/v1/1/filter.php?c=";
    static String randomString = "https://www.themealdb.com/api/json/v1/1/random.php";
    static String areaApi = "https://www.themealdb.com/api/json/v1/1/list.php?a=list";
    static String areaMeal = "https://www.themealdb.com/api/json/v1/1/filter.php?a=";
    static String ingredientApi = "https://www.themealdb.com/api/json/v1/1/list.php?i=list";
    static String ingredientgetmeals = "https://www.themealdb.com/api/json/v1/1/filter.php?i=";

    // Δημιουργία αντικειμένων για τον parser και τον builder του Gson
    static JsonParser parser = new JsonParser();
    static GsonBuilder builder = new GsonBuilder();

    //Μέθοδος που επιστρέφει ένα τυχαίο γεύμα από την API
    public static JsonObject randomMeal() {
        JsonObject jsonObject = null;
        try {
            // Λαμβάνουμε τα δεδομένα μέσω της μεθόδου getData από την API    
            String jsonData = APIRequest.getData(randomString);
            JsonParser parser = new JsonParser();

            // Μετατρέπουμε τα δεδομένα σε JsonObject
            jsonObject = parser.parse(jsonData).getAsJsonObject();
            // Ελέγχουμε αν το JsonObject είναι κενό ή null
            if (jsonObject == null || jsonObject.size() == 0 || jsonObject.isJsonNull()) {

                return null;
            }

            // Αντλούμε το στοιχείο με όνομα "meals" από το JsonObject
            JsonElement mealsElement = jsonObject.get("meals");
            // Ελέγχουμε εάν το JsonElement είναι null
            if (mealsElement == null || mealsElement.isJsonNull()) {
                // Επιστρέφουμε null εάν το JsonElement είναι null
                return null;
            }
            // Μετατρέπουμε το JsonElement σε έναν πίνακα JsonArray
            JsonArray mealsArray = mealsElement.getAsJsonArray();

            // Ελέγχουμε εάν ο πίνακας JsonArray είναι null ή κενός
            if (mealsArray == null || mealsArray.size() == 0 || mealsArray.isJsonNull()) {
                // Επιστρέφουμε null εάν ο πίνακας JsonArray είναι null ή κενός
                return null;
            }

            // Επιλέγουμε το πρώτο γεύμα από τον πίνακα JsonArray
            jsonObject = mealsArray.get(0).getAsJsonObject();
        } catch (Exception e) {

        }
        // Επιστρέφουμε το αντικείμενο JsonObject που περιέχει τα δεδομένα
        return jsonObject;
    }

    //Μέθοδος που επιστρέφει μια λίστα με όλες τις διαθέσιμες κατηγορίες γευμάτων από την API.
    public static ArrayList<MealCategory> getAllMealCategories() {
        // Δημιουργία μιας νέας λίστας MealCategory
        ArrayList<MealCategory> mealsArray = new ArrayList<MealCategory>();
        try {
            // Δημιουργία μιας νέας σύνδεσης με το API και λήψη των δεδομένων για τις κατηγορίες
            String jsonData = APIRequest.getData(apiCategoriesLink);
            JsonParser parser = new JsonParser();

            // Μετατροπή των δεδομένων σε JsonObject
            JsonObject jsonObject = parser.parse(jsonData).getAsJsonObject();
            // Έλεγχος για το αν υπάρχουν δεδομένα στο πεδίο "categories"
            if (!jsonObject.get("categories").isJsonNull()) {
                // Λήψη του πίνακα "categories" από το JsonObject
                JsonArray categoryArray = jsonObject.get("categories").getAsJsonArray();

                // Επιστροφή όλων των κατηγοριών στη λίστα mealsArray
                for (int i = 0; i < categoryArray.size(); i++) {
                    // Δημιουργία μιας νέας κατηγορίας φαγητού από το JsonObject
                    builder.setPrettyPrinting();
                    Gson gson = builder.create();
                    JsonObject category = categoryArray.get(i).getAsJsonObject();
                    mealsArray.add(gson.fromJson(category, MealCategory.class));
                }
                return mealsArray;
            } else {

            }
        } catch (Exception e) {

        }
        //Επιστρέφει μια λίστα από MealCategory αντικείμενα που περιέχουν όλες τις κατηγορίες γευμάτων
        return mealsArray;
    }

    // Μέθοδος που επιστρέφει μια λίστα με όλα τα γεύματα που ανήκουν σε μια συγκεκριμένη περιοχή
    public static ArrayList<Meal> getAllMealByArea(String area) {
        ArrayList<Meal> mealsArray = new ArrayList<>();

        try {
            // Λαμβάνουμε τα δεδομένα των γευμάτων από το API με βάση την περιοχή
            String jsonData = APIRequest.getData(areaMeal + area);

            // Δημιουργούμε έναν JsonParser για να αναλύσουμε τα δεδομένα JSON
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(jsonData).getAsJsonObject();

            if (jsonObject.has("meals")) {
                // Λαμβάνουμε τον πίνακα με τα γεύματα από το JsonObject
                JsonArray mealsJsonArray = jsonObject.getAsJsonArray("meals");
                // Δημιουργούμε ένα Gson object για να μετατρέψουμε το JSON σε αντικείμενα Meal
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                // Προσθέτουμε τα αντικείμενα Meal στη λίστα mealsArray
                for (int i = 0; i < mealsJsonArray.size(); i++) {
                    JsonObject mealJson = mealsJsonArray.get(i).getAsJsonObject();
                    Meal meal = gson.fromJson(mealJson, Meal.class);
                    mealsArray.add(meal);
                }
            }
        } catch (Exception e) {

        }
        // Επιστρέφουμε τη λίστα με τα αντικείμενα Meal
        return mealsArray;
    }

    //Μέθοδος που επιστρέφει μια λίστα με όλες τις περιοχές που υποστηρίζονται από το API
    public static ArrayList<String> getAllArea() {
        // Δημιουργία λίστας για τις περιοχές
        ArrayList<String> areaArray = new ArrayList<String>();
        try {
            // Ανάκτηση δεδομένων από το API
            String jsonData = APIRequest.getData(areaApi);
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(jsonData).getAsJsonObject();

            // Έλεγχος για το αν υπάρχει το πεδίο "meals" στο αντικείμενο JSON
            if (!jsonObject.get("meals").isJsonNull()) {
                // Ανάκτηση του πίνακα "meals" από το αντικείμενο JSON
                JsonArray categoryArray = jsonObject.get("meals").getAsJsonArray();

                // Προσθήκη κάθε περιοχής στην λίστα
                for (int i = 0; i < categoryArray.size(); i++) {
                    areaArray.add(categoryArray.get(i).getAsJsonObject().get("strArea").getAsString());

                }
                return areaArray;
            } else {

            }
        } catch (Exception e) {

        }
        // Επιστρέφει τη λίστα με τις περιοχές
        return areaArray;
    }

    //Μέθοδος που επιστρέφει απο την ΑΡΙ μιά λίστα με όλα τα διαθέσιμα συστατικά και τις περιγραφές τους.
    public static ArrayList<String[]> getAllIngredient() {
        ArrayList<String[]> ingredientArray = new ArrayList<String[]>();
        try {
            // Ανάκτηση του JSON από το API
            String jsonData = APIRequest.getData(ingredientApi);
            JsonParser parser = new JsonParser();

            // Μετατροπή του JSON σε JsonObject
            JsonObject jsonObject = parser.parse(jsonData).getAsJsonObject();
            // Έλεγχος εάν το πεδίο "meals" υπάρχει και δεν είναι κενό
            if (!jsonObject.get("meals").isJsonNull()) {
                // Ανάκτηση του πίνακα με τα συστατικά από το JsonObject
                JsonArray ingreJson = jsonObject.get("meals").getAsJsonArray();

                // Διατρέχουμε τον πίνακα με τα συστατικά
                for (int i = 0; i < ingreJson.size(); i++) {
                    // Έλεγχος εάν το πεδίο "strIngredient" και "strDescription" υπάρχουν και δεν είναι κενά
                    if ((!ingreJson.get(i).getAsJsonObject().get("strIngredient").isJsonNull())) {
                        // Ανάκτηση του ονόματος και της περιγραφής του συστατικού
                        String strIngredient = ingreJson.get(i).getAsJsonObject().get("strIngredient").getAsString();
                        String strDescription = "";
                        if (ingreJson.get(i).getAsJsonObject().get("strDescription").isJsonNull()) {
                            strDescription = "";
                        } else {
                            strDescription = ingreJson.get(i).getAsJsonObject().get("strDescription").getAsString();
                        }

                        // Δημιουργία πίνακα με τα δεδομένα του συστατικού
                        String[] row = {strIngredient, strDescription};
                        // Προσθήκη του πίνακα στη λίστα με τα συστατικά
                        ingredientArray.add(row);

                    }

                }
                // Επιστροφή της λίστας με τα συστατικά
                return ingredientArray;
            } else {

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        //Επιστρέφει λίστα με όλα τα διαθέσιμα συστατικά και τις περιγραφές τους
        return ingredientArray;
    }

    //Μέθοδος που επιστρέφει ένα JsonObject που περιέχει τα στοιχεία μιας συνταγής με βάση το όνομα της συνταγής.
    public static JsonObject getMealJsonString(String mealName) {
        JsonObject jsonObject = null;
        try {
            // Λήψη δεδομένων μέσω APIRequest
            String jsonData = APIRequest.getData(apiLink + mealName);
            // Δημιουργία ενός JsonParser
            JsonParser parser = new JsonParser();

            // Μετατροπή των δεδομένων σε JsonObject
            jsonObject = parser.parse(jsonData).getAsJsonObject();
            // Έλεγχος εάν το JsonObject είναι null ή έχει μέγεθος 0 ή είναι null
            if (jsonObject == null || jsonObject.size() == 0 || jsonObject.isJsonNull()) {
                // Επιστροφή null εάν το JsonObject είναι null ή έχει μέγεθος 0
                return null;
            }

            // Λήψη του JsonElement με τo κλειδί "meals"
            JsonElement mealsElement = jsonObject.get("meals");
            if (mealsElement == null || mealsElement.isJsonNull()) {
                // Επιστροφή null εάν το JsonElement είναι null
                return null;
            }
            // Μετατροπή του JsonElement σε JsonArray
            JsonArray mealsArray = mealsElement.getAsJsonArray();

            // Έλεγχος εάν το JsonArray είναι null ή έχει μέγεθος 0
            if (mealsArray == null || mealsArray.size() == 0 || mealsArray.isJsonNull()) {
                // Επιστροφή null εάν το JsonArray είναι null ή έχει μέγεθος 0
                return null;
            }

            // Λήψη του πρώτου JsonObject από το JsonArray
            jsonObject = mealsArray.get(0).getAsJsonObject();
        } catch (Exception e) {

        }
        // Επιστροφή του JsonObject
        return jsonObject;
    }

    //Μέθοδος που επιστρέφει ένα αντικείμενο Meal με το όνομα του γεύματος
    public static Meal logMealObjByName(JsonObject mealJson) {
        // Δημιουργία νέου αντικειμένου Meal με το όνομα του γεύματος από το JsonObject που δόθηκε ως παράμετρος
        Meal meal = new Meal(mealJson.get("strMeal").getAsString());
        // Επιστροφή του αντικειμένου Meal
        return meal;
    }

    //Μέθοδος που επιστρέφει ενα αντικείμενο Meal που δημιουργήθηκε από το JsonObject
    public static Meal createMealFromJson(JsonObject mealJson) {
        // Ορισμός του builder για τη μετατροπή του JSON σε String με μορφοποίηση.
        builder.setPrettyPrinting();
        // Δημιουργία του gson αντικειμένου με τη χρήση του builder για τη μετατροπή του JSON σε αντικείμενο Meal.
        Gson gson = builder.create();
        // Επιστρέφει το αντικείμενο Meal που δημιουργήθηκε από το JsonObject
        return gson.fromJson(mealJson, Meal.class);
    }

    //Μέθοδος που δημιουργεί ένα αντικείμενο γεύματος Meal βάσει ενός μοναδικού αριθμού id
    public static Meal createMealObjByID(String idMeal) {
        // Παίρνουμε τα δεδομένα από το API
        String jsonData = APIRequest.getData(apiIDLink + idMeal);

        try {
            // Μετατρέπουμε τα δεδομένα σε JsonObject
            JsonObject jsonObject = parser.parse(jsonData).getAsJsonObject();

            // Έλεγχος εάν το JsonObject είναι κενό
            if (jsonObject.get("meals").isJsonNull()) {
                return null;
            }

            // Παίρνουμε το πρώτο γεύμα από τον πίνακα με τα γεύματα
            JsonArray mealsArray = jsonObject.get("meals").getAsJsonArray();
            JsonObject firstMeal = mealsArray.get(0).getAsJsonObject();

            // Δημιουργούμε ένα νέο Gson object και καλούμε την μέθοδο fromJson για να μετατρέψουμε τα δεδομένα σε ένα αντικείμενο Meal
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.fromJson(firstMeal, Meal.class);
        } catch (Exception e) {

            return null;
        }
    }

    //Mέθοδος που λαμβάνει μια κατηγορία γευμάτων ως είσοδο και επιστρέφει μια λίστα με όλα τα γεύματα που ανήκουν σε αυτήν την κατηγορία. 
    public static ArrayList<Meal> getAllMealByCategory(String category) {
        // Δημιουργία μιας κενής λίστας που θα περιέχει τα γεύματα της κατηγορίας
        ArrayList<Meal> mealsArray = new ArrayList<>();

        try {
            // Λαμβάνουμε τα δεδομένα του αιτήματος από το API
            String jsonData = APIRequest.getData(apiMealOfCategories + category);

            // Χρησιμοποιούμε τη βιβλιοθήκη Gson για να μετατρέψουμε τα δεδομένα από JSON σε Java objects        
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(jsonData).getAsJsonObject();

            // Έλεγχος εάν υπάρχουν γεύματα για την επιλεγμένη κατηγορία
            if (jsonObject.has("meals")) {
                // Παίρνουμε τον πίνακα με τα γεύματα από το JSON
                JsonArray mealsJsonArray = jsonObject.getAsJsonArray("meals");

                // Χρησιμοποιούμε τη βιβλιοθήκη Gson για να μετατρέψουμε τα δεδομένα του κάθε γεύματος σε ένα αντικείμενο Meal και να τα προσθέσουμε στη λίστα με τα γεύματα
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                for (int i = 0; i < mealsJsonArray.size(); i++) {
                    JsonObject mealJson = mealsJsonArray.get(i).getAsJsonObject();
                    Meal meal = gson.fromJson(mealJson, Meal.class);
                    mealsArray.add(meal);
                }
            }
        } catch (Exception e) {

        }
        //Επιστρέφει ένα ArrayList από Meal αντικείμενα, που αντιστοιχούν σε γεύματα που ανήκουν στην κατηγορία που δόθηκε ως παράμετρος.
        return mealsArray;
    }

    //Μέθοδος που αναζητά όλα τα γεύματα που περιέχουν ένα συγκεκριμένο συστατικό (ingredient)
    public static ArrayList<Meal> getAllMealByIngridient(String ingredient) {
        ArrayList<Meal> mealsArray = new ArrayList<>();

        try {
            //Ανάκτηση δεδομένων από την εξωτερική API με βάση το όνομα του υλικού
            String jsonData = APIRequest.getData(ingredientgetmeals + ingredient);

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(jsonData).getAsJsonObject();

            //Έλεγχος για την ύπαρξη του πεδίου "meals" στο JSON αποτέλεσμα
            if (jsonObject.has("meals")) {
                JsonArray mealsJsonArray = jsonObject.getAsJsonArray("meals");
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                //Δημιουργία αντικειμένων Meal από τα JSON αντικείμενα της λίστας "meals"
                for (int i = 0; i < mealsJsonArray.size(); i++) {
                    JsonObject mealJson = mealsJsonArray.get(i).getAsJsonObject();
                    Meal meal = gson.fromJson(mealJson, Meal.class);
                    mealsArray.add(meal);
                }
            }
        } catch (Exception e) {

        }
        //Επιστροφή της λίστας με τα αντικείμενα Meal
        return mealsArray;
    }

    //Μέθοδος που δέχεται ένα JsonObject που περιέχει πληροφορίες για γεύματα που αναζητούνται και επιστρέφει 
    //μια λίστα από αντικείμενα Meal που αντιστοιχούν σε αυτά τα γεύματα.
    public static ArrayList<Meal> getAllMealBySearch(JsonObject mealsJsonObject) {
        ArrayList<Meal> mealsArray = new ArrayList<>();

        try {
            // Έλεγχος αν το JsonObject περιέχει το πεδίο "meals"
            if (mealsJsonObject.has("meals")) {
                // Ανάκτηση του JsonArray με τα γεύματα
                JsonArray mealsJsonArray = mealsJsonObject.getAsJsonArray("meals");
                // Δημιουργία ενός Gson object με την μέθοδο setPrettyPrinting για ένα ορατό JSON format
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                // Προσπέλαση του JsonArray και δημιουργία ενός αντικειμένου Meal για κάθε JsonObject
                for (int i = 0; i < mealsJsonArray.size(); i++) {
                    JsonObject mealJson = mealsJsonArray.get(i).getAsJsonObject();
                    Meal meal = gson.fromJson(mealJson, Meal.class);
                    mealsArray.add(meal);
                }
            }
        } catch (Exception e) {

        }
        // Επιστροφή της λίστας με τα γεύματα
        return mealsArray;
    }

    //Μέθοδος που επιστρέφει το JsonObject με όλες τις πληροφορίες για ένα γεύμα με βάση το όνομά του
    public static JsonObject getAllMealsJsonString(String mealName) {
        JsonObject jsonObject = null;
        try {
            // Ανακτά το δεδομένα σε μορφή JSON με βάση το όνομα του γεύματος
            String jsonData = APIRequest.getData(apiLink + mealName);
            JsonParser parser = new JsonParser();

            // Μετατροπή του JSON σε JsonObject
            jsonObject = parser.parse(jsonData).getAsJsonObject();
            // Έλεγχος για τυχόν κενό JsonObject
            if (jsonObject == null || jsonObject.size() == 0 || jsonObject.isJsonNull()) {

                return null;
            }

            // Ανάκτηση του JsonElement με τα γεύματα
            JsonElement mealsElement = jsonObject.get("meals");
            // Έλεγχος για τυχόν κενό JsonElement
            if (mealsElement == null || mealsElement.isJsonNull()) {

                return null;
            }
            // Μετατροπή του JsonElement με τα γεύματα σε JsonArray
            JsonArray mealsArray = mealsElement.getAsJsonArray();

            // Έλεγχος για τυχόν κενό JsonArray
            if (mealsArray == null || mealsArray.size() == 0 || mealsArray.isJsonNull()) {

                return null;
            }

        } catch (Exception e) {

        }
        // Επιστροφή του JsonObject με τις πληροφορίες για το γεύμα
        return jsonObject;
    }

}

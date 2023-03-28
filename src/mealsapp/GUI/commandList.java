
package mealsapp.GUI;

import com.google.gson.JsonObject;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import mealsapp.API.JsonParsers;
import mealsapp.DB.mealsCategoryQueryOperations;
import mealsapp.DB.mealsQueryOperations;
import mealsapp.MealClasses.Meal;
import mealsapp.MealClasses.MealCategory;
import mealsapp.Charts.PrintList;


public class commandList {
    // Αντικείμενο τύπου Meal για χρήση στις μεθόδους της κλάσης
    public static Meal meal = new Meal();
    
    // Μέθοδος που επιστρέφει μια λίστα με τα ονόματα των συνταγών που ανήκουν σε μια κατηγορία
    List<String> fetchMealsByCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Μέθοδος που δημιουργεί ένα αρχείο PDF για εκτύπωση των δεδομένων στον πίνακα
    public static void printPDF(int userView) throws FileNotFoundException, DocumentException, IOException {
        //Δημιουργία νέου αντικειμένου PrintList
        PrintList view = new PrintList();
        // Κλήση της μεθόδου exportToPDF για δημιουργία του αρχείου PDF
        view.exportToPDF(userView);
    }

    // Μέθοδος που κάνει αναζήτηση γεύματος στη βάση δεδομένων ή στο API.
    public static void searchCommand(String userMeal) throws MalformedURLException {

       //Παίρνουμε την τιμή από το κείμενο αναζήτησης
        JsonObject mealJsonObject = JsonParsers.getMealJsonString(userMeal);
        //αν η τιμή δεν είναι null
        if (mealJsonObject != null) {
            //αναζήτηση γεύματος στο https://www.themealdb.com/
            String mealName = mealJsonObject.get("strMeal").getAsString();
            //Παίρνουμε τα JSON και ελέγχουμε αν τα δεδομένα του JSON δεν είναι null
            if (mealsQueryOperations.findMealByStrMeal(mealName) == true) {
               //Ελέγχουμε αν ο χρήστης έχει αποθηκεύσει / επεξεργαστεί το γεύμα
                if (mealsQueryOperations.findIfMealIsSavedByUser(mealName) == true) {
                    //Ρωτάμε τον χρήστη αν θέλει τα δεδομένα από τη βάση δεδομένων ή το API.
                    int userChoice = MainForm.wantDataQuestion(mealName);
                    //Παίρνουμε τα δεδομένα από τη βάση δεδομένων και προσθέτουμε +1
                    if (userChoice == 0) {

                        meal = mealsQueryOperations.getMealByStrMeal(mealName);

                        MainForm.displayMeal(meal);
                        mealsQueryOperations.addVisit(meal.getStrmeal());
                        //Εμφανίζουμε τα δεδομένα από το API και προσθέτουμε +1
                    } else if (userChoice == 1) {

                        meal = JsonParsers.createMealFromJson(mealJsonObject);

                        MainForm.displayMeal(meal);
                        mealsQueryOperations.addVisit(meal.getStrmeal());
                       
                        } else {

                    }
                   //Αν το γεύμα δεν είναι αποθηκευμένο, παίρνουμε τα δεδομένα από το API και προσθέτουμε +1
                } else {
                    //Δημιουργούμε ένα αντικείμενο Meal βάσει του JSON
                    Meal helpMeal = JsonParsers.logMealObjByName(mealJsonObject);
                    //Προσθέτουμε το γεύμα στη βάση δεδομένων
                    mealsQueryOperations.insertNewMeal(helpMeal);
                    //Παίρνουμε τα δεδομένα από το JSON και προσθέτουμε +1
                    meal = JsonParsers.createMealFromJson(mealJsonObject);
                    mealsQueryOperations.addVisit(meal.getStrmeal());
                    //Εμφανίζουμε τα δεδομένα του γεύματος
                    MainForm.displayMeal(meal);

                }
               
            } else {
                //Δημιουργούμε ένα αντικείμενο Meal βάσει του JSON
                Meal helpMeal = JsonParsers.logMealObjByName(mealJsonObject);
                //Προσθέτουμε το γεύμα στη βάση δεδομένων
                mealsQueryOperations.insertNewMeal(helpMeal);
                //Παίρνουμε τα δεδομένα από το JSON και προσθέτουμε +1
                meal = JsonParsers.createMealFromJson(mealJsonObject);
                mealsQueryOperations.addVisit(meal.getStrmeal());
                //Εμφανίζουμε τα δεδομένα του γεύματος
                MainForm.displayMeal(meal);
            }
          //Αν το γεύμα έχει αποθηκευτεί στη βάση δεδομένων, παίρνουμε τα δεδομένα από τη βάση και προσθέτουμε +1
        } else if (mealsQueryOperations.findMealByStrMeal(userMeal) == true) {
            
            meal = mealsQueryOperations.getMealByStrMeal(userMeal);

            MainForm.displayMeal(meal);
            mealsQueryOperations.addVisit(meal.getStrmeal());

            
          //Αν το γεύμα δεν υπάρχει στη βάση δεδομένων ή στο https://www.themealdb.com/, εμφανίζουμε μήνυμα σφάλματος
        } else {
            MainForm.errorFound(userMeal + " not found in themealdb.com or local db!", "Error Message");
        }

    }
    
    // Μέθοδος που κάνει αναζήτηση ένα γεύμα βάσει του αναγνωριστικού του id
    public static void searchByMealID(String userMeal, String idString) throws MalformedURLException {

    
        //Παίρνουμε το γεύμα βάσει του ID
        Meal meal = JsonParsers.createMealObjByID(idString);

        try {

            String mealName = meal.getStrmeal();
            //Ελέγχουμε αν ο χρήστης έχει αποθηκεύσει / επεξεργαστεί το γεύμα
            if (mealsQueryOperations.findMealByStrMeal(mealName) == true) {
                //Ρωτάμε τον χρήστη αν θέλει τα δεδομένα από τη βάση δεδομένων ή το API.
                if (mealsQueryOperations.findIfMealIsSavedByUser(mealName) == true) {

                    int userChoice = MainForm.wantDataQuestion(mealName);

                    if (userChoice == 0) {
                        //Εμφανίζουμε τα δεδομένα από τη βάση δεδομένων και προσθέτουμε +1
                        meal = mealsQueryOperations.getMealByStrMeal(mealName);
                        
                        MainForm.displayMeal(meal);
                        mealsQueryOperations.addVisit(meal.getStrmeal());

                    } else if (userChoice == 1) {
                        //Εμφανίζουμε τα δεδομένα από το API και προσθέτουμε +1
                        MainForm.displayMeal(meal);
                        mealsQueryOperations.addVisit(meal.getStrmeal());
                        
                    } else {

                    }

                } else {

                    //Προσθέτουμε το γεύμα στη βάση δεδομένων και εμφανίζουμε τα δεδομένα του γεύματος, προσθέτοντας +1
                    mealsQueryOperations.insertNewMeal(meal);
                    mealsQueryOperations.addVisit(meal.getStrmeal());
                    MainForm.displayMeal(meal);

                }
                //Αν το γεύμα δεν είναι διαθέσιμο στο API, ελέγχουμε αν υπάρχει στη βάση δεδομένων
            } else {
                //Προσθέτουμε το γεύμα στη βάση δεδομένων και εμφανίζουμε τα δεδομένα του γεύματος, προσθέτοντας +1
                Meal helpMeal = new Meal(meal.getStrmeal());
                mealsQueryOperations.insertNewMeal(helpMeal);
                
                mealsQueryOperations.addVisit(meal.getStrmeal());
                MainForm.displayMeal(meal);
            }

            
        } catch (Exception e) {
            MainForm.errorFound("Check your connection!", "Error");
        }

    }
    
    /* 
    Μέθοδος που παίρνει ένα αντικείμενο γεύματος (τύπου Meal) και ελέγχει αν αυτό το γεύμα υπάρχει ήδη στη βάση δεδομένων. 
    Αν υπάρχει, ελέγχει επίσης αν ο χρήστης έχει αποθηκεύσει ή επεξεργαστεί το γεύμα και τον ρωτάει αν θέλει τα δεδομένα 
    του γεύματος από τη βάση δεδομένων ή από την API. Αν το γεύμα δεν υπάρχει στη βάση δεδομένων, τότε προσθέτει το γεύμα 
    στη βάση δεδομένων και το εμφανίζει.
    */
    public static void searchedMealAndImportToGui(Meal meal) throws MalformedURLException {

      // Λαμβάνουμε το γεύμα βάσει του ονόματος που προέρχεται από την αναζήτηση στο API.
        try {

            String mealName = meal.getStrmeal();
            // Ελέγχουμε αν ο χρήστης έχει αποθηκεύσει / επεξεργαστεί το γεύμα
            if (mealsQueryOperations.findMealByStrMeal(mealName) == true) {
                
               // Ρωτάμε τον χρήστη αν θέλει τα δεδομένα από τη βάση δεδομένων ή το API.
                if (mealsQueryOperations.findIfMealIsSavedByUser(mealName) == true) {

                    int userChoice = MainForm.wantDataQuestion(mealName);

                    if (userChoice == 0) {
                        // Εμφανίζουμε τα δεδομένα από τη βάση δεδομένων και προσθέτουμε +1.
                        meal = mealsQueryOperations.getMealByStrMeal(mealName);
                       // Εμφανίζουμε τα δεδομένα από το API και προσθέτουμε +1.
                        MainForm.displayMeal(meal);
                        mealsQueryOperations.addVisit(meal.getStrmeal());

                    } else if (userChoice == 1) {
                      
                        // Εμφανίζουμε τα δεδομένα από το API και προσθέτουμε +1.
                        MainForm.displayMeal(meal);
                        mealsQueryOperations.addVisit(meal.getStrmeal());
                       
                    } else {

                    }

                } else {

                    // Εμφανίζουμε τα δεδομένα από το API και προσθέτουμε +1.
                    mealsQueryOperations.insertNewMeal(meal);
                    mealsQueryOperations.addVisit(meal.getStrmeal());
                    MainForm.displayMeal(meal);

                }
                
            } else {
                
                // Εμφανίζουμε τα δεδομένα από το API και προσθέτουμε +1.
                Meal helpMeal = new Meal(meal.getStrmeal());
                mealsQueryOperations.insertNewMeal(helpMeal);
                mealsQueryOperations.addVisit(meal.getStrmeal());
                MainForm.displayMeal(meal);
            }

           // Εμφανίζουμε μήνυμα αποτυχίας.
        } catch (Exception e) {
            MainForm.errorFound("Check your connection!", "Error");
        }

    }
    
    /*
    Μέθοδος που αναζητά ένα γεύμα στην τοπική βάση δεδομένων. Αν ο χρήστης εισαγάγει ένα όνομα γεύματος, ο κώδικας ελέγχει
    αν υπάρχει στη βάση δεδομένων και αν είναι αποθηκευμένο από τον χρήστη. Αν ναι, το γεύμα εμφανίζεται στο πρόγραμμα. 
    Αν όχι, εμφανίζεται ένα μήνυμα λάθους.
    */
    public static void searchCommandDB(String userMeal) throws MalformedURLException {

        // Έλεγχος για κενό string
        if (!"".equals(userMeal)) {
            // Έλεγχος αν υπάρχει το γεύμα στη βάση δεδομένων
            if (mealsQueryOperations.findMealByStrMeal(userMeal) == true) {
                // Έλεγχος αν το γεύμα έχει αποθηκευτεί από τον χρήστη
                if (mealsQueryOperations.findIfMealIsSavedByUser(userMeal) == true) {

                     // Λήψη του γεύματος και εμφάνιση στην κύρια φόρμα
                    meal = mealsQueryOperations.getMealByStrMeal(userMeal);
                    MainForm.displayMeal(meal);
                    
                    // Αύξηση του μετρητή επισκέψεων του γεύματος
                    mealsQueryOperations.addVisit(meal.getStrmeal());
                    

                    
                } else {
                   // Εμφάνιση σφάλματος αν το γεύμα δεν έχει αποθηκευτεί από τον χρήστη
                    MainForm.errorFound(userMeal + " not found!", "Error Message");
                }
            } else {
                // Εμφάνιση σφάλματος αν το γεύμα δεν υπάρχει στη βάση δεδομένων
                MainForm.errorFound(userMeal + " not found!", "Error Message");
            }

        } else {
            // Εμφάνιση σφάλματος αν το πεδίο αναζήτησης είναι κενό
            MainForm.errorFound("Search field should contain characters when searching in DB!", "Error Message");
        }
    }

    // Μέθοδος που κάνει εύρεση ενός τυχαίου γεύματος
    public static void randomMeal() throws MalformedURLException {

        // Λήψη τυχαίου γεύματος από το API
        JsonObject mealJsonObject = JsonParsers.randomMeal();
        
        // Έλεγχος για τυχόν σφάλματα στη λήψη του γεύματος
        if (mealJsonObject != null) {
            // Ανάκτηση του ονόματος του γεύματος από το JSON αντικείμενο
            String mealName = mealJsonObject.get("strMeal").getAsString();
            
            // Έλεγχος αν το γεύμα υπάρχει ήδη στη βάση δεδομένων
            if (mealsQueryOperations.findMealByStrMeal(mealName) == true) {

                // Έλεγχος αν το γεύμα έχει ήδη αποθηκευτεί από τον χρήστη
                if (mealsQueryOperations.findIfMealIsSavedByUser(mealName) == true) {
                    // Ερώτηση για την εμφάνιση των δεδομένων του γεύματος
                    int userChoice = MainForm.wantDataQuestion(mealName);

                    if (userChoice == 0) {
                        // Λήψη των δεδομένων του γεύματος από τη βάση δεδομένων και εμφάνιση στην κύρια φόρμα
                        meal = mealsQueryOperations.getMealByStrMeal(mealName);
                        MainForm.displayMeal(meal);
                        // Αύξηση του μετρητή επισκέψεων του γεύματος
                        mealsQueryOperations.addVisit(meal.getStrmeal());

                    } else if (userChoice == 1) {
                        // Λήψη των δεδομένων του γεύματος από το JSON αντικείμενο και εμφάνιση στην κύρια φόρμα
                        meal = JsonParsers.createMealFromJson(mealJsonObject);

                        MainForm.displayMeal(meal);
                        // Αύξηση του μετρητή επισκέψεων του γεύματος
                        mealsQueryOperations.addVisit(meal.getStrmeal());
                      
                    } else {

                    }

                } else {
                    // Εισαγωγή του νέου γεύματος στη βάση δεδομένων και εμφάνιση των δεδομένων του στην κύρια φόρμα
                    Meal helpMeal = JsonParsers.logMealObjByName(mealJsonObject);
                    mealsQueryOperations.insertNewMeal(helpMeal);
                    meal = JsonParsers.createMealFromJson(mealJsonObject);
                    mealsQueryOperations.addVisit(meal.getStrmeal());
                    MainForm.displayMeal(meal);

                }

            } else {
                // Εισαγωγή του νέου γεύματος στη βάση δεδομένων και εμφάνιση των δεδομένων του στην κύρια φόρμα
                Meal helpMeal = JsonParsers.logMealObjByName(mealJsonObject);
                mealsQueryOperations.insertNewMeal(helpMeal);
                meal = JsonParsers.createMealFromJson(mealJsonObject);
                mealsQueryOperations.addVisit(meal.getStrmeal());
                MainForm.displayMeal(meal);
            }


        } else {
            // Εμφάνιση σφάλματος αν υπάρξει πρόβλημα με τη σύνδεση στο διαδίκτυο
            MainForm.errorFound("Something went wrong check your Internet Connection!", "Error");
        }

    }

    // Μέθοδος που κάνει αποθήκευση ενός γεύματος
    public static void saveMeal(String sbnTitleTxt, Map<String, Object> dataFromUser) {
        // Έλεγχος για το αν υπάρχει όνομα γεύματος
        if (!sbnTitleTxt.equals("")) {
            // Αφαίρεση κενών από την αρχή του ονόματος για σωστή αποθήκευση
            sbnTitleTxt = sbnTitleTxt.replaceAll("^\\s+", "");
            // Εμφάνιση ερωτήματος για αποθήκευση του γεύματος
            int userChoice = MainForm.wantQuestionYesNo("Do you want to save " + sbnTitleTxt + "?");

            if (userChoice == 0) {

                 // Έλεγχος για το αν το όνομα δεν είναι κενό
                if (sbnTitleTxt.isBlank() == false) {
                    // Λήψη δεδομένων για το γεύμα από τον χρήστη και αποθήκευση στο αντικείμενο Meal
                    HelpCommands.grabMealDataFromUser(meal, sbnTitleTxt, dataFromUser);

                    // Έλεγχος για το αν το γεύμα υπάρχει ήδη στη βάση δεδομένων
                    if (mealsQueryOperations.findMealByStrMeal(meal.getStrmeal()) == true) {
                        // Αποθήκευση του γεύματος στη βάση δεδομένων
                        mealsQueryOperations.saveMeal(meal);
                        

                    } else { 
                        // Εισαγωγή του νέου γεύματος στη βάση δεδομένων και αύξηση του μετρητή επισκέψεων
                        mealsQueryOperations.insertNewMeal(meal);
                        mealsQueryOperations.addVisit(meal.getStrmeal());
                        
                        
                    }

                    MainForm.infomessage(sbnTitleTxt + " meal saved!");
                }

            }
        } else {
            // Εμφάνιση σφάλματος αν δεν υπάρχει όνομα γεύματος
            MainForm.errorFound("Meal name should contain characters!", "Error");
        }
    }
    
    // Μέθοδος που διαγράφει ενα γεύμα απο την βάση δεδομένων του χρήστη
    public static boolean userDeletesMeal(String sbnTitleTxt) {
        // Έλεγχος για το αν το γεύμα υπάρχει στη βάση δεδομένων του χρήστη
        if (mealsQueryOperations.findMealByStrMeal(sbnTitleTxt) == true) {
            // Εμφάνιση επιβεβαίωσης για διαγραφή του γεύματος
            int userChoice = MainForm.wantDeleteQuestion(sbnTitleTxt);
            if (userChoice == 1) {

                // Διαγραφή των δεδομένων του γεύματος από τη βάση δεδομένων
                meal = mealsQueryOperations.getMealByStrMeal(sbnTitleTxt);
                HelpCommands.deleteMealData(meal);
                mealsQueryOperations.saveMeal(meal);
                
                // Εμφάνιση μηνύματος για επιτυχή διαγραφή του γεύματος
                MainForm.infomessage(sbnTitleTxt + " meal deleted!");

                meal = new Meal();
                return true;

            }
            // Ακύρωση διαγραφής των δεδομένων του γεύματος
            else if (userChoice == 0) {
                
                mealsQueryOperations.deleteSingleMeal(sbnTitleTxt);
                meal = mealsQueryOperations.getMealByStrMeal(sbnTitleTxt);
                
                // Εμφάνιση μηνύματος για επιτυχή διαγραφή των δεδομένων του γεύματος
                MainForm.infomessage(sbnTitleTxt + " meal data deleted!");

                meal = new Meal();
                return true;
                
            }

        } else {
            // Εμφάνιση σφάλματος αν το γεύμα δε βρέθηκε στη βάση δεδομένων
            MainForm.errorFound(sbnTitleTxt + " meal not found in local db!", "Error");
            return false;

        }
        return false;

    }
    
    // Μέθοδος για λήψη των κατηγοριών των γευμάτων από το API και εισαγωγή τους στη βάση δεδομένων
    public static DefaultListModel<String> getCategoriesModel(List<MealCategory> mealCategories) {
        
        // Λήψη των κατηγοριών των γευμάτων από το API και εισαγωγή τους στη βάση δεδομένων
        mealsCategoryQueryOperations.importMealCategories(mealCategories);
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < mealCategories.size(); i++) {
            
            // Προσθήκη των ονομάτων των κατηγοριών στη λίστα
            model.addElement(mealCategories.get(i).getStrcategory());
        }
        return model;
    }
    
    // Μέθοδος για λήψη των περιοχών από τη βάση δεδομένων και εισαγωγή τους στη λίστα
    public static DefaultListModel<String> getAreaModel(ArrayList<String> getAllArea) {
        
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < getAllArea.size(); i++) {
            // Προσθήκη των ονομάτων των περιοχών στη λίστα
            model.addElement(getAllArea.get(i));
        }
        return model;
    }
    
    // Μέθοδος για λήψη των συστατικών από τη βάση δεδομένων και εισαγωγή τους στη λίστα
    public static DefaultListModel<String> getIngredientsModel(ArrayList<String[]> getAllIngredients) {
        
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < getAllIngredients.size(); i++) {
            // Προσθήκη των ονομάτων των συστατικών στη λίστα
            model.addElement(getAllIngredients.get(i)[0]);
        }
        return model;
    }
    
    // Μέθοδος  για δημιουργία του πίνακα με τις πληροφορίες των γευμάτων από τη βάση δεδομένων
    public static DefaultTableModel getTableModel() {

        List<Meal> meals = mealsQueryOperations.getbyDecOrder();
        String[][] table = new String[meals.size()][2];

        for (int i = 0; i < meals.size(); i++) {
            // Προσθήκη του ονόματος του γεύματος και του αριθμού των φορών που έχει επισκεφτεί το γεύμα στον πίνακα
            table[i][0] = meals.get(i).getStrmeal();
            table[i][1] = meals.get(i).getTimesofvisit().toString();

        }

        String[] columnNames = {"Meals", "Times Of Visit"};
        // Δημιουργία μοντέλου πίνακα με τα δεδομένα και τα ονόματα των στηλών
        DefaultTableModel model = new DefaultTableModel(table, columnNames);

        return model;
    }
    
    // Μέθοδος για ανανέωση της λίστας με τα γεύματα
    public static DefaultListModel<String> updateModelMealList(List<Meal> meals) {

        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < meals.size(); i++) {
            // Προσθήκη των ονομάτων των γευμάτων
            model.addElement(meals.get(i).getStrmeal());

        }
        return model;
    }
}

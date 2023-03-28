package mealsapp.GUI;

import java.util.Map;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import mealsapp.MealClasses.Meal;



public class HelpCommands {
    
    //Μέθοδος η οποία δέχεται ένα αντικείμενο Meal και επιστρέφει ένα δισδιάστατο πίνακα (Object[][]) με τα συστατικά και τις δοσολογίες του γεύματος.
    public static Object[][] getTable(Meal meal) {
       
        Object[][] data = {
            {meal.getStringredient1(), meal.getStrmeasure1()},
            {meal.getStringredient2(), meal.getStrmeasure2()},
            {meal.getStringredient3(), meal.getStrmeasure3()},
            {meal.getStringredient4(), meal.getStrmeasure4()},
            {meal.getStringredient5(), meal.getStrmeasure5()},
            {meal.getStringredient6(), meal.getStrmeasure6()},
            {meal.getStringredient7(), meal.getStrmeasure7()},
            {meal.getStringredient8(), meal.getStrmeasure8()},
            {meal.getStringredient9(), meal.getStrmeasure9()},
            {meal.getStringredient10(), meal.getStrmeasure10()},
            {meal.getStringredient11(), meal.getStrmeasure11()},
            {meal.getStringredient12(), meal.getStrmeasure12()},
            {meal.getStringredient13(), meal.getStrmeasure13()},
            {meal.getStringredient14(), meal.getStrmeasure14()},
            {meal.getStringredient15(), meal.getStrmeasure15()},
            {meal.getStringredient16(), meal.getStrmeasure16()},
            {meal.getStringredient17(), meal.getStrmeasure17()},
            {meal.getStringredient18(), meal.getStrmeasure18()},
            {meal.getStringredient19(), meal.getStrmeasure19()},
            {meal.getStringredient20(), meal.getStrmeasure20()}
        };
        return data;
    }
    
    //Μέθοδος η οποία λαμβάνει στοιχεία ενός γεύματος από τον χρήστη μέσω μιας διεπαφής χρήστη και τα αποθηκεύει στο αντικείμενο Meal.
    public static Meal grabMealDataFromUser(Meal meal, String sbnTitleTxt, Map<String, Object> dataFromUser) {
        // Ορίζει τον τίτλο του γεύματος ως το κείμενο που εισήχθη από τον χρήστη
        meal.setStrmeal(sbnTitleTxt);
        // Λαμβάνει τις τιμές των πεδίων Category,Instructions,Ingredients,Area και Image 
        JTextField sbnCategoryTxt = (JTextField) dataFromUser.get("sbnCategoryTxt");
        JTextArea sbnInstructions = (JTextArea) dataFromUser.get("sbnInstructions");
        JTable ingredientsTable = (JTable) dataFromUser.get("ingredientsTable");
        JTextField sbnAreaTxt = (JTextField) dataFromUser.get("sbnAreaTxt");
        String sbnImageLabel = (String) dataFromUser.get("sbnImageLabel");

        meal.setStrcategory(sbnCategoryTxt.getText());
        meal.setStrarea(sbnAreaTxt.getText());
        meal.setStrinstructions(sbnInstructions.getText());
        meal.setStrmealthumb(sbnImageLabel);

        meal.setStringredient1((String) ingredientsTable.getValueAt(0, 0));
        meal.setStringredient2((String) ingredientsTable.getValueAt(1, 0));
        meal.setStringredient3((String) ingredientsTable.getValueAt(2, 0));
        meal.setStringredient4((String) ingredientsTable.getValueAt(3, 0));
        meal.setStringredient5((String) ingredientsTable.getValueAt(4, 0));
        meal.setStringredient6((String) ingredientsTable.getValueAt(5, 0));
        meal.setStringredient7((String) ingredientsTable.getValueAt(6, 0));
        meal.setStringredient8((String) ingredientsTable.getValueAt(7, 0));
        meal.setStringredient9((String) ingredientsTable.getValueAt(8, 0));
        meal.setStringredient10((String) ingredientsTable.getValueAt(9, 0));
        meal.setStringredient11((String) ingredientsTable.getValueAt(10, 0));
        meal.setStringredient12((String) ingredientsTable.getValueAt(11, 0));
        meal.setStringredient13((String) ingredientsTable.getValueAt(12, 0));
        meal.setStringredient14((String) ingredientsTable.getValueAt(13, 0));
        meal.setStringredient15((String) ingredientsTable.getValueAt(14, 0));
        meal.setStringredient16((String) ingredientsTable.getValueAt(15, 0));
        meal.setStringredient17((String) ingredientsTable.getValueAt(16, 0));
        meal.setStringredient18((String) ingredientsTable.getValueAt(17, 0));
        meal.setStringredient19((String) ingredientsTable.getValueAt(18, 0));
        meal.setStringredient20((String) ingredientsTable.getValueAt(19, 0));

        meal.setStrmeasure1((String) ingredientsTable.getValueAt(0, 1));
        meal.setStrmeasure2((String) ingredientsTable.getValueAt(1, 1));
        meal.setStrmeasure3((String) ingredientsTable.getValueAt(2, 1));
        meal.setStrmeasure4((String) ingredientsTable.getValueAt(3, 1));
        meal.setStrmeasure5((String) ingredientsTable.getValueAt(4, 1));
        meal.setStrmeasure6((String) ingredientsTable.getValueAt(5, 1));
        meal.setStrmeasure7((String) ingredientsTable.getValueAt(6, 1));
        meal.setStrmeasure8((String) ingredientsTable.getValueAt(7, 1));
        meal.setStrmeasure9((String) ingredientsTable.getValueAt(8, 1));
        meal.setStrmeasure10((String) ingredientsTable.getValueAt(9, 1));
        meal.setStrmeasure11((String) ingredientsTable.getValueAt(10, 1));
        meal.setStrmeasure12((String) ingredientsTable.getValueAt(11, 1));
        meal.setStrmeasure13((String) ingredientsTable.getValueAt(12, 1));
        meal.setStrmeasure14((String) ingredientsTable.getValueAt(13, 1));
        meal.setStrmeasure15((String) ingredientsTable.getValueAt(14, 1));
        meal.setStrmeasure16((String) ingredientsTable.getValueAt(15, 1));
        meal.setStrmeasure17((String) ingredientsTable.getValueAt(16, 1));
        meal.setStrmeasure18((String) ingredientsTable.getValueAt(17, 1));
        meal.setStrmeasure19((String) ingredientsTable.getValueAt(18, 1));
        meal.setStrmeasure20((String) ingredientsTable.getValueAt(19, 1));

        ingredientsTable.getValueAt(0, 0);

        return meal;

    }
    
    //Μέθοδος που διαγράφει τα δεδομένα μιας συνταγής από το αντικείμενο Meal
    public static Meal deleteMealData(Meal meal) {
        // Αρχικοποίηση των πεδίων της συνταγής με κενές τιμές
        meal.setStrcategory("");
        meal.setIdmeal("");
        meal.setStrarea("");
        meal.setStrinstructions("");
        meal.setStrmealthumb("");
        meal.setStryoutube("");
        meal.setStringredient1("");
        meal.setStringredient2("");
        meal.setStringredient3("");
        meal.setStringredient4("");
        meal.setStringredient5("");
        meal.setStringredient6("");
        meal.setStringredient7("");
        meal.setStringredient8("");
        meal.setStringredient9("");
        meal.setStringredient10("");
        meal.setStringredient11("");
        meal.setStringredient12("");
        meal.setStringredient13("");
        meal.setStringredient14("");
        meal.setStringredient15("");
        meal.setStringredient16("");
        meal.setStringredient17("");
        meal.setStringredient18("");
        meal.setStringredient19("");
        meal.setStringredient20("");
        meal.setStrmeasure1("");
        meal.setStrmeasure2("");
        meal.setStrmeasure3("");
        meal.setStrmeasure4("");
        meal.setStrmeasure5("");
        meal.setStrmeasure6("");
        meal.setStrmeasure7("");
        meal.setStrmeasure8("");
        meal.setStrmeasure9("");
        meal.setStrmeasure10("");
        meal.setStrmeasure11("");
        meal.setStrmeasure12("");
        meal.setStrmeasure13("");
        meal.setStrmeasure14("");
        meal.setStrmeasure15("");
        meal.setStrmeasure16("");
        meal.setStrmeasure17("");
        meal.setStrmeasure18("");
        meal.setStrmeasure19("");
        meal.setStrmeasure20("");
     
        // Επιστροφή του αντικειμένου Meal με τα δεδομένα της συνταγής διαγραμμένα
        return meal;
    }

}

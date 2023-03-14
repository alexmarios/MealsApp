/*
Αυτό το πακέτο αποτελεί μέρος της εφαρμογής MealsApp και αφορά τη δημιουργία
ενός γραφήματος τύπου 3Δ στήλης χρησιμοποιώντας τη βιβλιοθήκη JFreeChart.
*/
package mealsapp.Charts;

import java.util.List;
import javax.swing.JFrame;
import mealsapp.DB.mealsQueryOperations;
import mealsapp.MealClasses.Meal;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import javax.swing.JPanel;
import org.jfree.data.category.DefaultCategoryDataset;


public class ColumnChart extends JFrame {
/*
 * Η μέθοδος αυτή δημιουργεί και επιστρέφει ένα dataset το οποίο περιέχει τις πληροφορίες
 * για τα πιο συχνά επισκεπτόμενα γεύματα και πόσες φορές τα επισκέφθηκε ο χρήστης.
 */
    public static DefaultCategoryDataset columnDataset() {
        List<Meal> orderedMeal = mealsQueryOperations.getbyDecOrder();
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < orderedMeal.size() && i <= 14; i++) {
            dataset.addValue(orderedMeal.get(i).getTimesofvisit(), "", orderedMeal.get(i).getStrmeal());
        }
        return dataset;
    }
/*
 * Η μέθοδος αυτή δημιουργεί και επιστρέφει ένα JPanel το οποίο περιέχει το γράφημα στήλης
 * που παράχθηκε από το dataset της columnDataset().
 */
    public static JPanel ColumnChart() {

        JPanel chartPanel = new ChartPanel(generateBarChart());
        chartPanel.setSize(814, 594);

        return chartPanel;
    }

    
 /*
 * Η μέθοδος αυτή δημιουργεί και επιστρέφει ένα αντικείμενο JFreeChart που αναπαριστά
 * το γράφημα στήλης με βάση το dataset της columnDataset().
 */
    public static JFreeChart generateBarChart() {
        
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Most Visited Meals", // τίτλος του γραφήματος
                "Meals", // ετικέτα τουν άξονα των κατηγοριών
                "Times of Visit", // ετικέτα τουν άξονα των τιμών
                columnDataset(), // τα δεδομένα
                org.jfree.chart.plot.PlotOrientation.HORIZONTAL, // προσανατολισμός
                false, // εμφάνιση λεζάντας
                false,//εμφάνιση tooltips
                false);//εμφάνιση urls
        org.jfree.chart.axis.ValueAxis rangeAxis = chart.getCategoryPlot().getRangeAxis();// παίρνουμε τον άξονα τιμών του γραφήματος
        rangeAxis.setAutoTickUnitSelection(false);//και απενεργοποιούμε την αυτόματη επιλογή μονάδων του άξονα τιμών για εμφάνιση ακέραιας μονάδας
		return chart;
    }
    
    
    
    
}

   

    


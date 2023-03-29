/*
Αυτό το πακέτο αποτελεί μέρος της εφαρμογής MealsApp και 
δημιουργεί ένα διάγραμμα πίτας των πιο συχνά επισκεπτόμενων γευμάτων.
*/
package mealsapp.Charts;


import java.text.NumberFormat;
import java.util.List;
import mealsapp.DB.MealsQueryOperations;
import mealsapp.MealClasses.Meal;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import javax.swing.JPanel;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;

/**
 * Η κλάση PieChart δημιουργεί ένα διάγραμμα πίτας των πιο συχνά επισκεπτόμενων γευμάτων.
 */
public class PieChart extends ApplicationFrame {
    /**
     * Η μέθοδος pieDataset() δημιουργεί τα δεδομένα του διαγράμματος πίτας.
     * Ανακτά τα 15 πιο επισκεπτόμενα γεύματα και τα προσθέτει στο dataset.
     */
    public static DefaultPieDataset pieDataset() {
        List<Meal> orderedMeal = MealsQueryOperations.getbyDecOrder();
        final DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < orderedMeal.size() && i <= 9; i++) {
            dataset.setValue(orderedMeal.get(i).getStrmeal(), orderedMeal.get(i).getTimesofvisit());
        }
        return dataset;
    }
    /**
     * Η μέθοδος PieChart() δημιουργεί ένα πάνελ που περιλαμβάνει το διάγραμμα πίτας και
     *επιστρέφει το πάνελ στο κυρίως πρόγραμμα για να το προβάλει.
     */
    public static JPanel PieChart() {
        // Δημιουργεί το διάγραμμα πίτας .
        JFreeChart chart = generateImagePieChart();
        // Δημιουργεί ένα πάνελ που περιλαμβάνει το διάγραμμα πίτας.
        JPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(814, 594);

        // Ορίζουμε ένα formatter για τη μορφοποίηση των αριθμών του γραφήματος.
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        // Δημιουργούμε έναν generator που θα φτιάχνει τις ετικέτες των τομέων της πίτας, βάσει των δεδομένων που δίνουμε.
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} : {1}", formatter, formatter);
        // Ορίζουμε τον generator ως generator ετικετών στο γράφημα.
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        return chartPanel;

    }

    public PieChart(String title) {
        super(title);
    }
    // Κατασκευάζουμε το γράφημα της πίτας και επιστρέφουμε το αντικείμενο τύπου JFreeChart
    public static JFreeChart generateImagePieChart() {
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                "Most Visited Meals", // τίτλος του γραφήματος
                pieDataset(), // δεδομένα
                false, //εμφάνιση λεζάντας
                true,//εμφάνιση tooltips
                true);//εμφάνιση urls

        return chart;
    }
}

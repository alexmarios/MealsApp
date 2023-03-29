package mealsapp.Charts;

import java.awt.Desktop;
import mealsapp.MealClasses.Meal;
import java.io.File;
import mealsapp.DB.MealsQueryOperations;
import java.util.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import mealsapp.GUI.MainForm;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class PrintList {

    // Μέθοδος για εξαγωγή σε PDF ανάλογα με την επιλογή του χρήστη (3Δ Στήλες, Pie Chart ή Λίστα)
    public void exportToPDF(int userView) throws FileNotFoundException, DocumentException, IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save PDF File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {

            File pdfFile = fileChooser.getSelectedFile();
            // Προσθήκη επέκτασης .pdf στο όνομα αρχείου, αν δεν υπάρχει ήδη
            if (!pdfFile.getName().toLowerCase().endsWith(".pdf")) {
                pdfFile = new File(pdfFile.getAbsolutePath() + ".pdf");
            }
            if (pdfFile.exists()) {
                // Ερώτηση αν θέλετε να αντικαταστήσετε το αρχείο PDF σε περίπτωση που υπάρχει ήδη.
                int replaceOption = MainForm.wantQuestionYesNo("The file already exists. Do you want to replace it?");
                System.out.println(replaceOption);
                if (replaceOption == 1) {
                } else {
                    if (userView == 0) {
                        generateColomunPDF(pdfFile);
                    } else if (userView == 1) {
                        generatePiePDF(pdfFile);

                    } else if (userView == 2) {
                        pdfColumnContent(pdfFile);
                    }
                }
            } else {
                if (userView == 0) {
                    generateColomunPDF(pdfFile);
                } else if (userView == 1) {
                    generatePiePDF(pdfFile);
                } else if (userView == 2) {
                    pdfColumnContent(pdfFile);

                }

            }
        }
    }

    //Mέθοδος που χρησιμοποιείται για να δημιουργήσει έναν πίνακα με τα δεδομένα των γευμάτων και να τα εξάγει σε μορφή PDF.
    public static void pdfColumnContent(File pdfFile) {

        try {
            // Λίστα με όλα τα γεύματα που έχουν καταγραφεί στη βάση δεδομένων, ταξινομημένα κατά φθίνουσα σειρά
            List<Meal> meals = MealsQueryOperations.getbyDecOrder();// Λίστα με όλα τα γεύματα που έχουν καταγραφεί στη βάση δεδομένων, ταξινομημένα κατά φθίνουσα σειρά

            // Δημιουργία ενός νέου κενού αρχείου PDF
            Document document = new Document(PageSize.A4);// Δημιουργία ενός νέου κενού αρχείου PDF
            // Αρχικοποίηση PdfWriter για να γραφεί το αρχείο pdf στον σκληρό δίσκο
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile)); // Αρχικοποίηση PdfWriter για να γραφεί το αρχείο pdf στον σκληρό δίσκο

            // Άνοιγμα του κενού αρχείου PDF
            document.open();
            // Δημιουργία πίνακα με δύο στήλες
            PdfPTable table = new PdfPTable(2);
            // Ορισμός του ποσοστού του πίνακα που πρέπει να καλύπτει το PDF
            table.setWidthPercentage(100);
            // Δημιουργία του κελιού με την επικεφαλίδα της πρώτης στήλης
            PdfPCell cell1 = new PdfPCell(new Paragraph("Meal"));
            // Δημιουργία του κελιού με την επικεφαλίδα της δεύτερης στήλης
            PdfPCell cell2 = new PdfPCell(new Paragraph("Times of visit"));
            // Ορισμός της οριζόντιας στοίχισης του κελιού της πρώτης στήλης
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            // Ορισμός της οριζόντιας στοίχισης του κελιού της δεύτερης στήλης
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            // Προσθήκη του κελιού της πρώτης στήλης στον πίνακα
            table.addCell(cell1);
            // Προσθήκη του κελιού της δεύτερης στήλης στον πίνακα
            table.addCell(cell2);

            // Επανάληψη για όλα τα γεύματα που βρίσκονται στη λίστα
            for (int i = 0; i < meals.size(); i++) {
                // Δημιουργία κελιών για τα δεδομένα του πίνακα
                PdfPCell dataCell1 = new PdfPCell(new Paragraph(meals.get(i).getStrmeal()));
                PdfPCell dataCell2 = new PdfPCell(new Paragraph(meals.get(i).getTimesofvisit().toString()));

                dataCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                dataCell2.setHorizontalAlignment(Element.ALIGN_LEFT);

                dataCell1.setPaddingBottom(10);
                dataCell2.setPaddingBottom(10);

                table.addCell(dataCell1);
                table.addCell(dataCell2);
            }

            // Φόρτωση της γραμματοσειράς  Harlow Solid Italic
            var fontPath = "resources/Harlow_Solid_Italic.ttf";
            var bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            // Δημιουργία ενός αντικειμένου font με γραμματοσειρά Harlow Solid Italic
            var font = new com.itextpdf.text.Font(bf, 22);
            // Δημιουργία επικεφαλίδας με γραμματοσειρά Harlow Solid Italic
            var title = new com.itextpdf.text.Paragraph("Meals App Table Report", font);
            title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);//Στοίχιση στο κέντρο
            document.add(title);
            document.add(new Paragraph(" "));
            document.add(table);
            document.close();
            // Εμφάνιση μηνύματος επιτυχημένης αποθήκευσης
            JOptionPane.showMessageDialog(null, "Saved successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
            // Άνοιγμα του αρχείου PDF για προεπισκόπηση μετά την επιτυχημένη αποθήκευση
            Desktop.getDesktop().open(pdfFile);
        } catch (Exception e) {

        }

    }

    //Μέθοδος για τη δημιουργία ενός PDF αρχείου με ένα διάγραμμα στήλης.
    public static void generateColomunPDF(File pdfFile) throws FileNotFoundException, DocumentException, IOException {
        try {
            // Δημιουργία κενού αρχείου PDF με ρυθμίσεις σελίδας Α4 landscape
            Document document = new Document(PageSize.A4.rotate());
            // Προετοιμασία του αντικειμένου που θα εγγράψει το αρχείο PDF στο δίσκο
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

            document.open();

            // Προετοιμασία ενός αντικειμένου για να σχεδιαστεί το γράφημα σε αυτό
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(500, 400);
            // Προετοιμασία ενός αντικειμένου γραφικών για να σχεδιαστεί το γράφημα πάνω σε αυτό
            @SuppressWarnings("deprecation")
            Graphics2D graphics2d = template.createGraphicsShapes(500, 400);

            Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, 500, 400);
            // Σχεδίαση του γραφήματος με τη βοήθεια της κλάσης ColumnChart
            ColumnChart.generateBarChart().draw(graphics2d, rectangle2d);

            graphics2d.dispose();
            // Προσθήκη του γραφήματος στο PDF στις δοθήσες συντεταγμένες 
            contentByte.addTemplate(template, 120, 100);

            document.close();
            writer.close();
            // Άνοιγμα του αρχείου PDF για προεπισκόπηση μετά την επιτυχημένη αποθήκευση
            Desktop.getDesktop().open(pdfFile);

        } catch (Exception e) {
        }

    }

    //Μέθοδος για τη δημιουργία ενός PDF αρχείου με ένα γράφημα τύπου πίτας
    public static void generatePiePDF(File pdfFile) throws FileNotFoundException, DocumentException {

        try {
            // Δημιουργία και ανοίγματος του κεντρικού κειμένου του PDF αρχείου
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
            // Δημιουργία επιφάνειας σχεδίασης και προσαρμογή των διαστάσεών της
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(500, 400);
            @SuppressWarnings("deprecation")
            Graphics2D graphics2d = template.createGraphicsShapes(500, 400);
            // Δημιουργία του Pie Chart
            Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, 500, 400);

            PieChart.generateImagePieChart().draw(graphics2d, rectangle2d);

            graphics2d.dispose();
            // Προσθήκη του σχεδιασμένου Pie Chart στο PDF
            contentByte.addTemplate(template, 160, 100);
            
            document.close();
            writer.close();
            //Ανοιγμα του αρχείου στον υπολογιστή του χρήστη
            Desktop.getDesktop().open(pdfFile);
        } catch (Exception e) {
        }
    }
}

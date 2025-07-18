import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class KuesionerController {

    // Sidebar Navigation Buttons
    @FXML private Button btnDashboard;
    @FXML private Button btnArtikel;
    @FXML private Button btnPernapasan;
    @FXML private Button btnKuesioner;
    @FXML private Button btnTodoList;
    
    // Questionnaire Buttons
    @FXML private Button mood1;
    @FXML private Button mood2;
    @FXML private Button anxiety1;
    @FXML private Button anxiety2;
    @FXML private Button sleep1;
    @FXML private Button sleep2;
    @FXML private Button stress1;
    @FXML private Button stress2;
    @FXML private Button breathing1;
    @FXML private Button breathing2;
    @FXML private Button btnSubmit;
    
    // Result Display
    @FXML private TextArea resultArea;
    
    // Jawaban Kuesioner
    private String mood, anxiety, sleep, stress, breathing;

    //=============================================
    // SIDEBAR MENU HANDLERS
    //=============================================
    
   @FXML
    private void handleDashboardMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal membuka halaman Dashboard.");
        }
    }

    @FXML
    private void handleArtikelMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Artikel.fxml"));
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal membuka halaman Artikel.");
        }
    }

    @FXML
    private void handlePernapasanMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Panduanbernafas1.fxml"));
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal membuka halaman Pernapasan.");
        }
    }

    @FXML
    private void handleKuesionerMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/kusioner.fxml"));
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal membuka halaman Kuesioner.");
        }
    }

    @FXML
    private void handleTodoListMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ToDoList.fxml"));
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal membuka halaman To-Do List.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //=============================================
    // QUESTIONNAIRE HANDLERS
    //=============================================
    
    @FXML
    private void handleMoodYes() {
        mood = "Ya";
        System.out.println("Mood: Ya");
    }
    
    @FXML 
    private void handleMoodNo() {
        mood = "Tidak";
        System.out.println("Mood: Tidak");
    }
    
    @FXML
    private void handleAnxietyYes() {
        anxiety = "Ya";
        System.out.println("Anxiety: Ya");
    }
    
    @FXML 
    private void handleAnxietyNo() {
        anxiety = "Tidak";
        System.out.println("Anxiety: Tidak");
    }
    
    @FXML
    private void handleSleepYes() {
        sleep = "Ya";
        System.out.println("Sleep: Ya");
    }
    
    @FXML 
    private void handleSleepNo() {
        sleep = "Tidak";
        System.out.println("Sleep: Tidak");
    }
    
    @FXML
    private void handleStressYes() {
        stress = "Ya";
        System.out.println("Stress: Ya");
    }
    
    @FXML 
    private void handleStressNo() {
        stress = "Tidak";
        System.out.println("Stress: Tidak");
    }
    
    @FXML
    private void handleBreathingYes() {
        breathing = "Ya";
        System.out.println("Breathing: Ya");
    }
    
    @FXML 
    private void handleBreathingNo() {
        breathing = "Tidak"; 
        System.out.println("Breathing: Tidak");
    }
    
    @FXML
    private void handleSubmit() {
        if (mood == null || anxiety == null || sleep == null || stress == null || breathing == null) {
            showAlert("Harap isi semua pertanyaan sebelum submit!");
            return;
        }
        
        String result = generateResult();
        resultArea.setText(result);
        resultArea.setVisible(true);
        
        System.out.println("Hasil Kuesioner:");
        System.out.println(result);
    }
    
    private String generateResult() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== HASIL EVALUASI ===\n\n");
        
        sb.append("1. Mood: ").append(mood).append("\n");
        sb.append("2. Anxiety: ").append(anxiety).append("\n");
        sb.append("3. Sleep: ").append(sleep).append("\n");
        sb.append("4. Stress: ").append(stress).append("\n");
        sb.append("5. Breathing: ").append(breathing).append("\n\n");
        
        sb.append("=== REKOMENDASI ===\n");
        // Tambahkan rekomendasi sesuai jawaban
         int countYa = 0;
          int countTidak = 0;
        
        if ("Ya".equals(mood)) countYa++; else countTidak++;
        if ("Ya".equals(anxiety)) countYa++; else countTidak++;
        if ("Ya".equals(sleep)) countYa++; else countTidak++;
        if ("Ya".equals(stress)) countYa++; else countTidak++;
        if ("Ya".equals(breathing)) countYa++; else countTidak++;

         sb.append("\n"); // Add a newline for better formatting before the general recommendation

        // Specific recommendations based on individual answers
        if (mood.equals("Tidak")) sb.append("- Perbaiki suasana hati dengan melakukan hal-hal positif seperti mendengarkan musik favorit, berbicara dengan teman, atau melakukan hobi yang kamu nikmati.\n");
        if (anxiety.equals("Ya")) sb.append("- Cobalah latihan pernapasan dalam untuk menenangkan diri, atau mulailah journaling untuk mencatat perasaan dan pikiranmu. Ini bisa membantu mengurangi kecemasan.\n");
        if (sleep.equals("Tidak")) sb.append("- Tidur yang cukup sangat penting untuk kesehatan mental. Coba kurangi penggunaan gawai di malam hari setidaknya satu jam sebelum tidur, dan ciptakan rutinitas tidur yang teratur.\n");
        if (stress.equals("Ya")) sb.append("- Penting untuk istirahat sejenak dari pekerjaan atau aktivitas yang membuat stres. Dengarkan musik yang menenangkan, lakukan jalan kaki singkat di luar ruangan, atau coba meditasi ringan untuk meredakan ketegangan.\n");
        if (breathing.equals("Tidak")) sb.append("- Cobalah latihan relaksasi pernapasan selama 5 menit di sore hari. Fokus pada tarikan napas dalam-dalam dan hembusan napas perlahan untuk menenangkan sistem sarafmu.\n");

        // General recommendation based on the total number of "Ya" or "Tidak" answers
        if (countYa >= 3) { // If 3 or more 'Ya' answers (indicating potential issues)
        sb.append("\nKondisi mentalmu saat ini mungkin membutuhkan perhatian lebih. Disarankan untuk mencari bantuan profesional jika masalah ini terus berlanjut atau memburuk. Jangan ragu untuk berbicara dengan seseorang yang kamu percaya. 😟");
        } else if (countTidak >= 3) { // If 3 or more 'Tidak' answers (indicating generally good condition)
         sb.append("\nKondisi mentalmu hari ini terlihat cukup stabil. Pertahankan gaya hidup sehat dan terus lakukan hal-hal positif yang mendukung kesehatan mentalmu. 👍");
        } else { // Mixed answers
         sb.append("\nAda beberapa area yang bisa kamu perbaiki untuk meningkatkan kesehatan mentalmu. Tetap pantau diri dan coba terapkan rekomendasi di atas secara rutin. 💪");
}
        
        return sb.toString();
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Peringatan");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

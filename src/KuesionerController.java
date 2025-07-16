import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class KuesionerController implements Initializable {
    
    // Navigation buttons
    @FXML private Button btnArtikel;
    @FXML private Button btnPernapasan;
    @FXML private Button btnKuesioner;
    @FXML private Button btnTodoList;
    
    // Question buttons
    @FXML private Button mood1, mood2;
    @FXML private Button anxiety1, anxiety2;
    @FXML private Button sleep1, sleep2;
    @FXML private Button stress1, stress2;
    @FXML private Button breathing1, breathing2;
    
    // Labels
    @FXML private Label moodLabel;
    @FXML private Label anxietyLabel;
    @FXML private Label sleepLabel;
    @FXML private Label stressLabel;
    @FXML private Label breathingLabel;
    
    // Submit button and result area
    @FXML private Button btnSubmit;
    @FXML private VBox resultArea;
    @FXML private Label resultTitle;
    @FXML private Label resultText;
    @FXML private Label recommendationText;
    
    // Main content areas
    @FXML private VBox mainContent;
    @FXML private VBox kuesionerContent;
    
    // Variables to store answers
    private String moodAnswer = "";
    private String anxietyAnswer = "";
    private String sleepAnswer = "";
    private String stressAnswer = "";
    private String breathingAnswer = "";
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize result area as hidden
        resultArea.setVisible(false);
        
        // Set up hover effects for navigation buttons
        setupHoverEffects();
    }
    
    // Navigation handlers
    @FXML
    private void handleArtikelMenu() {
        System.out.println("Artikel menu clicked");
        // TODO: Implement navigation to artikel page
    }
    
    @FXML
    private void handlePernapasanMenu() {
        System.out.println("Pernapasan menu clicked");
        // TODO: Implement navigation to pernapasan page
    }
    
    @FXML
    private void handleKuesionerMenu() {
        System.out.println("Kuesioner menu clicked");
        // Already on kuesioner page
    }
    
    @FXML
    private void handleTodoListMenu() {
        System.out.println("Todo List menu clicked");
        // TODO: Implement navigation to todo list page
    }
    
    // Question 1: Mood handlers
    @FXML
    private void handleMoodYes() {
        moodAnswer = "yes";
        updateButtonSelection(mood1, mood2);
        moodLabel.setText("✓ Senang mendengarnya! Pertahankan mood positif Anda.");
        moodLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #27ae60;");
    }
    
    @FXML
    private void handleMoodNo() {
        moodAnswer = "no";
        updateButtonSelection(mood2, mood1);
        moodLabel.setText("Tidak apa-apa, hari yang buruk bisa terjadi pada siapa saja.");
        moodLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");
    }
    
    // Question 2: Anxiety handlers
    @FXML
    private void handleAnxietyYes() {
        anxietyAnswer = "yes";
        updateButtonSelection(anxiety1, anxiety2);
        anxietyLabel.setText("Coba lakukan teknik relaksasi untuk mengurangi kecemasan.");
        anxietyLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");
    }
    
    @FXML
    private void handleAnxietyNo() {
        anxietyAnswer = "no";
        updateButtonSelection(anxiety2, anxiety1);
        anxietyLabel.setText("✓ Bagus! Anda berhasil mengelola kecemasan dengan baik.");
        anxietyLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #27ae60;");
    }
    
    // Question 3: Sleep handlers
    @FXML
    private void handleSleepYes() {
        sleepAnswer = "yes";
        updateButtonSelection(sleep1, sleep2);
        sleepLabel.setText("✓ Tidur yang berkualitas sangat penting untuk kesehatan mental.");
        sleepLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #27ae60;");
    }
    
    @FXML
    private void handleSleepNo() {
        sleepAnswer = "no";
        updateButtonSelection(sleep2, sleep1);
        sleepLabel.setText("Cobalah untuk tidur lebih awal dan ciptakan rutinitas tidur yang baik.");
        sleepLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");
    }
    
    // Question 4: Stress handlers
    @FXML
    private void handleStressYes() {
        stressAnswer = "yes";
        updateButtonSelection(stress1, stress2);
        stressLabel.setText("Identifikasi sumber stres dan coba teknik manajemen stres.");
        stressLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");
    }
    
    @FXML
    private void handleStressNo() {
        stressAnswer = "no";
        updateButtonSelection(stress2, stress1);
        stressLabel.setText("✓ Hebat! Anda berhasil mengelola stres dengan baik hari ini.");
        stressLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #27ae60;");
    }
    
    // Question 5: Breathing/Relaxation handlers
    @FXML
    private void handleBreathingYes() {
        breathingAnswer = "yes";
        updateButtonSelection(breathing1, breathing2);
        breathingLabel.setText("✓ Luar biasa! Aktivitas relaksasi sangat baik untuk mental health.");
        breathingLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #27ae60;");
    }
    
    @FXML
    private void handleBreathingNo() {
        breathingAnswer = "no";
        updateButtonSelection(breathing2, breathing1);
        breathingLabel.setText("Cobalah sisihkan waktu untuk aktivitas relaksasi setiap hari.");
        breathingLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");
    }
    
    // Submit handler
    @FXML
    private void handleSubmit() {
        if (isAllAnswered()) {
            calculateResult();
            showResultWithAnimation();
        } else {
            // Show alert or message that all questions must be answered
            System.out.println("Please answer all questions before submitting.");
        }
    }
    
    // Helper methods
    private void updateButtonSelection(Button selected, Button deselected) {
        // Update selected button style
        selected.setStyle(selected.getStyle() + " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 2);");
        
        // Reset deselected button style
        String baseStyle = deselected.getUserData().equals("yes") ? 
            "-fx-background-color: #e8f5e8; -fx-text-fill: #2e7d32; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #4caf50; -fx-border-width: 2;" :
            "-fx-background-color: #fce4ec; -fx-text-fill: #c62828; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #e91e63; -fx-border-width: 2;";
        deselected.setStyle(baseStyle);
    }
    
    private boolean isAllAnswered() {
        return !moodAnswer.isEmpty() && !anxietyAnswer.isEmpty() && 
               !sleepAnswer.isEmpty() && !stressAnswer.isEmpty() && 
               !breathingAnswer.isEmpty();
    }
    
    private void calculateResult() {
        int positiveScore = 0;
        int totalQuestions = 5;
        
        // Calculate positive responses
        if (moodAnswer.equals("yes")) positiveScore++;
        if (anxietyAnswer.equals("no")) positiveScore++;  // No anxiety is positive
        if (sleepAnswer.equals("yes")) positiveScore++;
        if (stressAnswer.equals("no")) positiveScore++;   // No stress is positive
        if (breathingAnswer.equals("yes")) positiveScore++;
        
        double percentage = (double) positiveScore / totalQuestions * 100;
        
        // Generate result and recommendation
        String result;
        String recommendation;
        String titleStyle;
        
        if (percentage >= 80) {
            result = "Excellent! Kesehatan mental Anda dalam kondisi sangat baik hari ini.";
            recommendation = "Pertahankan kebiasaan positif ini dan tetap konsisten dengan rutinitas sehat Anda.";
            titleStyle = "-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #27ae60;";
        } else if (percentage >= 60) {
            result = "Good! Kesehatan mental Anda cukup baik, namun masih ada ruang untuk perbaikan.";
            recommendation = "Cobalah untuk lebih fokus pada area yang masih perlu diperbaiki, seperti kualitas tidur atau manajemen stres.";
            titleStyle = "-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #f39c12;";
        } else if (percentage >= 40) {
            result = "Perlu Perhatian. Kesehatan mental Anda memerlukan perhatian lebih.";
            recommendation = "Disarankan untuk melakukan aktivitas relaksasi, memperbaiki pola tidur, dan mengelola stres dengan lebih baik.";
            titleStyle = "-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #e67e22;";
        } else {
            result = "Perlu Bantuan Segera. Kesehatan mental Anda memerlukan perhatian serius.";
            recommendation = "Sangat disarankan untuk berkonsultasi dengan profesional kesehatan mental dan mulai menerapkan teknik self-care secara konsisten.";
            titleStyle = "-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #e74c3c;";
        }
        
        resultTitle.setText("Hasil Evaluasi Harian (" + String.format("%.0f", percentage) + "%)");
        resultTitle.setStyle(titleStyle);
        resultText.setText(result);
        recommendationText.setText("Rekomendasi: " + recommendation);
    }
    
    private void showResultWithAnimation() {
        resultArea.setVisible(true);
        
        // Fade in animation
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), resultArea);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        
        // Scroll to result area
        // You might want to add scroll functionality here
    }
    
    private void setupHoverEffects() {
        // Add hover effects to navigation buttons
        setupButtonHover(btnArtikel);
        setupButtonHover(btnPernapasan);
        setupButtonHover(btnTodoList);
        // btnKuesioner is already active, so no hover needed
    }
    
    private void setupButtonHover(Button button) {
        button.setOnMouseEntered(e -> {
            button.setStyle(button.getStyle() + " -fx-background-color: #4db6ac;");
        });
        
        button.setOnMouseExited(e -> {
            button.setStyle(button.getStyle().replace(" -fx-background-color: #4db6ac;", ""));
        });
    }
}
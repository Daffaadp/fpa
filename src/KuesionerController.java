import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class KuesionerController implements Initializable {

    // ========== FXML Elements (sudah ada di FXML) ==========
    @FXML private VBox mainContent;
    @FXML private VBox kuesionerContent;
    @FXML private VBox resultArea;
    
    // Navigation buttons
    @FXML private Button btnDashboard;
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
    @FXML private Label moodLabel, anxietyLabel, sleepLabel, stressLabel, breathingLabel;
    @FXML private Label resultTitle, resultText, recommendationText;
    
    // Submit button
    @FXML private Button btnSubmit;

    // ========== DATA STRUCTURE ARRAYS (ditambahkan di Controller) ==========
    
    // 1. Array untuk menyimpan pertanyaan kuesioner
    private String[] questions = {
        "Apakah Anda merasa bahagia atau senang hari ini?",
        "Apakah Anda merasa cemas atau khawatir berlebihan hari ini?",
        "Apakah Anda tidur dengan nyenyak tadi malam?",
        "Apakah Anda mengalami stres yang berat hari ini?",
        "Apakah Anda melakukan aktivitas relaksasi (seperti meditasi atau latihan pernapasan) hari ini?"
    };

    // 2. Array untuk menyimpan deskripsi/hint setiap pertanyaan
    private String[] questionHints = {
        "Pilih jawaban yang sesuai dengan perasaan Anda",
        "Kecemasan yang mengganggu aktivitas sehari-hari",
        "Tidur yang berkualitas dan tidak terganggu",
        "Stres yang mengganggu aktivitas atau konsentrasi",
        "Aktivitas untuk menenangkan pikiran dan tubuh"
    };

    // 3. Array untuk menyimpan jawaban user (boolean: true = Ya, false = Tidak)
    private boolean[] userAnswers = new boolean[5];

    // 4. Array untuk menyimpan status apakah pertanyaan sudah dijawab
    private boolean[] answeredStatus = new boolean[5];

    // 5. Array untuk kategori pertanyaan (untuk analisis)
    private String[] questionCategories = {
        "mood", "anxiety", "sleep", "stress", "relaxation"
    };

    // 6. Array untuk bobot scoring (positif/negatif impact)
    private int[] questionWeights = {
        1,   // Mood positif = +1
        -1,  // Anxiety = -1 (jawaban Ya = negatif)
        1,   // Sleep quality = +1
        -1,  // Stress = -1 (jawaban Ya = negatif)
        1    // Relaxation = +1
    };

    // 7. Array untuk rekomendasi berdasarkan jawaban
    private String[][] recommendations = {
        // Rekomendasi untuk mood [Ya, Tidak]
        {
            "Hebat! Pertahankan suasana hati yang positif ini.",
            "Coba lakukan aktivitas yang menyenangkan atau berbicara dengan teman dekat."
        },
        // Rekomendasi untuk anxiety [Ya, Tidak]
        {
            "Pertimbangkan untuk melakukan teknik relaksasi atau konsultasi dengan profesional.",
            "Bagus! Anda berhasil mengelola kecemasan dengan baik hari ini."
        },
        // Rekomendasi untuk sleep [Ya, Tidak]
        {
            "Tidur yang berkualitas sangat baik untuk kesehatan mental Anda.",
            "Cobalah untuk memperbaiki kualitas tidur dengan rutinitas yang konsisten."
        },
        // Rekomendasi untuk stress [Ya, Tidak]
        {
            "Pertimbangkan untuk mengurangi beban kerja atau melakukan aktivitas yang menenangkan.",
            "Baik! Anda berhasil mengelola stres dengan efektif."
        },
        // Rekomendasi untuk relaxation [Ya, Tidak]
        {
            "Luar biasa! Aktivitas relaksasi sangat baik untuk kesehatan mental.",
            "Cobalah untuk meluangkan waktu melakukan meditasi atau latihan pernapasan."
        }
    };

    // 8. Array untuk menyimpan hasil evaluasi berdasarkan skor
    private String[] evaluationResults = {
        "Kondisi Mental: Perlu Perhatian Khusus",
        "Kondisi Mental: Cukup Baik", 
        "Kondisi Mental: Baik",
        "Kondisi Mental: Sangat Baik"
    };

    // 9. Array untuk menyimpan rentang skor evaluasi
    private int[][] scoreRanges = {
        {-5, -3},  // Perlu perhatian khusus
        {-2, 0},   // Cukup baik
        {1, 3},    // Baik
        {4, 5}     // Sangat baik
    };

    // 10. Array untuk menyimpan emoji berdasarkan hasil
    private String[] resultEmojis = {
        "😟",  // Perlu perhatian
        "😐",  // Cukup baik
        "😊",  // Baik
        "😄"   // Sangat baik
    };

    // 11. Struktur data untuk menyimpan riwayat jawaban harian
    public class DailyResponse {
        String date;
        boolean[] answers;
        int totalScore;
        String evaluation;
        
        public DailyResponse(String date, boolean[] answers, int totalScore, String evaluation) {
            this.date = date;
            this.answers = answers.clone();
            this.totalScore = totalScore;
            this.evaluation = evaluation;
        }
    }

    // Array untuk menyimpan riwayat
    private ArrayList<DailyResponse> responseHistory = new ArrayList<>();

    // ========== INITIALIZE METHOD ==========
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize default state
        resetQuestionnaire();
        updateSubmitButtonState();
    }

    // ========== EVENT HANDLERS (yang ada di FXML) ==========
    
    // Navigation handlers
    @FXML
    private void handleDashboardMenu(ActionEvent event) {
        // Navigate to dashboard
    }

    @FXML
    private void handleArtikelMenu(ActionEvent event) {
        // Navigate to artikel
    }

    @FXML
    private void handlePernapasanMenu(ActionEvent event) {
        // Navigate to pernapasan
    }

    @FXML
    private void handleKuesionerMenu(ActionEvent event) {
        // Already on kuesioner page
    }

    @FXML
    private void handleTodoListMenu(ActionEvent event) {
        // Navigate to todo list
    }

    // Question handlers
    @FXML
    private void handleMoodYes(ActionEvent event) {
        setAnswer(0, true);
        updateButtonStates(mood1, mood2);
    }

    @FXML
    private void handleMoodNo(ActionEvent event) {
        setAnswer(0, false);
        updateButtonStates(mood2, mood1);
    }

    @FXML
    private void handleAnxietyYes(ActionEvent event) {
        setAnswer(1, true);
        updateButtonStates(anxiety1, anxiety2);
    }

    @FXML
    private void handleAnxietyNo(ActionEvent event) {
        setAnswer(1, false);
        updateButtonStates(anxiety2, anxiety1);
    }

    @FXML
    private void handleSleepYes(ActionEvent event) {
        setAnswer(2, true);
        updateButtonStates(sleep1, sleep2);
    }

    @FXML
    private void handleSleepNo(ActionEvent event) {
        setAnswer(2, false);
        updateButtonStates(sleep2, sleep1);
    }

    @FXML
    private void handleStressYes(ActionEvent event) {
        setAnswer(3, true);
        updateButtonStates(stress1, stress2);
    }

    @FXML
    private void handleStressNo(ActionEvent event) {
        setAnswer(3, false);
        updateButtonStates(stress2, stress1);
    }

    @FXML
    private void handleBreathingYes(ActionEvent event) {
        setAnswer(4, true);
        updateButtonStates(breathing1, breathing2);
    }

    @FXML
    private void handleBreathingNo(ActionEvent event) {
        setAnswer(4, false);
        updateButtonStates(breathing2, breathing1);
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        if (isAllAnswered()) {
            processResults();
        }
    }

    // ========== HELPER METHODS ==========
    
    private void setAnswer(int questionIndex, boolean answer) {
        userAnswers[questionIndex] = answer;
        answeredStatus[questionIndex] = true;
        updateSubmitButtonState();
    }

    private void updateButtonStates(Button selected, Button unselected) {
        // Update selected button style
        selected.setStyle(selected.getStyle() + "; -fx-background-color: #4caf50; -fx-text-fill: white;");
        
        // Reset unselected button style
        String baseStyle = unselected.getUserData().equals("yes") ? 
            "-fx-background-color: #e8f5e8; -fx-text-fill: #2e7d32;" : 
            "-fx-background-color: #fce4ec; -fx-text-fill: #c62828;";
        unselected.setStyle(baseStyle + " -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 20; -fx-border-radius: 20;");
    }

    private void updateSubmitButtonState() {
        btnSubmit.setDisable(!isAllAnswered());
    }

    private int calculateTotalScore() {
        int totalScore = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            if (answeredStatus[i]) {
                totalScore += userAnswers[i] ? questionWeights[i] : -questionWeights[i];
            }
        }
        return totalScore;
    }

    private String getEvaluationResult(int score) {
        for (int i = 0; i < scoreRanges.length; i++) {
            if (score >= scoreRanges[i][0] && score <= scoreRanges[i][1]) {
                return evaluationResults[i];
            }
        }
        return evaluationResults[1]; // Default: Cukup baik
    }

    private String getRecommendations() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rekomendasi untuk Anda:\n\n");
        
        for (int i = 0; i < userAnswers.length; i++) {
            if (answeredStatus[i]) {
                int answerIndex = userAnswers[i] ? 0 : 1;
                sb.append("• ").append(recommendations[i][answerIndex]).append("\n");
            }
        }
        
        return sb.toString();
    }

    private void processResults() {
        int totalScore = calculateTotalScore();
        String evaluation = getEvaluationResult(totalScore);
        String recommendations = getRecommendations();
        
        // Save to history
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        responseHistory.add(new DailyResponse(currentDate, userAnswers, totalScore, evaluation));
        
        // Display results
        resultTitle.setText("📊 " + evaluation);
        resultText.setText("Skor Anda: " + totalScore + "/5\n" + getScoreInterpretation(totalScore));
        recommendationText.setText(recommendations);
        
        // Show result area
        resultArea.setVisible(true);
        
        // Scroll to results
        mainContent.getChildren().get(mainContent.getChildren().size() - 1).requestFocus();
    }

    private String getScoreInterpretation(int score) {
        if (score >= 4) return "Anda memiliki kondisi mental yang sangat baik! 😄";
        else if (score >= 1) return "Kondisi mental Anda cukup baik. 😊";
        else if (score >= -2) return "Kondisi mental Anda perlu sedikit perhatian. 😐";
        else return "Kondisi mental Anda memerlukan perhatian khusus. 😟";
    }

    public void resetQuestionnaire() {
        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = false;
            answeredStatus[i] = false;
        }
        resultArea.setVisible(false);
        updateSubmitButtonState();
    }

    public boolean isAllAnswered() {
        for (boolean answered : answeredStatus) {
            if (!answered) return false;
        }
        return true;
    }

    // Getter for history (jika diperlukan di class lain)
    public ArrayList<DailyResponse> getResponseHistory() {
        return responseHistory;
    }
}
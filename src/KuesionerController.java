import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KuesionerController implements Initializable {

    // ========== FXML Elements ==========
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

    // ========== DATA STRUCTURE ARRAYS ==========
    
    // Array untuk menyimpan pertanyaan kuesioner
    private String[] questions = {
        "Apakah Anda merasa bahagia atau senang hari ini?",
        "Apakah Anda merasa cemas atau khawatir berlebihan hari ini?",
        "Apakah Anda tidur dengan nyenyak tadi malam?",
        "Apakah Anda mengalami stres yang berat hari ini?",
        "Apakah Anda melakukan aktivitas relaksasi (seperti meditasi atau latihan pernapasan) hari ini?"
    };

    // Array untuk menyimpan jawaban user
    private boolean[] userAnswers = new boolean[5];

    // Array untuk menyimpan status apakah pertanyaan sudah dijawab
    private boolean[] answeredStatus = new boolean[5];

    // Array untuk kategori pertanyaan
    private String[] questionCategories = {
        "mood", "anxiety", "sleep", "stress", "relaxation"
    };

    // Array untuk bobot scoring
    private int[] questionWeights = {
        1,   // Mood positif = +1
        -1,  // Anxiety = -1 (jawaban Ya = negatif)
        1,   // Sleep quality = +1
        -1,  // Stress = -1 (jawaban Ya = negatif)
        1    // Relaxation = +1
    };

    // Array untuk rekomendasi LENGKAP berdasarkan jawaban
    private String[][] detailedRecommendations = {
        // Rekomendasi untuk mood [Ya, Tidak]
        {
            "Mood Anda Positif: Hebat! Anda sedang dalam kondisi emosional yang baik. Pertahankan dengan terus melakukan aktivitas yang membuat Anda bahagia, seperti hobi favorit, berolahraga ringan, atau berkumpul dengan orang-orang terkasih.",
            "Mood Perlu Diperbaiki: Jangan khawatir, ini normal terjadi. Cobalah melakukan aktivitas yang biasanya membuat Anda senang, seperti mendengarkan musik favorit, menonton film yang menghibur, atau berbicara dengan teman dekat. Jika berlanjut, pertimbangkan untuk berkonsultasi dengan konselor."
        },
        // Rekomendasi untuk anxiety [Ya, Tidak]
        {
            "Mengelola Kecemasan: Kecemasan yang Anda rasakan dapat dikelola dengan teknik relaksasi. Cobalah teknik pernapasan dalam (tarik napas 4 detik, tahan 4 detik, buang 6 detik), meditasi singkat 5-10 menit, atau aktivitas fisik ringan seperti jalan santai. Jika kecemasan mengganggu aktivitas sehari-hari, disarankan untuk berkonsultasi dengan psikolog.",
            "Kecemasan Terkendali: Bagus! Anda berhasil mengelola kecemasan dengan baik hari ini. Pertahankan strategi yang sudah Anda gunakan dan terus praktikkan teknik-teknik relaksasi sebagai pencegahan."
        },
        // Rekomendasi untuk sleep [Ya, Tidak]
        {
            "Kualitas Tidur Baik: Tidur yang berkualitas sangat mendukung kesehatan mental Anda. Pertahankan rutinitas tidur yang konsisten, hindari gadget 1 jam sebelum tidur, dan ciptakan suasana kamar yang nyaman dan gelap.",
            "Perbaiki Kualitas Tidur: Tidur yang kurang berkualitas dapat mempengaruhi mood dan konsentrasi. Cobalah membuat rutinitas tidur yang konsisten (tidur dan bangun di waktu yang sama), hindari kafein setelah jam 2 siang, lakukan aktivitas relaksasi sebelum tidur seperti membaca atau mandi air hangat."
        },
        // Rekomendasi untuk stress [Ya, Tidak]
        {
            "Mengatasi Stres Berat: Stres yang berat perlu ditangani dengan serius. Cobalah teknik manajemen stres seperti membuat prioritas tugas, istirahat secara teratur, berbicara dengan orang yang dipercaya, atau melakukan aktivitas yang menenangkan seperti yoga atau meditasi. Jangan ragu untuk meminta bantuan profesional jika diperlukan.",
            "Stres Terkendali: Luar biasa! Anda berhasil mengelola stres dengan efektif. Pertahankan strategi yang sudah berhasil dan terus jaga keseimbangan antara pekerjaan dan istirahat."
        },
        // Rekomendasi untuk relaxation [Ya, Tidak]
        {
            "Aktivitas Relaksasi Baik: Sangat baik! Anda sudah memiliki kesadaran untuk merawat kesehatan mental melalui aktivitas relaksasi. Terus lanjutkan praktik ini secara konsisten, dan variasikan dengan teknik-teknik baru seperti mindfulness, yoga, atau journaling.",
            "Perlu Aktivitas Relaksasi: Sangat penting untuk meluangkan waktu untuk relaksasi. Cobalah mulai dengan 5-10 menit setiap hari untuk aktivitas yang menenangkan seperti meditasi, mendengarkan musik relaksasi, latihan pernapasan, atau sekadar duduk dalam keheningan. Ini akan membantu mengurangi stres dan meningkatkan kesejahteraan mental."
        }
    };

    // Array untuk hasil evaluasi
    private String[] evaluationResults = {
        "Kondisi Mental: Perlu Perhatian Khusus",
        "Kondisi Mental: Cukup Baik", 
        "Kondisi Mental: Baik",
        "Kondisi Mental: Sangat Baik"
    };

    // Array untuk rentang skor evaluasi
    private int[][] scoreRanges = {
        {-5, -3},  // Perlu perhatian khusus
        {-2, 0},   // Cukup baik
        {1, 3},    // Baik
        {4, 5}     // Sangat baik
    };

    // Struktur data untuk menyimpan riwayat jawaban harian
    public class DailyResponse {
        String date;
        String time;
        boolean[] answers;
        int totalScore;
        String evaluation;
        String[] recommendations;
        
        public DailyResponse(String date, String time, boolean[] answers, int totalScore, String evaluation, String[] recommendations) {
            this.date = date;
            this.time = time;
            this.answers = answers.clone();
            this.totalScore = totalScore;
            this.evaluation = evaluation;
            this.recommendations = recommendations.clone();
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
        updateActiveMenuButton();
        
        // Load existing data if available
        loadExistingData();
    }

    // ========== DATA SAVING METHODS ==========
    
    /**
     * Simpan data ke file CSV
     */
    private void saveDataToFile(DailyResponse response) {
        try {
            // Buat folder data jika belum ada
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }
            
            // Nama file berdasarkan tanggal
            String fileName = "data/kuesioner_" + response.date.replace("-", "") + ".csv";
            File file = new File(fileName);
            
            // Cek apakah file sudah ada (untuk header)
            boolean fileExists = file.exists();
            
            // Tulis data ke file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                // Tulis header jika file baru
                if (!fileExists) {
                    writer.write("Tanggal,Waktu,Mood,Kecemasan,Tidur,Stres,Relaksasi,Total_Skor,Evaluasi");
                    writer.newLine();
                }
                
                // Tulis data
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%d,%s",
                    response.date,
                    response.time,
                    response.answers[0] ? "Ya" : "Tidak",
                    response.answers[1] ? "Ya" : "Tidak",
                    response.answers[2] ? "Ya" : "Tidak",
                    response.answers[3] ? "Ya" : "Tidak",
                    response.answers[4] ? "Ya" : "Tidak",
                    response.totalScore,
                    response.evaluation
                ));
                writer.newLine();
                
                System.out.println("Data berhasil disimpan ke: " + fileName);
            }
            
        } catch (IOException e) {
            System.err.println("Error menyimpan data: " + e.getMessage());
            showErrorAlert("Error Penyimpanan", "Gagal menyimpan data ke file: " + e.getMessage());
        }
    }

    /**
     * Simpan data ke file teks yang lebih mudah dibaca
     */
    private void saveDetailedReport(DailyResponse response) {
        try {
            // Buat folder reports jika belum ada
            File reportsFolder = new File("reports");
            if (!reportsFolder.exists()) {
                reportsFolder.mkdirs();
            }
            
            // Nama file laporan
            String fileName = "reports/laporan_" + response.date.replace("-", "") + "_" + 
                            response.time.replace(":", "") + ".txt";
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("========================================");
                writer.newLine();
                writer.write("      LAPORAN KUESIONER KESEHATAN MENTAL");
                writer.newLine();
                writer.write("========================================");
                writer.newLine();
                writer.write("Tanggal: " + response.date);
                writer.newLine();
                writer.write("Waktu: " + response.time);
                writer.newLine();
                writer.newLine();
                
                writer.write("JAWABAN KUESIONER:");
                writer.newLine();
                writer.write("1. Mood bahagia: " + (response.answers[0] ? "Ya" : "Tidak"));
                writer.newLine();
                writer.write("2. Kecemasan berlebihan: " + (response.answers[1] ? "Ya" : "Tidak"));
                writer.newLine();
                writer.write("3. Tidur nyenyak: " + (response.answers[2] ? "Ya" : "Tidak"));
                writer.newLine();
                writer.write("4. Stres berat: " + (response.answers[3] ? "Ya" : "Tidak"));
                writer.newLine();
                writer.write("5. Aktivitas relaksasi: " + (response.answers[4] ? "Ya" : "Tidak"));
                writer.newLine();
                writer.newLine();
                
                writer.write("HASIL EVALUASI: " + response.evaluation);
                writer.newLine();
                writer.newLine();
                
                writer.write("REKOMENDASI LENGKAP:");
                writer.newLine();
                writer.write("----------------------------------------");
                writer.newLine();
                for (int i = 0; i < response.recommendations.length; i++) {
                    writer.write((i + 1) + ". " + response.recommendations[i]);
                    writer.newLine();
                    writer.newLine();
                }
                
                writer.write("========================================");
                writer.newLine();
                writer.write("Laporan ini dibuat secara otomatis oleh sistem LAPER");
                writer.newLine();
                writer.write("========================================");
                
                System.out.println("Laporan detail berhasil disimpan ke: " + fileName);
            }
            
        } catch (IOException e) {
            System.err.println("Error menyimpan laporan: " + e.getMessage());
        }
    }

    /**
     * Load data yang sudah ada (jika diperlukan)
     */
    private void loadExistingData() {
        // Implementasi untuk load data dari file jika diperlukan
        // Saat ini hanya print info
        System.out.println("Sistem siap menerima data kuesioner...");
    }

    // ========== ENHANCED RESULT PROCESSING ==========
    
    /**
     * Proses hasil dengan penyimpanan data dan rekomendasi lengkap
     */
    private void processResults() {
        int totalScore = calculateTotalScore();
        String evaluation = getEvaluationResult(totalScore);
        
        // Dapatkan rekomendasi lengkap
        String[] recommendations = getDetailedRecommendations();
        
        // Buat response object
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        
        DailyResponse response = new DailyResponse(currentDate, currentTime, userAnswers, totalScore, evaluation, recommendations);
        
        // Simpan ke history
        responseHistory.add(response);
        
        // Simpan ke file
        saveDataToFile(response);
        saveDetailedReport(response);
        
        // Display results tanpa skor
        displayResults(response);
        
        // Show success notification
        showSuccessNotification();
    }

    /**
     * Dapatkan rekomendasi lengkap berdasarkan jawaban
     */
    private String[] getDetailedRecommendations() {
        ArrayList<String> recommendations = new ArrayList<>();
        
        for (int i = 0; i < userAnswers.length; i++) {
            if (answeredStatus[i]) {
                int answerIndex = userAnswers[i] ? 0 : 1;
                recommendations.add(detailedRecommendations[i][answerIndex]);
            }
        }
        
        return recommendations.toArray(new String[0]);
    }

    /**
     * Tampilkan hasil tanpa skor
     */
    private void displayResults(DailyResponse response) {
        // Set judul hasil
        resultTitle.setText("📊 " + response.evaluation);
        
        // Set interpretasi tanpa skor
        resultText.setText(getDetailedInterpretation(response.totalScore));
        
        // Set rekomendasi lengkap
        StringBuilder sb = new StringBuilder();
        sb.append("📝 REKOMENDASI PERSONAL UNTUK ANDA:\n\n");
        
        for (int i = 0; i < response.recommendations.length; i++) {
            sb.append("💡 ").append(response.recommendations[i]).append("\n\n");
        }
        
        sb.append("🌟 TIPS TAMBAHAN:\n");
        sb.append("• Lakukan kuesioner ini secara rutin untuk memantau perkembangan kesehatan mental Anda\n");
        sb.append("• Jangan ragu untuk mencari bantuan profesional jika diperlukan\n");
        sb.append("• Ingatlah bahwa merawat kesehatan mental sama pentingnya dengan kesehatan fisik\n");
        
        recommendationText.setText(sb.toString());
        
        // Show result area
        resultArea.setVisible(true);
        
        // Scroll to results
        if (mainContent.getChildren().size() > 0) {
            mainContent.getChildren().get(mainContent.getChildren().size() - 1).requestFocus();
        }
    }

    /**
     * Interpretasi hasil tanpa menampilkan skor
     */
    private String getDetailedInterpretation(int score) {
        if (score >= 4) {
            return "Kondisi mental Anda sangat baik! 😄\n\n" +
                   "Anda menunjukkan tanda-tanda kesehatan mental yang optimal. Terus pertahankan gaya hidup dan kebiasaan positif yang sudah Anda jalani.";
        } else if (score >= 1) {
            return "Kondisi mental Anda cukup baik! 😊\n\n" +
                   "Anda berada dalam kondisi yang stabil. Ada beberapa area yang bisa ditingkatkan untuk mencapai kesehatan mental yang lebih optimal.";
        } else if (score >= -2) {
            return "Kondisi mental Anda perlu sedikit perhatian. 😐\n\n" +
                   "Beberapa aspek kesehatan mental Anda memerlukan perhatian khusus. Ikuti rekomendasi yang diberikan dan jangan ragu untuk mencari dukungan.";
        } else {
            return "Kondisi mental Anda memerlukan perhatian khusus. 😟\n\n" +
                   "Saat ini Anda mungkin mengalami beberapa tantangan dalam kesehatan mental. Sangat disarankan untuk mengikuti rekomendasi dan mencari bantuan profesional.";
        }
    }

    /**
     * Tampilkan notifikasi berhasil menyimpan
     */
    private void showSuccessNotification() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Data Berhasil Disimpan!");
        alert.setHeaderText("Kuesioner Telah Selesai");
        alert.setContentText("Data kuesioner Anda telah berhasil disimpan dan rekomendasi personal telah disiapkan. " +
                           "Anda dapat melihat hasilnya di bawah ini dan file laporan telah dibuat untuk referensi Anda.");
        alert.showAndWait();
    }

    // ========== EXISTING METHODS (UNCHANGED) ==========
    
    @FXML
    private void handleSubmit(ActionEvent event) {
        if (isAllAnswered()) {
            processResults();
        }
    }

    private void setAnswer(int questionIndex, boolean answer) {
        userAnswers[questionIndex] = answer;
        answeredStatus[questionIndex] = true;
        updateSubmitButtonState();
    }

    private void updateButtonStates(Button selected, Button unselected) {
        selected.setStyle(selected.getStyle() + "; -fx-background-color: #4caf50; -fx-text-fill: white;");
        
        String baseStyle = unselected.getUserData() != null && unselected.getUserData().equals("yes") ? 
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

    public boolean isAllAnswered() {
        for (boolean answered : answeredStatus) {
            if (!answered) return false;
        }
        return true;
    }

    public void resetQuestionnaire() {
        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = false;
            answeredStatus[i] = false;
        }
        
        resetQuestionButtonStyles();
        resultArea.setVisible(false);
        updateSubmitButtonState();
    }

    private void resetQuestionButtonStyles() {
        String yesStyle = "-fx-background-color: #e8f5e8; -fx-text-fill: #2e7d32; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 20; -fx-border-radius: 20;";
        String noStyle = "-fx-background-color: #fce4ec; -fx-text-fill: #c62828; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 20; -fx-border-radius: 20;";
        
        if (mood1 != null) mood1.setStyle(yesStyle);
        if (mood2 != null) mood2.setStyle(noStyle);
        if (anxiety1 != null) anxiety1.setStyle(yesStyle);
        if (anxiety2 != null) anxiety2.setStyle(noStyle);
        if (sleep1 != null) sleep1.setStyle(yesStyle);
        if (sleep2 != null) sleep2.setStyle(noStyle);
        if (stress1 != null) stress1.setStyle(yesStyle);
        if (stress2 != null) stress2.setStyle(noStyle);
        if (breathing1 != null) breathing1.setStyle(yesStyle);
        if (breathing2 != null) breathing2.setStyle(noStyle);
    }

    // ========== QUESTION HANDLERS ==========
    
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

    // ========== NAVIGATION METHODS (UNCHANGED) ==========
    
    @FXML
    private void handleDashboardMenu(ActionEvent event) {
        // ... existing navigation code ...
    }

    @FXML
    private void handleArtikelMenu(ActionEvent event) {
        // ... existing navigation code ...
    }

    @FXML
    private void handlePernapasanMenu(ActionEvent event) {
        // ... existing navigation code ...
    }

    @FXML
    private void handleKuesionerMenu(ActionEvent event) {
        if (hasUnsavedProgress()) {
            if (showConfirmationDialog("Reset Kuesioner", 
                "Apakah Anda ingin mereset kuesioner dan memulai dari awal?")) {
                resetQuestionnaire();
            }
        }
        updateActiveMenuButton();
    }

    @FXML
    private void handleTodoListMenu(ActionEvent event) {
        // ... existing navigation code ...
    }

    // ========== HELPER METHODS ==========
    
    private boolean hasUnsavedProgress() {
        for (boolean answered : answeredStatus) {
            if (answered) return true;
        }
        return false;
    }

    private boolean showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        return alert.showAndWait().orElse(null) == javafx.scene.control.ButtonType.OK;
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateActiveMenuButton() {
        resetMenuButtonStyles();
        btnKuesioner.setStyle("-fx-background-color: #26a69a; -fx-text-fill: white; -fx-font-weight: bold;");
    }

    private void resetMenuButtonStyles() {
        String defaultStyle = "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: normal;";
        
        btnDashboard.setStyle(defaultStyle);
        btnArtikel.setStyle(defaultStyle);
        btnPernapasan.setStyle(defaultStyle);
        btnKuesioner.setStyle(defaultStyle);
        btnTodoList.setStyle(defaultStyle);
    }

    // Getter for history
    public ArrayList<DailyResponse> getResponseHistory() {
        return responseHistory;
    }
}
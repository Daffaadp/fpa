package controller;

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
            Parent root = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/view/article.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/view/Panduanbernafas1.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/view/kusioner.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/view/ToDoList.fxml"));
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
        updateButtonStyles(mood1, mood2);
    }
    
    @FXML 
    private void handleMoodNo() {
        mood = "Tidak";
        updateButtonStyles(mood2, mood1);
    }
    
    @FXML
    private void handleAnxietyYes() {
        anxiety = "Ya";
        updateButtonStyles(anxiety1, anxiety2);
    }
    
    @FXML 
    private void handleAnxietyNo() {
        anxiety = "Tidak";
        updateButtonStyles(anxiety2, anxiety1);
    }
    
    @FXML
    private void handleSleepYes() {
        sleep = "Ya";
        updateButtonStyles(sleep1, sleep2);
    }
    
    @FXML 
    private void handleSleepNo() {
        sleep = "Tidak";
        updateButtonStyles(sleep2, sleep1);
    }
    
    @FXML
    private void handleStressYes() {
        stress = "Ya";
        updateButtonStyles(stress1, stress2);
    }
    
    @FXML 
    private void handleStressNo() {
        stress = "Tidak";
        updateButtonStyles(stress2, stress1);
    }
    
    @FXML
    private void handleBreathingYes() {
        breathing = "Ya";
        updateButtonStyles(breathing1, breathing2);
    }
    
    @FXML 
    private void handleBreathingNo() {
        breathing = "Tidak"; 
        updateButtonStyles(breathing2, breathing1);
    }
    
    // Method to update button styles when selected
    private void updateButtonStyles(Button selected, Button unselected) {
        selected.setStyle(selected.getStyle() + "; -fx-opacity: 1.0; -fx-font-weight: bold;");
        unselected.setStyle(unselected.getStyle() + "; -fx-opacity: 0.6; -fx-font-weight: normal;");
    }
    
    @FXML
    private void handleSubmit() {
        if (mood == null || anxiety == null || sleep == null || stress == null || breathing == null) {
            showAlert("Peringatan", "Harap isi semua pertanyaan sebelum submit!");
            return;
        }
        
        String result = generateEnhancedResult();
        resultArea.setText(result);
        resultArea.setVisible(true);
        
        // Scroll to result area
        resultArea.setScrollTop(0);
        
        System.out.println("Hasil Kuesioner:");
        System.out.println(result);
    }
    
    private String generateEnhancedResult() {
        StringBuilder sb = new StringBuilder();
        
        // Calculate mental health score
        MentalHealthScore score = calculateMentalHealthScore();
        
        sb.append("🧠 === EVALUASI KESEHATAN MENTAL === 🧠\n\n");
        
        // Display answers
        sb.append("📋 JAWABAN ANDA:\n");
        sb.append("• Mood positif: ").append(mood).append("\n");
        sb.append("• Mengalami kecemasan: ").append(anxiety).append("\n");
        sb.append("• Tidur nyenyak: ").append(sleep).append("\n");
        sb.append("• Stres berat: ").append(stress).append("\n");
        sb.append("• Aktivitas relaksasi: ").append(breathing).append("\n\n");
        
        // Display overall condition
        sb.append("🎯 KONDISI MENTAL SAAT INI:\n");
        sb.append("Status: ").append(score.getCondition()).append("\n");
        //sb.append("Skor: ").append(score.getScore()).append("/100\n");
        sb.append("Kategori: ").append(score.getCategory()).append("\n\n");
        
        sb.append("📝 PENJELASAN:\n");
        sb.append(score.getExplanation()).append("\n\n");
        
        sb.append("💡 REKOMENDASI KHUSUS:\n");
        sb.append(generateSpecificRecommendations(score)).append("\n");
        
        sb.append("🌟 LANGKAH SELANJUTNYA:\n");
        sb.append(generateNextSteps(score));
        
        return sb.toString();
    }
    
    private MentalHealthScore calculateMentalHealthScore() {
        int positivePoints = 0;
        int negativePoints = 0;
        
        // Calculate points based on answers
        if ("Ya".equals(mood)) positivePoints += 25; else negativePoints += 25;
        if ("Tidak".equals(anxiety)) positivePoints += 20; else negativePoints += 20;
        if ("Ya".equals(sleep)) positivePoints += 20; else negativePoints += 20;
        if ("Tidak".equals(stress)) positivePoints += 20; else negativePoints += 20;
        if ("Ya".equals(breathing)) positivePoints += 15; else negativePoints += 15;
        
        int totalScore = positivePoints;
        
        String condition, category, explanation;
        
        if (totalScore >= 80) {
            condition = "🟢 SANGAT BAIK";
            category = "Kesehatan Mental Optimal";
            explanation = "Kondisi mental Anda sangat baik! Anda menunjukkan tanda-tanda kesehatan mental yang optimal dengan mood positif, tidur berkualitas, stres terkendali, dan memiliki rutinitas self-care yang baik.";
        } else if (totalScore >= 60) {
            condition = "🟡 BAIK";
            category = "Kesehatan Mental Stabil";
            explanation = "Kondisi mental Anda cukup baik dan stabil. Ada beberapa area yang bisa diperbaiki, namun secara keseluruhan Anda masih dalam kondisi yang sehat.";
        } else if (totalScore >= 40) {
            condition = "🟠 PERLU PERHATIAN";
            category = "Kesehatan Mental Butuh Perbaikan";
            explanation = "Kondisi mental Anda memerlukan perhatian lebih. Beberapa aspek kesehatan mental menunjukkan tanda-tanda yang perlu diperbaiki untuk mencegah kondisi yang lebih buruk.";
        } else {
            condition = "🔴 MEMERLUKAN BANTUAN";
            category = "Kesehatan Mental Berisiko";
            explanation = "Kondisi mental Anda saat ini memerlukan perhatian serius. Sangat disarankan untuk mencari bantuan profesional dan melakukan perubahan signifikan dalam rutinitas harian.";
        }
        
        return new MentalHealthScore(totalScore, condition, category, explanation);
    }
    
    private String generateSpecificRecommendations(MentalHealthScore score) {
        StringBuilder recommendations = new StringBuilder();
        
        // Specific recommendations based on individual answers
        if ("Tidak".equals(mood)) {
            recommendations.append("🌈 MOOD IMPROVEMENT:\n");
            recommendations.append("  - Lakukan aktivitas yang Anda nikmati selama 30 menit setiap hari\n");
            recommendations.append("  - Dengarkan musik favorit atau podcast motivasi\n");
            recommendations.append("  - Hubungi teman atau keluarga untuk berbagi cerita\n");
            recommendations.append("  - Coba gratitude journaling - tulis 3 hal yang Anda syukuri\n\n");
        }
        
        if ("Ya".equals(anxiety)) {
            recommendations.append("😌 MENGATASI KECEMASAN:\n");
            recommendations.append("  - Praktikkan teknik grounding 5-4-3-2-1 (5 hal yang dilihat, 4 yang disentuh, dll)\n");
            recommendations.append("  - Lakukan breathing exercise 4-7-8 (tarik napas 4 detik, tahan 7, hembuskan 8)\n");
            recommendations.append("  - Batasi konsumsi kafein dan media sosial\n");
            recommendations.append("  - Tulis kekhawatiran di jurnal dan cari solusi konkret\n\n");
        }
        
        if ("Tidak".equals(sleep)) {
            recommendations.append("😴 PERBAIKAN KUALITAS TIDUR:\n");
            recommendations.append("  - Buat rutinitas tidur yang konsisten (tidur dan bangun di jam yang sama)\n");
            recommendations.append("  - Hindari layar gadget 1-2 jam sebelum tidur\n");
            recommendations.append("  - Ciptakan lingkungan tidur yang nyaman (gelap, sejuk, tenang)\n");
            recommendations.append("  - Minum teh chamomile atau susu hangat sebelum tidur\n\n");
        }
        
        if ("Ya".equals(stress)) {
            recommendations.append("🎯 MANAJEMEN STRES:\n");
            recommendations.append("  - Identifikasi sumber stres dan buat action plan\n");
            recommendations.append("  - Gunakan teknik time management seperti Pomodoro Technique\n");
            recommendations.append("  - Lakukan aktivitas fisik ringan seperti jalan kaki atau stretching\n");
            recommendations.append("  - Praktikkan saying 'no' untuk komitmen yang berlebihan\n\n");
        }
        
        if ("Tidak".equals(breathing)) {
            recommendations.append("🧘 AKTIVITAS RELAKSASI:\n");
            recommendations.append("  - Mulai dengan meditasi 5-10 menit menggunakan app seperti Headspace\n");
            recommendations.append("  - Coba progressive muscle relaxation sebelum tidur\n");
            recommendations.append("  - Lakukan yoga ringan atau tai chi\n");
            recommendations.append("  - Habiskan waktu 15 menit di alam (taman, balkon dengan tanaman)\n\n");
        }
        
        return recommendations.toString();
    }
    
    private String generateNextSteps(MentalHealthScore score) {
        StringBuilder nextSteps = new StringBuilder();
        
        if (score.getScore() >= 80) {
            nextSteps.append("✨ Pertahankan rutinitas positif yang sudah Anda jalankan!\n");
            nextSteps.append("📚 Pelajari lebih lanjut tentang wellness untuk mempertahankan kondisi optimal\n");
            nextSteps.append("🤝 Pertimbangkan untuk membantu orang lain yang membutuhkan dukungan mental\n");
            nextSteps.append("📅 Lakukan evaluasi rutin setiap minggu untuk memantau kondisi");
        } else if (score.getScore() >= 60) {
            nextSteps.append("🎯 Fokus pada 1-2 area yang perlu diperbaiki\n");
            nextSteps.append("📊 Monitor progress dengan mengisi kuesioner ini secara rutin\n");
            nextSteps.append("💪 Konsisten menerapkan rekomendasi selama 2-3 minggu\n");
            nextSteps.append("👥 Pertimbangkan untuk bergabung dengan support group atau komunitas wellness");
        } else if (score.getScore() >= 40) {
            nextSteps.append("🚨 Prioritaskan self-care dalam rutinitas harian\n");
            nextSteps.append("📞 Pertimbangkan untuk konsultasi dengan konselor atau psikolog\n");
            nextSteps.append("🏥 Evaluasi kondisi fisik dengan dokter (kadang masalah mental terkait kesehatan fisik)\n");
            nextSteps.append("⏰ Isi kuesioner ini setiap 3 hari untuk monitoring ketat");
        } else {
            nextSteps.append("🆘 SEGERA cari bantuan profesional (psikolog/psikiater)\n");
            nextSteps.append("🏥 Pertimbangkan konsultasi dengan dokter untuk evaluasi menyeluruh\n");
            nextSteps.append("👨‍👩‍👧‍👦 Informasikan kondisi Anda ke keluarga atau teman terdekat\n");
            nextSteps.append("☎  Jika merasa dalam bahaya: hubungi hotline kesehatan mental 119 ext 8\n");
            nextSteps.append("📱 Install aplikasi crisis support atau simpan nomor darurat");
        }
        
        return nextSteps.toString();
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Peringatan");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Inner class untuk menyimpan hasil evaluasi
    private class MentalHealthScore {
        private int score;
        private String condition;
        private String category;
        private String explanation;
        
        public MentalHealthScore(int score, String condition, String category, String explanation) {
            this.score = score;
            this.condition = condition;
            this.category = category;
            this.explanation = explanation;
        }
        
        public int getScore() { return score; }
        public String getCondition() { return condition; }
        public String getCategory() { return category; }
        public String getExplanation() { return explanation;}
    }
}
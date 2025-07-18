import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class ToDoListController {
    
    // ArrayList untuk menyimpan task breathing exercises
    private List<BreathingTask> breathingTasks = new ArrayList<>();
    
    // FXML CheckBox references - tambahkan sesuai dengan fx:id di FXML
    @FXML private CheckBox cbDudukTegak;
    @FXML private CheckBox cbTarikNapasPagi;
    @FXML private CheckBox cbTahanNapasPagi;
    @FXML private CheckBox cbBuangNapasPagi;
    @FXML private CheckBox cbTahanLagiPagi;
    @FXML private CheckBox cbUlangiPagi;
    
    @FXML private CheckBox cbDudukSore;
    @FXML private CheckBox cbTarikNapasSore;
    @FXML private CheckBox cbTahanNapasSore;
    @FXML private CheckBox cbBuangNapasSore;
    @FXML private CheckBox cbUlangiSore;
    
    @FXML private CheckBox cbBerbaringMalam;
    @FXML private CheckBox cbLetakkanTangan;
    @FXML private CheckBox cbTarikNapasMalam;
    @FXML private CheckBox cbBuangNapasMalam;
    @FXML private CheckBox cbUlangiMalam;
    
    // Inner class untuk representasi task
    public static class BreathingTask {
        private String name;
        private String category;
        private boolean completed;
        
        public BreathingTask(String name, String category, boolean completed) {
            this.name = name;
            this.category = category;
            this.completed = completed;
        }
        
        // Getters dan setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        
        public boolean isCompleted() { return completed; }
        public void setCompleted(boolean completed) { this.completed = completed; }
        
        @Override
        public String toString() {
            return String.format("[%s] %s - %s", 
                completed ? "✓" : " ", category, name);
        }
    }
    
    @FXML
    private void initialize() {
        // Inisialisasi task list dengan data default
        initializeDefaultTasks();
        
        // Setup listeners untuk checkbox changes
        setupCheckboxListeners();
    }
    
    private void initializeDefaultTasks() {
        // Energizing Breathing tasks
        breathingTasks.add(new BreathingTask("Duduk tegak atau berdiri dengan bahu rilek", "Energizing Breathing", false));
        breathingTasks.add(new BreathingTask("Tarik napas lewat hidung selama 4 detik", "Energizing Breathing", false));
        breathingTasks.add(new BreathingTask("Tahan napas selama 4 detik", "Energizing Breathing", false));
        breathingTasks.add(new BreathingTask("Buang napas perlahan lewat mulut selama 4 detik", "Energizing Breathing", false));
        breathingTasks.add(new BreathingTask("Tahan lagi napas selama 4 detik sebelum menarik napas berikutnya", "Energizing Breathing", false));
        breathingTasks.add(new BreathingTask("Ulangi selama 5-10 siklus", "Energizing Breathing", false));
        
        // Relaxing Breathing tasks
        breathingTasks.add(new BreathingTask("Duduk di kursi atau bersila di lantai", "Relaxing Breathing", false));
        breathingTasks.add(new BreathingTask("Tarik napas pelan lewat hidung selama 4 detik", "Relaxing Breathing", false));
        breathingTasks.add(new BreathingTask("Tahan napas selama 7 detik", "Relaxing Breathing", false));
        breathingTasks.add(new BreathingTask("Buang napas perlahan lewat mulut selama 8 detik", "Relaxing Breathing", false));
        breathingTasks.add(new BreathingTask("Ulangi 4-6 kali", "Relaxing Breathing", false));
        
        // Sleep-inducing Breathing tasks
        breathingTasks.add(new BreathingTask("Berbaring atau duduk nyaman, tutup mata", "Sleep-inducing Breathing", false));
        breathingTasks.add(new BreathingTask("Letakkan tangan di perut untuk merasakan gerakan napas", "Sleep-inducing Breathing", false));
        breathingTasks.add(new BreathingTask("Tarik napas dalam lewat hidung selama 5 detik, rasakan perut mengembang", "Sleep-inducing Breathing", false));
        breathingTasks.add(new BreathingTask("Buang napas perlahan lewat mulut selama 6-7 detik, rasakan perut mengempis", "Sleep-inducing Breathing", false));
        breathingTasks.add(new BreathingTask("Ulangi 8-10 siklus atau sampai kamu merasa mengantuk", "Sleep-inducing Breathing", false));
    }
    
    private void setupCheckboxListeners() {
        // Setup listener untuk setiap checkbox
        if (cbDudukTegak != null) {
            cbDudukTegak.selectedProperty().addListener((obs, oldVal, newVal) -> {
                updateTaskStatus("Duduk tegak atau berdiri dengan bahu rilek", newVal);
            });
        }
        
        if (cbTarikNapasPagi != null) {
            cbTarikNapasPagi.selectedProperty().addListener((obs, oldVal, newVal) -> {
                updateTaskStatus("Tarik napas lewat hidung selama 4 detik", newVal);
            });
        }
        
        // Tambahkan listener untuk checkbox lainnya sesuai kebutuhan
        // ... (implementasi serupa untuk checkbox lainnya)
    }
    
    private void updateTaskStatus(String taskName, boolean completed) {
        for (BreathingTask task : breathingTasks) {
            if (task.getName().equals(taskName)) {
                task.setCompleted(completed);
                break;
            }
        }
    }
    
    @FXML
    private void handleSelesaiButton(ActionEvent event) {
        // Simpan progress dan tampilkan notifikasi
        saveProgress();
        showCompletionNotification();
    }
    
    private void saveProgress() {
        // Hitung berapa banyak task yang telah diselesaikan
        int completedTasks = 0;
        int totalTasks = breathingTasks.size();
        
        for (BreathingTask task : breathingTasks) {
            if (task.isCompleted()) {
                completedTasks++;
            }
        }
        
        // Simpan ke ArrayList (bisa diperluas ke file/database)
        System.out.println("=== PROGRESS TERSIMPAN ===");
        System.out.println("Total task: " + totalTasks);
        System.out.println("Task selesai: " + completedTasks);
        System.out.println("Persentase: " + (completedTasks * 100 / totalTasks) + "%");
        System.out.println("\nDetail tasks:");
        
        for (BreathingTask task : breathingTasks) {
            System.out.println(task.toString());
        }
    }
    
    private void showCompletionNotification() {
        int completedTasks = 0;
        for (BreathingTask task : breathingTasks) {
            if (task.isCompleted()) {
                completedTasks++;
            }
        }
        
        String title = "Progress Tersimpan!";
        String message;
        
        if (completedTasks == 0) {
            message = "Belum ada latihan pernapasan yang diselesaikan. Yuk mulai latihan untuk kesehatan mental yang lebih baik!";
        } else if (completedTasks == breathingTasks.size()) {
            message = "Selamat! Anda telah menyelesaikan semua latihan pernapasan hari ini. " +
                     "Lanjutkan rutinitas ini untuk hasil yang optimal!";
        } else {
            message = String.format("Bagus! Anda telah menyelesaikan %d dari %d latihan pernapasan. " +
                                  "Progress Anda telah tersimpan.", completedTasks, breathingTasks.size());
        }
        
        // Tampilkan notifikasi dengan alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Latihan Pernapasan - " + java.time.LocalDate.now().format(
            java.time.format.DateTimeFormatter.ofPattern("dd MMMM yyyy")));
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Method untuk mendapatkan semua tasks (untuk keperluan lain)
    public List<BreathingTask> getAllTasks() {
        return new ArrayList<>(breathingTasks);
    }
    
    // Method untuk reset semua tasks
    public void resetAllTasks() {
        for (BreathingTask task : breathingTasks) {
            task.setCompleted(false);
        }
        // Update UI checkboxes
        updateUICheckboxes();
    }
    
    private void updateUICheckboxes() {
        // Reset semua checkbox ke unchecked
        if (cbDudukTegak != null) cbDudukTegak.setSelected(false);
        if (cbTarikNapasPagi != null) cbTarikNapasPagi.setSelected(false);
        // ... reset checkbox lainnya
    }

    // Navigation methods (tetap sama)
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
}
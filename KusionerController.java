package App;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class KuesionerController {

    @FXML
    private ToggleGroup anxietyGroup; // Pastikan ini terhubung dengan grup radio untuk kecemasan
    @FXML
    private Button btnSubmit;

    @FXML
    private void handleSubmit() {
        // Mendapatkan nilai kecemasan dari radio button yang dipilih
        RadioButton selectedAnxiety = (RadioButton) anxietyGroup.getSelectedToggle();
        
        // Memastikan bahwa ada radio button yang dipilih
        if (selectedAnxiety != null) {
            // Perbaikan di sini: gunakan getUser Data() tanpa spasi
            int anxietyValue = Integer.parseInt(selectedAnxiety.getUserData().toString());

            // Memeriksa apakah nilai kecemasan darurat
            if (anxietyValue > 3) {
                // Arahkan ke halaman 5
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Halaman5.fxml"));
                    Stage stage = (Stage) btnSubmit.getScene().getWindow();
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Logika untuk melanjutkan ke halaman berikutnya
                showResults();
            }
        } else {
            // Tindakan jika tidak ada radio button yang dipilih
            System.out.println("Silakan pilih tingkat kecemasan.");
        }
    }

    private void showResults() {
        // Logika untuk menampilkan hasil kuesioner
        // Misalnya, menampilkan area hasil
        // resultArea.setVisible(true);
        // resultText.setText("Hasil kuesioner Anda...");
    }
}
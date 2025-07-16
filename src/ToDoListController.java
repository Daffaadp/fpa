import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ToDoListController {

   @FXML
    private void handleDashboardMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Scene scene = new Scene(root, width, height); // Ukuran tetap sama
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal membuka halaman Artikel.");
        }
    }

    @FXML
    private void handleArtikelMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Artikel.fxml"));
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Scene scene = new Scene(root, width, height); // Ukuran tetap sama
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal membuka halaman Artikel.");
        }
    }


    @FXML
    private void handlePernapasanMenu(ActionEvent event) {
        showAlert("Navigasi", "Berpindah ke halaman Latihan Pernapasan.");
    }

    @FXML
    private void handleKuesionerMenu(ActionEvent event) {
        showAlert("Navigasi", "Berpindah ke halaman Isi Kuesioner.");
    }

    @FXML
    private void handleTodoListMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/.fxml"));
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Scene scene = new Scene(root, width, height); // Ukuran tetap sama
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal membuka halaman Artikel.");
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

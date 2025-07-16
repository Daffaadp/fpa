import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ToDoListController {

    @FXML
    private void handleDashboardMenu(ActionEvent event) {
        showAlert("Navigasi", "Berpindah ke halaman Dashboard.");
    }
    
    @FXML
    private void handleArtikelMenu(ActionEvent event) {
        showAlert("Navigasi", "Berpindah ke halaman Artikel.");
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
        showAlert("Navigasi", "Kamu sudah berada di halaman To Do List.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

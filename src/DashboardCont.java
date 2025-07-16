import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;

public class DashboardCont {

    @FXML
    private Label greetingLabel;

    @FXML
    private Button beginSessionButton;

    @FXML
    private VBox todoBox;

    @FXML
    private VBox breathingBox;

    @FXML
    private VBox articlesBox;

    @FXML
    private VBox questionnairesBox;

    @FXML
    private void initialize() {
        setGreetingMessage();
        System.out.println("DashboardController initialized.");
    }

     private void setGreetingMessage() {
        LocalTime now = LocalTime.now();
        String greeting;

        if (now.isAfter(LocalTime.of(5, 0)) && now.isBefore(LocalTime.of(12, 0))) {
            greeting = "Selamat pagi!";
        } else if (now.isBefore(LocalTime.of(15, 0))) {
            greeting = "Selamat siang!";
        } else if (now.isBefore(LocalTime.of(19, 0))) {
            greeting = "Selamat sore!";
        } else {
            greeting = "Selamat malam!";
        }

        greetingLabel.setText(greeting);
    }

    @FXML
    private void handleBeginSession() {
        System.out.println("BEGIN SESSION clicked.");
        // Tambahkan logika untuk memulai sesi di sini
    }

    @FXML
    private void handleTodoClick(MouseEvent event) {
        System.out.println("To-Do List clicked.");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ToDoList.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (root != null) {
            Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void handleBreathingClick(MouseEvent event) {
        System.out.println("Breathing Guide clicked.");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Panduanbernafas1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (root != null) {
            Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        
    }

    @FXML
    private void handleArticlesClick(MouseEvent event) {
        System.out.println("Articles clicked.");
        // Tambahkan logika navigasi atau aksi lainnya
    }

    @FXML
    private void handleQuestionnairesClick(MouseEvent event) {
        System.out.println("Questionnaires clicked.");
        // Tambahkan logika navigasi atau aksi lainnya
    }
}

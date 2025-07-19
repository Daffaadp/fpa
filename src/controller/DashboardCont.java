package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    Alert alert = new Alert(AlertType.ERROR); // Or other appropriate type
    alert.setTitle(title);
    alert.setHeaderText(null); // No header text
    alert.setContentText(message);
    alert.showAndWait();
    }


    @FXML
    private void handleTodoClick(MouseEvent event) {
        System.out.println("To-Do List clicked.");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/ToDoList.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("/view/Panduanbernafas1.fxml"));
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
        Parent root = null;
         try {
            root = FXMLLoader.load(getClass().getResource("/view/article.fxml"));
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
    private void handleQuestionnairesClick(MouseEvent event) {
        System.out.println("Questionnaires clicked.");
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/kusioner.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (root != null) {
            Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
       
    }
}

package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainNotifikasi extends Application {

    @Override
    public void start(Stage primaryStage) {
        showNotifikasi();
    }

    public void showNotifikasi() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Notifikasi.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Notifikasi Pagi");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Maindashboard extends Application {
    
    @Override
    public void start(Stage primaryStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
            Scene scene = new Scene(loader.load());
            Parent root = loader.load();
            
            primaryStage.setTitle("Dashboard");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.setFullScreen(true);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

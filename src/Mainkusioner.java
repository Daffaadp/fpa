import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Mainkusioner extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("kusioner.fxml"));
            Parent root = loader.load();
            
            // Create scene
            Scene scene = new Scene(root, 1000, 700);
            
            // Set stage properties
            primaryStage.setTitle("LAPER - Mental Health Companion");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.setMinWidth(1000);
            primaryStage.setMinHeight(700);
            
            // Show the stage
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading FXML: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

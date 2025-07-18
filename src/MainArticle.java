import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class MainArticle extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("article.fxml"));
            Parent root = loader.load();
            
            // Create the scene
            Scene scene = new Scene(root, 1200, 800);
            
            // Set up the stage
            primaryStage.setTitle("LAPER - Article Management System");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.setMinWidth(1000);
            primaryStage.setMinHeight(700);
            
            // Show the stage 
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
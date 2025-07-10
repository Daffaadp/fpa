import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PanduanBernafasMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PanduanBernafas.fxml"));
            AnchorPane root = loader.load();
            
            // Create scene
            Scene scene = new Scene(root, 800, 600);
            
            // Set up primary stage
            primaryStage.setTitle("Aplikasi Panduan Bernafas");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true); 
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
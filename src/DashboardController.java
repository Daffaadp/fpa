import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.util.List;

public class DashboardController {
    
    @FXML
    private Label welcomeLabel;
    
    @FXML
    private TextArea usersTextArea;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private Button refreshButton;
    
    private DatabaseManager dbManager = new DatabaseManager();
    private String currentUsername;
    
    public void setUsername(String username) {
        this.currentUsername = username;
        welcomeLabel.setText("Welcome, " + username + "!");
        loadUsers();
    }
    
    @FXML
    private void initialize() {
        loadUsers();
    }
    
    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(loader.load());
            
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleRefresh() {
        loadUsers();
    }
    
    private void loadUsers() {
        List<String[]> users = dbManager.getAllUsers();
        StringBuilder sb = new StringBuilder();
        sb.append("All Registered Users:\n");
        sb.append("==================\n");
        
        for (String[] user : users) {
            if (user.length >= 3) {
                sb.append("Username: ").append(user[0]).append("\n");
                sb.append("Email: ").append(user[2]).append("\n");
                sb.append("----------\n");
            }
        }
        
        usersTextArea.setText(sb.toString());
    }
}
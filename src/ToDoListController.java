import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

public class ToDoListController {

    @FXML
    private ImageView homeImageView;

    @FXML
    private ImageView favoritImageView;

    @FXML
    private ImageView historyImageView;

    @FXML
    private Button homeButton;

    @FXML
    private Button favoritButton;

    @FXML
    private Button historyButton;

    @FXML
    public void initialize() {
        try {
            // Load gambar dari folder src/images
            homeImageView.setImage(new Image(getClass().getResourceAsStream("images/Home.png")));
            favoritImageView.setImage(new Image(getClass().getResourceAsStream("images/Favorit.png")));
            historyImageView.setImage(new Image(getClass().getResourceAsStream("images/History.png")));
        } catch (Exception e) {
            System.out.println("Gagal memuat gambar: " + e.getMessage());
        }
    }

    @FXML
    private void handleHomeButtonAction(ActionEvent event) {
        System.out.println("Navigasi ke Home");
        // Tambahkan logika navigasi jika perlu
    }

    @FXML
    private void handleFavoritButtonAction(ActionEvent event) {
        System.out.println("Navigasi ke Favorit");
        // Tambahkan logika navigasi jika perlu
    }

    @FXML
    private void handleHistoryButtonAction(ActionEvent event) {
        System.out.println("Navigasi ke History");
        // Tambahkan logika navigasi jika perlu
    }
}

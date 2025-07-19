import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.awt.Desktop;
import java.net.URI;

public class ArticleController implements Initializable {
    
    // FXML Components
    @FXML private Button dashboardBtn;
    @FXML private Button readArticleBtn;
    @FXML private Button addArticleBtn;
    @FXML private Button manageArticleBtn;
    @FXML private Button settingsBtn;
    
    @FXML private VBox readArticleView;
    @FXML private VBox addArticleView;
    @FXML private VBox manageArticleView;
    @FXML private Label dateLabel;
    
    @FXML private VBox articleListContainer;
    @FXML private VBox manageArticleContainer;
    
    @FXML private TextField titleField;
    @FXML private TextField urlField;
    @FXML private TextArea descriptionField;
    
    @FXML private Button saveArticleBtn;
    @FXML private Button cancelBtn;
    
    // Data storage
    private List<Article> articles;
    
    // Article class to store article data
    public static class Article {
        private String title;
        private String url;
        private String description;
        
        public Article(String title, String url, String description) {
            this.title = title;
            this.url = url;
            this.description = description;
        }
        
        public String getTitle() { return title; }
        public String getUrl() { return url; }
        public String getDescription() { return description; }
        
        public void setTitle(String title) { this.title = title; }
        public void setUrl(String url) { this.url = url; }
        public void setDescription(String description) { this.description = description; }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize data
        articles = new ArrayList<>();
        
        // Set current date
        updateDateLabel();
        
        // Add some sample articles
        addSampleArticles();
        
        // Load articles into views
        loadArticles();
        
        // Set initial view
        showReadArticleView();
    }
    
    private void updateDateLabel() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", new Locale("id", "ID"));
        dateLabel.setText(today.format(formatter));
    }
    
    private void addSampleArticles() {
        articles.add(new Article(
            "Teknik Pernapasan untuk Mengurangi Stres",
            "https://www.halodoc.com/artikel/teknik-pernapasan-untuk-mengurangi-stres",
            "Pelajari berbagai teknik pernapasan yang dapat membantu mengurangi tingkat stres dan kecemasan dalam kehidupan sehari-hari."
        ));
        
        articles.add(new Article(
            "Pentingnya Tidur Berkualitas untuk Kesehatan Mental",
            "https://www.alodokter.com/tidur-berkualitas-untuk-kesehatan-mental",
            "Memahami hubungan antara kualitas tidur dan kesehatan mental, serta tips untuk mendapatkan tidur yang lebih baik."
        ));
        
        articles.add(new Article(
            "Mindfulness dalam Kehidupan Sehari-hari",
            "https://www.sehatmental.id/mindfulness-kehidupan-sehari-hari",
            "Cara menerapkan praktik mindfulness dalam aktivitas harian untuk meningkatkan kesehatan mental dan kesejahteraan."
        ));
        
        articles.add(new Article(
            "Mengatasi Kecemasan dengan Teknik Grounding",
            "https://www.kompas.com/health/read/2023/07/15/teknik-grounding-untuk-kecemasan",
            "Teknik grounding yang efektif untuk membantu mengatasi serangan kecemasan dan panic attack."
        ));
    }
    
    private void loadArticles() {
        loadReadArticleView();
        loadManageArticleView();
    }
    
    private void loadReadArticleView() {
        articleListContainer.getChildren().clear();
        
        for (Article article : articles) {
            VBox articleCard = createArticleCard(article, false);
            articleListContainer.getChildren().add(articleCard);
        }
    }
    
    private void loadManageArticleView() {
        manageArticleContainer.getChildren().clear();
        
        for (Article article : articles) {
            VBox articleCard = createArticleCard(article, true);
            manageArticleContainer.getChildren().add(articleCard);
        }
    }
    
    private VBox createArticleCard(Article article, boolean showDeleteButton) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: white; -fx-border-color: #E0E0E0; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 20; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2);");
        card.setPrefWidth(Region.USE_COMPUTED_SIZE);
        
        // Title
        Label titleLabel = new Label(article.getTitle());
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #333333;");
        titleLabel.setWrapText(true);
        
        // Description
        Label descLabel = new Label(article.getDescription());
        descLabel.setStyle("-fx-font-size: 14; -fx-text-fill: #666666;");
        descLabel.setWrapText(true);
        
        // URL
        Label urlLabel = new Label(article.getUrl());
        urlLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #2D9A93;");
        urlLabel.setWrapText(true);
        
        // Buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        
        Button readButton = new Button("Baca Artikel");
        readButton.setStyle("-fx-background-color: #2D9A93; -fx-text-fill: white; -fx-font-size: 12; -fx-padding: 8 15; -fx-background-radius: 5;");
        readButton.setOnAction(e -> openArticleInBrowser(article.getUrl()));
        
        buttonBox.getChildren().add(readButton);
        
        if (showDeleteButton) {
            Button deleteButton = new Button("Hapus");
            deleteButton.setStyle("-fx-background-color: #E53E3E; -fx-text-fill: white; -fx-font-size: 12; -fx-padding: 8 15; -fx-background-radius: 5;");
            deleteButton.setOnAction(e -> deleteArticle(article));
            buttonBox.getChildren().add(deleteButton);
        }
        
        card.getChildren().addAll(titleLabel, descLabel, urlLabel, buttonBox);
        
        return card;
    }
    
    private void openArticleInBrowser(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                // Fallback for systems that don't support Desktop
                showAlert("Info", "Tidak dapat membuka browser. URL: " + url);
            }
        } catch (Exception e) {
            showAlert("Error", "Tidak dapat membuka artikel: " + e.getMessage());
        }
    }
    
    private void deleteArticle(Article article) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus");
        alert.setHeaderText("Hapus Artikel");
        alert.setContentText("Apakah Anda yakin ingin menghapus artikel: " + article.getTitle() + "?");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                articles.remove(article);
                loadArticles();
                showAlert("Sukses", "Artikel berhasil dihapus!");
            }
        });
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Navigation methods
    @FXML
    private void handleDashboard() {
        resetNavigation();
        dashboardBtn.setStyle("-fx-background-color: #238B83; -fx-text-fill: white; -fx-alignment: center-left; -fx-padding: 0 0 0 40; -fx-border-color: transparent; -fx-font-size: 16;");
        // You can add dashboard view here
    }
    
    @FXML
    private void handleReadArticle() {
        showReadArticleView();
    }
    
    @FXML
    private void handleAddArticle() {
        showAddArticleView();
    }
    
    @FXML
    private void handleManageArticle() {
        showManageArticleView();
    }
    
    @FXML
    private void handleSettings() {
        resetNavigation();
        settingsBtn.setStyle("-fx-background-color: #238B83; -fx-text-fill: white; -fx-alignment: center-left; -fx-padding: 0 0 0 40; -fx-border-color: transparent; -fx-font-size: 16;");
        // You can add settings view here
    }
    
    private void showReadArticleView() {
        resetNavigation();
        readArticleBtn.setStyle("-fx-background-color: #238B83; -fx-text-fill: white; -fx-alignment: center-left; -fx-padding: 0 0 0 40; -fx-border-color: transparent; -fx-font-size: 16;");
        
        readArticleView.setVisible(true);
        addArticleView.setVisible(false);
        manageArticleView.setVisible(false);
    }
    
    private void showAddArticleView() {
        resetNavigation();
        addArticleBtn.setStyle("-fx-background-color: #238B83; -fx-text-fill: white; -fx-alignment: center-left; -fx-padding: 0 0 0 40; -fx-border-color: transparent; -fx-font-size: 16;");
        
        readArticleView.setVisible(false);
        addArticleView.setVisible(true);
        manageArticleView.setVisible(false);
        
        // Clear form fields
        titleField.clear();
        urlField.clear();
        descriptionField.clear();
    }
    
    private void showManageArticleView() {
        resetNavigation();
        manageArticleBtn.setStyle("-fx-background-color: #238B83; -fx-text-fill: white; -fx-alignment: center-left; -fx-padding: 0 0 0 40; -fx-border-color: transparent; -fx-font-size: 16;");
        
        readArticleView.setVisible(false);
        addArticleView.setVisible(false);
        manageArticleView.setVisible(true);
    }
    
    private void resetNavigation() {
        String defaultStyle = "-fx-background-color: transparent; -fx-text-fill: white; -fx-alignment: center-left; -fx-padding: 0 0 0 40; -fx-border-color: transparent; -fx-font-size: 16;";
        
        dashboardBtn.setStyle(defaultStyle);
        readArticleBtn.setStyle(defaultStyle);
        addArticleBtn.setStyle(defaultStyle);
        manageArticleBtn.setStyle(defaultStyle);
        settingsBtn.setStyle(defaultStyle);
    }
    
    @FXML
    private void handleSaveArticle() {
        String title = titleField.getText().trim();
        String url = urlField.getText().trim();
        String description = descriptionField.getText().trim();
        
        if (title.isEmpty() || url.isEmpty() || description.isEmpty()) {
            showAlert("Error", "Semua field harus diisi!");
            return;
        }
        
        // Basic URL validation
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            showAlert("Error", "URL harus dimulai dengan http:// atau https://");
            return;
        }
        
        // Add new article
        Article newArticle = new Article(title, url, description);
        articles.add(newArticle);
        
        // Refresh views
        loadArticles();
        
        // Clear form
        titleField.clear();
        urlField.clear();
        descriptionField.clear();
        
        showAlert("Sukses", "Artikel berhasil ditambahkan!");
        
        // Switch to read article view
        showReadArticleView();
    }
    
    @FXML
    private void handleCancel() {
        titleField.clear();
        urlField.clear();
        descriptionField.clear();
        showReadArticleView();
    }
}
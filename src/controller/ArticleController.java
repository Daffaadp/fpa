package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import java.util.Queue;
import java.util.LinkedList;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;

public class ArticleController implements Initializable {
    
    // FXML Components
    @FXML private Button dashboardBtn;
    @FXML private Button readArticleBtn;
    @FXML private Button addArticleBtn;
    @FXML private Button manageArticleBtn;
  
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
    private final int MAX_ARTICLE_COUNT = 5;
    private Queue<Article> articleQueue;
    
    // Article class to store article data
    public static class Article {
        private String title;
        private String url;
        private String description;
        private boolean deleted;

        public Article(String title, String url, String description) {
            this.title = title;
            this.url = url;
            this.description = description;
            this.deleted = false;
        }
        
        public String getTitle() { return title; }
        public String getUrl() { return url; }
        public String getDescription() { return description; }
        public boolean isDeleted() { return deleted; }
        
        public void setTitle(String title) { this.title = title; }
        public void setUrl(String url) { this.url = url; }
        public void setDescription(String description) { this.description = description; }
        public void setDeleted(boolean deleted) { this.deleted = deleted; }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize data
        articles = new ArrayList<>();
        articleQueue = new LinkedList<>();
        
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
        Article article1 = new Article(
            "5 teknik latihan pernapasan untuk menjaga fungsi paru-paru",
            "https://www.alodokter.com/5-teknik-latihan-pernapasan-untuk-menjaga-fungsi-paru-paru",
            "Ada beragam teknik latihan pernapasan yang mampu memperkuat otot pernapasan. Bila dilakukan dengan benar dan rutin, teknik ini bisa membuat paru-paru berfungsi lebih baik untuk memenuhi kebutuhan oksigen dalam tubuh."
        );
        
        Article article2 = new Article(
            "Pentingnya Tidur Berkualitas untuk Kesehatan Mental",
            "https://www.halodoc.com/artikel/ini-5-cara-mengatasi-insomnia-tanpa-obat-obatan?srsltid=AfmBOoqh3hCZHpNirtdYDLvPXTzGbcrwulMKupJaZYQzhfBb5tKfNmVo",
            "Insomnia adalah gangguan tidur yang dapat memengaruhi kesehatan fisik dan mental. Selain menggunakan obat-obatan, kamu juga bisa mengatasi masalah ini melalui kebiasaan sehat, seperti berolahraga secara rutin dan menggunakan aromaterapi sebelum tidur."
        );
        
        Article article3 = new Article(
            "Ini Pentingnya Menjaga Kesehatan Mental Remaja",
            "https://www.halodoc.com/artikel/ini-pentingnya-menjaga-kesehatan-mental-remaja?srsltid=AfmBOop1UTlRI8VSisnRyiLpavopB9X2KaGCUi-rYZYFgALJoM3wLsq6",
            "Kesehatan mental pada remaja perlu menjadi perhatian para orangtua. Sebab, seseorang yang mengalami gangguan mental bisa jadi salah satu faktor yang dapat memicu berbagai masalah, termasuk depresi dan bunuh diri."
        );
        
        Article article4 = new Article(
            "Olahraga Pernapasan Baik Untuk Mental, Masa Sih?",
            "https://www.kompas.com/health/read/2023/07/15/teknik-grounding-untuk-kecemasan",
            "Selain menyehatkan fisik, tentunya olahraga juga bisa membuat emosi atau mental kian terjaga kesehatannya. Manfaat olahraga pernapasan seperti yoga, chikung, atau taichi mampu meningkatkan konsentrasi dan mengendalikan nafas dengan baik. Nah, dengan begitu beban atau pikiran yang penat karena berbagai tekanan yang menumpuk bakal berkurang. Kok bisa?"
        );
        
        // Tambahkan ke kedua list
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);
        
        // Tambahkan juga ke queue untuk tampil di read view
        articleQueue.add(article1);
        articleQueue.add(article2);
        articleQueue.add(article3);
        articleQueue.add(article4);
    }
    
    private void loadArticles() {
        loadReadArticleView();
        loadManageArticleView();
    }
    
    private void loadReadArticleView() {
        articleListContainer.getChildren().clear();
        
        // Tampilkan artikel yang tidak dihapus dari queue
        for (Article article : articleQueue) {
            if (!article.isDeleted()) {
                VBox articleCard = createArticleCard(article, false);
                articleListContainer.getChildren().add(articleCard);
            }
        }
    }
    
    private void loadManageArticleView() {
        manageArticleContainer.getChildren().clear();
        
        // Tampilkan semua artikel (termasuk yang dihapus) di manage view
        for (Article article : articles) {
            VBox articleCard = createArticleCard(article, true);
            manageArticleContainer.getChildren().add(articleCard);
        }
    }
    
    private VBox createArticleCard(Article article, boolean showDeleteButton) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: white; -fx-border-color: #E0E0E0; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 20; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2);");
        card.setPrefWidth(Region.USE_COMPUTED_SIZE);
        
        // Jika artikel sudah dihapus, beri style berbeda
        if (article.isDeleted()) {
            card.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 20; -fx-opacity: 0.6;");
        }
        
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
        
        // Jika artikel dihapus, disable tombol baca
        if (article.isDeleted()) {
            readButton.setDisable(true);
            readButton.setStyle("-fx-background-color: #cccccc; -fx-text-fill: #666666; -fx-font-size: 12; -fx-padding: 8 15; -fx-background-radius: 5;");
        }
        
        buttonBox.getChildren().add(readButton);
        
        if (showDeleteButton) {
            if (!article.isDeleted()) {
                // Tombol Hapus
                Button deleteButton = new Button("Hapus");
                deleteButton.setStyle("-fx-background-color: #E53E3E; -fx-text-fill: white; -fx-font-size: 12; -fx-padding: 8 15; -fx-background-radius: 5;");
                deleteButton.setOnAction(e -> deleteArticle(article));
                buttonBox.getChildren().add(deleteButton);
            } else {
                // Tombol Pulihkan jika sudah dihapus
                Button restoreButton = new Button("Pulihkan");
                restoreButton.setStyle("-fx-background-color: #38A169; -fx-text-fill: white; -fx-font-size: 12; -fx-padding: 8 15; -fx-background-radius: 5;");
                restoreButton.setOnAction(e -> restoreArticle(article));
                buttonBox.getChildren().add(restoreButton);
                
                // Tombol Hapus Permanen
                Button permanentDeleteButton = new Button("Hapus Permanen");
                permanentDeleteButton.setStyle("-fx-background-color: #C53030; -fx-text-fill: white; -fx-font-size: 12; -fx-padding: 8 15; -fx-background-radius: 5;");
                permanentDeleteButton.setOnAction(e -> permanentDeleteArticle(article));
                buttonBox.getChildren().add(permanentDeleteButton);
            }
        }

        // Label status jika artikel dihapus
        if (article.isDeleted()) {
            Label deletedLabel = new Label("Artikel telah dihapus");
            deletedLabel.setStyle("-fx-font-size: 12; -fx-text-fill: red; -fx-font-style: italic;");
            card.getChildren().add(deletedLabel);
        }
        
        card.getChildren().addAll(titleLabel, descLabel, urlLabel, buttonBox);
        
        return card;
    }
    
    private void openArticleInBrowser(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                showAlert("Info", "Tidak dapat membuka browser. URL: " + url);
            }
        } catch (Exception e) {
            showAlert("Error", "Tidak dapat membuka artikel: " + e.getMessage());
        }
    }
    
    // Method untuk menghapus artikel (soft delete)
    private void deleteArticle(Article article) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus");
        alert.setHeaderText("Hapus Artikel");
        alert.setContentText("Apakah Anda yakin ingin menghapus artikel: " + article.getTitle() + "?");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                article.setDeleted(true);
                loadArticles(); // Refresh tampilan
                showAlert("Sukses", "Artikel berhasil dihapus!");
            }
        });
    }
    
    // Method untuk memulihkan artikel yang dihapus
    private void restoreArticle(Article article) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Pulihkan");
        alert.setHeaderText("Pulihkan Artikel");
        alert.setContentText("Apakah Anda yakin ingin memulihkan artikel: " + article.getTitle() + "?");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                article.setDeleted(false);
                loadArticles(); // Refresh tampilan
                showAlert("Sukses", "Artikel berhasil dipulihkan!");
            }
        });
    }
    
    // Method untuk menghapus artikel secara permanen
    private void permanentDeleteArticle(Article article) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus Permanen");
        alert.setHeaderText("Hapus Artikel Permanen");
        alert.setContentText("Apakah Anda yakin ingin menghapus permanen artikel: " + article.getTitle() + "?\nTindakan ini tidak dapat dibatalkan!");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Hapus dari kedua list
                articles.remove(article);
                articleQueue.remove(article);
                loadArticles(); // Refresh tampilan
                showAlert("Sukses", "Artikel berhasil dihapus permanen!");
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
    private void handleDashboard(ActionEvent event) {
        dashboardBtn.setStyle("-fx-background-color: #238B83; -fx-text-fill: white; -fx-alignment: center-left; -fx-padding: 0 0 0 40; -fx-border-color: transparent; -fx-font-size: 16;");
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
        resetNavigation();
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
    }
    
    // Method untuk menyimpan artikel baru
    @FXML
    private void handleSaveArticle() {
        String title = titleField.getText().trim();
        String url = urlField.getText().trim();
        String description = descriptionField.getText().trim();
        
        // Validasi input
        if (title.isEmpty() || url.isEmpty() || description.isEmpty()) {
            showAlert("Error", "Semua field harus diisi!");
            return;
        }
        
        // Validasi URL dasar
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            showAlert("Error", "URL harus dimulai dengan http:// atau https://");
            return;
        }
        
        // Buat artikel baru
        Article newArticle = new Article(title, url, description);
        
        // Tambahkan ke kedua list
        articles.add(newArticle);
        articleQueue.add(newArticle);
        
        // Jika melebihi batas maksimal, hapus artikel terlama dari queue
        if (articleQueue.size() > MAX_ARTICLE_COUNT) {
            articleQueue.poll(); // Hapus artikel pertama (terlama)
        }
        
        // Refresh tampilan
        loadArticles();
        
        // Bersihkan form
        titleField.clear();
        urlField.clear();
        descriptionField.clear();
        
        showAlert("Sukses", "Artikel berhasil ditambahkan!");
        
        // Pindah ke tampilan baca artikel
        showReadArticleView();
    }
    
    @FXML
    private void handleCancel() {
        titleField.clear();
        urlField.clear();
        descriptionField.clear();
        showReadArticleView();
    }

    @FXML
    private void handleHistory(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/history.fxml"));
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal membuka halaman Riwayat.");
        }
    }
}
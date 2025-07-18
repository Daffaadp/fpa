import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LaperController2 implements Initializable {
    
    // Navbar Elements
    @FXML private Button dashboardBtn;
    @FXML private Button artikelBtn;
    @FXML private Button pernapasanBtn;
    @FXML private Button kuesionerBtn;
    @FXML private Button todoBtn;
    @FXML private Button profileBtn;
    
    // Breathing Exercise Elements
    @FXML private Label headerLabel;
    @FXML private TextField tarikField;
    @FXML private TextField tahanField;
    @FXML private TextField hembuskanField;
    @FXML private TextField siklusField;
    @FXML private Circle breathingCircle;
    @FXML private Label circleLabel;
    @FXML private Button mulaiBtn;
    @FXML private Button jedaBtn;
    @FXML private Button resetBtn;
    @FXML private Label siklusLabel;
    @FXML private Label faseLabel;
    @FXML private ProgressBar progressBar;
    
    // Breathing Exercise Variables
    private Timeline breathingTimeline;
    private ScaleTransition scaleTransition;
    private int currentCount = 0;
    private int currentCycle = 0;
    private int maxCycles = 5;
    private String currentPhase = "Siap";
    private boolean isRunning = false;
    private boolean isPaused = false;
    
    // Breathing phases
    private enum BreathingPhase {
        INHALE, HOLD, EXHALE, PAUSE
    }
    private BreathingPhase currentBreathingPhase = BreathingPhase.INHALE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupInitialState();
        setupBreathingAnimation();
    }
    
    private void setupInitialState() {
        // Set initial breathing circle state
        breathingCircle.setFill(Color.web("#4169E1"));
        circleLabel.setText("0");
        
        // Set initial labels
        updateProgressLabels();
        
        // Disable jeda button initially
        jedaBtn.setDisable(true);
    }
    
    private void setupBreathingAnimation() {
        // Create scale transition for breathing circle
        scaleTransition = new ScaleTransition(Duration.seconds(1), breathingCircle);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(1.5);
        scaleTransition.setAutoReverse(false);
        scaleTransition.setCycleCount(1);
    }

        
    // Navbar Actions
    private void showAlert(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR); // Or other appropriate type
    alert.setTitle(title);
    alert.setHeaderText(null); // No header text
    alert.setContentText(message);
    alert.showAndWait();
    }

       @FXML
    private void handleDashboardMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/Artikel.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/Panduanbernafas1.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/kusioner.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/ToDoList.fxml"));
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
    
    private void updateActiveNavButton(Button activeButton) {
        // Reset all buttons to inactive state
        String inactiveStyle = "-fx-background-color: transparent; -fx-text-fill: white; -fx-alignment: CENTER_LEFT; -fx-padding: 15 20; -fx-border-color: transparent; -fx-cursor: hand;";
        String activeStyle = "-fx-background-color: #4ECDC4; -fx-text-fill: #2D8A8A; -fx-alignment: CENTER_LEFT; -fx-padding: 15 20; -fx-border-color: transparent; -fx-cursor: hand; -fx-font-weight: bold;";
        
        dashboardBtn.setStyle(inactiveStyle);
        artikelBtn.setStyle(inactiveStyle);
        pernapasanBtn.setStyle(inactiveStyle);
        kuesionerBtn.setStyle(inactiveStyle);
        todoBtn.setStyle(inactiveStyle);
        profileBtn.setStyle(inactiveStyle);
        
        // Set active button
        activeButton.setStyle(activeStyle);
    }
    
    // Breathing Exercise Actions
    @FXML
    private void onMulaiClick() {
        if (!isRunning && !isPaused) {
            startBreathingExercise();
        } else if (isPaused) {
            resumeBreathingExercise();
        }
    }
    
    @FXML
    private void onJedaClick() {
        if (isRunning) {
            pauseBreathingExercise();
        }
    }
    
    @FXML
    private void onResetClick() {
        resetBreathingExercise();
    }
    
    private void startBreathingExercise() {
        isRunning = true;
        isPaused = false;
        currentCycle = 0;
        currentBreathingPhase = BreathingPhase.INHALE;
        
        // Get settings
        try {
            maxCycles = Integer.parseInt(siklusField.getText());
        } catch (NumberFormatException e) {
            maxCycles = 5; // Default value
        }
        
        // Update UI
        mulaiBtn.setText("Mulai");
        mulaiBtn.setDisable(true);
        jedaBtn.setDisable(false);
        headerLabel.setText("Bernapas dengan tenang...");
        
        // Disable input fields
        setInputFieldsDisabled(true);
        
        startBreathingCycle();
    }
    
    private void pauseBreathingExercise() {
        isPaused = true;
        isRunning = false;
        
        if (breathingTimeline != null) {
            breathingTimeline.pause();
        }
        if (scaleTransition != null) {
            scaleTransition.pause();
        }
        
        mulaiBtn.setText("Lanjutkan");
        mulaiBtn.setDisable(false);
        jedaBtn.setDisable(true);
        headerLabel.setText("Latihan dijeda...");
    }
    
    private void resumeBreathingExercise() {
        isPaused = false;
        isRunning = true;
        
        if (breathingTimeline != null) {
            breathingTimeline.play();
        }
        if (scaleTransition != null) {
            scaleTransition.play();
        }
        
        mulaiBtn.setText("Mulai");
        mulaiBtn.setDisable(true);
        jedaBtn.setDisable(false);
        headerLabel.setText("Bernapas dengan tenang...");
    }
    
    private void resetBreathingExercise() {
        isRunning = false;
        isPaused = false;
        currentCycle = 0;
        currentCount = 0;
        currentPhase = "Siap";
        
        if (breathingTimeline != null) {
            breathingTimeline.stop();
        }
        if (scaleTransition != null) {
            scaleTransition.stop();
        }
        
        // Reset UI
        mulaiBtn.setText("Mulai");
        mulaiBtn.setDisable(false);
        jedaBtn.setDisable(true);
        headerLabel.setText("Bersiap untuk memulai...");
        circleLabel.setText("0");
        breathingCircle.setScaleX(1.0);
        breathingCircle.setScaleY(1.0);
        breathingCircle.setFill(Color.web("#4169E1"));
        
        // Enable input fields
        setInputFieldsDisabled(false);
        
        updateProgressLabels();
    }
    
    private void startBreathingCycle() {
        currentCycle++;
        currentBreathingPhase = BreathingPhase.INHALE;
        startPhase();
    }
    
    private void startPhase() {
        int duration = 0;
        String phaseText = "";
        Color circleColor = Color.web("#4169E1");
        
        try {
            switch (currentBreathingPhase) {
                case INHALE:
                    duration = Integer.parseInt(tarikField.getText());
                    phaseText = "Tarik";
                    circleColor = Color.web("#4CAF50");
                    animateCircle(duration, true);
                    break;
                case HOLD:
                    duration = Integer.parseInt(tahanField.getText());
                    phaseText = "Tahan";
                    circleColor = Color.web("#FF9800");
                    // No animation during hold, keep current scale
                    break;
                case EXHALE:
                    duration = Integer.parseInt(hembuskanField.getText());
                    phaseText = "Hembuskan";
                    circleColor = Color.web("#F44336");
                    animateCircle(duration, false);
                    break;
                case PAUSE:
                    duration = 3; // Short pause between cycles
                    phaseText = "Istirahat";
                    circleColor = Color.web("#4169E1");
                    // Reset to normal size during pause
                    resetCircleSize();
                    break;
            }
        } catch (NumberFormatException e) {
            // Use default values if parsing fails
            duration = currentBreathingPhase == BreathingPhase.INHALE ? 4 : 
                      currentBreathingPhase == BreathingPhase.HOLD ? 4 : 
                      currentBreathingPhase == BreathingPhase.EXHALE ? 4 : 3;
        }
        
        currentPhase = phaseText;
        breathingCircle.setFill(circleColor);
        updateProgressLabels();
        
        // Start countdown
        currentCount = duration;
        circleLabel.setText(String.valueOf(currentCount));
        
        breathingTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            currentCount--;
            circleLabel.setText(String.valueOf(currentCount));
            
            if (currentCount <= 0) {
                breathingTimeline.stop();
                moveToNextPhase();
            }
        }));
        breathingTimeline.setCycleCount(duration);
        breathingTimeline.play();
    }
    
    private void animateCircle(int duration, boolean expand) {
        scaleTransition.stop();
        
        if (expand) {
            // Inhale - expand from current size to 1.5x
            scaleTransition.setFromX(breathingCircle.getScaleX());
            scaleTransition.setFromY(breathingCircle.getScaleY());
            scaleTransition.setToX(1.5);
            scaleTransition.setToY(1.5);
        } else {
            // Exhale - shrink from current size to 0.7x (smaller than original)
            scaleTransition.setFromX(breathingCircle.getScaleX());
            scaleTransition.setFromY(breathingCircle.getScaleY());
            scaleTransition.setToX(0.7);
            scaleTransition.setToY(0.7);
        }
        
        scaleTransition.setDuration(Duration.seconds(duration));
        scaleTransition.setAutoReverse(false);
        scaleTransition.play();
    }
    
    private void resetCircleSize() {
        scaleTransition.stop();
        scaleTransition.setFromX(breathingCircle.getScaleX());
        scaleTransition.setFromY(breathingCircle.getScaleY());
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.setDuration(Duration.seconds(1));
        scaleTransition.play();
    }
    
    private void moveToNextPhase() {
        switch (currentBreathingPhase) {
            case INHALE:
                currentBreathingPhase = BreathingPhase.HOLD;
                break;
            case HOLD:
                currentBreathingPhase = BreathingPhase.EXHALE;
                break;
            case EXHALE:
                if (currentCycle < maxCycles) {
                    currentBreathingPhase = BreathingPhase.PAUSE;
                } else {
                    completeExercise();
                    return;
                }
                break;
            case PAUSE:
                startBreathingCycle();
                return;
        }
        
        startPhase();
    }
    
    private void completeExercise() {
        isRunning = false;
        headerLabel.setText("Latihan selesai! Bagus!");
        currentPhase = "Selesai";
        
        // Reset circle to normal size
        resetCircleSize();
        
        mulaiBtn.setDisable(false);
        jedaBtn.setDisable(true);
        setInputFieldsDisabled(false);
        
        updateProgressLabels();
    }
    
    private void updateProgressLabels() {
        siklusLabel.setText("Siklus: " + currentCycle + "/" + maxCycles);
        faseLabel.setText("Fase: " + currentPhase);
        progressBar.setProgress(maxCycles > 0 ? (double) currentCycle / maxCycles : 0);
    }
    
    private void setInputFieldsDisabled(boolean disabled) {
        tarikField.setDisable(disabled);
        tahanField.setDisable(disabled);
        hembuskanField.setDisable(disabled);
        siklusField.setDisable(disabled);
    }
}
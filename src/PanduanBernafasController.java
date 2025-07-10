import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class PanduanBernafasController implements Initializable {
    
    @FXML private Circle breathingCircle;
    @FXML private Text counterText;
    @FXML private TextField instructionField;
    @FXML private Button startButton;
    @FXML private Button pauseButton;
    @FXML private Button resetButton;
    @FXML private Spinner<Integer> inhaleSpinner;
    @FXML private Spinner<Integer> holdSpinner;
    @FXML private Spinner<Integer> exhaleSpinner;
    @FXML private Spinner<Integer> cycleSpinner;
    @FXML private Label currentCycleLabel;
    @FXML private Label phaseLabel;
    @FXML private ProgressBar progressBar;
    
    private Timeline breathingTimeline;
    private int currentCycle = 0;
    private int totalCycles = 5;
    private int counter = 0;
    private boolean isRunning = false;
    private boolean isPaused = false;
    
    // Breathing phases
    private enum BreathingPhase {
        INHALE, HOLD, EXHALE, PAUSE
    }
    
    private BreathingPhase currentPhase = BreathingPhase.INHALE;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupSpinners();
        setupInitialState();
    }
    
    private void setupSpinners() {
        inhaleSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, 4));
        holdSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 4));
        exhaleSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, 4));
        cycleSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 5));
        
        // Make spinners editable
        inhaleSpinner.setEditable(true);
        holdSpinner.setEditable(true);
        exhaleSpinner.setEditable(true);
        cycleSpinner.setEditable(true);
    }
    
    private void setupInitialState() {
        breathingCircle.setRadius(80.0);
        counterText.setText("0");
        instructionField.setText("Bersiap untuk memulai...");
        phaseLabel.setText("Fase: Siap");
        progressBar.setProgress(0.0);
        updateCycleLabel();
    }
    
    @FXML
    private void startBreathing() {
        if (!isRunning) {
            isRunning = true;
            isPaused = false;
            if (currentCycle == 0) {
                totalCycles = cycleSpinner.getValue();
                currentCycle = 1;
            }
            startBreathingCycle();
            startButton.setText("Berjalan");
            startButton.setDisable(true);
            pauseButton.setDisable(false);
            resetButton.setDisable(false);
        }
    }
    
    @FXML
    private void pauseBreathing() {
        if (isRunning) {
            if (isPaused) {
                // Resume
                breathingTimeline.play();
                isPaused = false;
                pauseButton.setText("Jeda");
                instructionField.setText("Melanjutkan...");
            } else {
                // Pause
                breathingTimeline.pause();
                isPaused = true;
                pauseButton.setText("Lanjut");
                instructionField.setText("Dijeda - Klik Lanjut untuk melanjutkan");
            }
        }
    }
    
    @FXML
    private void resetBreathing() {
        if (breathingTimeline != null) {
            breathingTimeline.stop();
        }
        
        isRunning = false;
        isPaused = false;
        currentCycle = 0;
        counter = 0;
        currentPhase = BreathingPhase.INHALE;
        
        setupInitialState();
        
        startButton.setText("Mulai");
        startButton.setDisable(false);
        pauseButton.setText("Jeda");
        pauseButton.setDisable(true);
        resetButton.setDisable(true);
        
        // Reset circle to original size
        breathingCircle.setRadius(80.0);
    }
    
    private void startBreathingCycle() {
        if (currentCycle > totalCycles) {
            completedAllCycles();
            return;
        }
        
        breathingTimeline = new Timeline();
        breathingTimeline.setCycleCount(1);
        
        // Get current settings
        int inhaleTime = inhaleSpinner.getValue();
        int holdTime = holdSpinner.getValue();
        int exhaleTime = exhaleSpinner.getValue();
        
        double totalTime = inhaleTime + holdTime + exhaleTime + 1; // +1 for pause
        
        // Create breathing animation sequence
        createBreathingSequence(inhaleTime, holdTime, exhaleTime);
        
        breathingTimeline.setOnFinished(e -> {
            if (isRunning && !isPaused) {
                currentCycle++;
                updateCycleLabel();
                updateProgress();
                
                if (currentCycle <= totalCycles) {
                    // Small pause between cycles
                    Timeline pauseTimeline = new Timeline(
                        new KeyFrame(Duration.seconds(1), event -> startBreathingCycle())
                    );
                    pauseTimeline.play();
                } else {
                    completedAllCycles();
                }
            }
        });
        
        breathingTimeline.play();
    }
    
    private void createBreathingSequence(int inhaleTime, int holdTime, int exhaleTime) {
        breathingTimeline.getKeyFrames().clear();
        
        double currentTime = 0;
        
        // INHALE PHASE
        currentPhase = BreathingPhase.INHALE;
        for (int i = 0; i <= inhaleTime; i++) {
            final int count = i;
            final double radius = 80 + (count * 15); // Grow from 80 to 80+(inhaleTime*15)
            
            breathingTimeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(currentTime + i), e -> {
                    counterText.setText(String.valueOf(inhaleTime - count));
                    breathingCircle.setRadius(radius);
                    if (count == 0) {
                        instructionField.setText("Tarik napas dalam-dalam...");
                        phaseLabel.setText("Fase: Menarik Napas");
                    }
                })
            );
        }
        currentTime += inhaleTime;
        
        // HOLD PHASE (if > 0)
        if (holdTime > 0) {
            currentPhase = BreathingPhase.HOLD;
            for (int i = 0; i <= holdTime; i++) {
                final int count = i;
                
                breathingTimeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(currentTime + i), e -> {
                        counterText.setText(String.valueOf(holdTime - count));
                        if (count == 0) {
                            instructionField.setText("Tahan napas...");
                            phaseLabel.setText("Fase: Menahan Napas");
                        }
                    })
                );
            }
            currentTime += holdTime;
        }
        
        // EXHALE PHASE
        currentPhase = BreathingPhase.EXHALE;
        double maxRadius = 80 + (inhaleTime * 15);
        for (int i = 0; i <= exhaleTime; i++) {
            final int count = i;
            final double radius = maxRadius - (count * (maxRadius - 80) / exhaleTime);
            
            breathingTimeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(currentTime + i), e -> {
                    counterText.setText(String.valueOf(exhaleTime - count));
                    breathingCircle.setRadius(radius);
                    if (count == 0) {
                        instructionField.setText("Hembuskan napas perlahan...");
                        phaseLabel.setText("Fase: Menghembuskan Napas");
                    }
                })
            );
        }
        currentTime += exhaleTime;
        
        // PAUSE PHASE
        breathingTimeline.getKeyFrames().add(
            new KeyFrame(Duration.seconds(currentTime), e -> {
                counterText.setText("0");
                breathingCircle.setRadius(80.0);
                instructionField.setText("Bersiap untuk siklus berikutnya...");
                phaseLabel.setText("Fase: Istirahat");
            })
        );
    }
    
    private void updateCycleLabel() {
        currentCycleLabel.setText("Siklus: " + currentCycle + "/" + totalCycles);
    }
    
    private void updateProgress() {
        double progress = (double) (currentCycle - 1) / totalCycles;
        progressBar.setProgress(progress);
    }
    
    private void completedAllCycles() {
        isRunning = false;
        instructionField.setText("Selesai! Latihan pernapasan telah selesai.");
        phaseLabel.setText("Fase: Selesai");
        progressBar.setProgress(1.0);
        
        startButton.setText("Mulai");
        startButton.setDisable(false);
        pauseButton.setDisable(true);
        resetButton.setDisable(false);
        
        // Reset for next session
        currentCycle = 0;
        counter = 0;
        counterText.setText("✓");
        breathingCircle.setRadius(80.0);
    }
}
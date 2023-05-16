package project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayerCharacterExample extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Rectangle player;
    private Rectangle platform;

    @Override
    public void start(Stage primaryStage) {

        // Create the player character
        player = new Rectangle(50, 50, Color.BLUE);
        player.setLayoutX(WIDTH / 2 - player.getWidth() / 2);
        player.setLayoutY(HEIGHT - player.getHeight() - 120);

        // Create a platform for the player to land on
        platform = new Rectangle(200, 20, Color.GREEN);
        platform.setLayoutX(WIDTH / 2 - platform.getWidth() / 2);
        platform.setLayoutY(HEIGHT - player.getHeight() - 100);

        // Create a timeline for updating the player character's position
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                // Move the player character down the screen
                player.setLayoutY(player.getLayoutY() + 5);
                // Check if the player character is colliding with the platform
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    // Move the player character up to the top of the platform
                    player.setLayoutY(platform.getLayoutY() - player.getHeight());
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        // Create a pane and add the player character and platform to it
        Pane root = new Pane();
        root.getChildren().addAll(player, platform);

        // Create a scene and add the pane to it
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

        // Handle the space key to make the player character jump
        scene.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            public void handle(javafx.scene.input.KeyEvent event) {
                switch (event.getCode()) {
                    case SPACE:
                        player.setLayoutY(player.getLayoutY() - 100);
                        break;
                }
            }
        });

        // Start the timeline
        timeline.play();

        // Show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


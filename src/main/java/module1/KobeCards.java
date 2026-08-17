package module1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class KobeCards extends Application {

    private final HBox cardBox = new HBox(15);

    @Override
    public void start(Stage primaryStage) {

        cardBox.setAlignment(Pos.CENTER);

        Button refreshButton = new Button("Refresh");

        // Lambda expression
        refreshButton.setOnAction(event -> displayCards());

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        root.getChildren().addAll(cardBox, refreshButton);

        // Show cards when program first starts
        displayCards();

        Scene scene = new Scene(root, 650, 400);

        primaryStage.setTitle("Random Playing Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayCards() {

        cardBox.getChildren().clear();

        ArrayList<Integer> cards = new ArrayList<>();

        // Add cards 1 through 52
        for (int i = 1; i <= 52; i++) {
            cards.add(i);
        }

        // Randomize card order
        Collections.shuffle(cards);

        // Display first four cards
        for (int i = 0; i < 4; i++) {

            int cardNumber = cards.get(i);

            Image cardImage = new Image(
                    getClass().getResourceAsStream(
                            "/module-1/cards/" + cardNumber + ".png"
                    )
            );

            ImageView cardView = new ImageView(cardImage);

            cardView.setFitWidth(130);
            cardView.setPreserveRatio(true);

            cardBox.getChildren().add(cardView);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
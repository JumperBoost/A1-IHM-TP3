package fr.umontpellier.iut.exercice1;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppMaths extends Application {

    private ObservableList<LigneExercice> exercices;
    private VBox exercices_box;
    private ComboBox<Integer> comboBox_exercice;
    private Button button_reponse;

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        exercices = FXCollections.observableArrayList(e -> new Observable[] {});
        exercices_box = new VBox();

        Label label_exercice = new Label("Combien d'exercice ?");
        comboBox_exercice = new ComboBox<>();
        comboBox_exercice.getItems().addAll(6, 9, 12, 15);
        HBox header_box = new HBox(label_exercice, comboBox_exercice);
        header_box.setSpacing(20);
        header_box.setPadding(new Insets(5));
        header_box.setAlignment(Pos.CENTER);

        button_reponse = new Button("Voir le résultat");
        button_reponse.setDisable(true);
        HBox reponse_box = new HBox(button_reponse);
        reponse_box.setAlignment(Pos.CENTER);

        VBox box = new VBox(header_box, exercices_box, reponse_box);
        box.setPadding(new Insets(5));
        addListenersAndBindings();

        Scene scene = new Scene(box);
        stage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void addListenersAndBindings() {
        comboBox_exercice.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            button_reponse.setDisable(false);
            exercices_box.getChildren().clear();
            exercices.clear();
            for(int i = 0; i < newValue; i++) {
                LigneExercice exercice = new LigneExercice();
                exercices.add(exercice);
                exercices_box.getChildren().add(exercice);
            }
            stage.sizeToScene();
        });

        button_reponse.pressedProperty().addListener((observableValue, oldValue, newValue) -> {
            int nbCorrect = (int) exercices.stream().filter(LigneExercice::isCorrect).count();
            for(LigneExercice ex : exercices)
                System.out.println(ex.getDoubleReponse());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Votre nombre de réponses correctes est de " + nbCorrect + "/" + exercices.size() + ".");
            alert.showAndWait();
        });
    }
}

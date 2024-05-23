package fr.umontpellier.iut.exercice1;

import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.converter.NumberStringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class LigneExercice extends HBox {
    private Label enonce;
    private TextField reponse;
    private final Exercice exercice;

    private DoubleProperty doubleReponse;
    private BooleanProperty correct;

    public LigneExercice() {
        setPrefWidth(207);
        setPrefHeight(47);
        setSpacing(10);
        setPadding(new Insets(5));
        setAlignment(Pos.CENTER);

        // À compléter
        exercice = new Exercice();
        enonce = new Label(exercice.getEnonce());
        reponse = new TextField();
        reponse.setTextFormatter(getDecimalTextFormatter());
        // ici mettre la bonne taille du TextField
        enonce.setBackground(Background.fill(Paint.valueOf("#90ee90")));
        enonce.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        enonce.setPrefHeight(36);
        enonce.setPrefWidth(120);
        enonce.setAlignment(Pos.CENTER_RIGHT);
        reponse.setPrefWidth(70);
        reponse.setPrefHeight(36);
        getChildren().addAll(enonce, reponse);

        doubleReponse = new SimpleDoubleProperty(0.0);
        correct = new SimpleBooleanProperty();
        reponse.textProperty().bindBidirectional(doubleReponse, new NumberStringConverter());
        correct.bind(Bindings.equal(exercice.solutionProperty(), doubleReponse));
    }

    private TextFormatter<String> getDecimalTextFormatter() {
        // Regular expression to match positive decimal numbers
        Pattern decimalPattern = Pattern.compile("-?((\\d+\\.\\d*)|(\\d*\\.\\d+)|(\\d+))?");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (decimalPattern.matcher(newText).matches()) {
                return change;
            }
            return null;
        };
        return new TextFormatter<>(filter);
    }

    public double getDoubleReponse() {
        return doubleReponse.get();
    }

    public DoubleProperty doubleReponseProperty() {
        return doubleReponse;
    }

    public boolean isCorrect() {
        return correct.get();
    }

    public BooleanProperty correctProperty() {
        return correct;
    }
}

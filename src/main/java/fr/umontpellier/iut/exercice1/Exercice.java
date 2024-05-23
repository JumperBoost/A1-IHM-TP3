package fr.umontpellier.iut.exercice1;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Random;

public class Exercice {
    private final String enonce;
    private final DoubleProperty solution;

    public Exercice() {
        Random random = new Random();
        int choice = random.nextInt(4);

        int operande1;
        int operande2;
        String symboleOperateur;
        switch (choice) {
            case 0 -> {
                operande1 = random.nextInt(26);
                operande2 = random.nextInt(26);
                symboleOperateur = "+";
                solution = new SimpleDoubleProperty(operande1 + operande2);
            }
            case 1 -> {
                operande1 = random.nextInt(16) + 10;
                operande2 = random.nextInt(26);
                symboleOperateur = "-";
                solution = new SimpleDoubleProperty(operande1 - operande2);
            }
            case 2 -> {
                operande1 = random.nextInt(15) + 1;
                operande2 = random.nextInt(15) + 1;
                symboleOperateur = "*";
                solution = new SimpleDoubleProperty(operande1 * operande2);
            }
            default -> {
                operande1 = random.nextInt(151);
                operande2 = random.nextInt(15) + 1;
                symboleOperateur = "/";
                solution = new SimpleDoubleProperty(Double.parseDouble(String.format("%.2f", (double) operande1 / operande2).replace(",", ".")));
            }
        }
        enonce = operande1 + " " + symboleOperateur + " " + operande2 + " = ";
        System.out.println(enonce + solution.get());
    }

    public String getEnonce() {
        return enonce;
    }

    public double getSolution() {
        return solution.get();
    }

    public DoubleProperty solutionProperty() {
        return solution;
    }
}

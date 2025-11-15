package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1, editTextNumber2;
    private Button buttonSelectAdd, buttonSelectSubtract, buttonSelectMultiply, buttonSelectDivide;
    private Button buttonCalculate;
    private TextView textViewOperation, textViewResult;

    private String currentOperation = "+"; // Opération par défaut

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser les vues
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        buttonSelectAdd = findViewById(R.id.buttonSelectAdd);
        buttonSelectSubtract = findViewById(R.id.buttonSelectSubtract);
        buttonSelectMultiply = findViewById(R.id.buttonSelectMultiply);
        buttonSelectDivide = findViewById(R.id.buttonSelectDivide);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewOperation = findViewById(R.id.textViewOperation);
        textViewResult = findViewById(R.id.textViewResult);

        // Définir les écouteurs pour sélectionner l'opération
        buttonSelectAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOperation = "+";
                updateOperationDisplay("Addition");
                resetButtonColors();
                buttonSelectAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            }
        });

        buttonSelectSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOperation = "-";
                updateOperationDisplay("Soustraction");
                resetButtonColors();
                buttonSelectSubtract.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            }
        });

        buttonSelectMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOperation = "*";
                updateOperationDisplay("Multiplication");
                resetButtonColors();
                buttonSelectMultiply.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            }
        });

        buttonSelectDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOperation = "/";
                updateOperationDisplay("Division");
                resetButtonColors();
                buttonSelectDivide.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            }
        });

        // Bouton pour effectuer le calcul
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();
            }
        });
    }

    private void resetButtonColors() {
        buttonSelectAdd.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        buttonSelectSubtract.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        buttonSelectMultiply.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        buttonSelectDivide.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    private void updateOperationDisplay(String operationName) {
        textViewOperation.setText("Opération: " + operationName);
    }

    private void performCalculation() {
        String num1Str = editTextNumber1.getText().toString();
        String num2Str = editTextNumber2.getText().toString();

        // Vérifier si les champs sont vides
        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            textViewResult.setText("Veuillez entrer deux nombres");
            return;
        }

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;
            String operationSymbol = "";
            String operationName = "";

            switch (currentOperation) {
                case "+":
                    result = num1 + num2;
                    operationSymbol = "+";
                    operationName = "Addition";
                    break;
                case "-":
                    result = num1 - num2;
                    operationSymbol = "-";
                    operationName = "Soustraction";
                    break;
                case "*":
                    result = num1 * num2;
                    operationSymbol = "×";
                    operationName = "Multiplication";
                    break;
                case "/":
                    if (num2 == 0) {
                        textViewResult.setText("Erreur: Division par zéro");
                        return;
                    }
                    result = num1 / num2;
                    operationSymbol = "÷";
                    operationName = "Division";
                    break;
            }

            // Afficher le résultat
            String resultText = String.format("%s: %.2f %s %.2f = %.2f",
                    operationName, num1, operationSymbol, num2, result);
            textViewResult.setText("Résultat: " + resultText);

        } catch (NumberFormatException e) {
            textViewResult.setText("Erreur: Format de nombre invalide");
        }
    }
}
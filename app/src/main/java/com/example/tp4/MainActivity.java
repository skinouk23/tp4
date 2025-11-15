package com.example.tp4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp4.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNombre1, editTextNombre2;
    private TextView textViewResultat;
    private Button buttonCalculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculatrice);

        // Initialisation des vues
        editTextNombre1 = findViewById(R.id.editTextNombre1);
        editTextNombre2 = findViewById(R.id.editTextNombre2);
        textViewResultat = findViewById(R.id.textViewResultat);
        buttonCalculer = findViewById(R.id.buttonCalculer);

        // Gestion du clic sur le bouton
        buttonCalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculerSomme();
            }
        });
    }

    private void calculerSomme() {
        // Vérifier si les champs ne sont pas vides
        if (editTextNombre1.getText().toString().isEmpty() ||
                editTextNombre2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Veuillez entrer les deux nombres", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Récupérer les valeurs des EditText
            double nombre1 = Double.parseDouble(editTextNombre1.getText().toString());
            double nombre2 = Double.parseDouble(editTextNombre2.getText().toString());

            // Calculer la somme
            double somme = nombre1 + nombre2;

            // Afficher le résultat
            textViewResultat.setText(String.valueOf(somme));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez entrer des nombres valides", Toast.LENGTH_SHORT).show();
        }
    }
}
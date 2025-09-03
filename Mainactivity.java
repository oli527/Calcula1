package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    EditText edtvopcion1, edtvopcion2;
    AppCompatButton btnSubmit;
    TextView txtResulto;
    Spinner spinnerOpera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtvopcion1 = findViewById(R.id.edtvopcion1);
        edtvopcion2 = findViewById(R.id.edtvopcion2);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtResulto = findViewById(R.id.txtResulto);
        spinnerOpera= findViewById(R.id.spinnerOpera);

        // Opciones para el Spinner como habia dicho si era de los botones paila por que borre
        // todo ,por cierto aunque no apareza como tal el simbilo si le da calcular si aparece okis
        String[] operaciones = {"+", "-", "×", "÷"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operaciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOpera.setAdapter(adapter);

        // Este es lo que me va a permitir calcular
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularOperacion();
            }
        });
    }

    private void calcularOperacion() {
        if (edtvopcion1.getText().toString().isEmpty() || edtvopcion2.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this,"Debes ingresar ambos números", Toast.LENGTH_LONG).show();
            return;
        }

        int num1 = Integer.parseInt(edtvopcion1.getText().toString());
        int num2 = Integer.parseInt(edtvopcion2.getText().toString());
        String operacion = spinnerOpera.getSelectedItem().toString();
        int r = 0;

        switch (operacion) {
            case "+":
                r = num1 + num2;
                break;
            case "-":
                r = num1 - num2;
                break;
            case "×":
                r = num1 * num2;
                break;
            case "÷":
                if (num2 == 0) {
                    Toast.makeText(MainActivity.this, "No se puede dividir entre 0", Toast.LENGTH_LONG).show();
                    return;
                }
                r = num1 / num2;
                break;
        }

        txtResulto.setText(String.valueOf(r));
    }
}

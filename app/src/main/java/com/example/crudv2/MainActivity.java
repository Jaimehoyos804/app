package com.example.crudv2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Atributos
    EditText txtcorreo, txtclave;
    Button btnIngresar, btnSalir;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        txtclave = findViewById(R.id.txtPass);
        txtcorreo = findViewById(R.id.txtCorreo);

        btnIngresar = findViewById(R.id.btnIngresar);
        btnSalir = findViewById(R.id.btnSalir);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Ingresar (View view){
        String email = txtcorreo.getText().toString();
        String clave = txtclave.getText().toString();

        if(email.equals("admin") && clave.equals("abcd")){
            Intent intent = new Intent(MainActivity.this, PantallaInicio.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Datos invalidos por favor revise", Toast.LENGTH_SHORT).show();
        }
        txtclave.setText("");

    }
    public void Salir (View view){

        System.exit(0);
    }

}
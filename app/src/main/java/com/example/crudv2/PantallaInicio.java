package com.example.crudv2;

import android.annotation.SuppressLint;
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

import java.util.List;

public class PantallaInicio extends AppCompatActivity {

    Button guardar, consular, salir, eliminar;
    EditText nombre, email, clave, telefono;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_inicio);

        guardar = findViewById(R.id.btnGuardar);
        consular = findViewById(R.id.btnConsultar);
        salir = findViewById(R.id.btnExit);
        eliminar = findViewById(R.id.btnEliminar);

        nombre = findViewById(R.id.txtName);
        email = findViewById(R.id.txtEmail);
        clave = findViewById(R.id.txtPass);
        telefono = findViewById(R.id.txtPhone);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void Guardar (View view){
        String name = nombre.getText().toString();
        String correo = email.getText().toString();
        String passw = clave.getText().toString();
        String tel = telefono.getText().toString();

        Usuario usu = new Usuario(name,correo,passw,tel);
        usu.save();
        //Limpiar Pantalla
        nombre.setText("");
        email.setText("");
        clave.setText("");
        telefono.setText("");

    }
    public void Consultar (View view){
        String name = nombre.getText().toString();

        List <Usuario> lista = Usuario.find(Usuario.class, "nombre = ?",name);
        Usuario usuario = lista.get(0);
        telefono.setText(usuario.getTelefono());
        nombre.setText("");
        nombre.setText(usuario.getNombre());
        clave.setText(usuario.getClave());
        email.setText(usuario.getEmail());
    }

    public void Eliminar(View view){
        String name = nombre.getText().toString();
        List <Usuario> lista = Usuario.find(Usuario.class, "nombre = ?",name);
        Usuario usuario = lista.get(0);
        usuario.delete();
        Toast.makeText(this, "Usuario eliminado con exito", Toast.LENGTH_SHORT).show();
        nombre.setText("");
        email.setText("");
        clave.setText("");
        telefono.setText("");
    }

    public void Salir (View view){
        System.exit(0);
    }


}
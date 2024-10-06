package com.example.crudv2;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class PantallaInicio extends AppCompatActivity {
    private boolean isConsulta = true;
    Button guardar, consular, salir, eliminar, actulizar;
    EditText documento,nombre, email, clave, telefono;

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
        actulizar = findViewById(R.id.btnActulizar);

        nombre = findViewById(R.id.txtName);
        email = findViewById(R.id.txtEmail);
        clave = findViewById(R.id.txtPass);
        telefono = findViewById(R.id.txtPhone);
        documento =  findViewById(R.id.txtNdocumento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void Guardar (View view){
        String numeroDocumento  = documento.getText().toString();
        String name = nombre.getText().toString();
        String correo = email.getText().toString();
        String passw = clave.getText().toString();
        String tel = telefono.getText().toString();

        Usuario usu = new Usuario(numeroDocumento,name,correo,passw,tel);
        usu.save();
        //Limpiar Pantalla
        documento.setText("");
        nombre.setText("");
        email.setText("");
        clave.setText("");
        telefono.setText("");

    }
    public void Consultar (View view){
        if(isConsulta){ String numeroD = documento.getText().toString();

            List <Usuario> lista = Usuario.find(Usuario.class, "numero_documento = ?",numeroD);
            if(!lista.isEmpty()){
                Usuario usuario = lista.get(0);
                telefono.setText(usuario.getTelefono());
                nombre.setText(usuario.getNombre());
                clave.setText(usuario.getClave());
                email.setText(usuario.getEmail());
                guardar.setEnabled(false);
                consular.setText("limpiar");
                isConsulta = false;
            }else{
                //Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                Alerta("Usuario no encontrado");
            }
        }else{
            limpiarCampos();
            consular.setText("Consultar");
            guardar.setEnabled(true);

        }

    }

    public void Eliminar(View view){
        String doc = documento.getText().toString();
        List <Usuario> lista = Usuario.find(Usuario.class, "numero_documento = ?",doc);
        Usuario usuario = lista.get(0);
        usuario.delete();
        Toast.makeText(this, "Usuario eliminado con exito", Toast.LENGTH_SHORT).show();
        documento.setText("");
        nombre.setText("");
        email.setText("");
        clave.setText("");
        telefono.setText("");
    }

    public void Actulizar(View view){
        String doc = documento.getText().toString();
        List <Usuario> lista = Usuario.find(Usuario.class, "numero_documento = ?",doc);
        Usuario usuario = lista.get(0);
        telefono.setText(usuario.getTelefono());
        nombre.setText(usuario.getNombre());
        clave.setText(usuario.getClave());
        email.setText(usuario.getEmail());
        usuario.save();
        Alerta("Usuario actulizado");
    }

    public void Salir (View view){
        System.exit(0);
    }

    private void limpiarCampos() {
        documento.setText("");
        nombre.setText("");
        email.setText("");
        clave.setText("");
        telefono.setText("");
    }

    public void Alerta(String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alerta");
        builder.setMessage(Mensaje);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
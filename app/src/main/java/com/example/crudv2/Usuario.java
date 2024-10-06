package com.example.crudv2;

import com.orm.SugarRecord;

public class Usuario extends SugarRecord {

    //Atributos entidad
    private String numeroDocumento;
    private String nombre;
    private String email;
    private String clave;
    private String telefono;

    public Usuario() {
    }

    public Usuario(String numeroDocumento, String nombre, String email, String clave, String telefono) {
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.telefono = telefono;
    }

    public String getnumeroDocumento() {
        return numeroDocumento;
    }

    public void setnumeroDocumento(String numeroDocumento) {
        numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}


package org.example;

public class Contact {
    private int id;
    private String nombre;
    private String telefono;
    private String email;

    public Contact(int id, String nombre, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public Contact(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public Contact(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Nombre: " + nombre + " Telefono: " + telefono + " Email: " + email;
    }
}

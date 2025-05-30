package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {

    public void addContact(Contact contact) {
        String sql = "INSERT INTO contactos (nombre, telefono, email) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, contact.getNombre());
            pstmt.setString(2, contact.getTelefono());
            pstmt.setString(3, contact.getEmail());
            pstmt.executeUpdate();
            System.out.println("Contacto agregado");

        }   catch(SQLException e) {
            System.err.println("Error al agregar contacto" + e.getMessage());
        }
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contactos";

        try (Connection conn = DatabaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                Contact contact = new Contact(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                contacts.add(contact);
            }
        }   catch (SQLException e) {
            System.err.println("Error al obtener contactos" + e.getMessage());
        }
        return contacts;
    }
}

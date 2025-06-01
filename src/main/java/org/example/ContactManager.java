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

    public List<Contact> searchContact(String search) {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contactos WHERE nombre LIKE ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + search + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Contact contact = new Contact(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("email")
                    );
                    contacts.add(contact);
                }
            }
        }   catch (SQLException e) {
            System.err.println("Error al buscar contacto: " + e.getMessage());
        }
        return contacts;
    }

    public void updateContact(Contact contact) {
        String sql = "UPDATE contactos SET nombre = ?, telefono = ?, email = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,contact.getNombre());
            pstmt.setString(2, contact.getTelefono());
            pstmt.setString(3, contact.getEmail());
            pstmt.setInt(4, contact.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Contacto actualizado");
            }   else {
                System.err.println("Contacto no encontrado");
            }
        }   catch (SQLException e) {
            System.err.println("Error al actualizar contacto" + e.getMessage());
        }
    }

    public void deleteContact(int contactId) {
        String sql = "DELETE FROM contactos WHERE id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, contactId);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Contacto eliminado");
            }   else {
                System.err.println("Contacto no encontrado");
            }

        }   catch (SQLException e) {
            System.err.println("Error al eliminar contacto" + e.getMessage());
        }
    }

}

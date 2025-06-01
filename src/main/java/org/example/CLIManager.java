package org.example;

import java.util.List;
import java.util.Scanner;

public class CLIManager {
    private final Scanner scanner = new Scanner(System.in);
    private final ContactManager contactManager = new ContactManager();

    public void start() {
        while(true) {
            mostrarMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> agregarContacto();
                case "2" -> verContactos();
                case "3" -> buscarContactos();
                case "4" -> editarContacto();
                case "5" -> eliminarContacto();
                case "6"-> {
                    System.out.println("Saliendo del programa...");
                    return;
                }
                default -> System.out.println("Opción no valida. Intentalo de nuevo.");
            }
        }
    }

    public void mostrarMenu() {
        System.out.println("\n--- GESTOR DE TAREAS ---");
        System.out.println("1. Agregar un contacto");
        System.out.println("2. Ver contactos");
        System.out.println("3. Buscar un contacto");
        System.out.println("4. Editar un contacto");
        System.out.println("5. Eliminar un contacto");
        System.out.println("6. Salir");
        System.out.print("Selecciona una opción: ");
    }

    public void agregarContacto() {
        System.out.print("Introduce el nombre del contacto: ");
        String nombre = scanner.nextLine();

        System.out.print("Introduce el numero de teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Introduce el email del contacto(opcional): ");
        String email = scanner.nextLine();

        contactManager.addContact(new Contact(nombre, telefono, email));
    }
    
    public void verContactos() {
        List<Contact> contacts = contactManager.getContacts();
        
        if (contacts.isEmpty()) {
            System.out.println("No hay contactos registrados");
        }   else {
            System.out.println("\n--- CONTACTOS REGISTRADOS ---");
            for (Contact contact : contacts) {
                System.out.println("Id: " + contact.getId());
                System.out.println("Nombre: " + contact.getNombre());
                System.out.println("Teléfono: " + contact.getTelefono());
                System.out.println("Email: " + contact.getEmail());
                System.out.println("---------------------------");
            }
        }
    }

    public void buscarContactos() {
        System.out.print("Introduce el nombre del contacto a buscar: ");
        String search = scanner.nextLine();

        List<Contact> contacts = contactManager.searchContact(search);

        if (contacts.isEmpty()) {
            System.out.println("No se encontraron contactos con ese nombre");
        }   else {
            System.out.println("\n--- CONTACTOS ENCONTRADOS ---");
            for (Contact contact : contacts) {
                System.out.println("Id: " + contact.getId());
                System.out.println("Nombre: " + contact.getNombre());
                System.out.println("Teléfono: " + contact.getTelefono());
                System.out.println("Email: " + contact.getEmail());
                System.out.println("---------------------------");
            }
        }
    }

    public void editarContacto() {
        System.out.print("Introduce el id del contacto a editar: ");
        String idInput = scanner.nextLine();
        int contactId;

        try {
            contactId = Integer.parseInt(idInput);
        }   catch (NumberFormatException e) {
            System.err.println("ID inválido. Introduzca un numero");
            return;
        }

        System.out.print("Introduce el nuevo nombre del contacto: ");
        String nombre = scanner.nextLine();

        System.out.print("Introduce el nuevo numero de teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Introduce el nuevo email del contacto(opcional): ");
        String email = scanner.nextLine();

        contactManager.updateContact(new Contact(contactId, nombre, telefono, email));
    }

    public void eliminarContacto() {
        System.out.print("Introduce el id del contacto a eliminar: ");
        String idInput = scanner.nextLine();
        int contactId;

        try {
            contactId = Integer.parseInt(idInput);
        }   catch (NumberFormatException e) {
            System.err.println("ID inválido. Introduzca un numero");
            return;
        }

        contactManager.deleteContact(contactId);
    }

}

package org.example;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();
        CLIManager cli = new CLIManager();
        cli.start();
    }
}
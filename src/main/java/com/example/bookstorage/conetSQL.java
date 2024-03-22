package com.example.bookstorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conetSQL {
    public static Connection connect() {
        Connection conn = null;
        try {
            // Establecer la conexión a la base de datos SQLite
            conn = DriverManager.getConnection("jdbc:sqlite:base_de_datos.db");
            System.out.println("Conexión a SQLite establecida");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}

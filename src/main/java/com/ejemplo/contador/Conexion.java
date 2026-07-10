package com.ejemplo.contador;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/contador_numeros";

    private static final String USER = "root";

    private static final String PASSWORD = "Tapiero123";

    public static Connection conectar() throws Exception {

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }       
}
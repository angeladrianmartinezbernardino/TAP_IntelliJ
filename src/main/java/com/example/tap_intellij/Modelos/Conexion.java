package com.example.tap_intellij.Modelos;

import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion extends Stage {
    public static Connection Conexión_a_la_base_de_datos;
    private static String Servidor = "localhost";
    private static String Usuario = "jefe";
    private static String Contraseña = "1234567890";
    private static String Base_de_datos = "taqueria";

    public static void Crear_conexión_a_la_base_de_datos() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Conexión_a_la_base_de_datos = DriverManager.getConnection("jdbc:mariadb://" + Servidor + ":3306/" + Base_de_datos, Usuario, Contraseña);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

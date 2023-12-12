package com.example.tap_intellij.Modelos;

import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion extends Stage {
    public static Connection conexion;
    private static String server = "localhost";
    private static String user = "root";
    private static String pass = "Laptop#2002";
    private static String db = "restaurante";

    public static void createConnection() {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.jc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://" + server + ":3310/" + db, user, pass);
            //conexion = DriverManager.getConnection("jdbc:mysql://"+server+":3310/"+db+"?allowPublicKeyRetrieval=true&useSSL=false",user,pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

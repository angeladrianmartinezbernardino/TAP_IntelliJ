package com.example.tap_intellij.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;

public class Categorías_DAO extends Stage {
    public int Categoría;
    private String Nombre_de_la_categoría, Consulta;
    private int ID_de_la_categoría;
    private Statement Declaración;
    private ResultSet Resultado;
    private ObservableList<Categorías_DAO> listCat;

    public int getCategoría() {
        return Categoría;
    }

    public void setCategoría(int categoría) {
        Categoría = categoría;
    }

    public String getNombre_de_la_categoría() {
        return Nombre_de_la_categoría;
    }

    public void setNombre_de_la_categoría(String nombre_de_la_categoría) {
        this.Nombre_de_la_categoría = nombre_de_la_categoría;
    }

    public int getID_de_la_categoría() {
        return ID_de_la_categoría;
    }

    public void setID_de_la_categoría(int ID_de_la_categoría) {
        this.ID_de_la_categoría = ID_de_la_categoría;
    }

    public void Insertar() {
        try {
            Consulta = "INSERT INTO Catagorías" +
                    "(Nombre) VALUES('" + this.Nombre_de_la_categoría + "')";
            Declaración = Conexión.Conexión_a_la_base_de_datos.createStatement();
            Declaración.execute(Consulta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Actualizar() {
        try {
            Consulta = "UPDATE Catagorías SET Nombre = '" + this.Nombre_de_la_categoría + "' " +
                    "WHERE ID = " + this.ID_de_la_categoría;
            Declaración = Conexión.Conexión_a_la_base_de_datos.createStatement();
            Declaración.executeUpdate(Consulta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Eliminar() {
        try {
            Consulta = "DELETE FROM Catagorías WHERE ID = " + this.ID_de_la_categoría;
            Declaración = Conexión.Conexión_a_la_base_de_datos.createStatement();
            Declaración.executeUpdate(Consulta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Categorías_DAO> Enlistar_categorías() {
        listCat = FXCollections.observableArrayList();
        Categorías_DAO objC;
        try {
            Consulta = "SELECT * FROM Catagorías";
            Declaración = Conexión.Conexión_a_la_base_de_datos.createStatement();
            Resultado = Declaración.executeQuery(Consulta);
            while (Resultado.next()) {
                objC = new Categorías_DAO();
                objC.ID_de_la_categoría = Resultado.getInt("ID");
                objC.Nombre_de_la_categoría = Resultado.getString("Nombre");
                listCat.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCat;
    }
}

package com.example.tap_intellij.Modelos.Taqueria;

import com.example.tap_intellij.Modelos.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class Mesa_DAO {
    private int idMesa;
    private int numero;

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void Insertar() {
        String query = "INSERT INTO mesas(numero) VALUES ('" + numero + "')";
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Actualizar() {
        String query = "UPDATE mesas SET numero='" + numero + "' WHERE idMesa=" + idMesa;
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Eliminar() {
        String query = "DELETE FROM mesas WHERE idMesa=" + idMesa;
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Mesa_DAO> Consultar() {
        ObservableList<Mesa_DAO> listaEmp = FXCollections.observableArrayList();
        String query = "SELECT * FROM mesas";
        try {
            Mesa_DAO objEmp;
            Statement stmt = Conexion.Conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objEmp = new Mesa_DAO();
                objEmp.idMesa = res.getInt("idMesa");
                objEmp.numero = res.getInt("numero");
                listaEmp.add(objEmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmp;
    }
}

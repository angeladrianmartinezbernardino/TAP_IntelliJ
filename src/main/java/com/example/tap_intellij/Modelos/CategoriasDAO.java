package com.example.tap_intellij.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriasDAO extends Stage {
    public int Categoria;
    private String nomCategoria;
    private int idCategoria;

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int categoria) {
        Categoria = categoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void INSERTAR() {
        try {
            String query = "INSERT INTO tblCatagorias" +
                    "(nomCategoria) VALUES('" + this.nomCategoria + "')";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR() {
        try {
            String query = "UPDATE tblCatagorias SET nomCategorias = '" + this.nomCategoria + "' " +
                    "WHERE idCategoria = " + this.idCategoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR() {
        try {
            String query = "DELETE FROM tblCatagorias WHERE idCategoria = " + this.idCategoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<CategoriasDAO> LISTARCATEGORIAS() {
        ObservableList<CategoriasDAO> listCat = FXCollections.observableArrayList();
        CategoriasDAO objC;
        try {
            String query = "SELECT * FROM tblCatagorias";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objC = new CategoriasDAO();
                objC.idCategoria = res.getInt("idCategoria");
                objC.nomCategoria = res.getString("nomCategoria");
                listCat.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCat;
    }
}

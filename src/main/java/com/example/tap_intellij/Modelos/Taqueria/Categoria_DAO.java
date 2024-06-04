package com.example.tap_intellij.Modelos.Taqueria;

import com.example.tap_intellij.Modelos.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class Categoria_DAO {
    private int idCategoria;
    private String nombre;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void INSERTAR(){
        String query = "INSERT INTO categorias(nombre) VALUES('" + nombre + "')";

        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR(){
        String query = "UPDATE categorias SET nombre='" + nombre + "' WHERE idCategoria=" + idCategoria;

        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR(){
        String query = "DELETE FROM categorias WHERE idCategoria=" + idCategoria;

        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Categoria_DAO> CONSULTAR(){
        ObservableList<Categoria_DAO> listaCategorias = FXCollections.observableArrayList();
        String query = "SELECT * FROM categorias";

        try {
            Categoria_DAO objCategoria;
            Statement stmt = Conexion.Conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()){
                objCategoria = new Categoria_DAO();
                objCategoria.idCategoria = res.getInt("idCategoria");
                objCategoria.nombre = res.getString("nombre");
                listaCategorias.add(objCategoria);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCategorias;
    }
}

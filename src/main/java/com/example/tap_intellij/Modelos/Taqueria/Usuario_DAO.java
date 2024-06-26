package com.example.tap_intellij.Modelos.Taqueria;

import com.example.tap_intellij.Modelos.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Usuario_DAO {
    private int idUsuario;
    private String nombre;
    private String contraseña;
    private String rol;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void Insertar() {
        String query = "INSERT INTO usuarios(nombre, contraseña, rol) VALUES ('" + nombre + "', '" + contraseña + "', '" + rol + "')";
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Actualizar() {
        String query = "UPDATE usuarios SET nombre='" + nombre + "', contraseña='" + contraseña + "', rol='" + rol + "' WHERE idUsuario=" + idUsuario;
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Eliminar() {
        String query = "DELETE FROM usuarios WHERE idUsuario=" + idUsuario;
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Usuario_DAO> Consultar() {
        ObservableList<Usuario_DAO> listaEmp = FXCollections.observableArrayList();
        String query = "SELECT * FROM usuarios";
        try {
            Usuario_DAO objUsu;
            Statement stmt = Conexion.Conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objUsu = new Usuario_DAO();
                objUsu.idUsuario = res.getInt("idUsuario");
                objUsu.nombre = res.getString("nombre");
                objUsu.contraseña = res.getString("contraseña");
                objUsu.rol = res.getString("rol");
                listaEmp.add(objUsu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmp;
    }

    public boolean autenticar(String usuario, String contraseña) {
        boolean autenticado = false;
        String query = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";
        try (PreparedStatement preparedStatement = Conexion.Conexion.prepareStatement(query)) {
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contraseña);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                autenticado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autenticado;
    }
}

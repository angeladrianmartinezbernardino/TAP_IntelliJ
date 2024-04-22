package com.example.tap_intellij.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Ordenes_DAO {
    private int idOrden;
    private Integer idEmpleado; // Usamos Integer en lugar de int para permitir null.
    private Date fecha;
    private String observaciones;
    private Integer idMesa; // Usamos Integer en lugar de int para permitir null.
    private int idUsuario;

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void Insertar() {
        String query = "INSERT INTO orden(idEmpleado, fecha, observaciones, idMesa, idUsuario) VALUES ('" + idEmpleado + "', '" + fecha + "', '" + observaciones + "', '" + idMesa + "', '" + idUsuario + "')";
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Actualizar() {
        String query = "UPDATE orden SET" + "idOrden='" + idOrden + "', idEmpleado='" + idEmpleado + "', fecha=" + fecha + ", " + "observaciones='" + observaciones + "', idMesa='" + idMesa + "', idUsuario='" + idUsuario + "' WHERE idOrden =" + idOrden;
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Eliminar() {
        String query = "DELETE FROM orden WHERE idOrden=" + idOrden;
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Ordenes_DAO> Consultar() {
        ObservableList<Ordenes_DAO> listaEmp = FXCollections.observableArrayList();
        String query = "SELECT * FROM orden";
        try {
            Ordenes_DAO objEmp;
            Statement stmt = Conexion.Conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objEmp = new Ordenes_DAO();
                objEmp.idOrden = res.getInt("idOrden");
                objEmp.idEmpleado = res.getInt("idEmpleado");
                objEmp.fecha = res.getDate("fecha");
                objEmp.observaciones = res.getString("observaciones");
                objEmp.idMesa = res.getInt("idMesa");
                objEmp.idUsuario = res.getInt("idUsuario");
                listaEmp.add(objEmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmp;
    }
}

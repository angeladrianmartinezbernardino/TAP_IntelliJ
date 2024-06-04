package com.example.tap_intellij.Modelos.Taqueria;

import com.example.tap_intellij.Modelos.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class Servicio_domicilio_DAO {
    private int idServicio;
    private String fecha;
    private String direccionEntrega;
    private double costo;
    private String observaciones;

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void INSERTAR() {
        String query = "INSERT INTO servicio_domicilio(fecha, direccion_entrega, costo, observaciones) VALUES('" + fecha + "', '" + direccionEntrega + "', " + costo + ", '" + observaciones + "')";

        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR() {
        String query = "DELETE FROM servicio_domicilio WHERE idServicio=" + idServicio;

        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR() {
        String query = "UPDATE servicio_domicilio SET fecha='" + fecha + "', direccion_entrega='" + direccionEntrega + "', costo=" + costo + ", observaciones='" + observaciones + "' WHERE idServicio=" + idServicio;

        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Servicio_domicilio_DAO> CONSULTAR() {
        ObservableList<Servicio_domicilio_DAO> listaServicios = FXCollections.observableArrayList();
        String query = "SELECT * FROM servicio_domicilio";

        try {
            Servicio_domicilio_DAO objServicio;
            Statement stmt = Conexion.Conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objServicio = new Servicio_domicilio_DAO();
                objServicio.setIdServicio(res.getInt("idServicio"));
                objServicio.setFecha(res.getString("fecha"));
                objServicio.setDireccionEntrega(res.getString("direccion_entrega"));
                objServicio.setCosto(res.getDouble("costo"));
                objServicio.setObservaciones(res.getString("observaciones"));
                listaServicios.add(objServicio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaServicios;
    }
}

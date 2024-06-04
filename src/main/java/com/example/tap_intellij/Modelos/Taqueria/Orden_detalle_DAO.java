package com.example.tap_intellij.Modelos.Taqueria;

import com.example.tap_intellij.Modelos.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class Orden_detalle_DAO {
    private int idOrden;
    private int idProducto;
    private int cantidad;
    private double precio;
    private int idPromocion;
    private int idMesa;

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public ObservableList<Orden_detalle_DAO> consultarDetallesPorOrden(int idOrden) {
        ObservableList<Orden_detalle_DAO> listaDetalles = FXCollections.observableArrayList();
        String query = "SELECT * FROM DetOrden WHERE idOrden = " + idOrden;

        try {
            Statement stmt = Conexion.Conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Orden_detalle_DAO detalle = new Orden_detalle_DAO();
                detalle.setIdOrden(res.getInt("idOrden"));
                detalle.setIdProducto(res.getInt("idProducto"));
                detalle.setCantidad(res.getInt("cantidad"));
                detalle.setPrecio(res.getDouble("precio"));
                detalle.setIdPromocion(res.getInt("idPromocion"));
                detalle.setIdMesa(res.getInt("idMesa"));
                listaDetalles.add(detalle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDetalles;
    }

    public void insertarDetalle(Orden_detalle_DAO detalle) {
        String query = String.format("INSERT INTO DetOrden (idOrden, idProducto, cantidad, precio, idPromocion, idMesa) " +
                        "VALUES (%d, %d, %d, %.2f, %d, %d)",
                detalle.getIdOrden(), detalle.getIdProducto(), detalle.getCantidad(), detalle.getPrecio(),
                detalle.getIdPromocion(), detalle.getIdMesa());

        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarDetalle(Orden_detalle_DAO detalle) {
        String query = "DELETE FROM DetOrden WHERE idOrden = " + detalle.getIdOrden() +
                " AND idProducto = " + detalle.getIdProducto();

        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

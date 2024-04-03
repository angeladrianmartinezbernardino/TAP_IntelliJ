package com.example.tap_intellij.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class Empleados_DAO {
    private int idEmpleado;
    private String nomEmpleado;
    private String RFCEmpleado;
    private float salario;
    private String telefono;
    private String direccion;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNomEmpleado() {
        return nomEmpleado;
    }

    public void setNomEmpleado(String nomEmpleado) {
        this.nomEmpleado = nomEmpleado;
    }

    public String getRFCEmpleado() {
        return RFCEmpleado;
    }

    public void setRFCEmpleado(String RFCEmpleado) {
        this.RFCEmpleado = RFCEmpleado;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void Insertar() {
        String query = "INSERT INTO empleado(nomEmpleado, RFCEmpleado, salario, telefono, direccion) VALUES ('" + nomEmpleado + "', '" + RFCEmpleado + "', " + salario + ", '" + telefono + "', '" + direccion + "')";
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Actualizar() {
        String query = "UPDATE empleado SET nomEmpleado='" + nomEmpleado + "', RFCEmpleado='" + RFCEmpleado + "', salario=" + salario + ", " + "telefono='" + telefono + "', direccion='" + direccion + "' WHERE idEmpleado =" + idEmpleado;
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Eliminar() {
        String query = "DELETE FROM empleado WHERE idEmpleado=" + idEmpleado;
        try {
            Statement stmt = Conexion.Conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Empleados_DAO> Consultar() {
        ObservableList<Empleados_DAO> listaEmp = FXCollections.observableArrayList();
        String query = "SELECT * FROM empleado";
        try {
            Empleados_DAO objEmp;
            Statement stmt = Conexion.Conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objEmp = new Empleados_DAO();
                objEmp.idEmpleado = res.getInt("idEmpleado");
                objEmp.nomEmpleado = res.getString("nomEmpleado");
                objEmp.RFCEmpleado = res.getString("RFCEmpleado");
                objEmp.salario = res.getFloat("salario");
                objEmp.telefono = res.getString("telefono");
                objEmp.direccion = res.getString("direccion");
                listaEmp.add(objEmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmp;
    }
}

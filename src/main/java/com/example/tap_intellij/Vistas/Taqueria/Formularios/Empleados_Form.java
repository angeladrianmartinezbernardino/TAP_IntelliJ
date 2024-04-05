package com.example.tap_intellij.Vistas.Taqueria.Formularios;

import com.example.tap_intellij.Modelos.Empleados_DAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Empleados_Form extends Stage {
    private TableView<Empleados_DAO> tbvEmpleado;
    private Empleados_DAO objEmp;
    String[] arPrompts = {"ID del empleado", "Nombre del empleado", "RFC del empleado", "Sueldo del empleado", "Telefono del empleado", "Direccion del empleado"};
    private Scene escena;
    private TextField[] arTxtCampos = new TextField[5];
    private Button btnGuardar;
    private VBox vbxPrincipal;

    public Empleados_Form(TableView<Empleados_DAO> tbvEmp, Empleados_DAO objEmp) {
        tbvEmpleado = tbvEmp;
        this.objEmp = (objEmp == null) ? new Empleados_DAO() : objEmp;
        CrearUI();
        this.setTitle("Insertar usuario");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        vbxPrincipal = new VBox();
        vbxPrincipal.setPadding(new Insets(10));
        vbxPrincipal.setSpacing(10);
        vbxPrincipal.setAlignment(Pos.CENTER);
        for (int i = 0; i < arTxtCampos.length; i++) {
            arTxtCampos[i] = new TextField();
            arTxtCampos[i].setPromptText(arPrompts[i]);
            vbxPrincipal.getChildren().add(arTxtCampos[i]);
        }
        LlenarForm();
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> GuardarEmpleado());
        vbxPrincipal.getChildren().add(btnGuardar);
        escena = new Scene(vbxPrincipal, 350, 250);
    }

    private void LlenarForm() {
        arTxtCampos[0].setText(objEmp.getIdEmpleado()+"");
        arTxtCampos[1].setText(objEmp.getNomEmpleado());
        arTxtCampos[2].setText(objEmp.getRFCEmpleado());
        arTxtCampos[3].setText(objEmp.getSalario()+"");
        arTxtCampos[4].setText(objEmp.getTelefono());
        arTxtCampos[5].setText(objEmp.getDireccion());
    }

    private void GuardarEmpleado() {
        objEmp.setIdEmpleado(Integer.parseInt(arTxtCampos[0].getText()));
        objEmp.setNomEmpleado(arTxtCampos[1].getText());
        objEmp.setRFCEmpleado(arTxtCampos[2].getText());
        objEmp.setSalario(Float.parseFloat(arTxtCampos[3].getText()));
        objEmp.setTelefono(arTxtCampos[4].getText());
        objEmp.setDireccion(arTxtCampos[5].getText());
        if (objEmp.getIdEmpleado() > 0) {
            objEmp.Actualizar();
        } else {
            objEmp.Insertar();
        }
        tbvEmpleado.setItems(objEmp.Consultar());
        tbvEmpleado.refresh();
        arTxtCampos[0].clear();
        arTxtCampos[1].clear();
        arTxtCampos[2].clear();
        arTxtCampos[3].clear();
        arTxtCampos[4].clear();
        arTxtCampos[5].clear();
    }
}

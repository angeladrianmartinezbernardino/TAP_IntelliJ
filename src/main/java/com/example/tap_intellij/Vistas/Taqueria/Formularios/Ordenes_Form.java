package com.example.tap_intellij.Vistas.Taqueria.Formularios;

import com.example.tap_intellij.Modelos.Taqueria.Ordenes_DAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;

public class Ordenes_Form extends Stage {
    private TableView<Ordenes_DAO> tbvOrdenes;
    private Ordenes_DAO objOrd;
    String[] arPrompts = {"id del Empleado", "Fecha", "Observaciones", "ID de la mesa", "ID del Usuario"};
    private Scene escena;
    private TextField[] arTxtCampos = new TextField[5];
    private Button btnGuardar;
    private VBox vbxPrincipal;

    public Ordenes_Form(TableView<Ordenes_DAO> tbvOrd, Ordenes_DAO objOrd) {
        tbvOrdenes = tbvOrd;
        this.objOrd = (objOrd == null) ? new Ordenes_DAO() : objOrd;
        CrearUI();
        this.setTitle("Insertar orden");
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
        btnGuardar.setOnAction(event -> GuardarOrden());
        vbxPrincipal.getChildren().add(btnGuardar);
        escena = new Scene(vbxPrincipal, 350, 250);
    }

    private void LlenarForm() {
        arTxtCampos[0].setText(objOrd.getIdEmpleado()+"");
        arTxtCampos[1].setText(objOrd.getFecha() + "");
        arTxtCampos[2].setText(objOrd.getObservaciones());
        arTxtCampos[3].setText(objOrd.getIdMesa()+"");
        arTxtCampos[4].setText(objOrd.getIdUsuario()+"");
    }

    private void GuardarOrden() {
        objOrd.setIdEmpleado(Integer.valueOf(arTxtCampos[0].getText()));
        objOrd.setFecha(Date.valueOf(arTxtCampos[1].getText()));
        objOrd.setObservaciones(arTxtCampos[2].getText());
        objOrd.setIdMesa(Integer.valueOf(arTxtCampos[3].getText()));
        objOrd.setIdUsuario(Integer.parseInt(arTxtCampos[4].getText()));
        if (objOrd.getIdOrden() > 0) {
            objOrd.Actualizar();
        } else {
            objOrd.Insertar();
        }
        tbvOrdenes.setItems(objOrd.Consultar());
        tbvOrdenes.refresh();
        arTxtCampos[0].clear();
        arTxtCampos[1].clear();
        arTxtCampos[2].clear();
        arTxtCampos[3].clear();
        arTxtCampos[4].clear();
    }
}

package com.example.tap_intellij.Vistas.Taqueria.Formularios;

import com.example.tap_intellij.Modelos.Taqueria.Promocion_DAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Promocion_Form extends Stage {
    private TableView<Promocion_DAO> tbvPromociones;
    private Promocion_DAO objPromocion;
    private String[] arPrompts = {"Nombre", "Descripción", "Costo Promoción", "Fecha Inicio", "Fecha Fin"};
    private Scene escena;
    private TextField[] arTxtCampos = new TextField[5];
    private Button btnGuardar;
    private VBox vbxPrincipal;

    public Promocion_Form(TableView<Promocion_DAO> tbvPromociones, Promocion_DAO objPromocion) {
        this.tbvPromociones = tbvPromociones;
        this.objPromocion = (objPromocion == null) ? new Promocion_DAO() : objPromocion;
        CrearUI();
        this.setTitle("Agregar/Editar Promoción");
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
        btnGuardar.setOnAction(event -> GuardarPromocion());
        vbxPrincipal.getChildren().add(btnGuardar);
        escena = new Scene(vbxPrincipal, 350, 300);
    }

    private void LlenarForm() {
        arTxtCampos[0].setText(objPromocion.getNombre());
        arTxtCampos[1].setText(objPromocion.getDescripcion());
        arTxtCampos[2].setText(String.valueOf(objPromocion.getCostoPromo()));
        arTxtCampos[3].setText(objPromocion.getFechaInicio());
        arTxtCampos[4].setText(objPromocion.getFechaFin());
    }

    private void GuardarPromocion() {
        objPromocion.setNombre(arTxtCampos[0].getText());
        objPromocion.setDescripcion(arTxtCampos[1].getText());
        objPromocion.setCostoPromo(Float.parseFloat(arTxtCampos[2].getText()));
        objPromocion.setFechaInicio(arTxtCampos[3].getText());
        objPromocion.setFechaFin(arTxtCampos[4].getText());
        if (objPromocion.getIdPromocion() > 0) objPromocion.ACTUALIZAR();
        else objPromocion.INSERTAR();
        tbvPromociones.setItems(objPromocion.CONSULTAR());
        tbvPromociones.refresh();
        for (TextField campo : arTxtCampos) {
            campo.clear();
        }
    }
}

package com.example.tap_intellij.Vistas.Taqueria.Formularios;

import com.example.tap_intellij.Modelos.Taqueria.Mesa_DAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Mesa_Form extends Stage {
    private TableView<Mesa_DAO> tbvMesas;
    private Mesa_DAO objMes;
    String[] arPrompts = {"Número de la mesa"};
    private Scene escena;
    private TextField[] arTxtCampos = new TextField[1];
    private Button btnGuardar;
    private VBox vbxPrincipal;

    public Mesa_Form(TableView<Mesa_DAO> tbvMes, Mesa_DAO objMes) {
        tbvMesas = tbvMes;
        this.objMes = (objMes == null) ? new Mesa_DAO() : objMes;
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
        btnGuardar.setOnAction(event -> GuardarMesa());
        vbxPrincipal.getChildren().add(btnGuardar);
        escena = new Scene(vbxPrincipal, 350, 250);
    }

    private void LlenarForm() {
        arTxtCampos[0].setText(objMes.getNumero() + "");
    }

    private void GuardarMesa() {
        objMes.setNumero(Integer.parseInt(arTxtCampos[0].getText()));
        if (objMes.getIdMesa() > 0) {
            objMes.Actualizar();
        } else {
            objMes.Insertar();
        }
        tbvMesas.setItems(objMes.Consultar());
        tbvMesas.refresh();
        arTxtCampos[0].clear();
    }
}

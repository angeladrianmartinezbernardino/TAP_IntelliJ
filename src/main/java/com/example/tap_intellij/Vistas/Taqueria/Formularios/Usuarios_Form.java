package com.example.tap_intellij.Vistas.Taqueria.Formularios;

import com.example.tap_intellij.Modelos.Usuarios_DAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Usuarios_Form extends Stage {
    private TableView<Usuarios_DAO> tbvEmpleado;
    private Usuarios_DAO objUsu;
    String[] arPrompts = {"Nombre", "Contraseña", "Rol"};
    private Scene escena;
    private TextField[] arTxtCampos = new TextField[3];
    private Button btnGuardar;
    private VBox vbxPrincipal;

    public Usuarios_Form(TableView<Usuarios_DAO> tbvUsu, Usuarios_DAO objUsu) {
        tbvEmpleado = tbvUsu;
        this.objUsu = (objUsu == null) ? new Usuarios_DAO() : objUsu;
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
        btnGuardar.setOnAction(event -> GuardarUsuario());
        vbxPrincipal.getChildren().add(btnGuardar);
        escena = new Scene(vbxPrincipal, 350, 250);
    }

    private void LlenarForm() {
        arTxtCampos[0].setText(objUsu.getNombre());
        arTxtCampos[1].setText(objUsu.getContraseña());
        arTxtCampos[2].setText(objUsu.getRol());
    }

    private void GuardarUsuario() {
        objUsu.setNombre(arTxtCampos[0].getText());
        objUsu.setContraseña(arTxtCampos[1].getText());
        objUsu.setRol(arTxtCampos[2].getText());
        if (objUsu.getIdUsuario() > 0) {
            objUsu.Actualizar();
        } else {
            objUsu.Insertar();
        }
        tbvEmpleado.setItems(objUsu.Consultar());
        tbvEmpleado.refresh();
        arTxtCampos[0].clear();
        arTxtCampos[1].clear();
        arTxtCampos[2].clear();
    }
}

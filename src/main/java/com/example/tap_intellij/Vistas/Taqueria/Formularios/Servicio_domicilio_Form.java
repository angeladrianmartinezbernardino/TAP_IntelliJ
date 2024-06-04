package com.example.tap_intellij.Vistas.Taqueria.Formularios;

import com.example.tap_intellij.Modelos.Taqueria.Servicio_domicilio_DAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Servicio_domicilio_Form extends Stage {
    private TableView<Servicio_domicilio_DAO> tbvServicioDomicilio;
    private Servicio_domicilio_DAO objServicioDomicilio;
    private String[] arPrompts = {"Fecha", "Dirección de Entrega", "Costo", "Observaciones"};
    private Scene escena;
    private TextField[] arTxtCampos = new TextField[4];
    private Button btnGuardar;
    private VBox vbxPrincipal;

    public Servicio_domicilio_Form(TableView<Servicio_domicilio_DAO> tbvServicioDomicilio, Servicio_domicilio_DAO objServicioDomicilio) {
        this.tbvServicioDomicilio = tbvServicioDomicilio;
        this.objServicioDomicilio = (objServicioDomicilio == null) ? new Servicio_domicilio_DAO() : objServicioDomicilio;
        CrearUI();
        this.setTitle("Agregar/Editar Servicio a Domicilio");
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
        btnGuardar.setOnAction(event -> GuardarServicioDomicilio());
        vbxPrincipal.getChildren().add(btnGuardar);

        escena = new Scene(vbxPrincipal, 350, 300);
    }

    private void LlenarForm() {
        arTxtCampos[0].setText(objServicioDomicilio.getFecha());
        arTxtCampos[1].setText(objServicioDomicilio.getDireccionEntrega());
        arTxtCampos[2].setText(String.valueOf(objServicioDomicilio.getCosto()));
        arTxtCampos[3].setText(objServicioDomicilio.getObservaciones());
    }

    private void GuardarServicioDomicilio() {
        objServicioDomicilio.setFecha(arTxtCampos[0].getText());
        objServicioDomicilio.setDireccionEntrega(arTxtCampos[1].getText());
        objServicioDomicilio.setCosto(Float.parseFloat(arTxtCampos[2].getText()));
        objServicioDomicilio.setObservaciones(arTxtCampos[3].getText());

        if (objServicioDomicilio.getIdServicio() > 0)
            objServicioDomicilio.ACTUALIZAR();
        else
            objServicioDomicilio.INSERTAR();

        tbvServicioDomicilio.setItems(objServicioDomicilio.CONSULTAR());
        tbvServicioDomicilio.refresh();

        // Limpiar los campos después de guardar
        for (TextField campo : arTxtCampos) {
            campo.clear();
        }
    }
}

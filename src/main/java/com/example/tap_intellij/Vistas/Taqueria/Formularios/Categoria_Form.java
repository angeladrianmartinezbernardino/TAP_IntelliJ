package com.example.tap_intellij.Vistas.Taqueria.Formularios;

import com.example.tap_intellij.Modelos.Taqueria.Categoria_DAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Categoria_Form extends Stage {
    private TableView<Categoria_DAO> tbvCategorias;
    private Categoria_DAO objCategoria;
    private String[] arPrompts = {"Nombre de la categoría"};
    private Scene escena;
    private TextField[] arTxtCampos = new TextField[1];
    private Button btnGuardar;
    private VBox vbxPrincipal;

    public Categoria_Form(TableView<Categoria_DAO> tbvCategorias, Categoria_DAO objCategoria){
        this.tbvCategorias = tbvCategorias;
        this.objCategoria = (objCategoria == null) ? new Categoria_DAO() : objCategoria;
        CrearUI();
        this.setTitle("Agregar/Editar Categoría");
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
        btnGuardar.setOnAction(event -> GuardarCategoria());
        vbxPrincipal.getChildren().add(btnGuardar);

        escena = new Scene(vbxPrincipal, 350, 250);
    }

    private void LlenarForm() {
        arTxtCampos[0].setText(objCategoria.getNombre());
    }

    private void GuardarCategoria() {
        objCategoria.setNombre(arTxtCampos[0].getText());

        if (objCategoria.getIdCategoria() > 0)
            objCategoria.ACTUALIZAR();
        else
            objCategoria.INSERTAR();

        tbvCategorias.setItems(objCategoria.CONSULTAR());
        tbvCategorias.refresh();

        // Limpiar los campos después de guardar
        for (TextField campo : arTxtCampos) {
            campo.clear();
        }
    }
}

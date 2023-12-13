package com.example.tap_intellij.Vistas;

import com.example.tap_intellij.Modelos.Categorías_DAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Formulario_de_Categoría extends Stage {
    private Scene Escena;
    private HBox HBox;
    private TextField Nombre_de_categoría;
    private Button Guardar;
    private Categorías_DAO Objeto_de_Categorías_DAO;
    TableView<Categorías_DAO> Categoría;

    public Formulario_de_Categoría(TableView<Categorías_DAO> tbvCat, Categorías_DAO Objeto_de_Categorías_DAO) {
        this.Categoría = tbvCat;
        this.Objeto_de_Categorías_DAO = Objeto_de_Categorías_DAO == null ? new Categorías_DAO() : Objeto_de_Categorías_DAO;
        Crear_UI();
        Escena = new Scene(HBox);
        this.setTitle("Gestión de Categorías");
        this.setScene(Escena);
        this.show();
    }

    private void Crear_UI() {
        Nombre_de_categoría = new TextField();
        Nombre_de_categoría.setText(Objeto_de_Categorías_DAO.getNombre_de_la_categoría());
        Nombre_de_categoría.setPromptText("Nombre de la categoría");
        Guardar = new Button("Guardar");
        Guardar.setOnAction(event -> Guardar_categoría());
        HBox = new HBox(Nombre_de_categoría, Guardar);
        HBox.setSpacing(10);
        HBox.setPadding(new Insets(10));
    }

    private void Guardar_categoría() {
        Objeto_de_Categorías_DAO.setNombre_de_la_categoría(Nombre_de_categoría.getText());
        if(Objeto_de_Categorías_DAO.getID_de_la_categoría() > 0){
            Objeto_de_Categorías_DAO.Actualizar();
        }else{
            Objeto_de_Categorías_DAO.Insertar();
        }
        Objeto_de_Categorías_DAO.Insertar();
        Categoría.setItems(Objeto_de_Categorías_DAO.Enlistar_categorías());
        Categoría.refresh();
        this.close();
    }
}

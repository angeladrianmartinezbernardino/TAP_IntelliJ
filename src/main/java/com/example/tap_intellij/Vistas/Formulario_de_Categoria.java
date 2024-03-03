package com.example.tap_intellij.Vistas;

import com.example.tap_intellij.Modelos.Categorias_DAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Formulario_de_Categoria extends Stage {
    private Scene Escena;
    private HBox HBox;
    private TextField Nombre_de_categoría;
    private Button Guardar;
    private Categorias_DAO objeto_de_Categorias_DAO;
    TableView<Categorias_DAO> Categoría;

    public Formulario_de_Categoria(TableView<Categorias_DAO> tbvCat, Categorias_DAO objeto_de_Categorias_DAO) {
        this.Categoría = tbvCat;
        this.objeto_de_Categorias_DAO = objeto_de_Categorias_DAO == null ? new Categorias_DAO() : objeto_de_Categorias_DAO;
        Crear_UI();
        Escena = new Scene(HBox);
        this.setTitle("Gestión de Categorías");
        this.setScene(Escena);
        this.show();
    }

    private void Crear_UI() {
        Nombre_de_categoría = new TextField();
        Nombre_de_categoría.setText(objeto_de_Categorias_DAO.getNombre_de_la_categoría());
        Nombre_de_categoría.setPromptText("Nombre de la categoría");
        Guardar = new Button("Guardar");
        Guardar.setOnAction(event -> Guardar_categoría());
        HBox = new HBox(Nombre_de_categoría, Guardar);
        HBox.setSpacing(10);
        HBox.setPadding(new Insets(10));
    }

    private void Guardar_categoría() {
        objeto_de_Categorias_DAO.setNombre_de_la_categoría(Nombre_de_categoría.getText());
        if(objeto_de_Categorias_DAO.getID_de_la_categoría() > 0){
            objeto_de_Categorias_DAO.Actualizar();
        }else{
            objeto_de_Categorias_DAO.Insertar();
        }
        objeto_de_Categorias_DAO.Insertar();
        Categoría.setItems(objeto_de_Categorias_DAO.Enlistar_categorías());
        Categoría.refresh();
        this.close();
    }
}

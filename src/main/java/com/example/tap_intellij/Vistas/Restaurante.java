package com.example.tap_intellij.Vistas;

import com.example.tap_intellij.Componentes.Celda_del_botón;
import com.example.tap_intellij.Modelos.CategoriasDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Restaurante extends Stage {
    private VBox vBox;
    private TableView<CategoriasDAO> tbvCategorias;
    private Button btnAgregar;
    private CategoriasDAO categoriasDAO;

    public Restaurante() {
        CrearUI();
        Panel panel = new Panel("This is the title");
        panel.getStyleClass().add("panel-primary");                            //(2)
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        //Button button = new Button("Hello BootstrapFX");
        //button.getStyleClass().setAll("btn", "btn-succes");                     //(2)
        content.setCenter(vBox);
        panel.setBody(content);
        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)
        this.setTitle("BootstrapFX");
        this.setScene(scene);
        this.sizeToScene();
        this.show();
    }

    private void CrearUI() {
        categoriasDAO = new CategoriasDAO();
        tbvCategorias = new TableView<CategoriasDAO>();
        CreateTable();
        btnAgregar = new Button("Agregar");
        btnAgregar.getStyleClass().setAll("btn", "btn-succes");
        btnAgregar.setOnAction(event -> new CategoriaForm(tbvCategorias, null));
        vBox = new VBox(tbvCategorias, btnAgregar);

    }

    private void CreateTable() {
        TableColumn<CategoriasDAO, Integer> tbcIdCat = new TableColumn<>("ID");
        tbcIdCat.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
        TableColumn<CategoriasDAO, String> tbcNomCat = new TableColumn<>("Categoria");
        tbcNomCat.setCellValueFactory(new PropertyValueFactory<>("nomCategoria"));
        tbvCategorias.setItems(categoriasDAO.LISTARCATEGORIAS());
        TableColumn<CategoriasDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
                    @Override
                    public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> param) {
                        return new Celda_del_botón(1);
                    }
                });
        TableColumn<CategoriasDAO, String> tbcEliminar = new TableColumn("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
            @Override
            public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> categoriasDAOStringTableColumn) {
                return new Celda_del_botón(1);
            }
        });
        tbvCategorias.getColumns().addAll(tbcIdCat, tbcNomCat, tbcEditar, tbcEliminar);
        tbvCategorias.setItems(categoriasDAO.LISTARCATEGORIAS());
    }
}

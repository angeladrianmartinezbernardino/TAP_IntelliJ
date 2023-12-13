package com.example.tap_intellij.Vistas;

import com.example.tap_intellij.Componentes.Celda_del_botón;
import com.example.tap_intellij.Modelos.Categorías_DAO;
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
    private final Panel Panel;
    private VBox VBox;
    private BorderPane Contenido;
    private Scene Escena;
    private Button Agregar;
    private Categorías_DAO Categorías_DAO;
    private TableView<Categorías_DAO> Categorías;
    private TableColumn<Categorías_DAO, Integer> ID_de_categoría;
    private TableColumn<Categorías_DAO, String> Nombre_de_categoría, Editar, Eliminar;

    public Restaurante() {
        Crear_UI();
        Panel = new Panel("This is the title");
        Panel.getStyleClass().add("panel-primary");
        Contenido = new BorderPane();
        Contenido.setPadding(new Insets(20));
        Contenido.setCenter(VBox);
        Panel.setBody(Contenido);
        Escena = new Scene(Panel);
        Escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        this.setTitle("BootstrapFX");
        this.setScene(Escena);
        this.sizeToScene();
        this.show();
    }

    private void Crear_UI() {
        Categorías_DAO = new Categorías_DAO();
        Categorías = new TableView<Categorías_DAO>();
        Crear_tabla();
        Agregar = new Button("Agregar");
        Agregar.getStyleClass().setAll("btn", "btn-succes");
        Agregar.setOnAction(event -> new Formulario_de_Categoría(Categorías, null));
        VBox = new VBox(Categorías, Agregar);

    }

    private void Crear_tabla() {
        ID_de_categoría = new TableColumn<>("ID");
        ID_de_categoría.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
        Nombre_de_categoría = new TableColumn<>("Categoría");
        Nombre_de_categoría.setCellValueFactory(new PropertyValueFactory<>("nomCategoria"));
        Categorías.setItems(Categorías_DAO.Enlistar_categorías());
        Editar = new TableColumn<>("Editar");
        Editar.setCellFactory(
                new Callback<TableColumn<Categorías_DAO, String>, TableCell<Categorías_DAO, String>>() {
                    @Override
                    public TableCell<Categorías_DAO, String> call(TableColumn<Categorías_DAO, String> param) {
                        return new Celda_del_botón(1);
                    }
                });
        Eliminar = new TableColumn("Eliminar");
        Eliminar.setCellFactory(new Callback<TableColumn<Categorías_DAO, String>, TableCell<Categorías_DAO, String>>() {
            @Override
            public TableCell<Categorías_DAO, String> call(TableColumn<Categorías_DAO, String> categoriasDAOStringTableColumn) {
                return new Celda_del_botón(1);
            }
        });
        Categorías.getColumns().addAll(ID_de_categoría, Nombre_de_categoría, Editar, Eliminar);
        Categorías.setItems(Categorías_DAO.Enlistar_categorías());
    }
}

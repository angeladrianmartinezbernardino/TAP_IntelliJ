package com.example.tap_intellij.Vistas.Taqueria.Tablas;

import com.example.tap_intellij.Componentes.Taqueria.Categoria_Button_Cell;
import com.example.tap_intellij.Modelos.Taqueria.Categoria_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Categoria_Form;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Categoria_Taqueria extends Stage {
    private TableView<Categoria_DAO> tbvCategorias;

    public Categoria_Taqueria() {
        CrearUI();
        this.setTitle("Categorías de Taqueria Los Inges");
    }

    public TableView<Categoria_DAO> getTableView() {
        return tbvCategorias;
    }

    private void CrearUI() {
        ImageView imvEmp = new ImageView(
                getClass().getResource("/imagenes/cat.jpg").toString()
        );
        tbvCategorias = new TableView<>();

        imvEmp.setFitHeight(50);
        imvEmp.setFitWidth(50);
        Button btnAgregarCategoria = new Button();
        btnAgregarCategoria.setOnAction(event -> new Categoria_Form(tbvCategorias, null));
        btnAgregarCategoria.setPrefSize(30, 30);
        btnAgregarCategoria.setGraphic(imvEmp);

        CrearTabla();

        BorderPane bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(btnAgregarCategoria);
        bpnPrincipal.setCenter(tbvCategorias);

        Panel pnlPrincipal = new Panel("Categorías de Taquería");
        pnlPrincipal.getStyleClass().add("panel-info");
        pnlPrincipal.setBody(bpnPrincipal);

        Scene escena = new Scene(pnlPrincipal, 600, 400); // Ajusta el tamaño según sea necesario
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        this.setScene(escena);
        this.show();
    }

    private void CrearTabla() {
        Categoria_DAO objCategoria = new Categoria_DAO();

        TableColumn<Categoria_DAO, Integer> tbcIdCategoria = new TableColumn<>("ID");
        tbcIdCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        TableColumn<Categoria_DAO, String> tbcNombreCategoria = new TableColumn<>("Nombre");
        tbcNombreCategoria.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Categoria_DAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<Categoria_DAO, String>, TableCell<Categoria_DAO, String>>() {
                    @Override
                    public TableCell<Categoria_DAO, String> call(TableColumn<Categoria_DAO, String> param) {
                        return new Categoria_Button_Cell(1);
                    }
                }
        );

        TableColumn<Categoria_DAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<Categoria_DAO, String>, TableCell<Categoria_DAO, String>>() {
                    @Override
                    public TableCell<Categoria_DAO, String> call(TableColumn<Categoria_DAO, String> param) {
                        return new Categoria_Button_Cell(2);
                    }
                }
        );

        tbvCategorias.getColumns().addAll(tbcIdCategoria, tbcNombreCategoria, tbcEditar, tbcEliminar);
        tbvCategorias.setItems(objCategoria.CONSULTAR());
    }
}

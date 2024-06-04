package com.example.tap_intellij.Vistas.Taqueria.Tablas;

import com.example.tap_intellij.Componentes.Taqueria.Producto_Button_Cell;
import com.example.tap_intellij.Modelos.Taqueria.Producto_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Producto_Form;
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

public class Producto_Taqueria extends Stage {
    private TableView<Producto_DAO> tbvProductos;

    public Producto_Taqueria() {
        CrearUI();
        this.setTitle("Productos de Taqueria Los Inges");
    }

    public TableView<Producto_DAO> getTableView() {
        return tbvProductos;
    }

    private void CrearUI() {
        ImageView imvEmp = new ImageView(
                getClass().getResource("/imagenes/Productos.jpg").toString()
        );
        tbvProductos = new TableView<>();

        imvEmp.setFitHeight(50);
        imvEmp.setFitWidth(50);
        Button btnAgregarProducto = new Button();
        btnAgregarProducto.setOnAction(event -> new Producto_Form(tbvProductos, null));
        btnAgregarProducto.setPrefSize(30, 30);
        btnAgregarProducto.setGraphic(imvEmp);

        CrearTabla();

        BorderPane bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(btnAgregarProducto);
        bpnPrincipal.setCenter(tbvProductos);

        Panel pnlPrincipal = new Panel("Productos de Taquería");
        pnlPrincipal.getStyleClass().add("panel-info");
        pnlPrincipal.setBody(bpnPrincipal);

        Scene escena = new Scene(pnlPrincipal, 800, 600); // Ajusta el tamaño según sea necesario
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        this.setScene(escena);
        this.show();
    }

    private void CrearTabla() {
        Producto_DAO objProducto = new Producto_DAO();

        TableColumn<Producto_DAO, Integer> tbcIdProducto = new TableColumn<>("ID");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));

        TableColumn<Producto_DAO, String> tbcNombreProducto = new TableColumn<>("Nombre");
        tbcNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Producto_DAO, Double> tbcPrecioProducto = new TableColumn<>("Precio");
        tbcPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<Producto_DAO, Double> tbcCostoProducto = new TableColumn<>("Costo");
        tbcCostoProducto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        TableColumn<Producto_DAO, Integer> tbcIdCategoria = new TableColumn<>("ID Categoría");
        tbcIdCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        TableColumn<Producto_DAO, Integer> tbcIdPromocion = new TableColumn<>("ID Promoción");
        tbcIdPromocion.setCellValueFactory(new PropertyValueFactory<>("idPromocion"));

        TableColumn<Producto_DAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<Producto_DAO, String>, TableCell<Producto_DAO, String>>() {
                    @Override
                    public TableCell<Producto_DAO, String> call(TableColumn<Producto_DAO, String> param) {
                        return new Producto_Button_Cell(1);
                    }
                }
        );

        TableColumn<Producto_DAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<Producto_DAO, String>, TableCell<Producto_DAO, String>>() {
                    @Override
                    public TableCell<Producto_DAO, String> call(TableColumn<Producto_DAO, String> param) {
                        return new Producto_Button_Cell(2);
                    }
                }
        );

        tbvProductos.getColumns().addAll(tbcIdProducto, tbcNombreProducto, tbcPrecioProducto, tbcCostoProducto,
                tbcIdCategoria, tbcIdPromocion, tbcEditar, tbcEliminar);
        tbvProductos.setItems(objProducto.CONSULTAR());
    }
}

package com.example.tap_intellij.Vistas.Taqueria.Tablas;

import com.example.tap_intellij.Modelos.Taqueria.Orden_detalle_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Orden_detalle_Form;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Orden_detalle_Taqueria extends Stage {
    private TableView<Orden_detalle_DAO> tablaDetalleOrden;
    private Button btnAgregarDetalle;
    private Button btnEliminarDetalle;
    private VBox vBoxPrincipal;
    private ObservableList<Orden_detalle_DAO> detalles;

    public Orden_detalle_Taqueria(ObservableList<Orden_detalle_DAO> detalles) {
        this.detalles = detalles;
        crearUI(detalles);
        this.setTitle("Detalle de Orden");
        this.setScene(new Scene(vBoxPrincipal, 600, 400));
        this.show();
    }

    private void crearUI(ObservableList<Orden_detalle_DAO> detalles) {
        tablaDetalleOrden = new TableView<>();
        tablaDetalleOrden.setItems(detalles);
        TableColumn<Orden_detalle_DAO, Integer> colIdOrden = new TableColumn<>("ID Orden");
        colIdOrden.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
        TableColumn<Orden_detalle_DAO, Integer> colIdProducto = new TableColumn<>("ID Producto");
        colIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        TableColumn<Orden_detalle_DAO, Integer> colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        TableColumn<Orden_detalle_DAO, Double> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        TableColumn<Orden_detalle_DAO, Integer> colIdPromocion = new TableColumn<>("ID Promoción");
        colIdPromocion.setCellValueFactory(new PropertyValueFactory<>("idPromocion"));
        TableColumn<Orden_detalle_DAO, Integer> colIdMesa = new TableColumn<>("ID Mesa");
        colIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        tablaDetalleOrden.getColumns().addAll(colIdOrden, colIdProducto, colCantidad, colPrecio, colIdPromocion, colIdMesa);
        btnAgregarDetalle = new Button("Agregar Detalle");
        btnEliminarDetalle = new Button("Eliminar Detalle");
        btnAgregarDetalle.setOnAction(event -> agregarDetalle());
        btnEliminarDetalle.setOnAction(event -> eliminarDetalle());
        HBox hBoxBotones = new HBox(10);
        hBoxBotones.getChildren().addAll(btnAgregarDetalle, btnEliminarDetalle);
        hBoxBotones.setAlignment(Pos.CENTER);
        vBoxPrincipal = new VBox(10);
        vBoxPrincipal.setPadding(new Insets(10));
        vBoxPrincipal.getChildren().addAll(tablaDetalleOrden, hBoxBotones);
    }

    private void agregarDetalle() {
        new Orden_detalle_Form(detalles);
    }

    private void eliminarDetalle() {
        Orden_detalle_DAO detalleSeleccionado = tablaDetalleOrden.getSelectionModel().getSelectedItem();
        if (detalleSeleccionado != null) {
            detalles.remove(detalleSeleccionado);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("No se seleccionó ningún detalle");
            alert.setContentText("Por favor, seleccione un detalle para eliminar.");
            alert.showAndWait();
        }
    }
}

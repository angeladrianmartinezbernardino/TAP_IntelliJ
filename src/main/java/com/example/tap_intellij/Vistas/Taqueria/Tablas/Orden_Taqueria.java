package com.example.tap_intellij.Vistas.Taqueria.Tablas;

import com.example.tap_intellij.Componentes.Taqueria.Ordenes_Button_Cell;
import com.example.tap_intellij.Modelos.Taqueria.Ordenes_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Ordenes_Form;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Orden_Taqueria extends Stage {
    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private TableView<Ordenes_DAO> tbvOrdenes;
    private Button btnAgregarOrden;

    public Orden_Taqueria() {
        CrearUI();
        this.setTitle("Órdenes");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        ImageView imvOrd = new ImageView(getClass().getResource("/Imagenes/Taqueria/Empleado.png").toString());
        imvOrd.setFitHeight(50);
        imvOrd.setFitWidth(50);
        btnAgregarOrden = new Button();
        btnAgregarOrden.setOnAction(event -> new Ordenes_Form(tbvOrdenes, null));
        btnAgregarOrden.setPrefSize(50, 50);
        btnAgregarOrden.setGraphic(imvOrd);
        tlbMenu = new ToolBar(btnAgregarOrden);
        CrearTable();
        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tbvOrdenes);
        pnlPrincipal = new Panel("Taqueria");
        pnlPrincipal.getStyleClass().add("panel-primary");
        pnlPrincipal.setBody(bpnPrincipal);
        escena = new Scene(pnlPrincipal, 700, 400);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }

    private void CrearTable() {
        Ordenes_DAO objOrd = new Ordenes_DAO();
        tbvOrdenes = new TableView<Ordenes_DAO>();
        TableColumn<Ordenes_DAO, String> tbcidOrden = new TableColumn<>("ID Orden");
        tbcidOrden.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
        TableColumn<Ordenes_DAO, String> tbcidEmpleado = new TableColumn<>("ID Empleado");
        tbcidEmpleado.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        TableColumn<Ordenes_DAO, String> tbcfecha = new TableColumn<>("Fecha");
        tbcfecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        TableColumn<Ordenes_DAO, Float> tbcobservaciones = new TableColumn<>("Observaciones");
        tbcobservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        TableColumn<Ordenes_DAO, String> tbcidMesa = new TableColumn<>("ID Mesa");
        tbcidMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        TableColumn<Ordenes_DAO, String> tbcidUsuario = new TableColumn<>("ID Usuario");
        tbcidUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        //1
        TableColumn<Ordenes_DAO, String> tbcEditar = new TableColumn<Ordenes_DAO, String>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<Ordenes_DAO, String>, TableCell<Ordenes_DAO, String>>() {
            @Override
            public TableCell<Ordenes_DAO, String> call(TableColumn<Ordenes_DAO, String> param) {
                return new Ordenes_Button_Cell(1);
            }
        });
        //2
        TableColumn<Ordenes_DAO, String> tbcEliminar = new TableColumn<Ordenes_DAO, String>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<Ordenes_DAO, String>, TableCell<Ordenes_DAO, String>>() {
            @Override
            public TableCell<Ordenes_DAO, String> call(TableColumn<Ordenes_DAO, String> param) {
                return new Ordenes_Button_Cell(2);
            }
        });
        tbvOrdenes.getColumns().addAll(tbcidOrden, tbcidEmpleado, tbcfecha, tbcobservaciones, tbcidMesa, tbcidUsuario, tbcEditar, tbcEliminar);
        tbvOrdenes.setItems(objOrd.Consultar());
    }
}

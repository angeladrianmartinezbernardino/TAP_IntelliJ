package com.example.tap_intellij.Vistas.Taqueria.Tablas;

import com.example.tap_intellij.Componentes.Taqueria.Orden_Button_Cell;
import com.example.tap_intellij.Modelos.Taqueria.Orden_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Orden_Form;
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
    private TableView<Orden_DAO> tbvOrdenes;
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
        btnAgregarOrden.setOnAction(event -> new Orden_Form(tbvOrdenes, null));
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
        Orden_DAO objOrd = new Orden_DAO();
        tbvOrdenes = new TableView<Orden_DAO>();
        TableColumn<Orden_DAO, String> tbcidOrden = new TableColumn<>("ID Orden");
        tbcidOrden.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
        TableColumn<Orden_DAO, String> tbcidEmpleado = new TableColumn<>("ID Empleado");
        tbcidEmpleado.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        TableColumn<Orden_DAO, String> tbcfecha = new TableColumn<>("Fecha");
        tbcfecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        TableColumn<Orden_DAO, Float> tbcobservaciones = new TableColumn<>("Observaciones");
        tbcobservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        TableColumn<Orden_DAO, String> tbcidMesa = new TableColumn<>("ID Mesa");
        tbcidMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        TableColumn<Orden_DAO, String> tbcidUsuario = new TableColumn<>("ID Usuario");
        tbcidUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        //1
        TableColumn<Orden_DAO, String> tbcEditar = new TableColumn<Orden_DAO, String>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<Orden_DAO, String>, TableCell<Orden_DAO, String>>() {
            @Override
            public TableCell<Orden_DAO, String> call(TableColumn<Orden_DAO, String> param) {
                return new Orden_Button_Cell(1);
            }
        });
        //2
        TableColumn<Orden_DAO, String> tbcEliminar = new TableColumn<Orden_DAO, String>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<Orden_DAO, String>, TableCell<Orden_DAO, String>>() {
            @Override
            public TableCell<Orden_DAO, String> call(TableColumn<Orden_DAO, String> param) {
                return new Orden_Button_Cell(2);
            }
        });
        tbvOrdenes.getColumns().addAll(tbcidOrden, tbcidEmpleado, tbcfecha, tbcobservaciones, tbcidMesa, tbcidUsuario, tbcEditar, tbcEliminar);
        tbvOrdenes.setItems(objOrd.Consultar());
    }
}

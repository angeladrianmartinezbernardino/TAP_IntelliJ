package com.example.tap_intellij.Vistas.Taqueria.Tablas;

import com.example.tap_intellij.Componentes.Taqueria.Servicio_domicilio_Button_Cell;
import com.example.tap_intellij.Modelos.Taqueria.Servicio_domicilio_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Servicio_domicilio_Form;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Servicio_domicilio_Taqueria extends Stage {
    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private Button btnAgregarServicio;
    private TableView<Servicio_domicilio_DAO> tbvServicios;

    public Servicio_domicilio_Taqueria() {
        CrearUI();
        this.setTitle("Servicio de Domicilio de Taquería Los Inges");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        ImageView imvEmp = new ImageView(
                getClass().getResource("/Imagenes/servicio.jpg").toString()
        );
        tbvServicios = new TableView<>();

        imvEmp.setFitHeight(50);
        imvEmp.setFitWidth(50);
        btnAgregarServicio = new Button();
        btnAgregarServicio.setOnAction(event -> new Servicio_domicilio_Form(tbvServicios, null));
        btnAgregarServicio.setPrefSize(30, 30);
        btnAgregarServicio.setGraphic(imvEmp);
        tlbMenu = new ToolBar(btnAgregarServicio);

        CrearTabla();

        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tbvServicios);
        pnlPrincipal = new Panel("Servicio de Domicilio de Taquería");
        pnlPrincipal.getStyleClass().add("panel-info");
        pnlPrincipal.setBody(bpnPrincipal);
        escena = new Scene(pnlPrincipal, 600, 400);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }

    private void CrearTabla() {
        Servicio_domicilio_DAO objServicio = new Servicio_domicilio_DAO();
        tbvServicios = new TableView<>();

        TableColumn<Servicio_domicilio_DAO, Integer> tbcIdServicio = new TableColumn<>("ID");
        tbcIdServicio.setCellValueFactory(new PropertyValueFactory<>("idServicio"));

        TableColumn<Servicio_domicilio_DAO, String> tbcFecha = new TableColumn<>("Fecha");
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<Servicio_domicilio_DAO, String> tbcDireccionEntrega = new TableColumn<>("Dirección de Entrega");
        tbcDireccionEntrega.setCellValueFactory(new PropertyValueFactory<>("direccionEntrega"));

        TableColumn<Servicio_domicilio_DAO, Double> tbcCosto = new TableColumn<>("Costo");
        tbcCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        TableColumn<Servicio_domicilio_DAO, String> tbcObservaciones = new TableColumn<>("Observaciones");
        tbcObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));

        TableColumn<Servicio_domicilio_DAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<Servicio_domicilio_DAO, String>, TableCell<Servicio_domicilio_DAO, String>>() {
                    @Override
                    public TableCell<Servicio_domicilio_DAO, String> call(TableColumn<Servicio_domicilio_DAO, String> param) {
                        return new Servicio_domicilio_Button_Cell(1);
                    }
                }
        );

        TableColumn<Servicio_domicilio_DAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<Servicio_domicilio_DAO, String>, TableCell<Servicio_domicilio_DAO, String>>() {
                    @Override
                    public TableCell<Servicio_domicilio_DAO, String> call(TableColumn<Servicio_domicilio_DAO, String> param) {
                        return new Servicio_domicilio_Button_Cell(2);
                    }
                }
        );

        tbvServicios.getColumns().addAll(tbcIdServicio, tbcFecha, tbcDireccionEntrega,
                tbcCosto, tbcObservaciones, tbcEditar, tbcEliminar);
        tbvServicios.setItems(objServicio.CONSULTAR());
    }
}


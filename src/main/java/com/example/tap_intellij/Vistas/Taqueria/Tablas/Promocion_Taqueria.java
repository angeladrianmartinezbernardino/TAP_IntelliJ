package com.example.tap_intellij.Vistas.Taqueria.Tablas;

import com.example.tap_intellij.Componentes.Taqueria.Promocion_Button_Cell;
import com.example.tap_intellij.Modelos.Taqueria.Promocion_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Promocion_Form;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Promocion_Taqueria extends Stage {
    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private Button btnAgregarPromocion;
    private TableView<Promocion_DAO> tbvPromociones;

    public Promocion_Taqueria() {
        CrearUI();
        this.setTitle("Promociones de Taqueria Los Inges");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        ImageView imvEmp = new ImageView(
                getClass().getResource("/imagenes/promo.jpg").toString()
        );
        tbvPromociones = new TableView<>();

        imvEmp.setFitHeight(50);
        imvEmp.setFitWidth(50);
        btnAgregarPromocion = new Button();
        btnAgregarPromocion.setOnAction(event -> new Promocion_Form(tbvPromociones, null));
        btnAgregarPromocion.setPrefSize(30, 30);
        btnAgregarPromocion.setGraphic(imvEmp);
        tlbMenu = new ToolBar(btnAgregarPromocion);

        CrearTabla();

        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tbvPromociones);
        pnlPrincipal = new Panel("Promociones de Taquería");
        pnlPrincipal.getStyleClass().add("panel-info");
        pnlPrincipal.setBody(bpnPrincipal);
        escena = new Scene(pnlPrincipal, 600, 400);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }

    private void CrearTabla() {
        Promocion_DAO objPromocion = new Promocion_DAO();
        tbvPromociones = new TableView<>();

        TableColumn<Promocion_DAO, Integer> tbcIdPromocion = new TableColumn<>("ID");
        tbcIdPromocion.setCellValueFactory(new PropertyValueFactory<>("idPromocion"));

        TableColumn<Promocion_DAO, String> tbcNombrePromocion = new TableColumn<>("Nombre");
        tbcNombrePromocion.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Promocion_DAO, String> tbcDescripcionPromocion = new TableColumn<>("Descripción");
        tbcDescripcionPromocion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<Promocion_DAO, Double> tbcCostoPromocion = new TableColumn<>("Costo");
        tbcCostoPromocion.setCellValueFactory(new PropertyValueFactory<>("costoPromo"));

        TableColumn<Promocion_DAO, String> tbcFechaInicio = new TableColumn<>("Fecha Inicio");
        tbcFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));

        TableColumn<Promocion_DAO, String> tbcFechaFin = new TableColumn<>("Fecha Fin");
        tbcFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));

        TableColumn<Promocion_DAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<Promocion_DAO, String>, TableCell<Promocion_DAO, String>>() {
                    @Override
                    public TableCell<Promocion_DAO, String> call(TableColumn<Promocion_DAO, String> param) {
                        return new Promocion_Button_Cell(1);
                    }
                }
        );

        TableColumn<Promocion_DAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<Promocion_DAO, String>, TableCell<Promocion_DAO, String>>() {
                    @Override
                    public TableCell<Promocion_DAO, String> call(TableColumn<Promocion_DAO, String> param) {
                        return new Promocion_Button_Cell(2);
                    }
                }
        );

        tbvPromociones.getColumns().addAll(tbcIdPromocion, tbcNombrePromocion, tbcDescripcionPromocion,
                tbcCostoPromocion, tbcFechaInicio, tbcFechaFin, tbcEditar, tbcEliminar);
        tbvPromociones.setItems(objPromocion.CONSULTAR());
    }
}

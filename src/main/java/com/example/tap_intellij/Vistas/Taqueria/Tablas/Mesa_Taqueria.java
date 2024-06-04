package com.example.tap_intellij.Vistas.Taqueria.Tablas;

import com.example.tap_intellij.Componentes.Taqueria.Mesa_Button_Cell;
import com.example.tap_intellij.Modelos.Taqueria.Mesa_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Mesa_Form;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Mesa_Taqueria extends Stage {
    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private TableView<Mesa_DAO> tbvMesas;
    private Button btnAgregarMesa;

    public Mesa_Taqueria() {
        CrearUI();
        this.setTitle("Mesas");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        ImageView imvMes = new ImageView(getClass().getResource("/Imagenes/Taqueria/Empleado.png").toString());
        imvMes.setFitHeight(50);
        imvMes.setFitWidth(50);
        btnAgregarMesa = new Button();
        btnAgregarMesa.setOnAction(event -> new Mesa_Form(tbvMesas, null));
        btnAgregarMesa.setPrefSize(50, 50);
        btnAgregarMesa.setGraphic(imvMes);
        tlbMenu = new ToolBar(btnAgregarMesa);
        CrearTable();
        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tbvMesas);
        pnlPrincipal = new Panel("Taqueria");
        pnlPrincipal.getStyleClass().add("panel-primary");
        pnlPrincipal.setBody(bpnPrincipal);
        escena = new Scene(pnlPrincipal, 700, 400);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }

    private void CrearTable() {
        Mesa_DAO objMes = new Mesa_DAO();
        tbvMesas = new TableView<Mesa_DAO>();
        TableColumn<Mesa_DAO, String> tbcidMesa = new TableColumn<>("ID Mesa");
        tbcidMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        TableColumn<Mesa_DAO, String> tbcnumero = new TableColumn<>("Numero de mesa");
        tbcnumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        //1
        TableColumn<Mesa_DAO, String> tbcEditar = new TableColumn<Mesa_DAO, String>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<Mesa_DAO, String>, TableCell<Mesa_DAO, String>>() {
            @Override
            public TableCell<Mesa_DAO, String> call(TableColumn<Mesa_DAO, String> param) {
                return new Mesa_Button_Cell(1);
            }
        });
        //2
        TableColumn<Mesa_DAO, String> tbcEliminar = new TableColumn<Mesa_DAO, String>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<Mesa_DAO, String>, TableCell<Mesa_DAO, String>>() {
            @Override
            public TableCell<Mesa_DAO, String> call(TableColumn<Mesa_DAO, String> param) {
                return new Mesa_Button_Cell(2);
            }
        });
        tbvMesas.getColumns().addAll(tbcidMesa, tbcnumero, tbcEditar, tbcEliminar);
        tbvMesas.setItems(objMes.Consultar());
    }
}

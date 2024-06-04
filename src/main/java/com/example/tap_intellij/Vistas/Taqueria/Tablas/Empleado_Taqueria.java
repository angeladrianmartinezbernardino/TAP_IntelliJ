package com.example.tap_intellij.Vistas.Taqueria.Tablas;

import com.example.tap_intellij.Componentes.Taqueria.Empleado_Button_Cell;
import com.example.tap_intellij.Modelos.Taqueria.Empleado_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Empleado_Form;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Empleado_Taqueria extends Stage {
    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private TableView<Empleado_DAO> tbvEmpleados;
    private Button btnAgregarEmpleado;

    public Empleado_Taqueria() {
        CrearUI();
        this.setTitle("Empleados");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        ImageView imvEmp = new ImageView(getClass().getResource("/Imagenes/Taqueria/Empleado.png").toString());
        imvEmp.setFitHeight(50);
        imvEmp.setFitWidth(50);
        btnAgregarEmpleado = new Button();
        btnAgregarEmpleado.setOnAction(event -> new Empleado_Form(tbvEmpleados, null));
        btnAgregarEmpleado.setPrefSize(50, 50);
        btnAgregarEmpleado.setGraphic(imvEmp);
        tlbMenu = new ToolBar(btnAgregarEmpleado);
        CrearTable();
        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tbvEmpleados);
        pnlPrincipal = new Panel("Taqueria");
        pnlPrincipal.getStyleClass().add("panel-primary");
        pnlPrincipal.setBody(bpnPrincipal);
        escena = new Scene(pnlPrincipal, 700, 400);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }

    private void CrearTable() {
        Empleado_DAO objEmp = new Empleado_DAO();
        tbvEmpleados = new TableView<Empleado_DAO>();
        TableColumn<Empleado_DAO, String> tbcidEmpleado = new TableColumn<>("ID Empleado");
        tbcidEmpleado.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        TableColumn<Empleado_DAO, String> tbcnomEmpleado = new TableColumn<>("Empleado");
        tbcnomEmpleado.setCellValueFactory(new PropertyValueFactory<>("nomEmpleado"));
        TableColumn<Empleado_DAO, String> tbcRFCEmpleado = new TableColumn<>("RFC");
        tbcRFCEmpleado.setCellValueFactory(new PropertyValueFactory<>("RFCEmpleado"));
        TableColumn<Empleado_DAO, Float> tbcsueldo = new TableColumn<>("Sueldo");
        tbcsueldo.setCellValueFactory(new PropertyValueFactory<>("salario"));
        TableColumn<Empleado_DAO, String> tbctelefono = new TableColumn<>("Telefono");
        tbctelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        TableColumn<Empleado_DAO, String> tbcdireccion = new TableColumn<>("Direccion");
        tbcdireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        //1
        TableColumn<Empleado_DAO, String> tbcEditar = new TableColumn<Empleado_DAO, String>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<Empleado_DAO, String>, TableCell<Empleado_DAO, String>>() {
            @Override
            public TableCell<Empleado_DAO, String> call(TableColumn<Empleado_DAO, String> param) {
                return new Empleado_Button_Cell(1);
            }
        });
        //2
        TableColumn<Empleado_DAO, String> tbcEliminar = new TableColumn<Empleado_DAO, String>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<Empleado_DAO, String>, TableCell<Empleado_DAO, String>>() {
            @Override
            public TableCell<Empleado_DAO, String> call(TableColumn<Empleado_DAO, String> param) {
                return new Empleado_Button_Cell(2);
            }
        });
        tbvEmpleados.getColumns().addAll(tbcidEmpleado, tbcnomEmpleado, tbcRFCEmpleado, tbcsueldo, tbctelefono, tbcdireccion, tbcEditar, tbcEliminar);
        tbvEmpleados.setItems(objEmp.Consultar());
    }
}

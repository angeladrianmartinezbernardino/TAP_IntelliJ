package com.example.tap_intellij.Vistas.Taqueria;

import com.example.tap_intellij.Componentes.Usuarios_Button_Cell;
import com.example.tap_intellij.Modelos.Usuarios_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Usuarios_Form;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Usuario_Taqueria extends Stage {
    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private TableView<Usuarios_DAO> tbvUsuarios;
    private Button btnAgregarUsuario;

    public Usuario_Taqueria() {
        CrearUI();
        this.setTitle("Usuarios");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        ImageView imvUsu = new ImageView(getClass().getResource("/Imagenes/Taqueria/Empleado.png").toString());
        imvUsu.setFitHeight(50);
        imvUsu.setFitWidth(50);
        btnAgregarUsuario = new Button();
        btnAgregarUsuario.setOnAction(event -> new Usuarios_Form(tbvUsuarios, null));
        btnAgregarUsuario.setPrefSize(50, 50);
        btnAgregarUsuario.setGraphic(imvUsu);
        tlbMenu = new ToolBar(btnAgregarUsuario);
        CrearTable();
        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tbvUsuarios);
        pnlPrincipal = new Panel("Taqueria");
        pnlPrincipal.getStyleClass().add("panel-primary");
        pnlPrincipal.setBody(bpnPrincipal);
        escena = new Scene(pnlPrincipal, 700, 400);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }

    private void CrearTable() {
        Usuarios_DAO objUsu = new Usuarios_DAO();
        tbvUsuarios = new TableView<Usuarios_DAO>();
        TableColumn<Usuarios_DAO, String> tbcidUsuario = new TableColumn<>("ID Usuario");
        tbcidUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        TableColumn<Usuarios_DAO, String> tbcnombre = new TableColumn<>("Nombre");
        tbcnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Usuarios_DAO, String> tbccontraseña = new TableColumn<>("Contraseña");
        tbccontraseña.setCellValueFactory(new PropertyValueFactory<>("contraseña"));
        TableColumn<Usuarios_DAO, Float> tbcrol = new TableColumn<>("Rol");
        tbcrol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        //1
        TableColumn<Usuarios_DAO, String> tbcEditar = new TableColumn<Usuarios_DAO, String>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<Usuarios_DAO, String>, TableCell<Usuarios_DAO, String>>() {
            @Override
            public TableCell<Usuarios_DAO, String> call(TableColumn<Usuarios_DAO, String> param) {
                return new Usuarios_Button_Cell(1);
            }
        });
        //2
        TableColumn<Usuarios_DAO, String> tbcEliminar = new TableColumn<Usuarios_DAO, String>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<Usuarios_DAO, String>, TableCell<Usuarios_DAO, String>>() {
            @Override
            public TableCell<Usuarios_DAO, String> call(TableColumn<Usuarios_DAO, String> param) {
                return new Usuarios_Button_Cell(2);
            }
        });
        tbvUsuarios.getColumns().addAll(tbcidUsuario, tbcnombre, tbccontraseña, tbcrol, tbcEditar, tbcEliminar);
        tbvUsuarios.setItems(objUsu.Consultar());
    }
}

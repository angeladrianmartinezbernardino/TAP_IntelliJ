package com.example.tap_intellij.Componentes;

import com.example.tap_intellij.Modelos.Usuarios_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Usuarios_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Usuarios_Button_Cell extends TableCell<Usuarios_DAO, String> {
    Button btnCelda;
    int opc;
    Usuarios_DAO objUsu;

    public Usuarios_Button_Cell(int opc) {
        this.opc = opc;
        String txtButton = (opc == 1) ? "Editar" : "Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<Usuarios_DAO> tbvUsuarios = Usuarios_Button_Cell.this.getTableView();
        objUsu = tbvUsuarios.getItems().get(Usuarios_Button_Cell.this.getIndex());
        if (opc == 1) {
            new Usuarios_Form(tbvUsuarios, objUsu);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar el usuario? " + objUsu.getNombre());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                objUsu.Eliminar();
                tbvUsuarios.setItems((objUsu.Consultar()));
                tbvUsuarios.refresh();
            }
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            this.setGraphic(btnCelda);
        }
    }
}
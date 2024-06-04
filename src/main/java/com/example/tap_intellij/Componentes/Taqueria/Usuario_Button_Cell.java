package com.example.tap_intellij.Componentes.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Usuario_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Usuario_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Usuario_Button_Cell extends TableCell<Usuario_DAO, String> {
    Button btnCelda;
    int opc;
    Usuario_DAO objUsu;

    public Usuario_Button_Cell(int opc) {
        this.opc = opc;
        String txtButton = (opc == 1) ? "Editar" : "Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<Usuario_DAO> tbvUsuarios = Usuario_Button_Cell.this.getTableView();
        objUsu = tbvUsuarios.getItems().get(Usuario_Button_Cell.this.getIndex());
        if (opc == 1) {
            new Usuario_Form(tbvUsuarios, objUsu);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar el usuario " + objUsu.getNombre() + "?");
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
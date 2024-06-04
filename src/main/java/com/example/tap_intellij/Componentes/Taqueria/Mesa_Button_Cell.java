package com.example.tap_intellij.Componentes.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Mesa_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Mesa_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Mesa_Button_Cell extends TableCell<Mesa_DAO, String> {
    Button btnCelda;
    int opc;
    Mesa_DAO objMes;

    public Mesa_Button_Cell(int opc) {
        this.opc = opc;
        String txtButton = (opc == 1) ? "Editar" : "Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<Mesa_DAO> tbvMesas = Mesa_Button_Cell.this.getTableView();
        objMes = tbvMesas.getItems().get(Mesa_Button_Cell.this.getIndex());
        if (opc == 1) {
            new Mesa_Form(tbvMesas, objMes);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar la mesa? " + objMes.getNumero());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                objMes.Eliminar();
                tbvMesas.setItems((objMes.Consultar()));
                tbvMesas.refresh();
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
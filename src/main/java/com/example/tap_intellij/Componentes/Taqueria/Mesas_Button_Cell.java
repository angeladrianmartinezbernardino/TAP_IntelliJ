package com.example.tap_intellij.Componentes.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Mesas_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Mesas_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Mesas_Button_Cell extends TableCell<Mesas_DAO, String> {
    Button btnCelda;
    int opc;
    Mesas_DAO objMes;

    public Mesas_Button_Cell(int opc) {
        this.opc = opc;
        String txtButton = (opc == 1) ? "Editar" : "Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<Mesas_DAO> tbvMesas = Mesas_Button_Cell.this.getTableView();
        objMes = tbvMesas.getItems().get(Mesas_Button_Cell.this.getIndex());
        if (opc == 1) {
            new Mesas_Form(tbvMesas, objMes);
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
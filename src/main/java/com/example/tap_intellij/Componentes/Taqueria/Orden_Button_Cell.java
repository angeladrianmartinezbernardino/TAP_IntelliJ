package com.example.tap_intellij.Componentes.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Orden_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Orden_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Orden_Button_Cell extends TableCell<Orden_DAO, String> {
    Button btnCelda;
    int opc;
    Orden_DAO objOrd;

    public Orden_Button_Cell(int opc) {
        this.opc = opc;
        String txtButton = (opc == 1) ? "Editar" : "Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<Orden_DAO> tbvOrdenes = Orden_Button_Cell.this.getTableView();
        objOrd = tbvOrdenes.getItems().get(Orden_Button_Cell.this.getIndex());
        if (opc == 1) {
            new Orden_Form(tbvOrdenes, objOrd);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar la orden " + objOrd.getIdOrden() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                objOrd.Eliminar();
                tbvOrdenes.setItems((objOrd.Consultar()));
                tbvOrdenes.refresh();
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
package com.example.tap_intellij.Componentes;

import com.example.tap_intellij.Modelos.Ordenes_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Ordenes_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Ordenes_Button_Cell extends TableCell<Ordenes_DAO, String> {
    Button btnCelda;
    int opc;
    Ordenes_DAO objOrd;

    public Ordenes_Button_Cell(int opc) {
        this.opc = opc;
        String txtButton = (opc == 1) ? "Editar" : "Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<Ordenes_DAO> tbvOrdenes = Ordenes_Button_Cell.this.getTableView();
        objOrd = tbvOrdenes.getItems().get(Ordenes_Button_Cell.this.getIndex());
        if (opc == 1) {
            new Ordenes_Form(tbvOrdenes, objOrd);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar la orden? " + objOrd.getIdOrden());
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
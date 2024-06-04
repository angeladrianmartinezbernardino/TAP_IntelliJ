package com.example.tap_intellij.Componentes.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Promocion_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Promocion_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Promocion_Button_Cell extends TableCell<Promocion_DAO, String> {
    private Button button;
    private int opc;
    private Promocion_DAO objPromocion;

    public Promocion_Button_Cell(int opc) {
        this.opc = opc;
        String buttonText = (opc == 1) ? "Editar" : "Eliminar";
        button = new Button(buttonText);
        button.setOnAction(event -> handleButtonAction(opc));
    }

    private void handleButtonAction(int opc) {
        TableView<Promocion_DAO> tbvPromociones = Promocion_Button_Cell.this.getTableView();
        objPromocion = tbvPromociones.getItems().get(Promocion_Button_Cell.this.getIndex());
        if (opc == 1) {
            new Promocion_Form(tbvPromociones, objPromocion);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("Eliminar Promoción");
            alert.setContentText("¿Estás seguro de eliminar la promoción " + objPromocion.getNombre() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                objPromocion.ELIMINAR();
                tbvPromociones.setItems(objPromocion.CONSULTAR());
            }
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setGraphic(button);
        } else {
            setGraphic(null);
        }
    }
}

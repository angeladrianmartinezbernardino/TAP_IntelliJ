package com.example.tap_intellij.Componentes.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Servicio_domicilio_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Servicio_domicilio_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Servicio_domicilio_Button_Cell extends TableCell<Servicio_domicilio_DAO, String> {
    private Button button;
    private int opc;
    private Servicio_domicilio_DAO objServicio;

    public Servicio_domicilio_Button_Cell(int opc) {
        this.opc = opc;
        String buttonText = (opc == 1) ? "Editar" : "Eliminar";
        button = new Button(buttonText);
        button.setOnAction(event -> handleButtonAction(opc));
    }

    private void handleButtonAction(int opc) {
        TableView<Servicio_domicilio_DAO> tbvServicio = Servicio_domicilio_Button_Cell.this.getTableView();
        objServicio = tbvServicio.getItems().get(Servicio_domicilio_Button_Cell.this.getIndex());
        if (opc == 1) {
            new Servicio_domicilio_Form(tbvServicio, objServicio);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("Eliminar Servicio a Domicilio");
            alert.setContentText("¿Estás seguro de eliminar el servicio a domicilio?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                objServicio.ELIMINAR();
                tbvServicio.setItems(objServicio.CONSULTAR());
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

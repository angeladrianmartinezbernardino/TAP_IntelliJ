package com.example.tap_intellij.Componentes.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Empleado_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Empleado_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Empleado_Button_Cell extends TableCell<Empleado_DAO, String> {
    Button btnCelda;
    int opc;
    Empleado_DAO objMes;

    public Empleado_Button_Cell(int opc) {
        this.opc = opc;
        String txtButton = (opc == 1) ? "Editar" : "Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<Empleado_DAO> tbvEmpleados = Empleado_Button_Cell.this.getTableView();
        objMes = tbvEmpleados.getItems().get(Empleado_Button_Cell.this.getIndex());
        if (opc == 1) {
            new Empleado_Form(tbvEmpleados, objMes);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar el empleado " + objMes.getNomEmpleado() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                objMes.Eliminar();
                tbvEmpleados.setItems((objMes.Consultar()));
                tbvEmpleados.refresh();
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
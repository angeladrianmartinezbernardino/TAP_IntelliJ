package com.example.tap_intellij.Componentes;

import com.example.tap_intellij.Modelos.Empleados_DAO;
import com.example.tap_intellij.Vistas.Empleados_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Button_cell extends TableCell<Empleados_DAO, String> {
    Button btnCelda;
    int opc;
    Empleados_DAO objEmp;

    public Button_cell(int opc) {
        this.opc = opc;
        String txtButton = (opc == 1) ? "Editar" : "Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<Empleados_DAO> tbvEmpleados = Button_cell.this.getTableView();
        objEmp = tbvEmpleados.getItems().get(Button_cell.this.getIndex());
        if (opc == 1) {
            new Empleados_Form(tbvEmpleados, objEmp);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar el empleado?" + objEmp.getNombre_empleado());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                objEmp.Eliminar();
                tbvEmpleados.setItems((objEmp.Consultar()));
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

package com.example.tap_intellij.Componentes.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Producto_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Producto_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Producto_Button_Cell extends TableCell<Producto_DAO, String> {
    private Button button;
    private int opc;
    private Producto_DAO objProducto;

    public Producto_Button_Cell(int opc) {
        this.opc = opc;
        String buttonText = (opc == 1) ? "Editar" : "Eliminar";
        button = new Button(buttonText);
        button.setOnAction(event -> handleButtonAction(opc));
    }

    private void handleButtonAction(int opc) {
        TableView<Producto_DAO> tbvProductos = Producto_Button_Cell.this.getTableView();
        objProducto = tbvProductos.getItems().get(Producto_Button_Cell.this.getIndex());
        if (opc == 1) {
            // Código para editar
            new Producto_Form(tbvProductos, objProducto);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje de sistema");
            alert.setHeaderText("Confirmación");
            alert.setContentText("¿Desea borrar el producto " + objProducto.getNombre() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                objProducto.ELIMINAR();
                tbvProductos.setItems(objProducto.CONSULTAR());
                tbvProductos.refresh();
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

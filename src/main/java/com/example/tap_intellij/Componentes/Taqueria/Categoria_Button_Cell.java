package com.example.tap_intellij.Componentes.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Categoria_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Formularios.Categoria_Form;
import javafx.scene.control.*;

import java.util.Optional;

public class Categoria_Button_Cell extends TableCell<Categoria_DAO, String> {
    Button button;
    int opc;
    Categoria_DAO objCategoria;
    public Categoria_Button_Cell(int opc) {
        this.opc = opc;
        String buttonText = (opc == 1) ? "Editar" : "Eliminar";
        button = new Button(buttonText);
        button.setOnAction(event -> handleButtonAction(opc));
    }

    private void handleButtonAction(int opc) {
        TableView<Categoria_DAO> tbvCategoria = Categoria_Button_Cell.this.getTableView();
        objCategoria = tbvCategoria.getItems().get(Categoria_Button_Cell.this.getIndex());
        if (opc == 1) {
            //codigo editar
            new Categoria_Form(tbvCategoria, objCategoria);

        }else {
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("mensaje de sistema");
            alert.setHeaderText("CONFIRMACION");
            alert.setContentText("¿Desea borrar la categoria: "+objCategoria.getIdCategoria());
            Optional<ButtonType> result  = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                objCategoria.ELIMINAR();
                tbvCategoria.setItems(objCategoria.CONSULTAR());
                tbvCategoria.refresh();

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

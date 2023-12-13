package com.example.tap_intellij.Componentes;

import com.example.tap_intellij.Modelos.Categorías_DAO;
import com.example.tap_intellij.Vistas.Formulario_de_Categoría;
import javafx.scene.control.*;

import java.util.Optional;

public class Celda_del_botón extends TableCell<Categorías_DAO, String> {
    private Button btnCelda;
    private int opc;
    private TableView<Categorías_DAO> tbvCategorias;
    private Categorías_DAO objCat;

    public Celda_del_botón(int opc) {
        this.opc = opc;
        String txtBtn = this.opc == 1 ? "Editar" : "Eliminar";
        btnCelda = new Button("Editar");
        btnCelda.setOnAction(event -> Eliminar_categoría());
    }

    private void Eliminar_categoría() {
        tbvCategorias = Celda_del_botón.this.getTableView();
        objCat = tbvCategorias.getItems().get(Celda_del_botón.this.getIndex());
        if (this.opc == 1) {
            new Formulario_de_Categoría(tbvCategorias, objCat);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Tópicos Avanzados de Programación");
            alert.setHeaderText("Confirmación del sistema");
            alert.setContentText("¿Deseas eliminar la categoría?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                objCat.Eliminar();
                tbvCategorias.setItems(objCat.Enlistar_categorías());
                tbvCategorias.refresh();
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

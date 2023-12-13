package com.example.tap_intellij.Componentes;

import com.example.tap_intellij.Modelos.Categorías_DAO;
import com.example.tap_intellij.Vistas.Formulario_de_Categoría;
import javafx.scene.control.*;

import java.util.Optional;

public class Celda_del_botón extends TableCell<Categorías_DAO, String> {
    private Button Celda;
    private int Opción;
    private TableView<Categorías_DAO> tbvCategorias;
    private Categorías_DAO objCat;
    private Alert Alerta;
    private Optional<ButtonType> Resultado;

    public Celda_del_botón(int Opción) {
        this.Opción = Opción;
        String txtBtn = this.Opción == 1 ? "Editar" : "Eliminar";
        Celda = new Button("Editar");
        Celda.setOnAction(event -> Eliminar_categoría());
    }

    private void Eliminar_categoría() {
        tbvCategorias = Celda_del_botón.this.getTableView();
        objCat = tbvCategorias.getItems().get(Celda_del_botón.this.getIndex());
        if (this.Opción == 1) {
            new Formulario_de_Categoría(tbvCategorias, objCat);
        } else {
            Alerta = new Alert(Alert.AlertType.CONFIRMATION);
            Alerta.setTitle("Tópicos Avanzados de Programación");
            Alerta.setHeaderText("Confirmación del sistema");
            Alerta.setContentText("¿Deseas eliminar la categoría?");
            Resultado = Alerta.showAndWait();
            if (Resultado.get() == ButtonType.OK) {
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
            this.setGraphic(Celda);
        }
    }
}

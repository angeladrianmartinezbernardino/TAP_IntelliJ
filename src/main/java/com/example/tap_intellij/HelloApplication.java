package com.example.tap_intellij;

import com.example.tap_intellij.Vistas.Calculadora;
import com.example.tap_intellij.Vistas.Lotería;
import com.example.tap_intellij.Vistas.Restaurante;
import com.example.tap_intellij.Vistas.Pista_de_Atletismo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * Ángel Adrián Martínez Bernardino.
 *
 * Proyectos de Tópicos Avanzados de Programación.
 * */

public class HelloApplication extends Application {
    private Scene Escena;
    private BorderPane BP;
    private MenuBar MB_Barra_de_menu;
    private Menu Menu_Parcial_1, Menu_Parcial_2;
    private MenuItem MI_Calculadora, MI_Lotería, MI_Restaurante, MI_Pista_de_Atletismo;

    public static void main(String[] args) {
        launch();
    }

    private void CrearUI() {
        MI_Calculadora = new MenuItem("Calculadora");
        MI_Calculadora.setOnAction((event) -> new Calculadora());
        MI_Lotería = new MenuItem("Lotería");
        MI_Lotería.setOnAction((event) -> new Lotería());
        MI_Restaurante = new MenuItem("Restaurante");
        MI_Restaurante.setOnAction((event) -> new Restaurante());
        MI_Pista_de_Atletismo = new MenuItem("Pista Atletismo");
        MI_Pista_de_Atletismo.setOnAction((event) -> new Pista_de_Atletismo());
        Menu_Parcial_1 = new Menu("Parcial 1");
        Menu_Parcial_1.getItems().addAll(MI_Calculadora, MI_Lotería);
        Menu_Parcial_2 = new Menu("Parcial 2");
        MB_Barra_de_menu = new MenuBar(Menu_Parcial_1, Menu_Parcial_2);
    }

    @Override
    public void start(Stage stage) {
        CrearUI();
        BP = new BorderPane();
        BP.setTop(MB_Barra_de_menu);
        Escena = new Scene(BP, 200, 300);
        stage.setScene(Escena);
        stage.setMaximized(true);
        stage.show();
    }

    private void Salir() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensaje del sistema");
    }
}

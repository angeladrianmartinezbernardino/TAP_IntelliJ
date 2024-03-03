package com.example.tap_intellij;

import com.example.tap_intellij.Modelos.Conexion;
import com.example.tap_intellij.Vistas.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * De Ángel Adrián Martínez Bernardino.
 *
 * Proyectos de Tópicos Avanzados de Programación.
 * */

public class HelloApplication extends Application {
    private Scene Escena;
    private BorderPane BP;
    private MenuBar MB_Barra_de_menu;
    private Menu Menu_Parcial_1, Menu_Parcial_2;
    private MenuItem MI_Calculadora, MI_Loteria, MI_Restaurante, MI_Pista_de_atletismo, MI_Simulador_de_impresion, MI_Cuadro_magico;
    private Alert Alerta;

    public static void main(String[] args) {
        launch();
    }

    private void Crear_UI() {
        MI_Calculadora = new MenuItem("Calculadora");
        MI_Calculadora.setOnAction((event) -> new Calculadora());
        MI_Cuadro_magico = new MenuItem("Cuadro mágico");
        MI_Cuadro_magico.setOnAction((event) -> new Cuadro_magico());
        MI_Loteria = new MenuItem("Loteria");
        MI_Loteria.setOnAction((event) -> new Loteria());
        MI_Restaurante = new MenuItem("Restaurante");
        MI_Restaurante.setOnAction((event) -> new Restaurante());
        MI_Pista_de_atletismo = new MenuItem("Pista Atletismo");
        MI_Pista_de_atletismo.setOnAction((event) -> new Pista_de_atletismo());
        MI_Simulador_de_impresion = new MenuItem("Simulador de impresión");
        MI_Simulador_de_impresion.setOnAction((event) -> new Simulador_de_impresion());
        Menu_Parcial_1 = new Menu("Parcial 1");
        Menu_Parcial_1.getItems().addAll(MI_Calculadora, MI_Loteria, MI_Cuadro_magico);
        Menu_Parcial_2 = new Menu("Parcial 2");
        Menu_Parcial_2.getItems().addAll(MI_Restaurante, MI_Pista_de_atletismo, MI_Simulador_de_impresion);
        MB_Barra_de_menu = new MenuBar(Menu_Parcial_1, Menu_Parcial_2);
    }

    @Override
    public void start(Stage stage) {
        Conexión_a_la_base_de_datos();
        Crear_UI();
        BP = new BorderPane();
        BP.setTop(MB_Barra_de_menu);
        Escena = new Scene(BP, 200, 300);
        Escena.getStylesheets().add(getClass().getResource("/Estilos/Main.css").toString());
        stage.setScene(Escena);
        stage.setMaximized(true);
        stage.show();
    }

    private void Salir() {
        Alerta = new Alert(Alert.AlertType.CONFIRMATION);
        Alerta.setTitle("Mensaje del sistema");
    }

    public void Conexión_a_la_base_de_datos() {
        Conexion.Crear_conexión_a_la_base_de_datos();
        System.out.println("Conexion establecida.");
    }
}

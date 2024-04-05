package com.example.tap_intellij;

import com.example.tap_intellij.Modelos.Conexion;
import com.example.tap_intellij.Vistas.*;
import com.example.tap_intellij.Vistas.Taqueria.Empleado_Taqueria;
import com.example.tap_intellij.Vistas.Taqueria.Orden_Taqueria;
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
    private Menu M_Parcial_1, M_Parcial_2, M_Salir;
    private MenuItem MI_Calculadora, MI_Cuadro_magico, MI_Loteria, MI_Memorama, MI_Empleado_Taqueria, MI_Orden_Taqueria, MI_Pista_de_atletismo, MI_Simulador_de_impresion, MI_Salir;
    private Alert Alerta;

    public static void main(String[] args) {
        launch();
    }

    private void Crear_UI() {
        //Menús.
        MI_Calculadora = new MenuItem("Calculadora");
        MI_Calculadora.setOnAction((event) -> new Calculadora());
        MI_Cuadro_magico = new MenuItem("Cuadro mágico");
        MI_Cuadro_magico.setOnAction((event) -> new Cuadro_magico());
        MI_Loteria = new MenuItem("Loteria");
        MI_Loteria.setOnAction((event) -> new Loteria());
        MI_Memorama = new MenuItem("Memorama");
        MI_Memorama.setOnAction((event) -> new Memorama());
        MI_Empleado_Taqueria = new MenuItem("Empleado_Taquería");
        MI_Empleado_Taqueria.setOnAction((event) -> new Empleado_Taqueria());
        MI_Orden_Taqueria = new MenuItem("Orden_Taquería");
        MI_Orden_Taqueria.setOnAction((event) -> new Orden_Taqueria());
        MI_Pista_de_atletismo = new MenuItem("Pista de atletismo");
        MI_Pista_de_atletismo.setOnAction((event) -> new Pista_atletismo());
        MI_Simulador_de_impresion = new MenuItem("Simulador de impresión");
        MI_Simulador_de_impresion.setOnAction((event) -> new Simulador_impresion());
        MI_Salir = new MenuItem("Salir");
        MI_Salir.setOnAction(event -> System.exit(0));
        //Opciones del menú principal del programa.
        M_Parcial_1 = new Menu("Competencia 1");
        M_Parcial_1.getItems().addAll(MI_Calculadora, MI_Cuadro_magico, MI_Loteria, MI_Memorama, MI_Empleado_Taqueria, MI_Orden_Taqueria);
        M_Parcial_2 = new Menu("Competencia 2");
        M_Parcial_2.getItems().addAll(MI_Pista_de_atletismo, MI_Simulador_de_impresion);
        M_Salir = new Menu("Salir");
        M_Salir.getItems().addAll(MI_Salir);
        //Barra del Menú principal del programa.
        MB_Barra_de_menu = new MenuBar(M_Parcial_1, M_Parcial_2, M_Salir);
    }

    @Override
    public void start(Stage stage) {
        Conexion.Crear_conexion();
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
}

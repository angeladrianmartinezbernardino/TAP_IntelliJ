package com.example.tap_intellij.Vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Ángel Adrián Martínez Bernardino.
 * */

public class Lotería extends Stage {
    private final Scene Escena;
    private final Button[][][] Todas_las_tablas = new Button[5][4][4];
    private Button[][] Tabla_actual;
    private HBox Juego;
    private HBox Seleccionar_Tabla;
    private VBox Cambio_de_tablas;
    private VBox Partida;
    private Button Anterior, Siguiente, Iniciar, Reiniciar, Botón_de_carta_en_tabla;
    private GridPane Tabla_para_jugar;
    private int Índice_de_la_tabla_actual = 0, Tabla, Fila, Columna;

    public Lotería() {
        this.inicializarTablas();
        this.Crear_UI();
        this.Escena = new Scene(this.Juego, 600.0, 625.0);
        this.setTitle("Loteria");
        this.setScene(this.Escena);
        this.show();
    }

    private void Crear_UI() {
        this.Crear_tabla(Índice_de_la_tabla_actual);
        this.Anterior = new Button("Tabla anterior");
        this.Anterior.setPrefSize(200.0, 100.0);
        this.Anterior.setOnAction(e -> cambiarTabla(-1));
        this.Siguiente = new Button("Siguiente tabla");
        this.Siguiente.setPrefSize(200.0, 100.0);
        this.Siguiente.setOnAction(e -> cambiarTabla(1));
        this.Seleccionar_Tabla = new HBox(this.Anterior, this.Siguiente);
        this.Cambio_de_tablas = new VBox(this.Tabla_para_jugar, this.Seleccionar_Tabla);
        this.Cambio_de_tablas.setSpacing(20.0);
        this.Iniciar = new Button("Iniciar");
        this.Partida = new VBox(this.Iniciar);
        this.Juego = new HBox(this.Cambio_de_tablas, this.Partida);
        this.Juego.setPadding(new Insets(20.0));
        this.Reiniciar = new Button("Comenzar de nuevo");
        this.Reiniciar.setVisible(false);
        this.Partida.getChildren().add(this.Reiniciar);
    }

    private void inicializarTablas() {
        for (Tabla = 0; Tabla < 5; Tabla++) {
            for (Fila = 0; Fila < 4; Fila++) {
                for (Columna = 0; Columna < 4; Columna++) {
                    Botón_de_carta_en_tabla = new Button("Botón [" + Tabla + "][" + Fila + "][" + Columna + "]");
                    Botón_de_carta_en_tabla.setPrefSize(100, 125);
                    Todas_las_tablas[Tabla][Fila][Columna] = Botón_de_carta_en_tabla;
                }
            }
        }
    }

    private void Crear_tabla(int indiceTabla) {
        this.Tabla_para_jugar = new GridPane();
        Tabla_actual = Todas_las_tablas[indiceTabla];
        for (Fila = 0; Fila < Tabla_actual.length; Fila++) {
            for (Columna = 0; Columna < Tabla_actual[Fila].length; Columna++) {
                this.Tabla_para_jugar.add(Tabla_actual[Fila][Columna], Columna, Fila);
            }
        }
    }

    private void cambiarTabla(int cambio) {
        Índice_de_la_tabla_actual += cambio;
        if (Índice_de_la_tabla_actual < 0) {
            Índice_de_la_tabla_actual = Todas_las_tablas.length - 1;
        } else if (Índice_de_la_tabla_actual >= Todas_las_tablas.length) {
            Índice_de_la_tabla_actual = 0;
        }
        System.out.println("Índice de la tabla actual: " + Índice_de_la_tabla_actual);
        this.Cambio_de_tablas.getChildren().remove(this.Tabla_para_jugar);
        Crear_tabla(Índice_de_la_tabla_actual);
        this.Cambio_de_tablas.getChildren().add(0, this.Tabla_para_jugar);
    }
}

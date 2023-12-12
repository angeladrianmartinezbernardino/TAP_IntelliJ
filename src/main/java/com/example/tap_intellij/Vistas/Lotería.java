package com.example.tap_intellij.Vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    private ImageView Mazo, IV;
    private Image Dorso, I_Carta;
    private Button Anterior, Siguiente, Iniciar, Reiniciar, Botón;
    private GridPane Tabla;
    private String[] Carta;
    private String Carta_actual;
    private int i, j, k, Posición;
    private int Índice_de_la_tabla_actual = 0;
    private List<String> Cartas_mezcladas;

    public Lotería() {
        this.Crear_mazo();
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
        this.Cambio_de_tablas = new VBox(this.Tabla, this.Seleccionar_Tabla);
        this.Cambio_de_tablas.setSpacing(20.0);
        this.Iniciar = new Button("Iniciar");
        this.Partida = new VBox(this.Mazo, this.Iniciar);
        this.Juego = new HBox(this.Cambio_de_tablas, this.Partida);
        this.Juego.setPadding(new Insets(20.0));
        this.Reiniciar = new Button("Comenzar de nuevo");
        this.Reiniciar.setVisible(false);
        this.Partida.getChildren().add(this.Reiniciar);
    }

    private void Crear_mazo() {
        Carta = new String[]{"1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg", "9.jpg", "10.jpg", "11.jpg", "12.jpg", "13.jpg", "14.jpg", "15.jpg", "16.jpg", "17.jpg", "18.jpg", "19.jpg", "20.jpg", "21.jpg", "22.jpg", "23.jpg", "24.jpg", "25.jpg", "26.jpg", "27.jpg", "28.jpg", "29.jpg", "30.jpg", "31.jpg", "32.jpg", "33.jpg", "34.jpg", "35.jpg", "36.jpg", "37.jpg", "38.jpg", "39.jpg", "40.jpg", "41.jpg", "42.jpg", "43.jpg", "44.jpg", "45.jpg", "46.jpg", "47.jpg", "48.jpg", "49.jpg", "50.jpg", "51.jpg", "52.jpg", "53.jpg", "54.jpg"};
        Dorso = new Image((new File("C:\\Users\\AAdri\\OneDrive\\Multimedia\\Documentos\\Programas\\IntelliJ\\TAP_IntelliJ\\src\\main\\resources\\Imágenes\\Dorso.png")).toURI().toString());
        this.Mazo = new ImageView(Dorso);
        this.Mazo.setFitWidth(119.2);
        this.Mazo.setFitHeight(168.4);
    }

    private void inicializarTablas() {
        for (k = 0; k < 5; k++) {
            Cartas_mezcladas = new ArrayList<>(Arrays.asList(Carta));
            Collections.shuffle(Cartas_mezcladas);
            Posición = 0;
            for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                    Carta_actual = Cartas_mezcladas.get(Posición);
                    Botón = new Button();
                    try {
                        I_Carta = new Image(new FileInputStream("C:\\\\Users\\\\AAdri\\\\OneDrive\\\\Multimedia\\\\Documentos\\\\Programas\\\\IntelliJ\\\\TAP_IntelliJ\\\\src\\\\main\\\\resources\\\\Imágenes\\\\" + Carta_actual));
                        IV = new ImageView(I_Carta);
                        IV.setFitHeight(126.324); // Ajusta estos valores según sea necesario
                        IV.setFitWidth(89.28);
                        Botón.setGraphic(IV);
                    } catch (FileNotFoundException e) {
                        System.out.println("Archivo no encontrado: " + e.getMessage());
                    }
                    Todas_las_tablas[k][i][j] = Botón;
                    Posición++;
                }
            }
        }
    }

    private void Crear_tabla(int indiceTabla) {
        this.Tabla = new GridPane();
        Tabla_actual = Todas_las_tablas[indiceTabla];
        for (i = 0; i < Tabla_actual.length; i++) {
            for (j = 0; j < Tabla_actual[i].length; j++) {
                this.Tabla.add(Tabla_actual[i][j], i, j);
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
        Crear_tabla(Índice_de_la_tabla_actual);
    }
}

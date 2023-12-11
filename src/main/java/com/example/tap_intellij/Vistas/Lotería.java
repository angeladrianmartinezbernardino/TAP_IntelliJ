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
import java.util.Random;

/*
 * Ángel Adrián Martínez Bernardino.
 * */

public class Lotería extends Stage {
    private final Scene Escena;
    private final Button[][] Carta_en_Tabla = new Button[4][4];
    private HBox Juego;
    private HBox Seleccionar_Tabla;
    private VBox Cambio_de_tablas;
    private VBox Partida;
    private ImageView Mazo, IV;
    private Image Dorso;
    private Button Anterior;
    private Button Siguiente;
    private Button Iniciar;
    private Button Reiniciar;
    private GridPane Tabla;
    private Random Aleatorio;
    private FileInputStream Stream;
    private String Temporal;
    private String[] Carta = new String[]{""};
    private int i, j, Posición, Índice_aleatorio_para_intercambiar;
    private int tablaActual = 0;
    private final String[][][] Tablas = new String[5][4][4];

    public Lotería() {
        this.Crear_mazo();
        this.Mezclar_cartas();
        for (int tabla = 0; tabla < 5; tabla++) {
            Mezclar_cartas();
            guardarTabla(tabla);
        }
        this.Crear_UI();
        this.Escena = new Scene(this.Juego, 600.0, 625.0);
        this.setTitle("Loteria");
        this.setScene(this.Escena);
        this.show();
        cargarTabla();
        guardarTabla(tablaActual);
    }

    private void Crear_UI() {
        this.Mezclar_cartas();
        this.Crear_tabla();
        this.Crear_mazo();
        this.Anterior = new Button("<");
        this.Anterior.setPrefSize(200.0, 100.0);
        this.Siguiente = new Button(">");
        this.Siguiente.setPrefSize(200.0, 100.0);
        this.Seleccionar_Tabla = new HBox(this.Anterior, this.Siguiente);
        this.Cambio_de_tablas = new VBox(this.Tabla, this.Seleccionar_Tabla);
        this.Cambio_de_tablas.setSpacing(20.0);
        this.Anterior.setOnAction(e -> Cambiar_de_tabla(false));
        this.Siguiente.setOnAction(e -> Cambiar_de_tabla(true));
        this.Iniciar = new Button("Iniciar");
        this.Partida = new VBox(this.Mazo, this.Iniciar);
        this.Juego = new HBox(this.Cambio_de_tablas, this.Partida);
        this.Juego.setPadding(new Insets(20.0));
        Reiniciar = new Button("Comenzar de nuevo");
        Reiniciar.setVisible(false);
        Partida.getChildren().add(Reiniciar);
    }

    private void Crear_mazo() {
        Carta = new String[]{"1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg", "9.jpg", "10.jpg", "11.jpg", "12.jpg", "13.jpg", "14.jpg", "15.jpg", "16.jpg", "17.jpg", "18.jpg", "19.jpg", "20.jpg", "21.jpg", "22.jpg", "23.jpg", "24.jpg", "25.jpg", "26.jpg", "27.jpg", "28.jpg", "29.jpg", "30.jpg", "31.jpg", "32.jpg", "33.jpg", "34.jpg", "35.jpg", "36.jpg", "37.jpg", "38.jpg", "39.jpg", "40.jpg", "41.jpg", "42.jpg", "43.jpg", "44.jpg", "45.jpg", "46.jpg", "47.jpg", "48.jpg", "49.jpg", "50.jpg", "51.jpg", "52.jpg", "53.jpg", "54.jpg"};
        Dorso = new Image((new File("C:\\Users\\AAdri\\OneDrive\\Multimedia\\Documentos\\Programas\\IntelliJ\\TAP_IntelliJ\\src\\main\\resources\\Imágenes\\Dorso.png")).toURI().toString());
        this.Mazo = new ImageView(Dorso);
        this.Mazo.setFitWidth(119.2);
        this.Mazo.setFitHeight(168.4);
        for (i = 0; i < 54; i++) {
            Carta[i] = String.valueOf(i + 1);
        }
    }

    private void Crear_tabla() {
        this.Tabla = new GridPane();
        Posición = 0;
        for (i = 0; i < 4; ++i) {
            for (j = 0; j < 4; ++j) {
                IV = null;
                try {
                    Stream = new FileInputStream("C:\\Users\\AAdri\\OneDrive\\Multimedia\\Documentos\\Programas\\IntelliJ\\TAP_IntelliJ\\src\\main\\resources\\Imágenes\\" + Carta[Posición] + ".jpg");
                    IV = new ImageView(new Image("C:\\Users\\AAdri\\OneDrive\\Multimedia\\Documentos\\Programas\\IntelliJ\\TAP_IntelliJ\\src\\main\\resources\\Imágenes\\" + Carta[Posición] + ".jpg"));
                    ++Posición;
                } catch (FileNotFoundException var8) {
                    IV = new ImageView();
                    System.out.println("Archivo no encontrado: " + var8.getMessage());
                }
                IV.setFitHeight(126.324);
                IV.setFitWidth(89.28);
                this.Carta_en_Tabla[i][j] = new Button();
                this.Carta_en_Tabla[i][j].setGraphic(IV);
                this.Carta_en_Tabla[i][j].setPrefSize(100.0, 140.0);
                this.Tabla.add(this.Carta_en_Tabla[i][j], i, j);
            }
        }
    }

    private void Mezclar_cartas() {
        Aleatorio = new Random();
        for (i = 0; i < Carta.length; i++) {
            Índice_aleatorio_para_intercambiar = Aleatorio.nextInt(Carta.length);
            Temporal = Carta[Índice_aleatorio_para_intercambiar];
            Carta[Índice_aleatorio_para_intercambiar] = Carta[i];
            Carta[i] = Temporal;
        }
    }

    private void guardarTabla(int tablaActual) {
        Posición = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                Tablas[tablaActual][i][j] = Carta[Posición];
                Posición++;
            }
        }
    }

    private void cargarTabla() {
        Posición = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                Carta[Posición] = Tablas[tablaActual][i][j];
                Posición++;
            }
        }
    }

    private void Cambiar_de_tabla(boolean adelante) {
        if (adelante) {
            tablaActual = tablaActual + 1;
            if (tablaActual > 4) {
                tablaActual = 0;
                cargarTabla();
            }
        } else {
            tablaActual = tablaActual - 1;
            if (tablaActual < 0) {
                tablaActual = 4;
                cargarTabla();
            }
        }
        cargarTabla();
        Crear_tabla();
    }
}
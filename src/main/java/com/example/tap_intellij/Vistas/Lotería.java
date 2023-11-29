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
import java.io.InputStream;
import java.util.Random;

/*
 * Ángel Adrián Martínez Bernardino.
 * */

public class Lotería extends Stage {
    private final Scene Escena;
    private final Button[][] B_Tabla = new Button[4][4];
    private HBox HB_Juego;
    private HBox HB_Seleccion;
    private VBox VB_Cambio_de_tablas;
    private VBox VB_Partida;
    private ImageView IV_Carta, IV;
    private Image I_Dorso;
    private Button B_Anterior;
    private Button B_Siguiente;
    private Button B_Iniciar;
    private Button B_Reiniciar;
    private GridPane GP_Tabla;
    private InputStream Stream;
    private Random Aleatorio;
    private String Temporal;
    private String[] Cartas = new String[]{""};
    private int i, j, Posicion, Indice_aleatorio_para_intercambiar;
    private int tablaActual = 0;
    private final String[][][] todasLasTablas = new String[5][4][4];

    public Lotería() {
        this.Crear_mazo();
        this.Mezclar_cartas();
        for (int tabla = 0; tabla < 5; tabla++) {
            Mezclar_cartas();
            guardarTabla(tabla);
        }
        this.Crear_UI();
        this.Escena = new Scene(this.HB_Juego, 600.0, 625.0);
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
        this.B_Anterior = new Button("<");
        this.B_Anterior.setPrefSize(200.0, 100.0);
        this.B_Siguiente = new Button(">");
        this.B_Siguiente.setPrefSize(200.0, 100.0);
        this.HB_Seleccion = new HBox(this.B_Anterior, this.B_Siguiente);
        this.VB_Cambio_de_tablas = new VBox(this.GP_Tabla, this.HB_Seleccion);
        this.VB_Cambio_de_tablas.setSpacing(20.0);
        this.B_Anterior.setOnAction(e -> Cambiar_de_tabla(false));
        this.B_Siguiente.setOnAction(e -> Cambiar_de_tabla(true));
        this.B_Iniciar = new Button("Iniciar");
        this.VB_Partida = new VBox(this.IV_Carta, this.B_Iniciar);
        this.HB_Juego = new HBox(this.VB_Cambio_de_tablas, this.VB_Partida);
        this.HB_Juego.setPadding(new Insets(20.0));
        B_Reiniciar = new Button("Comenzar de nuevo");
        B_Reiniciar.setVisible(false);
        VB_Partida.getChildren().add(B_Reiniciar);
    }

    private void Crear_mazo() {
        Cartas = new String[]{"1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg", "9.jpg", "10.jpg", "11.jpg", "12.jpg", "13.jpg", "14.jpg", "15.jpg", "16.jpg", "17.jpg", "18.jpg", "19.jpg", "20.jpg", "21.jpg", "22.jpg", "23.jpg", "24.jpg", "25.jpg", "26.jpg", "27.jpg", "28.jpg", "29.jpg", "30.jpg", "31.jpg", "32.jpg", "33.jpg", "34.jpg", "35.jpg", "36.jpg", "37.jpg", "38.jpg", "39.jpg", "40.jpg", "41.jpg", "42.jpg", "43.jpg", "44.jpg", "45.jpg", "46.jpg", "47.jpg", "48.jpg", "49.jpg", "50.jpg", "51.jpg", "52.jpg", "53.jpg", "54.jpg"};
        I_Dorso = new Image((new File("C:\\Users\\AAdri\\OneDrive\\Multimedia\\Documentos\\Programas\\IntelliJ\\TAP_IntelliJ\\src\\main\\resources\\Imágenes\\Dorso.png")).toURI().toString());
        this.IV_Carta = new ImageView(I_Dorso);
        this.IV_Carta.setFitWidth(119.2);
        this.IV_Carta.setFitHeight(168.4);
        for (i = 0; i < 54; i++) {
            Cartas[i] = String.valueOf(i + 1);
        }
    }

    private void Crear_tabla() {
        this.GP_Tabla = new GridPane();
        Posicion = 0;
        for (i = 0; i < 4; ++i) {
            for (j = 0; j < 4; ++j) {
                IV = null;
                try {
                    Stream = new FileInputStream("C:\\Users\\AAdri\\OneDrive\\Multimedia\\Documentos\\Programas\\IntelliJ\\TAP_IntelliJ\\src\\main\\resources\\Imágenes\\" + Cartas[Posicion] + ".jpg");
                    IV = new ImageView(new Image("C:\\Users\\AAdri\\OneDrive\\Multimedia\\Documentos\\Programas\\IntelliJ\\TAP_IntelliJ\\src\\main\\resources\\Imágenes\\" + Cartas[Posicion] + ".jpg"));
                    ++Posicion;
                } catch (FileNotFoundException var8) {
                    IV = new ImageView();
                    System.out.println("Archivo no encontrado: " + var8.getMessage());
                }
                IV.setFitHeight(126.324);
                IV.setFitWidth(89.28);
                this.B_Tabla[i][j] = new Button();
                this.B_Tabla[i][j].setGraphic(IV);
                this.B_Tabla[i][j].setPrefSize(100.0, 140.0);
                this.GP_Tabla.add(this.B_Tabla[i][j], i, j);
            }
        }
    }

    private void Mezclar_cartas() {
        Aleatorio = new Random();
        for (i = 0; i < Cartas.length; i++) {
            Indice_aleatorio_para_intercambiar = Aleatorio.nextInt(Cartas.length);
            Temporal = Cartas[Indice_aleatorio_para_intercambiar];
            Cartas[Indice_aleatorio_para_intercambiar] = Cartas[i];
            Cartas[i] = Temporal;
        }
    }

    private void guardarTabla(int tablaActual) {
        Posicion = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                todasLasTablas[tablaActual][i][j] = Cartas[Posicion];
                Posicion++;
            }
        }
    }

    private void cargarTabla() {
        Posicion = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                Cartas[Posicion] = todasLasTablas[tablaActual][i][j];
                Posicion++;
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

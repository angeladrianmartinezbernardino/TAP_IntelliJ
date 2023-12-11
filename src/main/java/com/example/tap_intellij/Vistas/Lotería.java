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
    private final List<Button[][]> todasLasTablas = new ArrayList<>(); // Lista de tablas
    private HBox Juego;
    private HBox Seleccionar_Tabla;
    private VBox Cambio_de_tablas;
    private VBox Partida;
    private ImageView Mazo, IV;
    private Image Dorso;
    private Button Anterior, Siguiente, Iniciar, Reiniciar;
    private GridPane Tabla;
    private FileInputStream Stream;
    private String[] Carta;
    private int i, j, Posición;
    private int tablaActualIndex = 0; // Índice de la tabla actual

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
        this.Crear_tabla(tablaActualIndex); // Crear la primera tabla
        this.Anterior = new Button("<");
        this.Anterior.setPrefSize(200.0, 100.0);
        this.Anterior.setOnAction(e -> cambiarTabla(-1)); // Cambiar a la tabla anterior
        this.Siguiente = new Button(">");
        this.Siguiente.setPrefSize(200.0, 100.0);
        this.Siguiente.setOnAction(e -> cambiarTabla(1)); // Cambiar a la siguiente tabla
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
        for (int k = 0; k < 5; k++) {
            List<String> cartasMezcladas = new ArrayList<>(Arrays.asList(Carta));
            Collections.shuffle(cartasMezcladas);

            Button[][] tabla = new Button[4][4];
            int pos = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    String cartaActual = cartasMezcladas.get(pos);
                    Button boton = new Button();

                    try {
                        Image imagenCarta = new Image(new FileInputStream("C:\\\\Users\\\\AAdri\\\\OneDrive\\\\Multimedia\\\\Documentos\\\\Programas\\\\IntelliJ\\\\TAP_IntelliJ\\\\src\\\\main\\\\resources\\\\Imágenes\\\\" + cartaActual));
                        ImageView imageView = new ImageView(imagenCarta);
                        imageView.setFitHeight(126.324); // Ajusta estos valores según sea necesario
                        imageView.setFitWidth(89.28);
                        boton.setGraphic(imageView);
                    } catch (FileNotFoundException e) {
                        System.out.println("Archivo no encontrado: " + e.getMessage());
                        // Manejar el caso de que el archivo no se encuentre
                    }

                    tabla[i][j] = boton;
                    pos++;
                }
            }
            todasLasTablas.add(tabla);
        }
    }


    private void Crear_tabla(int indiceTabla) {
        this.Tabla = new GridPane();
        Button[][] tablaActual = todasLasTablas.get(indiceTabla);
        for (int i = 0; i < tablaActual.length; i++) {
            for (int j = 0; j < tablaActual[i].length; j++) {
                this.Tabla.add(tablaActual[i][j], i, j);
            }
        }
    }

    private void cambiarTabla(int cambio) {
        tablaActualIndex += cambio;
        if (tablaActualIndex < 0) tablaActualIndex = 4;
        else if (tablaActualIndex > 4) tablaActualIndex = 0;
        Crear_tabla(tablaActualIndex);
    }

    // ... (resto del código, si hay más)
}

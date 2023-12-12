package com.example.tap_intellij.Vistas;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Button Anterior, Siguiente, Iniciar, Reiniciar, Botón_de_carta_en_tabla;
    private GridPane Tabla_para_jugar;
    private int Índice_de_la_tabla_actual = 0, Tabla, Fila, Columna, Índice, Número_de_carta;
    private List<Integer> Números_disponibles;

    private Label mazoLabel;
    private Timeline timeline;
    private final int tiempoCarta = 4;
    private List<Integer> cartasJuego;

    public Lotería() {
        this.cartasJuego = new ArrayList<>(Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                51, 52, 53, 54
        ));
        Collections.shuffle(this.cartasJuego);
        this.Iniciar_tablas();
        this.Crear_UI();
        this.Escena = new Scene(this.Juego, 600.0, 625.0);
        this.setTitle("Lotería");
        this.setScene(this.Escena);
        this.show();
        this.mazoLabel = new Label("Mazo");
        mazoLabel.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 20;");
        mazoLabel.setPrefSize(200, 200);
        this.Partida.getChildren().add(0, mazoLabel); // Asegúrate de que el mazoLabel sea el primer elemento en Partida
    }

    private void Crear_UI() {
        this.Crear_tabla(Índice_de_la_tabla_actual);
        this.Anterior = new Button("Tabla anterior");
        this.Anterior.setPrefSize(200.0, 100.0);
        this.Anterior.setOnAction(e -> Cambiar_tabla(-1));
        this.Siguiente = new Button("Siguiente tabla");
        this.Siguiente.setPrefSize(200.0, 100.0);
        this.Siguiente.setOnAction(e -> Cambiar_tabla(1));
        this.Seleccionar_Tabla = new HBox(this.Anterior, this.Siguiente);
        this.Cambio_de_tablas = new VBox(this.Tabla_para_jugar, this.Seleccionar_Tabla);
        this.Cambio_de_tablas.setSpacing(20.0);
        this.Iniciar = new Button("Iniciar");
        this.Iniciar.setOnAction(e -> {
            Anterior.setDisable(true);
            Siguiente.setDisable(true);
            iniciarJuego();
        });
        this.Partida = new VBox(this.Iniciar);
        this.Juego = new HBox(this.Cambio_de_tablas, this.Partida);
        this.Juego.setPadding(new Insets(20.0));
        this.Reiniciar = new Button("Comenzar de nuevo");
        this.Reiniciar.setVisible(false);
        this.Partida.getChildren().add(this.Reiniciar);
    }

    private void Iniciar_tablas() {
        Números_disponibles = new ArrayList<>();
        for (int i = 1; i <= 54; i++) {
            Números_disponibles.add(i);
        }
        for (Tabla = 0; Tabla < 5; Tabla++) {
            Collections.shuffle(Números_disponibles);
            for (Fila = 0; Fila < 4; Fila++) {
                for (Columna = 0; Columna < 4; Columna++) {
                    Índice = Fila * 4 + Columna;
                    Número_de_carta = Números_disponibles.get(Índice);
                    Botón_de_carta_en_tabla = new Button("Botón [" + Tabla + "][" + Fila + "][" + Columna + "] es " + Número_de_carta);
                    Botón_de_carta_en_tabla.setPrefSize(140, 165);
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

    private void Cambiar_tabla(int Cambio) {
        Índice_de_la_tabla_actual += Cambio;
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

    private void iniciarJuego() {
        Anterior.setDisable(true);
        Siguiente.setDisable(true);

        timeline = new Timeline(new KeyFrame(Duration.seconds(tiempoCarta), e -> sacarCarta()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Usa INDEFINITE si quieres que continúe hasta que se detenga manualmente
        timeline.play();
    }

    private void sacarCarta() {
        // Generar un número aleatorio entre 1 y 54
        int cartaActual = cartasJuego.remove(0);
        mazoLabel.setText("Carta actual es " + cartaActual);

        // Marcar la carta automáticamente después de 4 segundos si el jugador no lo ha hecho
        new Timeline(new KeyFrame(Duration.seconds(tiempoCarta), event -> {
            marcarCarta(cartaActual);
        })).play();

        // Si cartasJuego está vacío, detener el juego
        if (cartasJuego.isEmpty()) {
            timeline.stop();
            mazoLabel.setText("Fin del juego");
        }
    }

    private void marcarCarta(int numeroCarta) {
        // Encuentra el botón en la tabla actual que corresponde al número de carta sacado
        for (Button[] fila : Tabla_actual) {
            for (Button boton : fila) {
                if (boton.getText().endsWith(String.valueOf(numeroCarta))) {
                    boton.setStyle("-fx-opacity: 0.5;"); // Marcar la carta visualmente
                    boton.setDisable(true); // Deshabilitar el botón para evitar más clics
                }
            }
        }
    }
}

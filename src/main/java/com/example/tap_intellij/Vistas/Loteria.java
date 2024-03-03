package com.example.tap_intellij.Vistas;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class Loteria extends Stage {
    private final Scene Escena;
    private final Button[][][] Todas_las_tablas = new Button[5][4][4];
    private Button[][] Tabla_actual;
    private HBox Juego, Seleccionar_Tabla;
    private VBox Cambio_de_tablas, Partida;
    private Button Anterior, Siguiente, Iniciar, Reiniciar, Botón_de_carta_en_tabla;
    private GridPane Tabla_para_jugar;
    private int Índice_de_la_tabla_actual = 0, Tabla, Fila, Columna, Índice, Número_de_carta, Carta_actual;
    private List<Integer> Números_disponibles, Cartas_del_mazo;
    private Label Mazo;
    private Timeline Línea_del_tiempo;
    private final double Timpo_para_carta = 4.0;
    private Alert Alerta;

    public Loteria() {
        this.Cartas_del_mazo = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54));
        Collections.shuffle(this.Cartas_del_mazo);
        this.Iniciar_tablas();
        this.Crear_UI();
        this.Escena = new Scene(this.Juego, 600.0, 625.0);
        this.setTitle("Loteria");
        this.setScene(this.Escena);
        this.show();
        this.Mazo = new Label("Mazo");
        Mazo.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 20;");
        Mazo.setPrefSize(200, 200);
        this.Partida.getChildren().add(0, Mazo);
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
            Iniciar.setDisable(true);
            Iniciar_juego();
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
                    Botón_de_carta_en_tabla.setOnAction(event -> {
                        Button btn = (Button) event.getSource();
                        if (btn.getText().endsWith(String.valueOf(Carta_actual))) {
                            btn.setStyle("-fx-opacity: 0.5;");
                            btn.setDisable(true);
                        }
                    });
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

    private void Iniciar_juego() {
        Anterior.setDisable(true);
        Siguiente.setDisable(true);
        Línea_del_tiempo = new Timeline(new KeyFrame(Duration.seconds(Timpo_para_carta), e -> Sacar_carta()));
        Línea_del_tiempo.setCycleCount(Timeline.INDEFINITE);
        Línea_del_tiempo.play();
    }

    private void Sacar_carta() {
        Carta_actual = Cartas_del_mazo.remove(0);
        Mazo.setText("La carta es " + Carta_actual);
        Marcar_cartas_en_la_tabla(Carta_actual);
        if (Verificar_victoria()) {
            Línea_del_tiempo.stop();
            System.out.println("¡Fin del juego! Has ganado.");
            Mostrar_victoria();
        } else if (Cartas_del_mazo.isEmpty()) {
            Línea_del_tiempo.stop();
            System.out.println("¡Fin del juego! Pero no has ganado.");
            Mostrar_derrota();
        }
    }

    private boolean Verificar_victoria() {
        for (Button[] fila : Tabla_actual) {
            for (Button boton : fila) {
                if (!boton.isDisabled()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void Marcar_cartas_en_la_tabla(int numeroDeCarta) {
        for (Button[] Fila : Tabla_actual) {
            for (Button Botón : Fila) {
                if (Botón.getText().endsWith(String.valueOf(numeroDeCarta))) {
                    Botón.setStyle("-fx-opacity: 0.5;");
                    Botón.setDisable(true);
                }
            }
        }
    }

    private void Mostrar_victoria() {
        Platform.runLater(() -> {
            Alerta = new Alert(Alert.AlertType.INFORMATION);
            Alerta.setTitle("Juego Terminado");
            Alerta.setHeaderText(null);
            Alerta.setContentText("¡Felicidades! Has ganado el juego.");
            Alerta.showAndWait();
        });
    }

    private void Mostrar_derrota() {
        Platform.runLater(() -> {
            Alerta = new Alert(Alert.AlertType.INFORMATION);
            Alerta.setTitle("Juego Terminado");
            Alerta.setHeaderText(null);
            Alerta.setContentText("¡Vaya! No has ganado el juego.");
            Alerta.showAndWait();
        });
    }
}
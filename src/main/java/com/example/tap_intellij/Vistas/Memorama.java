package com.example.tap_intellij.Vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Ángel Adrián Martínez Bernardino.
 * */

public class Memorama extends Stage {
    private Scene Escena;
    private List<Integer> Pares_1, Pares_2;
    private HBox HB_Menu;
    private VBox VB_Juego, VB_Jugadores;
    private TextField TF_Numero_pares;
    private Button B_Resolver, B_Carta;
    private Label L_Temporizador;
    private GridPane GP_Principal, GP_Cartas;
    private Label L_Jugador_1, L_Jugador_2;
    private Alert Alerta;
    private int Total_cartas, Numero_pares, i, Columna, Fila;

    public Memorama() {
        // Llamada a métodos.
        this.Crear_UI();
        // Crear una Scene con GP_Principal como raíz.
        Escena = new Scene(this.GP_Principal);
        this.setScene(Escena);
        this.show();
        // Mezcla de números para asignarlos a cada carta posible.
        this.Pares_1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        this.Pares_2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        Collections.shuffle(this.Pares_1);
        Collections.shuffle(this.Pares_2);
    }

    private void Crear_UI() {
        // Elementos individuales.
        TF_Numero_pares = new TextField("Ingresa el número.");
        B_Resolver = new Button("Resolver");
        B_Resolver.setOnAction(event -> Manejar_resolver());
        L_Temporizador = new Label("00:00");
        GP_Cartas = new GridPane();
        L_Jugador_1 = new Label("Jugador 1");
        L_Jugador_2 = new Label("Jugador 2");
        // Creación del GridPane principal.
        GP_Principal = new GridPane();
        GP_Principal.setHgap(10);
        GP_Principal.setVgap(10);
        GP_Principal.setPadding(new Insets(20.0));
        // Menú.
        HB_Menu = new HBox(10);
        HB_Menu.getChildren().addAll(TF_Numero_pares, B_Resolver, L_Temporizador);
        // Jugadores.
        VB_Jugadores = new VBox(10);
        VB_Jugadores.getChildren().addAll(L_Jugador_1, L_Jugador_2);
        // Juego.
        VB_Juego = new VBox(10);
        VB_Juego.getChildren().addAll(GP_Cartas, VB_Jugadores);
        // Ahora agregamos los elementos al GridPane principal en las posiciones deseadas
        GP_Principal.add(HB_Menu, 0, 0);
        GP_Principal.add(VB_Juego, 0, 1);
    }

    private void Manejar_resolver() {
        try {
            Numero_pares = Integer.parseInt(TF_Numero_pares.getText());
            if (Numero_pares < 3 || Numero_pares > 15) {
                // Mostrar ventana de aviso.
                Alerta = new Alert(Alert.AlertType.WARNING);
                Alerta.setTitle("Número de pares inválido");
                Alerta.setHeaderText(null);
                Alerta.setContentText("Por favor, ingresa un número de pares entre 3 y 15.");
                Alerta.showAndWait();
            } else {
                Generar_tablero_cartas(Numero_pares);
                B_Resolver.setDisable(true);
            }
        } catch (NumberFormatException e) {
            // Mostrar ventana de error.
            Alerta = new Alert(Alert.AlertType.ERROR);
            Alerta.setTitle("Entrada inválida");
            Alerta.setHeaderText(null);
            Alerta.setContentText("La entrada debe ser un número entero.");
            Alerta.showAndWait();
        }
    }

    private void Generar_tablero_cartas(int Numero_pares) {
        GP_Cartas.getChildren().clear();
        Total_cartas = Numero_pares * 2;
        // Asegurándonos de que las cartas se colocan en dos filas.
        for (i = 0; i < Total_cartas; i++) {
            B_Carta = new Button();
            B_Carta.setPrefSize(100, 150);
            Columna = i % (Total_cartas / 2); // Asegurar que haya dos filas.
            Fila = i / (Total_cartas / 2); // La división determina la fila.
            GP_Cartas.add(B_Carta, Columna, Fila);
        }
    }
}

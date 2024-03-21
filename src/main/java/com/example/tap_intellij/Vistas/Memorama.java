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
    private Button B_Resolver;
    private Label L_Temporizador;
    private GridPane GP_Principal, GP_Cartas;
    private Label L_Jugador_1, L_Jugador_2;

    public Memorama() {
        // Llamada a métodos.
        this.Crear_UI();
        // Crear una Scene con VB_Juego como raíz.
        Escena = new Scene(this.HB_Menu);
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
        // Establecer el GridPane principal como la raíz de la escena.
        Escena = new Scene(GP_Principal);
        this.setScene(Escena);
        this.show();
    }

    private void Manejar_resolver() {
        // En algún lugar dentro de tu clase, por ejemplo en un método que maneja el evento de clic del botón 'B_Resolver':
        try {
            int numeroPares = Integer.parseInt(TF_Numero_pares.getText());
            if (numeroPares < 3 || numeroPares > 15) {
                // Mostrar ventana de aviso
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Número de Pares Inválido");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, ingresa un número de pares entre 3 y 15.");
                alert.showAndWait();
            } else {
                // Limpia el GridPane antes de añadir nuevas cartas
                GP_Cartas.getChildren().clear();
                GP_Cartas.getColumnConstraints().clear(); // Limpia las restricciones de las columnas si existen
                for (int i = 0; i < numeroPares * 2; i++) {
                    Button carta = new Button();
                    // Configura el tamaño del botón y otros estilos necesarios
                    carta.setPrefSize(100, 150);
                    int columna = i % (numeroPares * 2);
                    int fila = i / (numeroPares * 2);
                    GP_Cartas.add(carta, columna, fila);
                }
            }
        } catch (NumberFormatException e) {
            // Mostrar ventana de aviso para entrada no numérica
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Entrada Inválida");
            alert.setHeaderText(null);
            alert.setContentText("La entrada debe ser un número entero.");
            alert.showAndWait();
        }
    }

    private void Configurar_GP_Cartas(int numeroPares) {
        GP_Cartas.getChildren().clear(); // Limpia el GridPane antes de añadir nuevas cartas.
        int totalCartas = numeroPares * 2; // Total de cartas a mostrar.

        for (int i = 0; i < totalCartas; i++) {
            Button carta = new Button();
            carta.setPrefSize(100, 150); // Configura el tamaño preferido del botón.
            int columna = i % (numeroPares * 2);
            int fila = i / (numeroPares * 2);
            GP_Cartas.add(carta, columna, fila);
        }
    }
}

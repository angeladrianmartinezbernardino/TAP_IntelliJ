package com.example.tap_intellij.Vistas;

import java.util.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Memorama extends Stage {
    private VBox VB1, VB2;
    private HBox HBJ1, HBJ2, HB;
    private Label Puntaje_jugador_1, Puntaje_jugador_2, L_Jugador_1, L_Jugador_2, L_Temporizador;
    private GridPane GP;
    private TextField TF_Pares;
    private Image Imagen_trasera;
    private int Pares_encontrados_jugador_1, Pares_encontrados_jugador_2, Tiempo_restante_turno;
    private String Imagen_primea_carta;
    private boolean Primera_carta_revelada, Turno_jugador_1 = true;
    private Button Primera_carta_volteada, Segunda_carta_volteada;
    private Timeline Linea_tiempo;
    private final int Tiempo_limite_turno = 10;

    public Memorama() {
        CrearUI();
        Puntajes(0, 0);
    }

    private void CrearUI() {
        VB1 = new VBox(10);
        VB1.setId("v1");
        Button revolverButton = new Button("Revolver");
        TF_Pares = new TextField();
        GP = new GridPane();
        GP.setHgap(10);
        revolverButton.setOnAction(event -> revolver());
        VB1.getChildren().addAll(revolverButton, new HBox(10, new Label("Pares"), TF_Pares), GP);
        VB2 = new VBox(10);
        HBJ1 = new HBox(10);
        HBJ2 = new HBox(10);
        L_Jugador_1 = new Label("Jugador 1");
        L_Jugador_2 = new Label("Jugador 2");
        Puntaje_jugador_1 = new Label("Puntaje: 0");
        Puntaje_jugador_2 = new Label("Puntaje: 0");
        L_Temporizador = new Label("Tiempo restante: " + Tiempo_limite_turno + " segundos");
        HBJ1.getChildren().addAll(L_Jugador_1, Puntaje_jugador_1);
        HBJ2.getChildren().addAll(L_Jugador_2, Puntaje_jugador_2);
        VB2.getChildren().addAll(HBJ1, HBJ2, L_Temporizador);
        HBox HB = new HBox(50);
        HB.getChildren().addAll(VB1, VB2);
        HB.setId("ARRIBA");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(HB);
        scrollPane.setFitToWidth(true);
        Scene scene = new Scene(scrollPane, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/Estilos/Memorama.css").toString());
        this.setScene(scene);
        this.setTitle("Memorama");
        this.show();
        Imagen_trasera = new Image(getClass().getResourceAsStream("/Imagenes/back.jpg"));
    }

    private void iniciarTimer() {
        if (Linea_tiempo != null) {
            Linea_tiempo.stop();
        }
        Tiempo_restante_turno = Tiempo_limite_turno;
        L_Temporizador.setText("Tiempo restante: " + Tiempo_restante_turno + " segundos");
        Linea_tiempo = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
            Tiempo_restante_turno--;
            L_Temporizador.setText("Tiempo restante: " + Tiempo_restante_turno + " segundos");
            if (Tiempo_restante_turno <= 0) {
                cambiarTurno();
            }
        }));
        Linea_tiempo.setCycleCount(Timeline.INDEFINITE);
        Linea_tiempo.play();
    }

    private void cambiarTurno() {
        if (Linea_tiempo != null) {
            Linea_tiempo.stop();
        }
        Turno_jugador_1 = !Turno_jugador_1;
        ColoresTurno();
        for (Node node : GP.getChildren()) {
            if (node instanceof Button && !node.isDisabled()) {
                node.setDisable(false);
                node.setOpacity(1.0);
            }
        }
        iniciarTimer();
    }


    private void revolver() {
        try {
            if (Linea_tiempo != null) {
                Linea_tiempo.stop();
            }
            int cantidadPares = Integer.parseInt(TF_Pares.getText());
            if (cantidadPares < 3 || cantidadPares > 15) {
                mostrarError("Por favor, introduce un número de pares entre 3 y 15.");
                return;
            }
            Pares_encontrados_jugador_1 = 0;
            Pares_encontrados_jugador_2 = 0;
            Puntajes(Pares_encontrados_jugador_1, Pares_encontrados_jugador_2);
            limpiarGrid();
            List<String> imagenes = generarImagenes(cantidadPares);
            Collections.shuffle(imagenes);
            colocarImagenes(imagenes);
            Turno_jugador_1 = true;
            ColoresTurno();
            iniciarTimer();
        } catch (NumberFormatException e) {
            mostrarError("Por favor, introduce un número válido de pares.");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void ColoresTurno() {
        if (Turno_jugador_1) {
            L_Jugador_1.setTextFill(Color.GREEN);
            L_Jugador_2.setTextFill(Color.RED);
        } else {
            L_Jugador_1.setTextFill(Color.RED);
            L_Jugador_2.setTextFill(Color.GREEN);
        }
    }

    private void limpiarGrid() {
        GP.getChildren().clear();
    }

    private List<String> generarImagenes(int cantidadPares) {
        List<String> imagenes = Arrays.asList(
                "nezuco.jpg",
                "ren.jpg",
                "tanji.jpg",
                "-1.jpg",
                "-2.jpg",
                "-3.jpg",
                "-4.jpg",
                "-5.jpg",
                "-6.jpg",
                "amor.jpg",
                "ino.jpg",
                "logods.jpg",
                "rayo.jpg",
                "sol.jpg",
                "to.jpg"
        );
        Collections.shuffle(imagenes);
        List<String> imagenesSeleccionadas = new ArrayList<>();
        for (int i = 0; i < cantidadPares; i++) {
            imagenesSeleccionadas.add(imagenes.get(i));
            imagenesSeleccionadas.add(imagenes.get(i));
        }
        return imagenesSeleccionadas;
    }

    private void colocarImagenes(List<String> imagenes) {
        List<int[]> posiciones = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                posiciones.add(new int[]{i, j});
            }
        }
        Collections.shuffle(imagenes);
        Collections.shuffle(posiciones);
        for (int i = 0; i < imagenes.size(); i++) {
            String imagen = imagenes.get(i);
            int[] posicion = posiciones.get(i);
            Button boton = new Button();
            Image imagenFrontal = new Image(getClass().getResourceAsStream("/Imagenes/" + imagen));
            Image imagenTrasera = new Image(getClass().getResourceAsStream("/Imagenes/back.jpg"));
            ImageView imageView = new ImageView(imagenTrasera);
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);
            imageView.setOpacity(1.0);
            boton.setGraphic(imageView);
            boton.setPrefSize(100, 150);
            boton.setOnAction(event -> manejarClickCarta(boton, imagenFrontal, imagen));
            GP.add(boton, posicion[1], posicion[0]);
        }
    }

    private void manejarClickCarta(Button cartaSeleccionada, Image imagenFrontal, String imagen) {
        if (cartaSeleccionada.isDisabled()) {
            return;
        }
        ImageView imageViewCartaSeleccionada = (ImageView) cartaSeleccionada.getGraphic();
        imageViewCartaSeleccionada.setImage(imagenFrontal);
        imageViewCartaSeleccionada.setOpacity(1.0);
        if (!Primera_carta_revelada) {
            Primera_carta_revelada = true;
            Imagen_primea_carta = imagen;
            Primera_carta_volteada = cartaSeleccionada;
            cartaSeleccionada.setDisable(true);
        } else {
            Segunda_carta_volteada = cartaSeleccionada;
            if (imagen.equals(Imagen_primea_carta)) {
                Segunda_carta_volteada.setDisable(true);
                if (Turno_jugador_1) {
                    Pares_encontrados_jugador_1++;
                } else {
                    Pares_encontrados_jugador_2++;
                }
                Puntajes(Pares_encontrados_jugador_1, Pares_encontrados_jugador_2);
                Primera_carta_volteada.setDisable(true);
                Segunda_carta_volteada.setDisable(true);
                Primera_carta_revelada = false;
                verificarFinJuego();
                iniciarTimer();
            } else {
                Segunda_carta_volteada.setDisable(true);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
                    ((ImageView) Primera_carta_volteada.getGraphic()).setImage(Imagen_trasera);
                    ((ImageView) Segunda_carta_volteada.getGraphic()).setImage(Imagen_trasera);
                    Primera_carta_volteada.setDisable(false);
                    Segunda_carta_volteada.setDisable(false);
                }));
                timeline.setOnFinished(e -> {
                    Primera_carta_revelada = false;
                    cambiarTurno();
                });
                timeline.play();
            }
        }
    }

    private void Puntajes(int puntajeJugador1, int puntajeJugador2) {
        this.Puntaje_jugador_1.setText("Puntaje: " + puntajeJugador1);
        this.Puntaje_jugador_2.setText("Puntaje: " + puntajeJugador2);
    }

    private void verificarFinJuego() {
        int totalPares = Integer.parseInt(TF_Pares.getText());
        if (Pares_encontrados_jugador_1 + Pares_encontrados_jugador_2 == totalPares) {
            String mensajeGanador;
            if (Pares_encontrados_jugador_1 > Pares_encontrados_jugador_2) {
                mensajeGanador = "¡Jugador 1 ha ganado!";
            } else if (Pares_encontrados_jugador_2 > Pares_encontrados_jugador_1) {
                mensajeGanador = "¡Jugador 2 ha ganado!";
            } else {
                mensajeGanador = "¡Empate!";
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin del juego");
            alert.setHeaderText(null);
            alert.setContentText(mensajeGanador);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            reiniciarJuego();
        } else {
            iniciarTimer();
        }
    }

    private void reiniciarJuego() {
        Pares_encontrados_jugador_1 = 0;
        Pares_encontrados_jugador_2 = 0;
        Puntajes(0, 0);
        limpiarGrid();
        Linea_tiempo.stop();
        L_Temporizador.setText("Tiempo restante: " + Tiempo_limite_turno + " segundos");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
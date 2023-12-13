package com.example.tap_intellij.Vistas;

import com.example.tap_intellij.Componentes.Hilo;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Pista_de_Atletismo extends Stage {
    private ProgressBar[] Corredores = new ProgressBar[6];
    private Hilo[] Hilos = new Hilo[6];
    private Button Iniciar;
    private VBox VBox;
    private Scene Escena;
    private String[] Jugadores = {"Martina", "Germán", "Yuno", "Rodrigo", "Rubén", "Vanessa"};
    private int i;

    public Pista_de_Atletismo() {
        Crear_UI();
        Escena = new Scene(VBox);
        this.setTitle("Pista de Atletismo");
        this.setScene(Escena);
        this.show();
    }

    private void Crear_UI() {
        VBox = new VBox();
        for (i = 0; i < Corredores.length; i++) {
            Corredores[i] = new ProgressBar(0);
            Hilos[i] = new Hilo(Jugadores[i], Corredores[i]);
            VBox.getChildren().add(Corredores[i]);
        }
        Iniciar = new Button("Iniciar Carrera");
        Iniciar.setOnAction(event -> {
            for (i = 0; i < Corredores.length; i++) {
                Hilos[i].start();
            }
        });
        VBox.getChildren().add(Iniciar);
    }
}

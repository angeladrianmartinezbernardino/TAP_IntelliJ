package com.example.tap_intellij.Vistas;

import com.example.tap_intellij.Componentes.Hilo;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Pista_de_Atletismo extends Stage {
    private ProgressBar[] pgbCorredores = new ProgressBar[6];
    private Hilo[] thrCorredores = new Hilo[6];
    private Button btnIniciar;
    private VBox vBox;
    private Scene escena;
    private String[] strCorredores = {"Martina", "Germán", "Yuno", "Rodrigo", "Rubén", "Vanessa"};

    public Pista_de_Atletismo() {
        Crear_UI();
        escena = new Scene(vBox);
        this.setTitle("Pista de Atletismo :)");
        this.setScene(escena);
        this.show();
    }

    private void Crear_UI() {
        vBox = new VBox();
        for (int i = 0; i < pgbCorredores.length; i++) {
            pgbCorredores[i] = new ProgressBar(0);
            thrCorredores[i] = new Hilo(strCorredores[i], pgbCorredores[i]);
            vBox.getChildren().add(pgbCorredores[i]);
        }
        btnIniciar = new Button("Iniciar Carrera.");
        btnIniciar.setOnAction(event -> {
            for (int i = 0; i < pgbCorredores.length; i++) {
                thrCorredores[i].start();
            }
        });
        vBox.getChildren().add(btnIniciar);
    }
}

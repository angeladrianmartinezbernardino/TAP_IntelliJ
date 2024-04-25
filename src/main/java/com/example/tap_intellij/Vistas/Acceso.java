package com.example.tap_intellij.Vistas;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Acceso extends Login {
    private Scene escena;

    public Acceso() {
        CrearUI();
        this.setTitle("Empleados");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
    }
}

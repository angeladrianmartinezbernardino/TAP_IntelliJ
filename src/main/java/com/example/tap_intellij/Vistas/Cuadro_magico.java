package com.example.tap_intellij.Vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Cuadro_magico extends Stage {
    private Scene escena;
    private GridPane gpVentana;
    private TextField tfTamano;
    private Button btnCalcular;

    public Cuadro_magico() {
        CrearUI();
        escena = new Scene(gpVentana, 600, 500);
        this.setTitle("Cuadro Mágico");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        gpVentana = new GridPane();
        gpVentana.setAlignment(Pos.CENTER);
        gpVentana.setHgap(10);
        gpVentana.setVgap(10);

        Label lblTamano = new Label("Tamaño del cuadro:");
        tfTamano = new TextField();
        btnCalcular = new Button("Calcular Cuadro Mágico");

        btnCalcular.setOnAction(event -> calcularCuadroMagico());

        gpVentana.add(lblTamano, 0, 0);
        gpVentana.add(tfTamano, 1, 0);
        gpVentana.add(btnCalcular, 1, 1);
    }

    private void calcularCuadroMagico() {
        int tamano = Integer.parseInt(tfTamano.getText());
        // Validación del tamaño introducido por el usuario
        if (tamano < 3 || tamano % 2 == 0) {
            System.out.println("El tamaño debe ser impar y mayor o igual a 3");
            return;
        }

        // Limpia el GridPane antes de agregar nuevos elementos
        gpVentana.getChildren().clear();
        CrearUI(); // Re-agrega los elementos de entrada

        // Aquí implementarías el algoritmo para generar el cuadro mágico
        // utilizando archivos de acceso aleatorio en vez de arreglos.
        generarCuadroMagico(tamano);

        // Mostrar el cuadro mágico en el GridPane
        mostrarCuadroMagico(tamano);
    }

    private void generarCuadroMagico(int tamano) {
        try {
            RandomAccessFile archivo = new RandomAccessFile("cuadroMagico.dat", "rw");
            // Inicializar archivo
            for (int i = 0; i < tamano * tamano; i++) {
                archivo.writeInt(0);
            }

            int num = 1;
            int fila = tamano / 2;
            int columna = tamano - 1;
            while (num <= tamano * tamano) {
                if (fila == -1 && columna == tamano) {
                    columna = tamano - 2;
                    fila = 0;
                } else {
                    if (columna == tamano) {
                        columna = 0;
                    }
                    if (fila < 0) {
                        fila = tamano - 1;
                    }
                }

                archivo.seek((fila * tamano + columna) * Integer.BYTES);
                if (archivo.readInt() != 0) {
                    columna -= 2;
                    fila++;
                    continue;
                } else {
                    archivo.seek((fila * tamano + columna) * Integer.BYTES);
                    archivo.writeInt(num++);
                }

                fila--;
                columna++;
            }
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarCuadroMagico(int tamano) {
        try {
            RandomAccessFile archivo = new RandomAccessFile("cuadroMagico.dat", "r");
            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    archivo.seek((i * tamano + j) * Integer.BYTES);
                    int valor = archivo.readInt();
                    Label label = new Label(String.valueOf(valor));
                    gpVentana.add(label, j, i + 2); // +2 para ajustar por los elementos de entrada
                }
            }
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
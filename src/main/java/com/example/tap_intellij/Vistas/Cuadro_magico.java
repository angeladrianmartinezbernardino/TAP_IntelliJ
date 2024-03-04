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
    private Scene Escena;
    private GridPane Ventana;
    private TextField Cuadro_texto;
    private Button Calcular, Celda;
    private Label Introducir_tamano;
    private int Tamano, i, Numero, Fila, Columna, Valor;
    private RandomAccessFile Archivo;

    public Cuadro_magico() {
        CrearUI();
        Escena = new Scene(Ventana, 600, 500);
        Escena.getStylesheets().add(getClass().getResource("/Estilos/Cuadro_magico.css").toString());
        this.setTitle("Cuadro mágico");
        this.setScene(Escena);
        this.show();
    }

    private void CrearUI() {
        Ventana = new GridPane();
        Ventana.setAlignment(Pos.CENTER);
        Ventana.setHgap(10);
        Ventana.setVgap(10);
        Introducir_tamano = new Label("Tamaño del cuadro:");
        Cuadro_texto = new TextField();
        Calcular = new Button("Calcular Cuadro Mágico");
        Calcular.setOnAction(event -> calcularCuadroMagico());
        // Asegúrate de no agregar los elementos de la UI nuevamente aquí.
        // Solo configura el GridPane y agrega los elementos de control.
        Ventana.add(Introducir_tamano, 0, 0);
        Ventana.add(Cuadro_texto, 1, 0);
        Ventana.add(Calcular, 1, 1);
    }

    private void calcularCuadroMagico() {
        Tamano = Integer.parseInt(Cuadro_texto.getText());
        // Validación del tamaño introducido por el usuario
        if (Tamano < 3 || Tamano % 2 == 0) {
            System.out.println("El tamaño debe ser impar y mayor o igual a 3.");
            return;
        }
        // Solo limpia la parte del GridPane donde se mostrará el cuadro mágico.
        Ventana.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
        // Implementación del algoritmo para generar el cuadro mágico.
        Generar_cuadro_magico(Tamano);
        // Mostrar el cuadro mágico en el GridPane
        Mostrar_cuadro_magico(Tamano);
    }

    private void Generar_cuadro_magico(int Tamano) {
        try {
            Archivo = new RandomAccessFile("cuadroMagico.dat", "rw");
            // Inicializar archivo
            for (i = 0; i < Tamano * Tamano; i++) {
                Archivo.writeInt(0);
            }
            Numero = 1;
            Fila = Tamano / 2;
            Columna = Tamano - 1;
            while (Numero <= Tamano * Tamano) {
                if (Fila == -1 && Columna == Tamano) {
                    Columna = Tamano - 2;
                    Fila = 0;
                } else {
                    if (Columna == Tamano) {
                        Columna = 0;
                    }
                    if (Fila < 0) {
                        Fila = Tamano - 1;
                    }
                }
                Archivo.seek((Fila * Tamano + Columna) * Integer.BYTES);
                if (Archivo.readInt() != 0) {
                    Columna -= 2;
                    Fila++;
                    continue;
                } else {
                    Archivo.seek((Fila * Tamano + Columna) * Integer.BYTES);
                    Archivo.writeInt(Numero++);
                }
                Fila--;
                Columna++;
            }
            Archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Mostrar_cuadro_magico(int Tamano) {
        try {
            Archivo = new RandomAccessFile("cuadroMagico.dat", "r");
            for (Fila = 0; Fila < Tamano; Fila++) {
                for (Columna = 0; Columna < Tamano; Columna++) {
                    Archivo.seek((Fila * Tamano + Columna) * Integer.BYTES);
                    Valor = Archivo.readInt();
                    Celda = new Button(String.valueOf(Valor));
                    Celda.setMinWidth(60); // Establece un ancho mínimo para los botones
                    Celda.setMinHeight(60); // Establece una altura mínima para los botones
                    // Ahora agrega el botón al GridPane en lugar de una etiqueta
                    Ventana.add(Celda, Columna + 2, Fila); // +2 para ajustar por los elementos de entrada
                }
            }
            Archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

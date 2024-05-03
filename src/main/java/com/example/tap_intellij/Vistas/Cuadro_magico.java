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

/*
 * Ángel Adrián Martínez Bernardino.
 *
 * Cuadro mágico.
 * */

public class Cuadro_magico extends Stage {
    private Scene Escena_Principal, Escena_CM;
    private GridPane Ventana_datos, Ventana_CM;
    private TextField Cuadro_texto;
    private Button Calcular, Celda;
    private Label Introducir_tamano;
    private int Tamano, i, Numero, Fila, Columna, Valor;
    private RandomAccessFile Archivo;
    private Stage S_CM;

    public Cuadro_magico() {
        CrearUI();
        Escena_Principal = new Scene(Ventana_datos, 800, 400);
        Escena_Principal.getStylesheets().add(getClass().getResource("/Estilos/Datos_CM.css").toString());
        this.setTitle("Cuadro mágico");
        this.setScene(Escena_Principal);
        this.show();
    }

    private void CrearUI() {
        Ventana_datos = new GridPane();
        Ventana_datos.setAlignment(Pos.CENTER);
        Ventana_datos.setHgap(10);
        Ventana_datos.setVgap(10);
        Introducir_tamano = new Label("Tamaño del cuadro");
        Cuadro_texto = new TextField();
        Calcular = new Button("Calcular cuadro mágico");
        Calcular.setOnAction(event -> calcularCuadroMagico());
        Ventana_datos.add(Introducir_tamano, 0, 0);
        Ventana_datos.add(Cuadro_texto, 1, 0);
        Ventana_datos.add(Calcular, 1, 1);
    }

    private void calcularCuadroMagico() {
        Tamano = Integer.parseInt(Cuadro_texto.getText());
        // Validación del tamaño introducido por el usuario
        if (Tamano < 3 || Tamano % 2 == 0) {
            System.out.println("El tamaño debe ser impar y mayor o igual a 3.");
            return;
        }
        // Implementación del algoritmo para generar el cuadro mágico.
        Generar_cuadro_magico(Tamano);
        // Mostrar el cuadro mágico en el GridPane.
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
        S_CM = new Stage();
        Ventana_CM = new GridPane();
        Ventana_CM.setAlignment(Pos.CENTER);
        Ventana_CM.setHgap(10);
        Ventana_CM.setVgap(10);
        try {
            Archivo = new RandomAccessFile("cuadroMagico.dat", "r");
            for (Fila = 0; Fila < Tamano; Fila++) {
                for (Columna = 0; Columna < Tamano; Columna++) {
                    Archivo.seek((Fila * Tamano + Columna) * Integer.BYTES);
                    Valor = Archivo.readInt();
                    Celda = new Button(String.valueOf(Valor));
                    Celda.setMinWidth(30);
                    Celda.setMinHeight(30);
                    // Ahora se agrega el botón al GridPane en lugar de una etiqueta y +2 para ajustar por los elementos de entrada.
                    Ventana_CM.add(Celda, Columna + 2, Fila);
                }
            }
            Archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Escena_CM = new Scene(Ventana_CM, 300, 300);
        Escena_CM.getStylesheets().add(getClass().getResource("/Estilos/CM.css").toString());
        S_CM.setTitle("Cuadro mágico");
        S_CM.setScene(Escena_CM);
        S_CM.show();
    }
}
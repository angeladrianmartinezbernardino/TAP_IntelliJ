package com.example.tap_intellij.Vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private Stage CM;
    private Scene S_tamano_cuadro_magico, S_cuadro_magico;
    private GridPane GP_tamano_cuadro_magico, GP_cuadro_magico;
    private int Tamano, i, Numero_cuadrado_individual, Fila, Columna, Valor, Nueva_fila, Nueva_columna;
    private TextField Tamano_cuadro_magico;
    private Label Introducir_tamano_cuadro_magico;
    private Button Calcular, Cuadrado_individual;
    private Alert Alerta;

    public Cuadro_magico() {
        Crear_UI();
        S_tamano_cuadro_magico = new Scene(GP_tamano_cuadro_magico, 800, 400);
        S_tamano_cuadro_magico.getStylesheets().add(getClass().getResource("/Estilos/Datos_CM.css").toString());
        this.setTitle("Cuadro mágico");
        this.setScene(S_tamano_cuadro_magico);
        this.show();
    }

    private void Crear_UI() {
        GP_tamano_cuadro_magico = new GridPane();
        GP_tamano_cuadro_magico.setAlignment(Pos.CENTER);
        GP_tamano_cuadro_magico.setHgap(10);
        GP_tamano_cuadro_magico.setVgap(10);
        Introducir_tamano_cuadro_magico = new Label("Tamaño del cuadro");
        Tamano_cuadro_magico = new TextField();
        Calcular = new Button("Calcular cuadro mágico");
        Calcular.setOnAction(event -> Calcular_cuadro_magico());
        GP_tamano_cuadro_magico.add(Introducir_tamano_cuadro_magico, 0, 0);
        GP_tamano_cuadro_magico.add(Tamano_cuadro_magico, 1, 0);
        GP_tamano_cuadro_magico.add(Calcular, 1, 1);
    }

    private void Calcular_cuadro_magico() {
        try {
            Tamano = Integer.parseInt(Tamano_cuadro_magico.getText());
            if (!Es_tamano_valido(Tamano)) {
                Mostrar_alerta("Error", "El tamaño debe ser impar y mayor o igual a 3.");
                return;
            }
            Generar_cuadro_magico(Tamano);
            Mostrar_cuadro_magico(Tamano);
        } catch (NumberFormatException e) {
            Mostrar_alerta("Error", "Por favor, introduce un número válido.");
        }
    }

    private boolean Es_tamano_valido(int Tamano) {
        return Tamano >= 3 && Tamano % 2 != 0;
    }

    private void Generar_cuadro_magico(int Tamano) {
        try (RandomAccessFile Archivo = new RandomAccessFile("Cuadro_magico.dat", "rw")) {
            for (i = 0; i < Tamano * Tamano; i++) {
                Archivo.writeInt(0);
            }
            Numero_cuadrado_individual = 1;
            Fila = 0;
            Columna = Tamano / 2;
            while (Numero_cuadrado_individual <= Tamano * Tamano) {
                Archivo.seek((Fila * Tamano + Columna) * Integer.BYTES);
                Archivo.writeInt(Numero_cuadrado_individual++);
                Nueva_fila = (Fila - 1 + Tamano) % Tamano;
                Nueva_columna = (Columna + 1) % Tamano;
                Archivo.seek((Nueva_fila * Tamano + Nueva_columna) * Integer.BYTES);
                if (Archivo.readInt() != 0) {
                    Fila = (Fila + 1) % Tamano;
                } else {
                    Fila = Nueva_fila;
                    Columna = Nueva_columna;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Mostrar_cuadro_magico(int Tamano) {
        CM = new Stage();
        GP_cuadro_magico = new GridPane();
        GP_cuadro_magico.setAlignment(Pos.CENTER);
        GP_cuadro_magico.setHgap(10);
        GP_cuadro_magico.setVgap(10);
        try (RandomAccessFile Archivo = new RandomAccessFile("Cuadro_magico.dat", "r")) {
            for (Fila = 0; Fila < Tamano; Fila++) {
                for (Columna = 0; Columna < Tamano; Columna++) {
                    Archivo.seek((Fila * Tamano + Columna) * Integer.BYTES);
                    Valor = Archivo.readInt();
                    Cuadrado_individual = new Button(String.valueOf(Valor));
                    Cuadrado_individual.setMinWidth(30);
                    Cuadrado_individual.setMinHeight(30);
                    GP_cuadro_magico.add(Cuadrado_individual, Columna + 2, Fila);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        S_cuadro_magico = new Scene(GP_cuadro_magico, 300, 300);
        S_cuadro_magico.getStylesheets().add(getClass().getResource("/Estilos/CM.css").toString());
        CM.setTitle("Cuadro mágico");
        CM.setScene(S_cuadro_magico);
        CM.show();
    }

    private void Mostrar_alerta(String Titulo, String Mensaje) {
        Alerta = new Alert(Alert.AlertType.ERROR);
        Alerta.setTitle(Titulo);
        Alerta.setHeaderText(null);
        Alerta.setContentText(Mensaje);
        Alerta.showAndWait();
    }
}

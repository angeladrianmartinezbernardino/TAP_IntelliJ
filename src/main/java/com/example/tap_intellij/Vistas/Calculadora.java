package com.example.tap_intellij.Vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
 * Ángel Adrián Martínez Bernardino.
 *
 * Calculadora.
 * */

public class Calculadora extends Stage {
    private final Button[][] Tecla = new Button[4][4];
    private final char[] Digito = {'7', '8', '9', '/', '4', '5', '6', '*', '1', '2', '3', '-', '0', '.', '=', '+'};
    private GridPane GP_Ventana;
    private TextField Calculos;
    private Button Limpiar_pantalla;
    private int Posicion;
    private double Primer_numero = 0, Segundo_numero = 0, Resultado = 0;
    private String Operador = "", Contenido_pantalla = "";
    private boolean Tiene_decimal = false, Despues_de_igual = false, Es_negativo = false;
    private Scene Escena;

    public Calculadora() {
        Crear_UI();
        Escena = new Scene(GP_Ventana, 250, 250);
        Escena.getStylesheets().add(getClass().getResource("/Estilos/Calculadora.css").toString());
        this.setTitle("Calculadora");
        this.setScene(Escena);
        this.show();
    }

    private void Crear_UI() {
        GP_Ventana = new GridPane();
        Calculos = new TextField("0");
        Calculos.setAlignment(Pos.BASELINE_RIGHT);
        Calculos.setEditable(false);
        GP_Ventana.add(Calculos, 0, 0, 4, 1);
        Limpiar_pantalla = new Button("C");
        Limpiar_pantalla.setPrefSize(50, 50);
        Limpiar_pantalla.setOnAction((event) -> {
            Calculos.setText("0");
            Contenido_pantalla = "";
            Primer_numero = 0;
            Segundo_numero = 0;
            Operador = "";
            Tiene_decimal = false;
            Despues_de_igual = false;
            Es_negativo = false;
        });
        GP_Ventana.add(Limpiar_pantalla, 4, 0);
        Posicion = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < 4; j++) {
                final char Simbolo_actual = Digito[Posicion];
                Tecla[i - 1][j] = new Button(String.valueOf(Simbolo_actual));
                Tecla[i - 1][j].setPrefSize(50, 50);
                Tecla[i - 1][j].setOnAction((event) -> Generar_expresion(Simbolo_actual));
                GP_Ventana.add(Tecla[i - 1][j], j, i);
                Posicion++;
            }
        }
    }

    private void Generar_expresion(char Simbolo) {
        if (Calculos.getText().equals("Error")) {
            Calculos.setText("0");
            Contenido_pantalla = "";
            Primer_numero = 0;
            Segundo_numero = 0;
            Operador = "";
            Tiene_decimal = false;
            Despues_de_igual = false;
        }
        if (Despues_de_igual && "0123456789.".indexOf(Simbolo) >= 0) {
            Calculos.setText("0");
            Contenido_pantalla = "";
            Primer_numero = Resultado;
            Operador = "";
            Tiene_decimal = false;
            Despues_de_igual = false;
        }
        if (Simbolo == '-') {
            if (Contenido_pantalla.isEmpty()) {
                Es_negativo = !Es_negativo;
                Contenido_pantalla = Es_negativo ? "-" : "";
                Primer_numero = 0;
            } else {
                Primer_numero = Double.parseDouble(Contenido_pantalla);
                Operador = "-";
                Contenido_pantalla = "";
                Es_negativo = false;
            }
        } else if ("+-*/".indexOf(Simbolo) >= 0) {
            if (Contenido_pantalla.isEmpty()) {
                if ("+-*/".indexOf(Simbolo) >= 0) {
                    Operador = Simbolo + "";
                } else if (Despues_de_igual) {
                    Primer_numero = Resultado;
                    Operador = Simbolo + "";
                    Despues_de_igual = false;
                } else {
                    Calculos.setText("Error");
                }
            } else {
                Primer_numero = Double.parseDouble(Contenido_pantalla);
                Operador = Simbolo + "";
                Contenido_pantalla = "";
                Tiene_decimal = false;
            }
        } else if ('=' == Simbolo) {
            if (Contenido_pantalla.isEmpty() || Operador.isEmpty()) {
                Calculos.setText("Error");
                return;
            }
            Segundo_numero = Double.parseDouble(Contenido_pantalla);
            Calcular();
            Calculos.setText(Calculos.getText().equals("Error") ? "Error" : String.valueOf(Resultado));
            Contenido_pantalla = "";
            Operador = "";
            Tiene_decimal = false;
            Despues_de_igual = true;
        } else {
            if (Simbolo == '.' && Tiene_decimal) {
                Calculos.setText("Error");
                return;
            }
            if (Simbolo == '.') {
                Tiene_decimal = true;
            }
            if (Contenido_pantalla.isEmpty() && Simbolo == '.') {
                Contenido_pantalla = "0.";
            } else {
                Contenido_pantalla += Simbolo;
            }
            Calculos.setText(Contenido_pantalla);
        }
    }

    private void Calcular() {
        if ("Error".equals(Calculos.getText())) {
            return;
        }
        if (Primer_numero == 0 && Segundo_numero == 0) {
            Calculos.setText("Error");
            return;
        }
        if ("/".equals(Operador) && Segundo_numero == 0) {
            Calculos.setText("Error");
            return;
        }
        if ("/".equals(Operador) && Primer_numero == 0) {
            Resultado = 0;
            Calculos.setText("0");
            return;
        }
        switch (Operador) {
            case "+":
                Resultado = Primer_numero + Segundo_numero;
                break;
            case "-":
                Resultado = Primer_numero - Segundo_numero;
                break;
            case "*":
                Resultado = Primer_numero * Segundo_numero;
                break;
            case "/":
                Resultado = Primer_numero / Segundo_numero;
                break;
            default:
                Calculos.setText("Error");
                return;
        }
        if (Resultado % 1 == 0) {
            Calculos.setText(String.format("%.0f", Resultado));
        } else {
            Calculos.setText(String.valueOf(Resultado));
        }
    }
}

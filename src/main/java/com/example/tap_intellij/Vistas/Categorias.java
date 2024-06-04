package com.example.tap_intellij.Vistas;

import com.example.tap_intellij.Vistas.Taqueria.Generar_PDF;
import com.example.tap_intellij.Vistas.Taqueria.Tablas.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Categorias extends Stage {
    private Scene escena;
    private VBox layoutPrincipal;
    private HBox botoneraSuperior;
    private HBox botoneraInferior;
    private Button[] botonesCategorias;
    private Button btnPDF, btnGraficas;

    public Categorias() {
        CrearUI();
        this.setTitle("Categorías");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        // Contenedor principal
        layoutPrincipal = new VBox(10);
        // Crear botonera superior
        botoneraSuperior = new HBox(5);
        Button btnA = new Button("Categorias");
        //btnA.setOnAction(event -> new Categoria_Taqueria());
        Button btnB = new Button("Detalle de la orden");
        //btnB.setOnAction(event -> new Detalle_Orden_Taqueria());
        Button btnC = new Button("Empleado");
        btnC.setOnAction(event -> new Empleado_Taqueria());
        Button btnD = new Button("Mesas");
        btnD.setOnAction(event -> new Mesa_Taqueria());
        Button btnE = new Button("Orden");
        btnE.setOnAction(event -> new Orden_Taqueria());
        Button btnF = new Button("Productos");
        btnF.setOnAction(event -> new Producto_Taqueria());
        Button btnG = new Button("Promociones");
        //btnG.setOnAction(event -> new Promocion_Taqueria());
        Button btnH = new Button("Servicio a domicilio");
        //btnH.setOnAction(event -> new Servicio_Domicilio_Taqueria());
        Button btnI = new Button("Usuarios");
        btnI.setOnAction(event -> new Usuario_Taqueria());
        // Agregar botones a la botonera
        botoneraSuperior.getChildren().addAll(btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI);
        // Crear botones PDF y Gráficas
        botoneraInferior = new HBox(5);
        btnPDF = new Button("PDF");
        btnPDF.setOnAction(event -> new Generar_PDF());
        btnGraficas = new Button("Gráficas");
        botoneraInferior.getChildren().addAll(btnPDF, btnGraficas);
        // Agregar todas las botoneras al layout principal
        layoutPrincipal.getChildren().addAll(botoneraSuperior, botoneraInferior);
        // Espacio reservado para TableView (vacío por ahora)
        VBox tableViewPlaceholder = new VBox();
        tableViewPlaceholder.setPrefHeight(200); // Ajustar al tamaño deseado
        layoutPrincipal.getChildren().add(tableViewPlaceholder);
        // Configurar la escena
        escena = new Scene(layoutPrincipal, 800, 600);
    }
}

package com.example.tap_intellij.Vistas.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Orden_DAO;
import com.example.tap_intellij.Modelos.Taqueria.Orden_detalle_DAO;
import com.example.tap_intellij.Vistas.Taqueria.Tablas.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Categoria extends Stage {
    private Scene escena;
    private VBox layoutPrincipal;
    private HBox botoneraSuperior;
    private HBox botoneraInferior;
    private Button[] botonesCategorias;
    private Button btnPDF, btnGraficas;
    private ObservableList<Orden_detalle_DAO> detalles;
    private Orden_DAO orden;

    public Categoria() {
        CrearUI();
        this.setTitle("Categorías");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        detalles = FXCollections.observableArrayList();
        layoutPrincipal = new VBox(10);
        botoneraSuperior = new HBox(5);
        Button btnA = new Button("Categoria");
        btnA.setOnAction(event -> new Categoria_Taqueria());
        Button btnB = new Button("Detalle de la orden");
        btnB.setOnAction(event -> new Orden_detalle_Taqueria(detalles));
        Button btnC = new Button("Empleado");
        btnC.setOnAction(event -> new Empleado_Taqueria());
        Button btnD = new Button("Mesas");
        btnD.setOnAction(event -> new Mesa_Taqueria());
        Button btnE = new Button("Orden");
        btnE.setOnAction(event -> new Orden_Taqueria());
        Button btnF = new Button("Productos");
        btnF.setOnAction(event -> new Producto_Taqueria());
        Button btnG = new Button("Promociones");
        btnG.setOnAction(event -> new Promocion_Taqueria());
        Button btnH = new Button("Servicio a domicilio");
        btnH.setOnAction(event -> new Servicio_domicilio_Taqueria());
        Button btnI = new Button("Usuarios");
        btnI.setOnAction(event -> new Usuario_Taqueria());
        botoneraSuperior.getChildren().addAll(btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI);
        botoneraInferior = new HBox(5);
        btnPDF = new Button("PDF");
        btnPDF.setOnAction(event -> new Generar_ticket_PDF(orden, detalles));
        btnGraficas = new Button("Gráficas");
        btnGraficas.setOnAction(event -> new Graficas_Taqueria().start(new Stage()));
        botoneraInferior.getChildren().addAll(btnPDF, btnGraficas);
        layoutPrincipal.getChildren().addAll(botoneraSuperior, botoneraInferior);
        VBox tableViewPlaceholder = new VBox();
        tableViewPlaceholder.setPrefHeight(200); // Ajustar al tamaño deseado
        layoutPrincipal.getChildren().add(tableViewPlaceholder);
        escena = new Scene(layoutPrincipal, 800, 600);
    }
}

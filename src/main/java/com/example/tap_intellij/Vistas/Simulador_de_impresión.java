package com.example.tap_intellij.Vistas;

import com.example.tap_intellij.Componentes.Impresión_de_tarea;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Simulador_de_impresión extends Stage {

    private TableView<Impresión_de_tarea> tablaTareas;
    private ProgressBar barraProgreso;
    private Button btnAgregarTarea, btnControlSimulador;
    private ObservableList<Impresión_de_tarea> listaTareas;
    private SimuladorHilo hiloSimulador;
    private boolean simuladorActivo;

    public Simulador_de_impresión() {
        Crear_UI();
        hiloSimulador = new SimuladorHilo();
        simuladorActivo = false;
        this.setTitle("Simulador de Impresión");
        this.setScene(new Scene(new VBox(tablaTareas, barraProgreso, btnAgregarTarea, btnControlSimulador)));
        this.show();
    }

    private void Crear_UI() {
        tablaTareas = new TableView<>();
        listaTareas = FXCollections.observableArrayList();
        tablaTareas.setItems(listaTareas);
        TableColumn<Impresión_de_tarea, String> columnaNoArchivo = new TableColumn<>("No. Archivo");
        columnaNoArchivo.setCellValueFactory(new PropertyValueFactory<>("noArchivo"));
        TableColumn<Impresión_de_tarea, String> columnaNombre = new TableColumn<>("Nombre de archivo");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreArchivo"));
        TableColumn<Impresión_de_tarea, Integer> columnaHojas = new TableColumn<>("Numero de hojas");
        columnaHojas.setCellValueFactory(new PropertyValueFactory<>("numeroHojas"));
        TableColumn<Impresión_de_tarea, String> columnaHoraAcceso = new TableColumn<>("Hora de acceso");
        columnaHoraAcceso.setCellValueFactory(new PropertyValueFactory<>("horaAcceso"));
        tablaTareas.getColumns().addAll(columnaNoArchivo, columnaNombre, columnaHojas, columnaHoraAcceso);
        barraProgreso = new ProgressBar(0);
        btnAgregarTarea = new Button("Agregar Tarea");
        btnAgregarTarea.setOnAction(event -> agregarTarea());
        btnControlSimulador = new Button("Iniciar Simulador");
        btnControlSimulador.setOnAction(event -> controlarSimulador());
    }

    private void agregarTarea() {
        Random random = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String nombreArchivo = "Archivo_" + dateFormat.format(new Date()) + ".txt";
        int numeroHojas = random.nextInt(50) + 1;
        String horaAcceso = new SimpleDateFormat("HH:mm:ss").format(new Date());
        Impresión_de_tarea tarea = new Impresión_de_tarea(String.valueOf(listaTareas.size() + 1), nombreArchivo, numeroHojas, horaAcceso);
        listaTareas.add(tarea);
    }

    private void controlarSimulador() {
        if (simuladorActivo) {
            simuladorActivo = false;
            btnControlSimulador.setText("Iniciar simulador");
            hiloSimulador.interrupt();
        } else {
            simuladorActivo = true;
            btnControlSimulador.setText("Detener simulador");
            hiloSimulador = new SimuladorHilo();
            hiloSimulador.start();
        }
    }

    private class SimuladorHilo extends Thread {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                if (!listaTareas.isEmpty() && simuladorActivo) {
                    Impresión_de_tarea tareaActual = listaTareas.get(0);
                    for (int i = 0; i < tareaActual.getNúmero_de_hojas(); i++) {
                        try {
                            Thread.sleep(100);
                            final double progreso = (i + 1) / (double) tareaActual.getNúmero_de_hojas();
                            Platform.runLater(() -> barraProgreso.setProgress(progreso));
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    Platform.runLater(() -> listaTareas.remove(tareaActual));
                }
            }
        }
    }
}

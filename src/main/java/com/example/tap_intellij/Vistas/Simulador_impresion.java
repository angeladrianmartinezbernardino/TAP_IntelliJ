package com.example.tap_intellij.Vistas;

import com.example.tap_intellij.Componentes.Impresion_tarea;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Simulador_impresion extends Stage {

    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene Escena;
    private TableView<Impresion_tarea> Tareas;
    private ProgressBar Barra_progreso;
    private Button Agregar_tarea, Control_simulador;
    private ObservableList<Impresion_tarea> Lista_tareas;
    private SimuladorHilo Simulador_hilo;
    private boolean Simulador_activo;
    private Random Aleatorio;
    private SimpleDateFormat Formato_fecha;
    private ImageView Imagen_Tarea;
    private String Nombre_archivo, Hora_acceso_archivo;
    private int Numero_hojas;
    private static final String Ruta = "/Imagenes/Simulador_impresion/Tarea.png"; // Cambia esto por la ruta de tu imagen

    public Simulador_impresion() {
        Aleatorio = new Random();
        Formato_fecha = new SimpleDateFormat("yyyyMMddHHmmss");
        CrearUI();
        Simulador_hilo = new SimuladorHilo();
        Simulador_activo = false;
        this.setTitle("Simulador de Impresión");
        this.setScene(Escena);
        this.show();
    }

    private void CrearUI() {
        Crear_barra_herramientas();
        Crear_tabla();
        Crear_barra_progreso();
        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(Tareas);
        bpnPrincipal.setBottom(Barra_progreso);
        pnlPrincipal = new Panel("Simulador de Impresión");
        pnlPrincipal.getStyleClass().add("panel-primary");
        pnlPrincipal.setBody(bpnPrincipal);
        Escena = new Scene(pnlPrincipal, 700, 400);
        Escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }

    private void Crear_barra_herramientas() {
        Imagen_Tarea = new ImageView(new Image(getClass().getResourceAsStream(Ruta)));
        Imagen_Tarea.setFitHeight(50);
        Imagen_Tarea.setFitWidth(50);
        Agregar_tarea = new Button();
        Agregar_tarea.setOnAction(event -> Agregar_tarea());
        Agregar_tarea.setPrefSize(50, 50);
        Agregar_tarea.setGraphic(Imagen_Tarea);
        Control_simulador = new Button("Iniciar simulador");
        Control_simulador.setOnAction(event -> Controlar_simulador());
        tlbMenu = new ToolBar(Agregar_tarea, Control_simulador);
    }

    private void Crear_tabla() {
        Tareas = new TableView<>();
        Lista_tareas = FXCollections.observableArrayList();
        Tareas.setItems(Lista_tareas);
        TableColumn<Impresion_tarea, String> TBC_Numero_archivo = new TableColumn<>("Número de archivo");
        TBC_Numero_archivo.setCellValueFactory(new PropertyValueFactory<>("Numero_archivo"));
        TableColumn<Impresion_tarea, String> TBC_Nombre_archivo = new TableColumn<>("Nombre del archivo");
        TBC_Nombre_archivo.setCellValueFactory(new PropertyValueFactory<>("Nombre_archivo"));
        TableColumn<Impresion_tarea, Integer> TBC_Numero_hojas = new TableColumn<>("Número de hojas a imprimir");
        TBC_Numero_hojas.setCellValueFactory(new PropertyValueFactory<>("Numero_hojas"));
        TableColumn<Impresion_tarea, String> TBC_Hora_acceso = new TableColumn<>("Hora de acceso a la cola de impresión");
        TBC_Hora_acceso.setCellValueFactory(new PropertyValueFactory<>("Hora_acceso"));
        Tareas.getColumns().addAll(TBC_Numero_archivo, TBC_Nombre_archivo, TBC_Numero_hojas, TBC_Hora_acceso);
    }

    private void Crear_barra_progreso() {
        Barra_progreso = new ProgressBar(0);
    }

    private void Agregar_tarea() {
        Nombre_archivo = "Archivo_" + Formato_fecha.format(new Date()) + ".txt";
        Numero_hojas = Aleatorio.nextInt(50) + 1;
        Hora_acceso_archivo = new SimpleDateFormat("HH:mm:ss").format(new Date());
        Impresion_tarea Tarea = new Impresion_tarea(String.valueOf(Lista_tareas.size() + 1), Nombre_archivo, Numero_hojas, Hora_acceso_archivo);
        Lista_tareas.add(Tarea);
        Tareas.refresh();
    }

    private void Controlar_simulador() {
        if (Simulador_activo) {
            Simulador_activo = false;
            Control_simulador.setText("Iniciar simulador");
            Simulador_hilo.interrupt();
        } else {
            Simulador_activo = true;
            Control_simulador.setText("Detener simulador");
            Simulador_hilo = new SimuladorHilo();
            Simulador_hilo.start();
        }
    }

    private class SimuladorHilo extends Thread {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                if (!Lista_tareas.isEmpty() && Simulador_activo) {
                    Impresion_tarea Tarea_actual = Lista_tareas.get(0);
                    for (int i = 0; i < Tarea_actual.getNumero_hojas(); i++) {
                        try {
                            Thread.sleep(100);
                            final double Progreso = (i + 1) / (double) Tarea_actual.getNumero_hojas();
                            Platform.runLater(() -> Barra_progreso.setProgress(Progreso));
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    Platform.runLater(() -> Lista_tareas.remove(Tarea_actual));
                }
            }
        }
    }
}

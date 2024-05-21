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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Simulador_impresion extends Stage {

    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private TableView<Impresion_tarea> tablaTareas;
    private ProgressBar barraProgreso;
    private Button btnAgregarTarea, btnControlSimulador;
    private ObservableList<Impresion_tarea> listaTareas;
    private SimuladorHilo hiloSimulador;
    private boolean simuladorActivo;
    private Random aleatorio;
    private SimpleDateFormat formatoFecha;
    private static final String IMAGEN_RUTA = "/Imagenes/Simulador_impresion/Tarea.png"; // Cambia esto por la ruta de tu imagen

    public Simulador_impresion() {
        aleatorio = new Random();
        formatoFecha = new SimpleDateFormat("yyyyMMddHHmmss");

        CrearUI();
        hiloSimulador = new SimuladorHilo();
        simuladorActivo = false;

        this.setTitle("Simulador de Impresión");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        CrearBarraDeHerramientas();
        CrearTabla();
        CrearBarraDeProgreso();

        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tablaTareas);
        bpnPrincipal.setBottom(barraProgreso);

        pnlPrincipal = new Panel("Simulador de Impresión");
        pnlPrincipal.getStyleClass().add("panel-primary");
        pnlPrincipal.setBody(bpnPrincipal);

        escena = new Scene(pnlPrincipal, 700, 400);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }

    private void CrearBarraDeHerramientas() {
        ImageView imvTarea = new ImageView(new Image(getClass().getResourceAsStream(IMAGEN_RUTA)));
        imvTarea.setFitHeight(50);
        imvTarea.setFitWidth(50);

        btnAgregarTarea = new Button();
        btnAgregarTarea.setOnAction(event -> AgregarTarea());
        btnAgregarTarea.setPrefSize(50, 50);
        btnAgregarTarea.setGraphic(imvTarea);

        btnControlSimulador = new Button("Iniciar simulador");
        btnControlSimulador.setOnAction(event -> ControlarSimulador());

        tlbMenu = new ToolBar(btnAgregarTarea, btnControlSimulador);
    }

    private void CrearTabla() {
        tablaTareas = new TableView<>();
        listaTareas = FXCollections.observableArrayList();
        tablaTareas.setItems(listaTareas);

        TableColumn<Impresion_tarea, String> tbcNumeroArchivo = new TableColumn<>("Número de archivo");
        tbcNumeroArchivo.setCellValueFactory(new PropertyValueFactory<>("Numero_archivo"));
        TableColumn<Impresion_tarea, String> tbcNombreArchivo = new TableColumn<>("Nombre del archivo");
        tbcNombreArchivo.setCellValueFactory(new PropertyValueFactory<>("Nombre_archivo"));
        TableColumn<Impresion_tarea, Integer> tbcNumeroHojas = new TableColumn<>("Número de hojas a imprimir");
        tbcNumeroHojas.setCellValueFactory(new PropertyValueFactory<>("Numero_hojas"));
        TableColumn<Impresion_tarea, String> tbcHoraAcceso = new TableColumn<>("Hora de acceso a la cola de impresión");
        tbcHoraAcceso.setCellValueFactory(new PropertyValueFactory<>("Hora_acceso"));

        tablaTareas.getColumns().addAll(tbcNumeroArchivo, tbcNombreArchivo, tbcNumeroHojas, tbcHoraAcceso);
    }

    private void CrearBarraDeProgreso() {
        barraProgreso = new ProgressBar(0);
    }

    private void AgregarTarea() {
        String nombreArchivo = "Archivo_" + formatoFecha.format(new Date()) + ".txt";
        int numeroHojas = aleatorio.nextInt(50) + 1;
        String horaAccesoArchivo = new SimpleDateFormat("HH:mm:ss").format(new Date());
        Impresion_tarea tarea = new Impresion_tarea(String.valueOf(listaTareas.size() + 1), nombreArchivo, numeroHojas, horaAccesoArchivo);
        listaTareas.add(tarea);
        tablaTareas.refresh();
    }

    private void ControlarSimulador() {
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
                    Impresion_tarea tareaActual = listaTareas.get(0);
                    for (int i = 0; i < tareaActual.getNumero_hojas(); i++) {
                        try {
                            Thread.sleep(100);
                            final double progreso = (i + 1) / (double) tareaActual.getNumero_hojas();
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

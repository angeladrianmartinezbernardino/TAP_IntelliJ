package com.example.tap_intellij.Vistas.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Producto_DAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class Graficas_Taqueria extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();

        JFreeChart barChart = ChartFactory.createBarChart(
                "Productos Más Vendidos",
                "Producto",
                "Cantidad",
                createDatasetProductosVendidos(),
                PlotOrientation.VERTICAL,
                true, true, false);

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Ventas por Día",
                "Día",
                "Ventas",
                createDatasetVentasPorDia(),
                PlotOrientation.VERTICAL,
                true, true, false);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Empleado con Más Ventas",
                createDatasetEmpleadoVentas(),
                true, true, false);

        ChartViewer barChartViewer = new ChartViewer(barChart);
        ChartViewer lineChartViewer = new ChartViewer(lineChart);
        ChartViewer pieChartViewer = new ChartViewer(pieChart);

        root.getChildren().addAll(barChartViewer, lineChartViewer, pieChartViewer);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Gráficas de Taquería");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private CategoryDataset createDatasetProductosVendidos() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Producto_DAO productoDAO = new Producto_DAO();
        productoDAO.getProductosMasVendidos().forEach((producto, cantidad) -> {
            dataset.addValue(cantidad, producto, producto);
        });
        return dataset;
    }

    private CategoryDataset createDatasetVentasPorDia() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // Aquí debes implementar la lógica para obtener las ventas por día
        return dataset;
    }

    private PieDataset createDatasetEmpleadoVentas() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        // Aquí debes implementar la lógica para obtener el empleado con más ventas
        return dataset;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

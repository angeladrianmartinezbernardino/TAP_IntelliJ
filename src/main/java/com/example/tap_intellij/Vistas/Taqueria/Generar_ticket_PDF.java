package com.example.tap_intellij.Vistas.Taqueria;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.TextFragment;
import com.example.tap_intellij.Modelos.Taqueria.Orden_DAO;
import com.example.tap_intellij.Modelos.Taqueria.Orden_detalle_DAO;
import javafx.collections.ObservableList;

public class Generar_ticket_PDF {
    private Orden_DAO orden;
    private ObservableList<Orden_detalle_DAO> detalles;

    public Generar_ticket_PDF(Orden_DAO orden, ObservableList<Orden_detalle_DAO> detalles) {
        this.orden = orden;
        this.detalles = detalles;
        generarTicketPDF();
    }

    public void generarTicketPDF() {
        Document pdfDocument = new Document();
        Page page = pdfDocument.getPages().add();
        TextFragment titulo = new TextFragment("Ticket de Compra");
        titulo.getTextState().setFontSize(20);
        page.getParagraphs().add(titulo);
        page.getParagraphs().add(new TextFragment("ID Orden: " + orden.getIdOrden()));
        page.getParagraphs().add(new TextFragment("Fecha: " + orden.getFecha()));
        page.getParagraphs().add(new TextFragment("ID Empleado: " + orden.getIdEmpleado()));
        page.getParagraphs().add(new TextFragment("ID Mesa: " + orden.getIdMesa()));
        for (Orden_detalle_DAO detalle : detalles) {
            page.getParagraphs().add(new TextFragment("ID Producto: " + detalle.getIdProducto() + " | Cantidad: " + detalle.getCantidad() + " | Precio: " + detalle.getPrecio()));
        }
        double total = detalles.stream().mapToDouble(d -> d.getCantidad() * d.getPrecio()).sum();
        page.getParagraphs().add(new TextFragment("Total: $" + total));
        try {
            pdfDocument.save("TicketCompra.pdf");
            System.out.println("Ticket de compra generado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error al generar el ticket de compra.");
        }
    }
}

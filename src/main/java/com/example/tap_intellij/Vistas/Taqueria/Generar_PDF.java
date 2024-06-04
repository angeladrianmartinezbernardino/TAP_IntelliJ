package com.example.tap_intellij.Vistas.Taqueria;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.TextFragment;

public class Generar_PDF {
    public Generar_PDF() {
        generarDocumentoPDF();
    }

    public void generarDocumentoPDF() {
        Document pdfDocument = new Document();
        Page page = pdfDocument.getPages().add();
        TextFragment textFragment = new TextFragment("Hola, este es un documento PDF generado con Aspose.PDF!");
        page.getParagraphs().add(textFragment);
        try {
            pdfDocument.save("GeneradoDocumento.pdf");
            System.out.println("Documento PDF generado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error al generar el documento PDF.");
        }
    }
}

package org.example;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.InputStream;
import java.io.IOException;



public class PDFReader {
    public static String extractTextFromPDF(String filePath) {
        StringBuilder text = new StringBuilder();

        try (InputStream pdfFileStream = PDFReader.class.getResourceAsStream("/" + filePath);
             PDDocument document = PDDocument.load(pdfFileStream)) {

            if (!document.isEncrypted()) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                text.append(pdfStripper.getText(document));
            } else {
                System.out.println("The PDF file is encrypted and cannot be read.");
            }
        } catch (IOException e) {
            System.err.println("Error loading or reading the PDF file: " + e.getMessage());
        }

        return text.toString();
    }
}

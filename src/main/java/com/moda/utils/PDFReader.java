package com.moda.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFReader {

    private PDFReader(){
        throw new IllegalStateException("This is utility class");
    }

    public static String readPDFContent(String pdfURL) throws IOException {
        URL url = new URL(pdfURL);
        InputStream inputStream = url.openStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        PDDocument document = PDDocument.load(bufferedInputStream);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        return pdfTextStripper.getText(document);
    }
}

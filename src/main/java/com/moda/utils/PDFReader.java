package com.moda.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFReader {
    public static String ReadPDFContent(String _pdfURL) throws IOException {
        URL pdfURL = new URL(_pdfURL);
        InputStream inputStream = pdfURL.openStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        PDDocument document = PDDocument.load(bufferedInputStream);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        return pdfTextStripper.getText(document);
    }
}

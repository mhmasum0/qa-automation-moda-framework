package com.moda.listeners;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.moda.utils.ConfigFileReader;
import com.moda.utils.EmailSender;
import com.moda.utils.LogHelper;
import com.moda.utils.PDFGenerator;
import org.testng.*;
import org.testng.xml.XmlSuite;
import com.itextpdf.text.pdf.PdfWriter;

import javax.activation.DataSource;
import javax.activation.FileDataSource;


public class PDFReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        String reportPath = PDFGenerator.generatePdfReport(xmlSuites, suites, outputDirectory);
        if (reportPath != null) {
            DataSource source = new FileDataSource(reportPath);
            EmailSender.sendEmailPDFFile(source, true);
        }
    }

}

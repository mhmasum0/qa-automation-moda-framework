package com.moda.listeners;

import java.util.List;

import com.moda.utils.EmailSender;
import com.moda.utils.PDFGenerator;
import org.testng.*;
import org.testng.xml.XmlSuite;

import javax.activation.DataSource;
import javax.activation.FileDataSource;


public class PDFReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        String reportPath = PDFGenerator.generatePdfReport(suites);
        if (reportPath != null) {
            DataSource source = new FileDataSource(reportPath);
            EmailSender.sendEmailPDFFile(source, true);
        }
    }

}

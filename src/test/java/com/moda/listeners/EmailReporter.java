package com.moda.listeners;

import com.moda.utils.EmailSender;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.List;

public class EmailReporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        File htmlReport = new File(outputDirectory, "emailable-report.html");

        EmailSender.sendEmailHTMLFile(htmlReport, true);
    }

}

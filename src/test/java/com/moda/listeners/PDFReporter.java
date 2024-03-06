package com.moda.listeners;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.moda.utils.ConfigFileReader;
import org.testng.*;
import org.testng.xml.XmlSuite;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        try {
            String reportPath = "report-" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(reportPath));
            document.open();

            // Add additional information
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            PdfPCell cell;
            cell = new PdfPCell(new Paragraph("Additional Information"));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            table.addCell("Email:");
            table.addCell("your-email@example.com");
            table.addCell("URL:");
            table.addCell("http://example.com");
            table.addCell("Credentials:");
            table.addCell("username - yourusername, password - yourpassword");
            document.add(table);

            document.add(new Paragraph("\n"));

            for (ISuite suite : suites) {
                String suiteName = suite.getName();
                for (ISuiteResult suiteResult : suite.getResults().values()) {
                    ITestContext context = suiteResult.getTestContext();
                    document.add(new Paragraph("Suite Name: " + suiteName));
                    document.add(new Paragraph("Test Name: " + context.getName()));
                    document.add(new Paragraph("Passed tests: " + context.getPassedTests().size()));
                    document.add(new Paragraph("Failed tests: " + context.getFailedTests().size()));
                    document.add(new Paragraph("Skipped tests: " + context.getSkippedTests().size()));
                    document.add(new Paragraph("Test Cases:"));
                    for (ITestNGMethod method : context.getAllTestMethods()) {
                        Font font = new Font();
                        font.setStyle(Font.BOLD);
                        String result = "";
                        if (context.getPassedTests().getAllMethods().contains(method)) {
                            result = "Passed";
                            Paragraph para = new Paragraph("- " + method.getMethodName() + ": ", font);
                            para.add(new Chunk(result, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.GREEN)));
                            document.add(para);
                        } else if (context.getFailedTests().getAllMethods().contains(method)) {
                            result = "Failed";
                            document.add(new Paragraph("- " + method.getMethodName() + ": " + result, font));
                        } else {
                            result = "Skipped";
                            document.add(new Paragraph("- " + method.getMethodName() + ": " + result, font));
                        }
                    }
                    document.add(new Paragraph("\n"));
                }
            }
            document.close();
            System.out.println("PDF Report generated at: " + reportPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

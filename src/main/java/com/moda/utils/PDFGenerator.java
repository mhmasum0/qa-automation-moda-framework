package com.moda.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ISuite;
import org.testng.ISuiteResult;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PDFGenerator {

    private PDFGenerator(){
        throw new IllegalStateException("This is utility class");
    }

    public static String generatePdfReport(List<ISuite> suites) {
        String reportPath = null;
        try {
            reportPath = "report-" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(reportPath));
            document.open();

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
            table.addCell("testingusermoda1@gmail.com");

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
                        String result;
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
            LogHelper.getLogger().info("PDF Report generated at: {} ", reportPath);
        } catch (Exception e) {
            LogHelper.getLogger().error(e.getMessage());
        }
        return reportPath;
    }
}
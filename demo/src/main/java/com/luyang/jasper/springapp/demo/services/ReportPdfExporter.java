package com.luyang.jasper.springapp.demo.services;

import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ReportPdfExporter extends JRPdfExporter{

    @Getter
    @Setter
    private JasperPrint jasperPrint;

    public ReportPdfExporter(){}

    public ReportPdfExporter(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public boolean exportToPdf(String fileName) {

        this.setExporterInput(new SimpleExporterInput(jasperPrint));
        this.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName));

        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setEncrypted(true);
        this.setConfiguration(reportConfig);
        this.setConfiguration(exportConfig);
        try {
            this.exportReport();
            return true;
        } catch (JRException ex) {
            Logger.getLogger(ReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}

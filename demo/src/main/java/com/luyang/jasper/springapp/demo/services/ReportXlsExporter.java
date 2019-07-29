package com.luyang.jasper.springapp.demo.services;

import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportXlsExporter extends JRXlsxExporter {

    @Getter
    @Setter
    private JasperPrint jasperPrint;

    public ReportXlsExporter(){}

    public ReportXlsExporter(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public boolean exportToXlsx(String fileName, String sheetName) {
        this.setExporterInput(new SimpleExporterInput(jasperPrint));
        this.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName));

        SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
        reportConfig.setSheetNames(new String[]{sheetName});

        this.setConfiguration(reportConfig);

        try {
            this.exportReport();
            return true;
        } catch (JRException ex) {
            Logger.getLogger(ReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

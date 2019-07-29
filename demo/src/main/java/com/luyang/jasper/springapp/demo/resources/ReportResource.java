package com.luyang.jasper.springapp.demo.resources;

import com.luyang.jasper.springapp.demo.services.ReportFiller;
import com.luyang.jasper.springapp.demo.services.ReportPdfExporter;
import com.luyang.jasper.springapp.demo.services.ReportXlsExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Description(value = "Jasper Report Resource Handler")
@RestController
@RequestMapping("/api/report")
public class ReportResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportResource.class);

    @Autowired
    private ReportPdfExporter pdfExporter;

    @Autowired
    private ReportXlsExporter xlsExporter;

    @Autowired
    private ReportFiller reportFiller;

    /**
     * Endpoint for generating simple PDF report
     *
     * @return HTTP code and string
     */
    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generatePDFReport() {
        LOGGER.info("Generating Fund PDF report.");

     //   ReportPdfExporter pdfExporter = context.getBean(ReportPdfExporter.class);
        boolean success =false;
        if(configFilter().getJasperPrint() != null) {
            pdfExporter.setJasperPrint(configFilter().getJasperPrint());
            success = pdfExporter.exportToPdf("fundReport.pdf");
        }
        if (success) {
            return new ResponseEntity<>("PDF Report is successfully generated ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("PDF Report cannot be generated ", HttpStatus.OK);
        }
    }

    /**
     * Endpoint for generating simple DOCx report
     */
    @GetMapping(value = "/xls", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateXLSReport() {
        LOGGER.info("Generating Fund XLS report.");

        boolean success =false;
        if(configFilter().getJasperPrint() != null) {
            xlsExporter.setJasperPrint(configFilter().getJasperPrint());
             success = xlsExporter.exportToXlsx("fundReport.xlsx", "Fund Data");
        }
        if (success) {
            return new ResponseEntity<>("XLS Report is successfully generated ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("XLS Report cannot be generated ", HttpStatus.OK);
        }
    }

    private ReportFiller configFilter() {
        reportFiller.setReportFileName("fundReport.jrxml");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", "Fund Report Example");

        reportFiller.setParameters(parameters);
        reportFiller.fillReport();

        return reportFiller;
    }

}

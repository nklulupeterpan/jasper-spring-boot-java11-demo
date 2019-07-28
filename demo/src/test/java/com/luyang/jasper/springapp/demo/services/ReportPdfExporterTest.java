package com.luyang.jasper.springapp.demo.services;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
class ReportPdfExporterTest {

    @Spy
    ReportPdfExporter toTest;

    @BeforeEach
    void setUp() {
        toTest = new ReportPdfExporter();
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void exportToPdf_success() throws JRException {
        doNothing().when(toTest).exportReport();
        toTest.setJasperPrint(new JasperPrint());
        boolean success = toTest.exportToPdf("report file");
        assertTrue(success);

    }

    @Test
    void exportToPdf_fail() throws JRException {
        toTest.setJasperPrint(new JasperPrint());
        doThrow(new JRException("Test JR Exception")).when(toTest).exportReport();

        boolean success = toTest.exportToPdf("report file");
        assertFalse(success);

    }

}
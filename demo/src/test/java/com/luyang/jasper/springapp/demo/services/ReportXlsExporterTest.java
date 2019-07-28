package com.luyang.jasper.springapp.demo.services;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ReportXlsExporterTest {

    @Spy
    ReportXlsExporter toTest;

    @BeforeEach
    void setUp() {
        toTest =  new ReportXlsExporter();
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void exportToXlsx_success() {

        toTest.setJasperPrint(new JasperPrint());
        boolean success = toTest.exportToXlsx("report file", "sheet name");
        assertTrue(success);

    }

    @Test
    void exportToXlsx_fail() throws JRException {
        toTest.setJasperPrint(new JasperPrint());
        doThrow(new JRException("Test JR Exception")).when(toTest).exportReport();

        boolean success = toTest.exportToXlsx("report file", "sheet name");
        asserFalse(success);

    }
}

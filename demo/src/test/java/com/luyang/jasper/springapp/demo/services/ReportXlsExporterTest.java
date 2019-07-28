package com.luyang.jasper.springapp.demo.services;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

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
    void exportToXlsx_success() throws JRException {

        doNothing().when(toTest).exportReport();
        toTest.setJasperPrint(new JasperPrint());
        boolean success = toTest.exportToXlsx("report file", "sheet name");
        assertTrue(success);

    }

    @Test
    void exportToXlsx_fail() throws JRException {
        toTest.setJasperPrint(new JasperPrint());
        doThrow(new JRException("Test JR Exception")).when(toTest).exportReport();

        boolean success = toTest.exportToXlsx("report file", "sheet name");
        assertFalse(success);
    }
}

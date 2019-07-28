package com.luyang.jasper.springapp.demo.resources;

import com.luyang.jasper.springapp.demo.DemoApplication;
import com.luyang.jasper.springapp.demo.services.ReportFiller;
import com.luyang.jasper.springapp.demo.services.ReportPdfExporter;
import com.luyang.jasper.springapp.demo.services.ReportXlsExporter;
import net.sf.jasperreports.engine.JasperPrint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
//@WebAppConfiguration
@WebMvcTest
class ReportResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportPdfExporter reportPdfExporter;


    @MockBean
    private ReportXlsExporter reportXlsExporter;

    @MockBean
    private ReportFiller reportFiller;

    protected void setUp() {
    }

//    @Mock
//    ReportPdfExporter reportPdfExporter;

    @Test
    void generatePDFReportSuccess() throws Exception {
        given(reportPdfExporter.exportToPdf(anyString())).willReturn(true);

        mockMvc.perform(get("/api/report/pdf"))
                .andExpect(status().isOk())
        .andExpect(content().string("PDF Report is successfully generated "));
    }

    @Test
    void generatePDFReportFail() throws Exception {
        given(reportPdfExporter.exportToPdf(anyString())).willReturn(false);

        mockMvc.perform(get("/api/report/pdf"))
                .andExpect(status().isOk())
                .andExpect(content().string("PDF Report cannot be generated "));
    }

    @Test
    void generateXLSReportSuccess() throws Exception {
        given(reportXlsExporter.exportToXlsx(anyString(),anyString())).willReturn(true);

        mockMvc.perform(get("/api/report/xls"))
                .andExpect(status().isOk())
                .andExpect(content().string("XLS Report is successfully generated "));
    }

    @Test
    void generateXLSReportFail() throws Exception {
        given(reportXlsExporter.exportToXlsx(anyString(),anyString())).willReturn(false);

        mockMvc.perform(get("/api/report/xls"))
                .andExpect(status().isOk())
                .andExpect(content().string("XLS Report cannot be generated "));
    }
}
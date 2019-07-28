package com.luyang.jasper.springapp.demo.resources;


import com.luyang.jasper.springapp.demo.services.ReportFiller;
import com.luyang.jasper.springapp.demo.services.ReportPdfExporter;
import com.luyang.jasper.springapp.demo.services.ReportXlsExporter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
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
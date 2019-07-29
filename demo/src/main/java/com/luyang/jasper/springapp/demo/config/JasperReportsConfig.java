package com.luyang.jasper.springapp.demo.config;

import javax.sql.DataSource;

import com.luyang.jasper.springapp.demo.services.ReportFiller;
import com.luyang.jasper.springapp.demo.services.ReportPdfExporter;
import com.luyang.jasper.springapp.demo.services.ReportXlsExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class JasperReportsConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("classpath:fund-schema.sql").build();
    }

    @Bean
    public ReportPdfExporter pdfExporter(){
        return new ReportPdfExporter();
    }

    @Bean
    public ReportXlsExporter xlsExporter(){
        return new ReportXlsExporter();
    }

    @Bean
    public ReportFiller reportFiller(){
        return new ReportFiller();
    }
}

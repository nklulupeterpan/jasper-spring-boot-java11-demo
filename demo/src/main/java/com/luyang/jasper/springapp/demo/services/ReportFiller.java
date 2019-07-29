package com.luyang.jasper.springapp.demo.services;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportFiller {
    @Setter
    @Getter
    private String reportFileName;

    @Setter
    @Getter
    private JasperReport jasperReport;

    @Setter
    @Getter
    private JasperPrint jasperPrint;

    @Autowired
    @Setter
    @Getter
    private DataSource dataSource;

    @Setter
    @Getter
    private Map<String, Object> parameters;

    public ReportFiller() {
        parameters = new HashMap<>();
    }


    public void fillReport() {
        try {
            InputStream reportStream = getClass().getResourceAsStream("/".concat(reportFileName));
            if(reportStream != null) {
                jasperReport = JasperCompileManager.compileReport(reportStream);
                JRSaver.saveObject(jasperReport, reportFileName.replace(".jrxml", ".jasper"));
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
            }
            Logger.getLogger(ReportFiller.class.getName()).log(Level.WARNING, "No Report template found with name " + reportFileName);
        } catch (JRException | SQLException ex) {
            Logger.getLogger(ReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

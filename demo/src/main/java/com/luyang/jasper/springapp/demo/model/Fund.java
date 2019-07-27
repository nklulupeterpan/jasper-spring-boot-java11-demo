package com.luyang.jasper.springapp.demo.model;

import org.springframework.context.annotation.Description;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Description(value = "fund Model Definition")
public class Fund implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    @NotNull @NotBlank private String fundCode;
    @NotNull @NotBlank private String fundName;
    @NotNull @NotBlank private String bank;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}

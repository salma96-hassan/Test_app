package com.Test.app.Test_app.io.reqDto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class InvoiceLineReqDto {
    private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal value;
    private Long invoiceId;

    // Calculate the value dynamically if needed
    public BigDecimal calculateValue() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}

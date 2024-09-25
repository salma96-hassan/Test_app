package com.Test.app.Test_app.io.respDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceLineRespDto {
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
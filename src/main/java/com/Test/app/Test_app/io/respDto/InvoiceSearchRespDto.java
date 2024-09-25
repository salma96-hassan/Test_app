package com.Test.app.Test_app.io.respDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceSearchRespDto {
    private LocalDateTime dateTime;

    private String providerName;

    private String address;

    private BigDecimal total;

    private BigDecimal paid;

    private BigDecimal remaining;

    private String deliveredBy;

    private List<InvoiceLineRespDto> invoiceLinesList;
}

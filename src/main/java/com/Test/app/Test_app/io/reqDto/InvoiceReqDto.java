package com.Test.app.Test_app.io.reqDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceReqDto {
    private Long id;
    private LocalDateTime dateTime;
    private Long providerId;
    private String address;
    private BigDecimal total;
    private BigDecimal paid;
    private BigDecimal remaining;
    private String deliveredBy;
    private List<InvoiceLineReqDto> invoiceLinesList;
}

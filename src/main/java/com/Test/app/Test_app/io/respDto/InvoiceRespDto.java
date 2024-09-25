package com.Test.app.Test_app.io.respDto;

import com.Test.app.Test_app.io.reqDto.InvoiceReqDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceRespDto {
    private LocalDateTime dateTime;

    private Long providerId;

    private String address;

    private BigDecimal total;

    private BigDecimal paid;

    private BigDecimal remaining;

    private String deliveredBy;

    private List<InvoiceLineRespDto> invoiceLineRespDtoList;
}

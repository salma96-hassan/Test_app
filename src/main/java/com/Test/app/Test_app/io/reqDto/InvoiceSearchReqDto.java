package com.Test.app.Test_app.io.reqDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvoiceSearchReqDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String providerName;
}

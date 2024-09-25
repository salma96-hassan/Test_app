package com.Test.app.Test_app.io.reqDto;

import com.Test.app.Test_app.io.entities.Invoice;
import lombok.Data;

import java.util.List;

@Data
public class ProviderReqDto {
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String service;

    private String note;
}

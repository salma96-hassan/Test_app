package com.Test.app.Test_app.io.mapper;

import com.Test.app.Test_app.io.entities.Invoice;
import com.Test.app.Test_app.io.entities.InvoiceLines;
import com.Test.app.Test_app.io.reqDto.InvoiceLineReqDto;
import com.Test.app.Test_app.io.reqDto.InvoiceReqDto;
import com.Test.app.Test_app.io.reqDto.InvoiceSearchReqDto;
import com.Test.app.Test_app.io.respDto.InvoiceLineRespDto;
import com.Test.app.Test_app.io.respDto.InvoiceRespDto;
import com.Test.app.Test_app.io.respDto.InvoiceSearchRespDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(target = "invoiceLineRespDtoList", source = "invoiceLinesList")
    @Mapping(target = "providerId", source = "providerId.id")
    InvoiceRespDto toResponseDto(Invoice invoice);

    @Mapping(target = "providerId.id", source = "providerId")
    Invoice toEntity(InvoiceReqDto invoiceRequestDTO);

    @Mapping(target = "invoiceId", source = "invoiceId.id")
    InvoiceLineRespDto toLineResponseDto(InvoiceLines invoiceLine);

    @Mapping(target = "invoiceId.id", source = "invoiceId")
    InvoiceLines toLineEntity(InvoiceLineReqDto invoiceLineRequestDTO);

    List<InvoiceSearchRespDto> toResponseSearchDtoList(List<Invoice> invoices);

    List<InvoiceLineRespDto> toLineRespSearchList(List<InvoiceLines> invoiceLinesList);

}

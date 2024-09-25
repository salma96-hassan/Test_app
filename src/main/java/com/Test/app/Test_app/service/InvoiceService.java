package com.Test.app.Test_app.service;


import com.Test.app.Test_app.io.reqDto.InvoiceLineReqDto;
import com.Test.app.Test_app.io.reqDto.InvoiceReqDto;
import com.Test.app.Test_app.io.reqDto.InvoiceSearchReqDto;
import com.Test.app.Test_app.io.respDto.InvoiceLineRespDto;
import com.Test.app.Test_app.io.respDto.InvoiceRespDto;
import com.Test.app.Test_app.io.respDto.InvoiceSearchRespDto;

import java.util.List;

public interface InvoiceService {
    public InvoiceRespDto createInvoice(InvoiceReqDto invoiceReqDto);

    public InvoiceLineRespDto createInvoiceLine(InvoiceLineReqDto invoiceLineReqDto) throws Exception;

    public void deleteInvoice(Long invoiceId) throws Exception;

    public void deleteInvoiceLine(Long invoiceLineId) throws Exception;

    public InvoiceRespDto updateInvoice(InvoiceReqDto invoiceReqDto) throws Exception;

    public InvoiceLineRespDto updateInvoiceLine(InvoiceLineReqDto invoiceLineReqDto) throws Exception;

    public List<InvoiceSearchRespDto> findInvoices(InvoiceSearchReqDto invoiceSearchReqDto) throws Exception;

    public List<InvoiceLineRespDto> findInvoiceLines(Long invoiceId) throws Exception;

}

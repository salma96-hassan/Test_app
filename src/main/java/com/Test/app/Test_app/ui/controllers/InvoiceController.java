package com.Test.app.Test_app.ui.controllers;

import com.Test.app.Test_app.io.reqDto.InvoiceLineReqDto;
import com.Test.app.Test_app.io.reqDto.InvoiceReqDto;
import com.Test.app.Test_app.io.reqDto.InvoiceSearchReqDto;
import com.Test.app.Test_app.io.respDto.InvoiceLineRespDto;
import com.Test.app.Test_app.io.respDto.InvoiceRespDto;
import com.Test.app.Test_app.io.respDto.InvoiceSearchRespDto;
import com.Test.app.Test_app.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;


    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceReqDto invoiceReqDto) {
        InvoiceRespDto invoiceRespDto = invoiceService.createInvoice(invoiceReqDto);
        return new ResponseEntity<>(invoiceRespDto, HttpStatus.OK);
    }

    @PostMapping("/invoiceLine")
    public ResponseEntity<?> createInvoiceLine(@RequestBody InvoiceLineReqDto invoiceLineReqDto) throws Exception {
        InvoiceLineRespDto invoiceLineRespDto = invoiceService.createInvoiceLine(invoiceLineReqDto);
        return new ResponseEntity<>(invoiceLineRespDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateInvoice(@RequestBody InvoiceReqDto invoiceReqDto) throws Exception {
        InvoiceRespDto invoiceRespDto = invoiceService.updateInvoice(invoiceReqDto);
        return new ResponseEntity<>(invoiceRespDto, HttpStatus.OK);
    }

    @PutMapping("/invoiceLine")
    public ResponseEntity<?> updateInvoiceLine(@RequestBody InvoiceLineReqDto invoiceLineReqDto) throws Exception {
        InvoiceLineRespDto invoiceLineRespDto = invoiceService.updateInvoiceLine(invoiceLineReqDto);
        return new ResponseEntity<>(invoiceLineRespDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long id) throws Exception {
        invoiceService.deleteInvoice(id);
        return new ResponseEntity<>("Invoice Deleted", HttpStatus.OK);
    }

    @DeleteMapping("/invoiceLine/{id}")
    public ResponseEntity<?> deleteInvoiceLine(@PathVariable Long id) throws Exception {
        invoiceService.deleteInvoiceLine(id);
        return new ResponseEntity<>("InvoiceLine Deleted", HttpStatus.OK);
    }

    @GetMapping("/invoices")
    public ResponseEntity<?> findInvoices(@RequestBody InvoiceSearchReqDto invoiceSearchReqDto) throws Exception {
        List<InvoiceSearchRespDto> invoiceSearchRespDtos = invoiceService.findInvoices(invoiceSearchReqDto);
        return new ResponseEntity<>(invoiceSearchRespDtos, HttpStatus.OK);
    }

    @GetMapping("/invoiceLines/{invoiceId}")
    public ResponseEntity<?> findInvoiceLines(@PathVariable Long invoiceId) throws Exception {
        List<InvoiceLineRespDto> invoiceLineRespDtoList = invoiceService.findInvoiceLines(invoiceId);
        return new ResponseEntity<>(invoiceLineRespDtoList, HttpStatus.OK);
    }
}

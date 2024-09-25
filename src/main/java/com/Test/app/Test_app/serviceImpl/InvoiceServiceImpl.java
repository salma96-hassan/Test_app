package com.Test.app.Test_app.serviceImpl;

import com.Test.app.Test_app.io.entities.Invoice;
import com.Test.app.Test_app.io.entities.InvoiceLines;
import com.Test.app.Test_app.io.entities.Provider;
import com.Test.app.Test_app.io.mapper.InvoiceMapper;
import com.Test.app.Test_app.io.repository.InvoiceLinesRepository;
import com.Test.app.Test_app.io.repository.InvoiceRepository;
import com.Test.app.Test_app.io.repository.ProviderRepository;
import com.Test.app.Test_app.io.reqDto.InvoiceLineReqDto;
import com.Test.app.Test_app.io.reqDto.InvoiceReqDto;
import com.Test.app.Test_app.io.reqDto.InvoiceSearchReqDto;
import com.Test.app.Test_app.io.respDto.InvoiceLineRespDto;
import com.Test.app.Test_app.io.respDto.InvoiceRespDto;
import com.Test.app.Test_app.io.respDto.InvoiceSearchRespDto;
import com.Test.app.Test_app.service.InvoiceService;
import com.Test.app.Test_app.specifications.InvoiveSpecifications;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceMapper invoiceMapper;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceLinesRepository invoiceLinesRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Override
    public InvoiceRespDto createInvoice(InvoiceReqDto invoiceReqDto) {
        Invoice invoice = invoiceMapper.toEntity(invoiceReqDto);
        BigDecimal total = invoice.getInvoiceLinesList().stream().map(value -> value.getValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
        invoice.setTotal(total);
        invoice.setRemaining(total.subtract(invoice.getPaid()));
        for (InvoiceLines invoiceLines : invoice.getInvoiceLinesList()) {
            invoiceLines.setInvoiceId(invoice);
        }
        invoiceRepository.save(invoice);
        InvoiceRespDto invoiceRespDto = invoiceMapper.toResponseDto(invoice);
        return invoiceRespDto;
    }

    @Override
    public InvoiceLineRespDto createInvoiceLine(InvoiceLineReqDto invoiceLineReqDto) throws Exception {
        try {
            Optional<Invoice> invoice = invoiceRepository.findById(invoiceLineReqDto.getInvoiceId());
            if (invoice.isPresent()) {
                InvoiceLines invoiceLines = invoiceMapper.toLineEntity(invoiceLineReqDto);
                invoiceLines.setInvoiceId(invoice.get());
                invoiceLinesRepository.save(invoiceLines);
                InvoiceLineRespDto invoiceLineRespDto = invoiceMapper.toLineResponseDto(invoiceLines);
                return invoiceLineRespDto;
            } else {
                throw new BadRequestException("Invoice not found");
            }
        } catch (Exception ex) {
            throw new BadRequestException("Invoice not found");
        }

    }

    @Override
    public void deleteInvoice(Long invoiceId) throws Exception {
        try {
            Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
            if (invoice.isPresent()) {
                invoiceRepository.delete(invoice.get());
            } else {
                throw new BadRequestException("Invoice not found");
            }
        } catch (Exception ex) {
            throw new BadRequestException("Invoice not found");
        }
    }

    @Override
    public void deleteInvoiceLine(Long invoiceLineId) throws Exception {
        try {
            Optional<InvoiceLines> invoiceLines = invoiceLinesRepository.findById(invoiceLineId);
            if (invoiceLines.isPresent()) {
                Optional<Invoice> invoice = invoiceRepository.findById(invoiceLines.get().getInvoiceId().getId());
                BigDecimal total = invoice.get().getTotal().subtract(invoiceLines.get().getValue());
                invoice.get().setTotal(total);
                invoiceRepository.save(invoice.get());
                invoiceLinesRepository.delete(invoiceLines.get());
            } else {
                throw new BadRequestException("InvoiceLine not found");
            }
        } catch (Exception ex) {
            throw new BadRequestException("InvoiceLine not found");
        }
    }

    @Override
    public InvoiceRespDto updateInvoice(InvoiceReqDto invoiceReqDto) throws Exception {
        try {
            Optional<Invoice> invoice = invoiceRepository.findById(invoiceReqDto.getId());
            if (invoice.isPresent()) {
                if (invoiceReqDto.getDateTime() != null) {
                    invoice.get().setDateTime(invoiceReqDto.getDateTime());
                }
                if (invoiceReqDto.getProviderId() != null) {
                    Optional<Provider> provider = providerRepository.findById(invoiceReqDto.getProviderId());
                    invoice.get().setProviderId(provider.get());
                }
                if (invoiceReqDto.getAddress() != null) {
                    invoice.get().setAddress(invoiceReqDto.getAddress());
                }
                if (invoiceReqDto.getPaid() != null) {
                    invoice.get().setPaid(invoiceReqDto.getPaid());
                }
                if (invoiceReqDto.getDeliveredBy() != null) {
                    invoice.get().setDeliveredBy(invoiceReqDto.getDeliveredBy());
                }
                if (invoiceReqDto.getInvoiceLinesList() != null) {
                    updateInvoiceLinesOfInvoice(invoiceReqDto.getInvoiceLinesList(), invoice.get());
                }
                updateTotalInvoice(invoice.get());
                InvoiceRespDto invoiceRespDto = invoiceMapper.toResponseDto(invoice.get());
                return invoiceRespDto;
            } else {
                throw new BadRequestException("Invoice not found");
            }
        } catch (Exception ex) {
            throw new BadRequestException("Invoice not found");
        }

    }

    @Override
    public InvoiceLineRespDto updateInvoiceLine(InvoiceLineReqDto invoiceLineReqDto) throws Exception {
        try {
            Optional<InvoiceLines> invoiceLines = invoiceLinesRepository.findById(invoiceLineReqDto.getId());
            if (invoiceLines.isPresent()) {
                InvoiceLines line = invoiceLines.get();
                Optional<Invoice> invoice = invoiceRepository.findById(line.getInvoiceId().getId());
                line.setProductName(invoiceLineReqDto.getProductName());
                line.setQuantity(invoiceLineReqDto.getQuantity());
                line.setPrice(invoiceLineReqDto.getPrice());
                line.setInvoiceId(invoice.get());
                invoiceLinesRepository.save(line);
                updateTotalInvoice(invoice.get());
                InvoiceLineRespDto invoiceLineRespDto = invoiceMapper.toLineResponseDto(invoiceLines.get());
                return invoiceLineRespDto;
            } else {
                throw new BadRequestException("InvoiceLine not found");
            }
        } catch (Exception ex) {
            throw new BadRequestException("InvoiceLine not found");
        }
    }

    @Override
    public List<InvoiceSearchRespDto> findInvoices(InvoiceSearchReqDto invoiceSearchReqDto) {
        Specification<Invoice> specs = InvoiveSpecifications.findInvoices(invoiceSearchReqDto);
        List<Invoice> invoices = invoiceRepository.findAll(specs);
        List<InvoiceSearchRespDto> invoiceSearchRespDtos = invoiceMapper.toResponseSearchDtoList(invoices);
        return invoiceSearchRespDtos;
    }

    @Override
    public List<InvoiceLineRespDto> findInvoiceLines(Long invoiceId) throws Exception {
        try {
            Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
            if (invoice.isPresent()) {
                List<InvoiceLines> invoiceLines = invoiceLinesRepository.findAllByInvoiceId(invoice.get());
                List<InvoiceLineRespDto> invoiceLinesRespDto = invoiceMapper.toLineRespSearchList(invoiceLines);
                return invoiceLinesRespDto;
            } else {
                throw new BadRequestException("InvoiceLine not found");
            }
        } catch (Exception ex) {
            throw new BadRequestException("InvoiceLine not found");
        }

    }

    private void updateTotalInvoice(Invoice invoice) {
        List<InvoiceLines> invoiceLinesList = invoiceLinesRepository.findAllByInvoiceId(invoice);
        BigDecimal total = invoiceLinesList.stream().map(value -> value.getValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
        invoice.setTotal(total);
        invoice.setRemaining(total.subtract(invoice.getPaid()));
        invoiceRepository.save(invoice);
    }

    private void updateInvoiceLinesOfInvoice(List<InvoiceLineReqDto> invoiceLinesList, Invoice invoice) {
        for (InvoiceLineReqDto invoiceLineReqDto : invoiceLinesList) {
            if (invoiceLineReqDto.getId() != null) {
                Optional<InvoiceLines> invoiceLines = invoiceLinesRepository.findById(invoiceLineReqDto.getId());
                if (invoiceLines.isPresent()) {
                    InvoiceLines line = invoiceLines.get();
                    line.setProductName(invoiceLineReqDto.getProductName());
                    line.setQuantity(invoiceLineReqDto.getQuantity());
                    line.setPrice(invoiceLineReqDto.getPrice());
                    line.setInvoiceId(invoice);
                    invoiceLinesRepository.save(line);
                }
            } else {
                InvoiceLines line = invoiceMapper.toLineEntity(invoiceLineReqDto);
                line.setInvoiceId(invoice);
                invoiceLinesRepository.save(line);
            }
        }
    }
}

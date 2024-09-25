package com.Test.app.Test_app.io.repository;

import com.Test.app.Test_app.io.entities.Invoice;
import com.Test.app.Test_app.io.entities.InvoiceLines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceLinesRepository extends JpaRepository<InvoiceLines, Long>, JpaSpecificationExecutor<InvoiceLines> {
    List<InvoiceLines> findAllByInvoiceId (Invoice invoiceId);
}

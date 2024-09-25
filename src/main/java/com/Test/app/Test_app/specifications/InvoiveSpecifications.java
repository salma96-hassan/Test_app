package com.Test.app.Test_app.specifications;

import com.Test.app.Test_app.io.entities.Invoice;
import com.Test.app.Test_app.io.reqDto.InvoiceSearchReqDto;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class InvoiveSpecifications {

    public static Specification<Invoice> findInvoices(InvoiceSearchReqDto invoiceSearchReqDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (invoiceSearchReqDto.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), invoiceSearchReqDto.getId()));
            }
            if (invoiceSearchReqDto.getProviderName() != null && !invoiceSearchReqDto.getProviderName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("providerId").get("name"), "%" + invoiceSearchReqDto.getProviderName() + "%"));
            }
            if (invoiceSearchReqDto.getStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateTime"), invoiceSearchReqDto.getStartDate()));
            }
            if (invoiceSearchReqDto.getEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateTime"), invoiceSearchReqDto.getEndDate()));
            }
            root.fetch("invoiceLinesList", JoinType.LEFT);
            query.distinct(true);
            // Combine predicates into a single predicate
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

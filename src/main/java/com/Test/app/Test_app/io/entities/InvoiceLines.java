package com.Test.app.Test_app.io.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="INVOICE_LINES")
@ToString
public class InvoiceLines implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private int quantity;

    private BigDecimal price;

    @Transient
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "INVOICE_ID")
    private Invoice invoiceId;

    public BigDecimal getValue() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}

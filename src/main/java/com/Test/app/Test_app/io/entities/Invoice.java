package com.Test.app.Test_app.io.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "INVOICE")
@ToString
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "PROVIDER_ID")
    private Provider providerId;

    private String address;

    private BigDecimal total;

    private BigDecimal paid;

    private BigDecimal remaining;

    private String deliveredBy;

    @OneToMany(mappedBy = "invoiceId", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<InvoiceLines> invoiceLinesList;
}

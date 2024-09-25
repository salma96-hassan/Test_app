package com.Test.app.Test_app.io.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PROVIDER")
public class Provider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String service;

    private String note;

    @OneToMany(mappedBy = "providerId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Invoice> invoicesList;
}

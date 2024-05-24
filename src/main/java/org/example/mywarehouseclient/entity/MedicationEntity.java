package org.example.mywarehouseclient.entity;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationEntity {

    private Long id;
    private String name;

    private LocalDate dateReceipt;
    private LocalDate dateManufacture;
    private LocalDate expirationDate;
    private double prices;
    private int remains;
    private String recipe;
    private AnnotationEntity annotation;
    private StorageLocationEntity storageLocation;
    private ManufacturerCompanyEntity manufacturerCompany;
}


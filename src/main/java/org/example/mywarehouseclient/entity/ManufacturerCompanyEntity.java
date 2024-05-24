package org.example.mywarehouseclient.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerCompanyEntity {

    private Long id;
    private String manufacturerCompany;

    @Override
    public String toString() {
        return manufacturerCompany;
    }
}
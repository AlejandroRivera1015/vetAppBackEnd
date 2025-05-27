package com.backend.vetApp.Entity.Products.MedicalSupply;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class MedicalSupplyDTO {

    private Long id;
    private String name;
    private Long amount;

}

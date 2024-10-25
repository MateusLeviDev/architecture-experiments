package com.levi.gof.templatemethod.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Contract {

    private Long id;
    private String legalName;
    private String document;

}

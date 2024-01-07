package org.geoproject.ingeo.dto.classificators.kga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoilKindGroupTypeDto {

    private Long id;
    private SoilGroupTypeDto soilGroupTypeDto;
    private SoilKindDto soilKindDto;
}

package org.geoproject.ingeo.dto.classificators.kga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoilClassKindGroupDto {

    private Long id;
    private SoilClassDto soilClassDto;
    private String soilKindGroup;
}

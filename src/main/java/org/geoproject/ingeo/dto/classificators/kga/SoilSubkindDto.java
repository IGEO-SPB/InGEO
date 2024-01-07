package org.geoproject.ingeo.dto.classificators.kga;

import lombok.Getter;
import lombok.Setter;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;

@Getter
@Setter
public class SoilSubkindDto {

    private Long id;
    private Long cltId;
    private SoilKindGroupTypeDto soilKindGroupTypeDto;
    private String ssName;
    private Boolean ssEnable;
    private Long cltBoreId;
    private String ssDescr;
    private Boolean ssOtm;
    private Integer ssFcR;
    private Integer ssFcG;
    private Integer ssFcB;
    private String acad;
}

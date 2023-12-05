package org.geoproject.ingeo.dto;

import lombok.Getter;
import lombok.Setter;
import org.geoproject.ingeo.models.classificators.kga.Color;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.geoproject.ingeo.models.classificators.kga.SoilGroupType;
import org.geoproject.ingeo.models.classificators.kga.SoilKind;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class DescriptionKgaDto {

    private Long egeId;

    private SoilClass soilClass;

    private SoilClassKindGroup soilClassKindGroup;

    private SoilKind soilKind;

//    private SoilKindGroupType soilKindGroupType;

//    private SoilSubkind SS1;
//    private SoilSubkind SS2;
//    private SoilSubkind SS3;
//    private SoilSubkind SS4;
//    private SoilSubkind SS5;
//    private SoilSubkind SS6;
//    private SoilSubkind SS7;
//    private SoilSubkind SS8;
//    private SoilSubkind SS9;
//    private SoilSubkind SS10;
//    private SoilSubkind SS11;
//    private SoilSubkind SS12;
//    private SoilSubkind SS13;
//    private SoilSubkind SS14;
//    private SoilSubkind SS15;
//    private SoilSubkind SS16;
//    private SoilSubkind SS17;
//    private SoilSubkind SS18;
//    private SoilSubkind SS19;
//    private SoilSubkind SS20;

    private Map<String, SoilSubkind> soilSubkindMap;

    private Color color;

//    private SoilSubkindAdj ssa1;
//    private SoilSubkindAdj ssa2;
//    private SoilSubkindAdj ssa3;
//    private SoilSubkindAdj ssa4;
//    private SoilSubkindAdj ssa5;
//    private SoilSubkindAdj ssa6;
//    private SoilSubkindAdj ssa7;
//    private SoilSubkindAdj ssa8;
//    private SoilSubkindAdj ssa9;
//    private SoilSubkindAdj ssa10;
//    private SoilSubkindAdj ssa11;
//    private SoilSubkindAdj ssa12;

    private Map<String, SoilSubkindAdj> soilSubkindAdjMap;

    private Float waterDepth;

    private String descriptionKga;

    public DescriptionKgaDto() {
        setSoilSubkindMap();
        setSoilSubkindAdjMap();
    }

    private void setSoilSubkindMap() {
        this.soilSubkindMap = new LinkedHashMap<>();

        for (int i = 1; i < 21; i++) {
            soilSubkindMap.put("SS" + i, null);
        }
    }

    private void setSoilSubkindAdjMap() {
        this.soilSubkindAdjMap = new LinkedHashMap<>();

        for (int i = 1; i < 13; i++) {
            soilSubkindAdjMap.put("SSA" + i, null);
        }
    }
}

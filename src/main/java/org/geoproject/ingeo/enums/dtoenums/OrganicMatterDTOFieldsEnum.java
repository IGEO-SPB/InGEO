package com.geoproject.igeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrganicMatterDTOFieldsEnum {
    DISPERSITY_INDEX("emptyPotMassFirstMeasurement"),
    HEAVING_DEGREE("emptyPotMassSecondMeasurement"),
    IS_SAND("absolutelyDrySoilPotMassFirstMeasurement");

//    allOrganicMatterTextFieldMap.put(emptyPotMassFirstMeasurement, organicMatterDTO.emptyPotMassFirstMeasurementProperty());
//        allOrganicMatterTextFieldMap.put(emptyPotMassSecondMeasurement, organicMatterDTO.emptyPotMassSecondMeasurementProperty());
//        allOrganicMatterTextFieldMap.put(absolutelyDrySoilPotMassFirstMeasurement, organicMatterDTO.absolutelyDrySoilPotMassFirstMeasurementProperty());


        //        allOrganicMatterTextFieldMap.put(absolutelyDrySoilPotMassSecondMeasurement, organicMatterDTO.absolutelyDrySoilPotMassSecondMeasurementProperty());
//        allOrganicMatterTextFieldMap.put(calcinedSoilPotMassFirstMeasurement, organicMatterDTO.calcinedSoilPotMassFirstMeasurementProperty());
//        allOrganicMatterTextFieldMap.put(calcinedSoilPotMassSecondMeasurement, organicMatterDTO.calcinedSoilPotMassSecondMeasurementProperty());
//        allOrganicMatterTextFieldMap.put(ignitionLossMassFirstMeasurement, organicMatterDTO.ignitionLossMassFirstMeasurementProperty());
//        allOrganicMatterTextFieldMap.put(ignitionLossMassSecondMeasurement, organicMatterDTO.ignitionLossMassSecondMeasurementProperty());
//        allOrganicMatterTextFieldMap.put(dryMatterContentBefore, organicMatterDTO.dryMatterContentBeforeProperty());
//        allOrganicMatterTextFieldMap.put(dryMatterContentAfter, organicMatterDTO.dryMatterContentAfterProperty());
//        allOrganicMatterTextFieldMap.put(ignitionLossAverageMass, organicMatterDTO.ignitionLossAverageMassProperty());
//        allOrganicMatterTextFieldMap.put(P250, organicMatterDTO.p250Property());
//        allOrganicMatterTextFieldMap.put(decompositionDegree, organicMatterDTO.decompositionDegreeProperty());

    private final String fieldName;
}

package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.*;
import org.geoproject.ingeo.dto.methodDtos.AreometryDTO;
import org.geoproject.ingeo.dto.methodDtos.DensityDTO;
import org.geoproject.ingeo.dto.methodDtos.OrganicMatterDTO;
import org.geoproject.ingeo.dto.methodDtos.RingDensityDTO;
import org.geoproject.ingeo.dto.methodDtos.WaterContentDTO;
import org.geoproject.ingeo.models.Sample;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapStructConfiguration.class)
public interface SampleMapper {

    SampleDto sampleToSampleDTO(Sample sample);

    Sample sampleDtoToSample(SampleDto sourceDto);


//    @Mapping(source = "laborNumber", target = "laborNumber")
//    @Mapping(source = "particleDensity", target = "particleDensity")
//    @Mapping(source = "areometricTotalSubsample", target = "areometricTotalSubsample")
//    @Mapping(source = "areometricUndisturbedSampleWaterContent", target = "areometricUndisturbedSampleWaterContent")
//    @Mapping(source = "areometricSubsampleWetSoil", target = "areometricSubsampleWetSoil")
//    @Mapping(source = "areometricWeighingBottleNumber", target = "areometricWeighingBottleNumber")
//    @Mapping(source = "areometricEmptyWeighingBottleMass", target = "areometricEmptyWeighingBottleMass")
//    @Mapping(source = "areometricWeighingBottleWithWetSoilMass", target = "areometricWeighingBottleWithWetSoilMass")
//    @Mapping(source = "areometricWeighingBottleWithDrySoilMass", target = "areometricWeighingBottleWithDrySoilMass")
//    @Mapping(source = "areometricParticleMassOver10mm", target = "areometricParticleMassOver10mm")
//    @Mapping(source = "areometricParticleMassBetween10and5mm", target = "areometricParticleMassBetween10and5mm")
//    @Mapping(source = "areometricParticleMassBetween5and2mm", target = "areometricParticleMassBetween5and2mm")
//    @Mapping(source = "areometricParticleMassBetween2and1mm", target = "areometricParticleMassBetween2and1mm")
//    @Mapping(source = "areometricParticleMassBetween1and05mm", target = "areometricParticleMassBetween1and05mm")
//    @Mapping(source = "areometricParticleMassBetween05and025mm", target = "areometricParticleMassBetween05and025mm")
//    @Mapping(source = "areometricParticleMassBetween025and01mm", target = "areometricParticleMassBetween025and01mm")
//    @Mapping(source = "areometricTotalReadingForParticleSizeBetween005and001", target = "areometricTotalReadingForParticleSizeBetween005and001")
//    @Mapping(source = "areometricTotalReadingForParticleSizeBetween001and0002", target = "areometricTotalReadingForParticleSizeBetween001and0002")
//    @Mapping(source = "areometricTotalReadingForParticleSizeBetweenLess0002", target = "areometricTotalReadingForParticleSizeBetweenLess0002")
//    @Mapping(source = "areometricFirstAmendment", target = "areometricFirstAmendment")
//    @Mapping(source = "areometricSecondAmendment", target = "areometricSecondAmendment")
//    @Mapping(source = "areometricThirdAmendment", target = "areometricThirdAmendment")
//    @Mapping(source = "areometricFourthAmendment", target = "areometricFourthAmendment")
//    @Mapping(source = "sand", target = "isSand")
//    AreometricDTO sampleToAreometricDTO(Sample sample);

//    @BeanMapping(ignoreByDefault = true)
//    @Mapping(target = "pycnometerWeightWithDrySoilFirstMeasurement", source = "sampleDensityDTO.pycnometerWeightWithDrySoilFirstMeasurement")
//    @Mapping(target = "emptyPycnometerWeightFirstMeasurement", source = "sampleDensityDTO.emptyPycnometerWeightFirstMeasurement")
//    @Mapping(target = "pycnometerWeightWithWaterFirstMeasurement", source = "sampleDensityDTO.pycnometerWeightWithWaterFirstMeasurement")
//    @Mapping(target = "pycnometerWeightWithSoilAndWaterFirstMeasurement", source = "sampleDensityDTO.pycnometerWeightWithSoilAndWaterFirstMeasurement")
//    @Mapping(target = "drySoilWeightFirstMeasurement", source = "sampleDensityDTO.drySoilWeightFirstMeasurement")
//    @Mapping(target = "soilVolumeFirstMeasurement", source = "sampleDensityDTO.soilVolumeFirstMeasurement")
//    @Mapping(target = "soilDensityFirstMeasurement", source = "sampleDensityDTO.soilDensityFirstMeasurement")
//    @Mapping(target = "pycnometerWeightWithDrySoilSecondMeasurement", source = "sampleDensityDTO.pycnometerWeightWithDrySoilSecondMeasurement")
//    @Mapping(target = "emptyPycnometerWeightSecondMeasurement", source = "sampleDensityDTO.emptyPycnometerWeightSecondMeasurement")
//    @Mapping(target = "pycnometerWeightWithWaterSecondMeasurement", source = "sampleDensityDTO.pycnometerWeightWithWaterSecondMeasurement")
//    @Mapping(target = "pycnometerWeightWithSoilAndWaterSecondMeasurement", source = "sampleDensityDTO.pycnometerWeightWithSoilAndWaterSecondMeasurement")
//    @Mapping(target = "drySoilWeightSecondMeasurement", source = "sampleDensityDTO.drySoilWeightSecondMeasurement")
//    @Mapping(target = "soilVolumeSecondMeasurement", source = "sampleDensityDTO.soilVolumeSecondMeasurement")
//    @Mapping(target = "soilDensitySecondMeasurement", source = "sampleDensityDTO.soilDensitySecondMeasurement")
//    @Mapping(target = "averageDensity", source = "sampleDensityDTO.averageDensity")
//    Sample sampleDensityDTOToSample(SampleDensityDTO sampleDensityDTO);

    DensityDTO cloneSampleDensityDTO(DensityDTO densityDTO);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateSampleFromRingDensityDTO(@MappingTarget Sample sample, RingDensityDTO ringDensityDTO);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateSampleFromOrganicMatterDTO(@MappingTarget Sample sample, OrganicMatterDTO organicMatterDTO);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateSampleFromAreometricDTO(@MappingTarget Sample sample, AreometryDTO areometryDTO);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateSampleFromWaterContentDTO(@MappingTarget Sample sample, WaterContentDTO waterContentDTO);
}


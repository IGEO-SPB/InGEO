package org.geoproject.ingeo.controllers.laborMethods.densityWaterContent;


import org.geoproject.ingeo.controllers.laborMethods.AbstractLaborMethodController;
import org.geoproject.ingeo.dto.BoychenkoConeDTO;
import org.geoproject.ingeo.dto.DensityDTO;
import org.geoproject.ingeo.dto.OrganicMatterDTO;
import org.geoproject.ingeo.dto.PhysicalPropertiesDTO;
import org.geoproject.ingeo.dto.RingDensityDTO;
import org.geoproject.ingeo.dto.WaterContentDTO;
import org.geoproject.ingeo.methods.labor.PhysicalPropertiesMethod;
import org.geoproject.ingeo.models.BoychenkoCone;
import org.geoproject.ingeo.models.Density;
import org.geoproject.ingeo.models.OrganicMatter;
import org.geoproject.ingeo.models.PhysicalProperties;
import org.geoproject.ingeo.models.RingDensity;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.WaterContent;
import org.geoproject.ingeo.services.classificators.PotService;
import org.geoproject.ingeo.services.classificators.RingService;
import org.geoproject.ingeo.services.classificators.WeighingBottleService;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.methodViews.BoychenkoConeService;
import org.geoproject.ingeo.services.methodViews.DensityService;
import org.geoproject.ingeo.services.methodViews.MethodViewService;
import org.geoproject.ingeo.services.methodViews.OrganicMatterService;
import org.geoproject.ingeo.services.methodViews.RingDensityService;
import org.geoproject.ingeo.services.methodViews.WaterContentService;
import org.geoproject.ingeo.services.tableViews.PhysicalPropertiesService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.enums.StageTitleEnum.PHYSICAL_PROPERTIES;
import static org.geoproject.ingeo.enums.StageTitleEnum.RINGS;
import static org.geoproject.ingeo.enums.StageTitleEnum.WEIGHING_BOTTLES;
import static org.geoproject.ingeo.enums.ViewsEnum.PHYSICAL_PROPERTIES_RESULT_TABLE_VIEW;
import static org.geoproject.ingeo.enums.ViewsEnum.RINGS_VIEW;
import static org.geoproject.ingeo.enums.ViewsEnum.WEIGHING_BOTTLES_VIEW;

@Component
public class DensityWaterContentMethodController extends AbstractLaborMethodController implements Initializable {

    private final DensityService densityService;
    private final BoychenkoConeService boychenkoConeService;
    private final WaterContentService waterContentService;
    private final RingDensityService ringDensityService;
    private final OrganicMatterService organicMatterService;
    private final PhysicalPropertiesService physicalPropertiesService;

    private PhysicalProperties physicalProperties;

    //region Density Fields
    @FXML
    private TextField pycnometerWeightWithDrySoilFirstMeasurement;
    @FXML
    private TextField emptyPycnometerWeightFirstMeasurement;
    @FXML
    private TextField pycnometerWeightWithWaterFirstMeasurement;
    @FXML
    private TextField pycnometerWeightWithSoilAndWaterFirstMeasurement;

    @FXML
    private TextField drySoilWeightFirstMeasurement;
    @FXML
    private TextField soilVolumeFirstMeasurement;
    @FXML
    private TextField soilDensityFirstMeasurement;

    @FXML
    private TextField pycnometerWeightWithDrySoilSecondMeasurement;
    @FXML
    private TextField emptyPycnometerWeightSecondMeasurement;
    @FXML
    private TextField pycnometerWeightWithWaterSecondMeasurement;
    @FXML
    private TextField pycnometerWeightWithSoilAndWaterSecondMeasurement;

    @FXML
    private TextField drySoilWeightSecondMeasurement;
    @FXML
    private TextField soilVolumeSecondMeasurement;
    @FXML
    private TextField soilDensitySecondMeasurement;

    @FXML
    private TextField averageDensity;

    private Density density;
    private DensityDTO densityDTO;
    //endregion

    //region Water Content Fields
    @FXML
    private TextField naturalWaterContentWeighingBottleNumberFirstMeasurement;
    @FXML
    private TextField naturalWaterContentWeighingBottleNumberSecondMeasurement;
    @FXML
    private TextField liquidityWeighingBottleNumberFirstMeasurement;
    @FXML
    private TextField liquidityWeighingBottleNumberSecondMeasurement;
    @FXML
    private TextField plasticWeighingBottleNumberFirstMeasurement;
    @FXML
    private TextField plasticWeighingBottleNumberSecondMeasurement;

    @FXML
    private TextField naturalWaterContentEmptyWeighingBottleMassFirstMeasurement;
    @FXML
    private TextField naturalWaterContentEmptyWeighingBottleMassSecondMeasurement;
    @FXML
    private TextField liquidityEmptyWeighingBottleMassFirstMeasurement;
    @FXML
    private TextField liquidityEmptyWeighingBottleMassSecondMeasurement;
    @FXML
    private TextField plasticEmptyWeighingBottleMassFirstMeasurement;
    @FXML
    private TextField plasticEmptyWeighingBottleMassSecondMeasurement;

    @FXML
    private TextField naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement;
    @FXML
    private TextField naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement;
    @FXML
    private TextField liquidityWeighingBottleWithWetSoilMassFirstMeasurement;
    @FXML
    private TextField liquidityWeighingBottleWithWetSoilMassSecondMeasurement;
    @FXML
    private TextField plasticWeighingBottleWithWetSoilMassFirstMeasurement;
    @FXML
    private TextField plasticWeighingBottleWithWetSoilMassSecondMeasurement;
    @FXML
    private TextField naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement;
    @FXML
    private TextField naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement;
    @FXML
    private TextField liquidityWeighingBottleWithDrySoilMassFirstMeasurement;
    @FXML
    private TextField liquidityWeighingBottleWithDrySoilMassSecondMeasurement;
    @FXML
    private TextField plasticWeighingBottleWithDrySoilMassFirstMeasurement;
    @FXML
    private TextField plasticWeighingBottleWithDrySoilMassSecondMeasurement;

    @FXML
    private TextField naturalWaterContentWeighingBottleWaterContentFirst;
    @FXML
    private TextField naturalWaterContentWeighingBottleWaterContentSecond;
    @FXML
    private TextField liquidityWeighingBottleWaterContentFirstMeasurement;
    @FXML
    private TextField liquidityWeighingBottleWaterContentSecondMeasurement;
    @FXML
    private TextField plasticWeighingBottleWaterContentFirstMeasurement;
    @FXML
    private TextField plasticWeighingBottleWaterContentSecondMeasurement;
    @FXML
    private TextField naturalAverageWaterContent;
    @FXML
    private TextField averageLiquidLimit;
    @FXML
    private TextField averagePlasticLimit;
    @FXML
    private TextField plasticityIndex;
    @FXML
    private TextField liquidityIndex;

    private WaterContent waterContent;
    private WaterContentDTO waterContentDto;
    //endregion

    //region Boychenko Cone Fields
    @FXML
    private TextField undisturbedStrImmersionDepthFirstMeasur;
    @FXML
    private TextField undisturbedStrImmersionDepthSecondMeasur;
    @FXML
    private TextField undisturbedStrImmersionDepthThirdMeasur;
    @FXML
    private TextField brokenStrImmersionDepthFirstMeasur;
    @FXML
    private TextField brokenStrImmersionDepthSecondMeasur;
    @FXML
    private TextField brokenStrImmersionDepthThirdMeasur;
    @FXML
    private TextField undisturbedStrImmersionDepthAverage;
    @FXML
    private TextField brokenStrImmersionDepthAverage;

    private BoychenkoCone boychenkoCone;
    private BoychenkoConeDTO boychenkoConeDTO;
    //endregion

    //region Ring Density Fields
    @FXML
    private TextField ringNumberFirstMeasurement;
    @FXML
    private TextField ringNumberSecondMeasurement;
    @FXML
    private TextField ringEmptyMassFirstMeasurement;
    @FXML
    private TextField ringEmptyMassSecondMeasurement;
    @FXML
    private TextField ringWithWetSoilMassFirstMeasurement;
    @FXML
    private TextField ringWithWetSoilMassSecondMeasurement;
    @FXML
    private TextField ringVolumeFirstMeasurement;
    @FXML
    private TextField ringVolumeSecondMeasurement;
    @FXML
    private TextField ringDensityFirstMeasurement;
    @FXML
    private TextField ringDensitySecondMeasurement;
    @FXML
    private TextField ringDensityAverage;
    @FXML
    private TextField ringDrySoilDensity;
    @FXML
    private TextField voidRatio;
    @FXML
    private TextField fullWaterContent;
    @FXML
    private TextField saturationRatio;

    private RingDensity ringDensity;
    private RingDensityDTO ringDensityDTO;
    //endregion

    //region Organic Matter Content Determination Fields
    @FXML
    private TextField emptyPotMassFirstMeasurement;
    @FXML
    private TextField emptyPotMassSecondMeasurement;
    @FXML
    private TextField absolutelyDrySoilPotMassFirstMeasurement;
    @FXML
    private TextField absolutelyDrySoilPotMassSecondMeasurement;
    @FXML
    private TextField calcinedSoilPotMassFirstMeasurement;
    @FXML
    private TextField calcinedSoilPotMassSecondMeasurement;
    @FXML
    private TextField ignitionLossMassFirstMeasurement;
    @FXML
    private TextField ignitionLossMassSecondMeasurement;
    @FXML
    private TextField dryMatterContentBefore;
    @FXML
    private TextField dryMatterContentAfter;
    @FXML
    private TextField ignitionLossAverageMass;
    @FXML
    private TextField P250;
    @FXML
    private TextField decompositionDegree;

    private OrganicMatter organicMatter;
    private OrganicMatterDTO organicMatterDTO;
    //endregion

    //region Common Fields
    @FXML
    ChoiceBox<String> sampleChoiceBox;

    private PhysicalPropertiesDTO physicalPropertiesDTO;

    protected Map<TextField, Property> allDensityTextFieldMap;
    protected Map<TextField, Property> nonEditableDensityTextFieldMap;

    protected Map<TextField, Property> allWaterContentTextFieldMap;
    protected Map<TextField, Property> nonEditableWaterContentTextFieldMap;

    protected Map<TextField, Property> allRingDensityTextFieldMap;
    protected Map<TextField, Property> nonEditableRingDensityTextFieldMap;

    protected Map<TextField, Property> allBoychenkoConeTextFieldMap;
    protected Map<TextField, Property> nonEditableBoychenkoConeTextFieldMap;

    protected Map<TextField, Property> allOrganicMatterTextFieldMap;
    protected Map<TextField, Property> nonEditableOrganicMatterTextFieldMap;
    //endregion

    public DensityWaterContentMethodController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                               MethodViewService<Density, DensityDTO> service, CurrentState currentState,
                                               DensityService densityService, BoychenkoConeService boychenkoConeService,
                                               WaterContentService waterContentService, RingDensityService ringDensityService,
                                               OrganicMatterService organicMatterService, WeighingBottleService weighingBottleService,
                                               RingService ringService, PhysicalPropertiesService physicalPropertiesService,
                                               PotService potService) {
        super(applicationContext, sampleService, service, currentState, weighingBottleService, potService, ringService);
        this.densityService = densityService;
        this.boychenkoConeService = boychenkoConeService;
        this.waterContentService = waterContentService;
        this.ringDensityService = ringDensityService;
        this.organicMatterService = organicMatterService;
        this.physicalPropertiesService = physicalPropertiesService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Density and Water content Method View init...");
        init();
    }

    @Override
    public void initMaps() {
        allDensityTextFieldMap = new HashMap<>();
        nonEditableDensityTextFieldMap = new HashMap<>();
        allWaterContentTextFieldMap = new HashMap<>();
        nonEditableWaterContentTextFieldMap = new HashMap<>();
        allRingDensityTextFieldMap = new HashMap<>();
        nonEditableRingDensityTextFieldMap = new HashMap<>();
        allBoychenkoConeTextFieldMap = new HashMap<>();
        nonEditableBoychenkoConeTextFieldMap = new HashMap<>();
        allOrganicMatterTextFieldMap = new HashMap<>();
        nonEditableOrganicMatterTextFieldMap = new HashMap<>();
    }

    //region Setters
    @Override
    public void setDTO() {
        Sample sample = currentState.getSample();

        density = densityService.getBySample(sample);
        waterContent = waterContentService.getBySample(sample);
        boychenkoCone = boychenkoConeService.getBySample(sample);
        ringDensity = ringDensityService.getBySample(sample);
        organicMatter = organicMatterService.getBySample(sample);


        densityDTO = densityService.getDto(density);
        waterContentDto = waterContentService.getDto(waterContent);
        boychenkoConeDTO = boychenkoConeService.getDto(boychenkoCone);
        ringDensityDTO = ringDensityService.getDto(ringDensity);
        organicMatterDTO = organicMatterService.getDto(organicMatter);

        physicalProperties = physicalPropertiesService.getBySampleAndUpdate(sample, densityDTO, waterContentDto,
                boychenkoConeDTO, ringDensityDTO, organicMatterDTO);

        physicalPropertiesDTO = physicalPropertiesService.getDto(physicalProperties);
    }

    public void setTextFieldLists() {
        setDensityTextFieldLists();
        setBoychenkoConeTextFieldLists();
        setWaterContentTextFieldLists();
        setRingDensityTextFieldLists();
        setOrganicMatterTextFieldLists();
    }

    @Override
    public void setFieldBinding() {
        JavaFXCommonMethods.setFieldBinding(allDensityTextFieldMap);
        JavaFXCommonMethods.setFieldBinding(allWaterContentTextFieldMap);
        JavaFXCommonMethods.setFieldBinding(allRingDensityTextFieldMap);
        JavaFXCommonMethods.setFieldBinding(allBoychenkoConeTextFieldMap);
        JavaFXCommonMethods.setFieldBinding(allOrganicMatterTextFieldMap);
    }
    //endregion

    //region Density Methods
    private void setDensityTextFieldLists() {
        allDensityTextFieldMap.put(pycnometerWeightWithDrySoilFirstMeasurement, densityDTO.pycnometerWeightWithDrySoilFirstMeasurementProperty());
        allDensityTextFieldMap.put(emptyPycnometerWeightFirstMeasurement, densityDTO.emptyPycnometerWeightFirstMeasurementProperty());
        allDensityTextFieldMap.put(pycnometerWeightWithWaterFirstMeasurement, densityDTO.pycnometerWeightWithWaterFirstMeasurementProperty());
        allDensityTextFieldMap.put(pycnometerWeightWithSoilAndWaterFirstMeasurement, densityDTO.pycnometerWeightWithSoilAndWaterFirstMeasurementProperty());
        allDensityTextFieldMap.put(drySoilWeightFirstMeasurement, densityDTO.drySoilWeightFirstMeasurementProperty());
        allDensityTextFieldMap.put(soilVolumeFirstMeasurement, densityDTO.soilVolumeFirstMeasurementProperty());
        allDensityTextFieldMap.put(soilDensityFirstMeasurement, densityDTO.soilDensityFirstMeasurementProperty());
        allDensityTextFieldMap.put(pycnometerWeightWithDrySoilSecondMeasurement, densityDTO.pycnometerWeightWithDrySoilSecondMeasurementProperty());
        allDensityTextFieldMap.put(emptyPycnometerWeightSecondMeasurement, densityDTO.emptyPycnometerWeightSecondMeasurementProperty());
        allDensityTextFieldMap.put(pycnometerWeightWithWaterSecondMeasurement, densityDTO.pycnometerWeightWithWaterSecondMeasurementProperty());
        allDensityTextFieldMap.put(pycnometerWeightWithSoilAndWaterSecondMeasurement, densityDTO.pycnometerWeightWithSoilAndWaterSecondMeasurementProperty());
        allDensityTextFieldMap.put(drySoilWeightSecondMeasurement, densityDTO.drySoilWeightSecondMeasurementProperty());
        allDensityTextFieldMap.put(soilVolumeSecondMeasurement, densityDTO.soilVolumeSecondMeasurementProperty());
        allDensityTextFieldMap.put(soilDensitySecondMeasurement, densityDTO.soilDensitySecondMeasurementProperty());
        allDensityTextFieldMap.put(averageDensity, densityDTO.averageDensityProperty());

        nonEditableDensityTextFieldMap.put(drySoilWeightFirstMeasurement, densityDTO.drySoilWeightFirstMeasurementProperty());
        nonEditableDensityTextFieldMap.put(soilVolumeFirstMeasurement, densityDTO.soilVolumeFirstMeasurementProperty());
        nonEditableDensityTextFieldMap.put(soilDensityFirstMeasurement, densityDTO.soilDensityFirstMeasurementProperty());
        nonEditableDensityTextFieldMap.put(drySoilWeightSecondMeasurement, densityDTO.drySoilWeightSecondMeasurementProperty());
        nonEditableDensityTextFieldMap.put(soilVolumeSecondMeasurement, densityDTO.soilVolumeSecondMeasurementProperty());
        nonEditableDensityTextFieldMap.put(soilDensitySecondMeasurement, densityDTO.soilDensitySecondMeasurementProperty());
        nonEditableDensityTextFieldMap.put(averageDensity, densityDTO.averageDensityProperty());

        JavaFXCommonMethods.setTextFieldProperties(allDensityTextFieldMap, nonEditableDensityTextFieldMap,
                null, this::updateEntity);
    }
    //endregion

    //region Water Content Methods
    private void setWaterContentTextFieldLists() {
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleNumberFirstMeasurement, waterContentDto.naturalWaterContentWeighingBottleNumberFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleNumberSecondMeasurement, waterContentDto.naturalWaterContentWeighingBottleNumberSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(liquidityWeighingBottleNumberFirstMeasurement, waterContentDto.liquidityWeighingBottleNumberFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(liquidityWeighingBottleNumberSecondMeasurement, waterContentDto.liquidityWeighingBottleNumberSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(plasticWeighingBottleNumberFirstMeasurement, waterContentDto.plasticWeighingBottleNumberFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(plasticWeighingBottleNumberSecondMeasurement, waterContentDto.plasticWeighingBottleNumberSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentEmptyWeighingBottleMassFirstMeasurement, waterContentDto.naturalWaterContentEmptyWeighingBottleMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentEmptyWeighingBottleMassSecondMeasurement, waterContentDto.naturalWaterContentEmptyWeighingBottleMassSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(liquidityEmptyWeighingBottleMassFirstMeasurement, waterContentDto.liquidityEmptyWeighingBottleMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(liquidityEmptyWeighingBottleMassSecondMeasurement, waterContentDto.liquidityEmptyWeighingBottleMassSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(plasticEmptyWeighingBottleMassFirstMeasurement, waterContentDto.plasticEmptyWeighingBottleMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(plasticEmptyWeighingBottleMassSecondMeasurement, waterContentDto.plasticEmptyWeighingBottleMassSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement, waterContentDto.naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement, waterContentDto.naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(liquidityWeighingBottleWithWetSoilMassFirstMeasurement, waterContentDto.liquidityWeighingBottleWithWetSoilMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(liquidityWeighingBottleWithWetSoilMassSecondMeasurement, waterContentDto.liquidityWeighingBottleWithWetSoilMassSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(plasticWeighingBottleWithWetSoilMassFirstMeasurement, waterContentDto.plasticWeighingBottleWithWetSoilMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(plasticWeighingBottleWithWetSoilMassSecondMeasurement, waterContentDto.plasticWeighingBottleWithWetSoilMassSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement, waterContentDto.naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement, waterContentDto.naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(liquidityWeighingBottleWithDrySoilMassFirstMeasurement, waterContentDto.liquidityWeighingBottleWithDrySoilMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(liquidityWeighingBottleWithDrySoilMassSecondMeasurement, waterContentDto.liquidityWeighingBottleWithDrySoilMassSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(plasticWeighingBottleWithDrySoilMassFirstMeasurement, waterContentDto.plasticWeighingBottleWithDrySoilMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(plasticWeighingBottleWithDrySoilMassSecondMeasurement, waterContentDto.plasticWeighingBottleWithDrySoilMassSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWaterContentFirst, waterContentDto.naturalWaterContentWeighingBottleWaterContentFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWaterContentSecond, waterContentDto.naturalWaterContentWeighingBottleWaterContentSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(liquidityWeighingBottleWaterContentFirstMeasurement, waterContentDto.liquidityWeighingBottleWaterContentFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(liquidityWeighingBottleWaterContentSecondMeasurement, waterContentDto.liquidityWeighingBottleWaterContentSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(plasticWeighingBottleWaterContentFirstMeasurement, waterContentDto.plasticWeighingBottleWaterContentFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(plasticWeighingBottleWaterContentSecondMeasurement, waterContentDto.plasticWeighingBottleWaterContentSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalAverageWaterContent, waterContentDto.naturalAverageWaterContentProperty());
        allWaterContentTextFieldMap.put(averageLiquidLimit, waterContentDto.averageLiquidLimitProperty());
        allWaterContentTextFieldMap.put(averagePlasticLimit, waterContentDto.averagePlasticLimitProperty());
        allWaterContentTextFieldMap.put(plasticityIndex, waterContentDto.plasticityIndexProperty());
        allWaterContentTextFieldMap.put(liquidityIndex, waterContentDto.liquidityIndexProperty());

        nonEditableWaterContentTextFieldMap.put(naturalWaterContentEmptyWeighingBottleMassFirstMeasurement, waterContentDto.naturalWaterContentEmptyWeighingBottleMassFirstMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(naturalWaterContentEmptyWeighingBottleMassSecondMeasurement, waterContentDto.naturalWaterContentEmptyWeighingBottleMassSecondMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(liquidityEmptyWeighingBottleMassFirstMeasurement, waterContentDto.liquidityEmptyWeighingBottleMassFirstMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(liquidityEmptyWeighingBottleMassSecondMeasurement, waterContentDto.liquidityEmptyWeighingBottleMassSecondMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(plasticEmptyWeighingBottleMassFirstMeasurement, waterContentDto.plasticEmptyWeighingBottleMassFirstMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(plasticEmptyWeighingBottleMassSecondMeasurement, waterContentDto.plasticEmptyWeighingBottleMassSecondMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWaterContentFirst, waterContentDto.naturalWaterContentWeighingBottleWaterContentFirstMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWaterContentSecond, waterContentDto.naturalWaterContentWeighingBottleWaterContentSecondMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(liquidityWeighingBottleWaterContentFirstMeasurement, waterContentDto.liquidityWeighingBottleWaterContentFirstMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(liquidityWeighingBottleWaterContentSecondMeasurement, waterContentDto.liquidityWeighingBottleWaterContentSecondMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(plasticWeighingBottleWaterContentFirstMeasurement, waterContentDto.plasticWeighingBottleWaterContentFirstMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(plasticWeighingBottleWaterContentSecondMeasurement, waterContentDto.plasticWeighingBottleWaterContentSecondMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(naturalAverageWaterContent, waterContentDto.naturalAverageWaterContentProperty());
        nonEditableWaterContentTextFieldMap.put(averageLiquidLimit, waterContentDto.averageLiquidLimitProperty());
        nonEditableWaterContentTextFieldMap.put(averagePlasticLimit, waterContentDto.averagePlasticLimitProperty());
        nonEditableWaterContentTextFieldMap.put(plasticityIndex, waterContentDto.plasticityIndexProperty());
        nonEditableWaterContentTextFieldMap.put(liquidityIndex, waterContentDto.liquidityIndexProperty());

        JavaFXCommonMethods.setTextFieldProperties(allWaterContentTextFieldMap, nonEditableWaterContentTextFieldMap,
                weighingBottleService, this::updateEntity);
    }
    //endregion

    //region Boychenko Cone Methods
    private void setBoychenkoConeTextFieldLists() {
        allBoychenkoConeTextFieldMap.put(undisturbedStrImmersionDepthFirstMeasur, boychenkoConeDTO.undisturbedStrImmersionDepthFirstMeasurProperty());
        allBoychenkoConeTextFieldMap.put(undisturbedStrImmersionDepthSecondMeasur, boychenkoConeDTO.undisturbedStrImmersionDepthSecondMeasurProperty());
        allBoychenkoConeTextFieldMap.put(undisturbedStrImmersionDepthThirdMeasur, boychenkoConeDTO.undisturbedStrImmersionDepthThirdMeasurProperty());
        allBoychenkoConeTextFieldMap.put(brokenStrImmersionDepthFirstMeasur, boychenkoConeDTO.brokenStrImmersionDepthFirstMeasurProperty());
        allBoychenkoConeTextFieldMap.put(brokenStrImmersionDepthSecondMeasur, boychenkoConeDTO.brokenStrImmersionDepthSecondMeasurProperty());
        allBoychenkoConeTextFieldMap.put(brokenStrImmersionDepthThirdMeasur, boychenkoConeDTO.brokenStrImmersionDepthThirdMeasurProperty());
        allBoychenkoConeTextFieldMap.put(undisturbedStrImmersionDepthAverage, boychenkoConeDTO.undisturbedStrImmersionDepthAverageProperty());
        allBoychenkoConeTextFieldMap.put(brokenStrImmersionDepthAverage, boychenkoConeDTO.brokenStrImmersionDepthAverageProperty());

        nonEditableBoychenkoConeTextFieldMap.put(undisturbedStrImmersionDepthAverage, boychenkoConeDTO.undisturbedStrImmersionDepthAverageProperty());
        nonEditableBoychenkoConeTextFieldMap.put(brokenStrImmersionDepthAverage, boychenkoConeDTO.brokenStrImmersionDepthAverageProperty());

        JavaFXCommonMethods.setTextFieldProperties(allBoychenkoConeTextFieldMap, nonEditableBoychenkoConeTextFieldMap,
                null, this::updateEntity);
    }
    //endregion

    //region RingDensity Methods
    private void setRingDensityTextFieldLists() {
        allRingDensityTextFieldMap.put(ringNumberFirstMeasurement, ringDensityDTO.ringNumberFirstMeasurementProperty());
        allRingDensityTextFieldMap.put(ringNumberSecondMeasurement, ringDensityDTO.ringNumberSecondMeasurementProperty());
        allRingDensityTextFieldMap.put(ringEmptyMassFirstMeasurement, ringDensityDTO.emptyRingMassFirstMeasurementProperty());
        allRingDensityTextFieldMap.put(ringEmptyMassSecondMeasurement, ringDensityDTO.emptyRingMassSecondMeasurementProperty());
        allRingDensityTextFieldMap.put(ringWithWetSoilMassFirstMeasurement, ringDensityDTO.ringWithWetSoilMassFirstMeasurementProperty());
        allRingDensityTextFieldMap.put(ringWithWetSoilMassSecondMeasurement, ringDensityDTO.ringWithWetSoilMassSecondMeasurementProperty());
        allRingDensityTextFieldMap.put(ringVolumeFirstMeasurement, ringDensityDTO.ringVolumeFirstMeasurementProperty());
        allRingDensityTextFieldMap.put(ringVolumeSecondMeasurement, ringDensityDTO.ringVolumeSecondMeasurementProperty());
        allRingDensityTextFieldMap.put(ringDensityFirstMeasurement, ringDensityDTO.ringDensityFirstMeasurementProperty());
        allRingDensityTextFieldMap.put(ringDensitySecondMeasurement, ringDensityDTO.ringDensitySecondMeasurementProperty());
        allRingDensityTextFieldMap.put(ringDensityAverage, ringDensityDTO.ringDensityAverageProperty());
        allRingDensityTextFieldMap.put(ringDrySoilDensity, ringDensityDTO.ringDrySoilDensityProperty());
        allRingDensityTextFieldMap.put(voidRatio, ringDensityDTO.voidRatioProperty());
        allRingDensityTextFieldMap.put(fullWaterContent, ringDensityDTO.fullWaterContentProperty());
        allRingDensityTextFieldMap.put(saturationRatio, ringDensityDTO.saturationRatioProperty());

        nonEditableRingDensityTextFieldMap.put(ringEmptyMassFirstMeasurement, ringDensityDTO.emptyRingMassFirstMeasurementProperty());
        nonEditableRingDensityTextFieldMap.put(ringEmptyMassSecondMeasurement, ringDensityDTO.emptyRingMassSecondMeasurementProperty());
        nonEditableRingDensityTextFieldMap.put(ringVolumeFirstMeasurement, ringDensityDTO.ringVolumeFirstMeasurementProperty());
        nonEditableRingDensityTextFieldMap.put(ringVolumeSecondMeasurement, ringDensityDTO.ringVolumeSecondMeasurementProperty());
        nonEditableRingDensityTextFieldMap.put(ringDensityFirstMeasurement, ringDensityDTO.ringDensityFirstMeasurementProperty());
        nonEditableRingDensityTextFieldMap.put(ringDensitySecondMeasurement, ringDensityDTO.ringDensitySecondMeasurementProperty());
        nonEditableRingDensityTextFieldMap.put(ringDensityAverage, ringDensityDTO.ringDensityAverageProperty());
        nonEditableRingDensityTextFieldMap.put(ringDrySoilDensity, ringDensityDTO.ringDrySoilDensityProperty());
        nonEditableRingDensityTextFieldMap.put(voidRatio, ringDensityDTO.voidRatioProperty());
        nonEditableRingDensityTextFieldMap.put(fullWaterContent, ringDensityDTO.fullWaterContentProperty());
        nonEditableRingDensityTextFieldMap.put(saturationRatio, ringDensityDTO.saturationRatioProperty());

        JavaFXCommonMethods.setTextFieldProperties(allRingDensityTextFieldMap, nonEditableRingDensityTextFieldMap,
                ringService, this::updateEntity);
    }
    //endregion

    //region Organic Matter Content Determination Methods
    private void setOrganicMatterTextFieldLists() {
        allOrganicMatterTextFieldMap.put(emptyPotMassFirstMeasurement, organicMatterDTO.emptyPotMassFirstMeasurementProperty());
        allOrganicMatterTextFieldMap.put(emptyPotMassSecondMeasurement, organicMatterDTO.emptyPotMassSecondMeasurementProperty());
        allOrganicMatterTextFieldMap.put(absolutelyDrySoilPotMassFirstMeasurement, organicMatterDTO.absolutelyDrySoilPotMassFirstMeasurementProperty());
        allOrganicMatterTextFieldMap.put(absolutelyDrySoilPotMassSecondMeasurement, organicMatterDTO.absolutelyDrySoilPotMassSecondMeasurementProperty());
        allOrganicMatterTextFieldMap.put(calcinedSoilPotMassFirstMeasurement, organicMatterDTO.calcinedSoilPotMassFirstMeasurementProperty());
        allOrganicMatterTextFieldMap.put(calcinedSoilPotMassSecondMeasurement, organicMatterDTO.calcinedSoilPotMassSecondMeasurementProperty());
        allOrganicMatterTextFieldMap.put(ignitionLossMassFirstMeasurement, organicMatterDTO.ignitionLossMassFirstMeasurementProperty());
        allOrganicMatterTextFieldMap.put(ignitionLossMassSecondMeasurement, organicMatterDTO.ignitionLossMassSecondMeasurementProperty());
        allOrganicMatterTextFieldMap.put(dryMatterContentBefore, organicMatterDTO.dryMatterContentBeforeProperty());
        allOrganicMatterTextFieldMap.put(dryMatterContentAfter, organicMatterDTO.dryMatterContentAfterProperty());
        allOrganicMatterTextFieldMap.put(ignitionLossAverageMass, organicMatterDTO.ignitionLossAverageMassProperty());
        allOrganicMatterTextFieldMap.put(P250, organicMatterDTO.p250Property());
        allOrganicMatterTextFieldMap.put(decompositionDegree, organicMatterDTO.decompositionDegreeProperty());

        nonEditableOrganicMatterTextFieldMap.put(ignitionLossMassFirstMeasurement, organicMatterDTO.ignitionLossMassFirstMeasurementProperty());
        nonEditableOrganicMatterTextFieldMap.put(ignitionLossMassSecondMeasurement, organicMatterDTO.ignitionLossMassSecondMeasurementProperty());
        nonEditableOrganicMatterTextFieldMap.put(ignitionLossAverageMass, organicMatterDTO.ignitionLossAverageMassProperty());
        nonEditableOrganicMatterTextFieldMap.put(decompositionDegree, organicMatterDTO.decompositionDegreeProperty());
        nonEditableOrganicMatterTextFieldMap.put(P250, organicMatterDTO.p250Property());

        JavaFXCommonMethods.setTextFieldProperties(allOrganicMatterTextFieldMap, nonEditableOrganicMatterTextFieldMap,
                null, this::updateEntity);
    }
    //endregion

    //region Common Methods
    @Override
    public void updateEntity() {
        calculate();
    }

    @Override
    public void calculate() {
        densityService.calculate(currentState.getSample(), densityDTO);
        waterContentService.calculate(currentState.getSample(), waterContentDto);
        ringDensityService.calculate(currentState.getSample(), ringDensityDTO);
        boychenkoConeService.calculate(currentState.getSample(), boychenkoConeDTO);
        organicMatterService.calculate(currentState.getSample(), organicMatterDTO);
    }
    //endregion

    //region buttons
    @FXML
    public void onSetWeighingBottleButtonClick(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeSceneToModalWindow(event, WEIGHING_BOTTLES_VIEW.getPath(), applicationContext,
                stage, WEIGHING_BOTTLES.getTitle());
    }

    @FXML
    public void onSetRingButtonClick(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeSceneToModalWindow(event, RINGS_VIEW.getPath(), applicationContext, stage, RINGS.getTitle());
    }

    @FXML
    public void onCalculatePhysicalPropertiesButtonClick() {
        physicalPropertiesDTO.setNaturalAverageWaterContent(waterContentDto.getNaturalAverageWaterContent());
        physicalPropertiesDTO.setAverageLiquidLimit(waterContentDto.getAverageLiquidLimit());
        physicalPropertiesDTO.setPlasticLimit(waterContentDto.getAveragePlasticLimit());
        physicalPropertiesDTO.setAverageDensity(densityDTO.getAverageDensity());
        physicalPropertiesDTO.setRingDensityAverage(ringDensityDTO.getRingDensityAverage());
        physicalPropertiesDTO.setRingDrySoilDensity(ringDensityDTO.getRingDrySoilDensity());
        physicalPropertiesDTO.setVoidRatio(ringDensityDTO.getVoidRatio());

        physicalPropertiesDTO.setSaturationRatio(ringDensityDTO.getSaturationRatio());
        physicalPropertiesDTO.setFullWaterContent(ringDensityDTO.getFullWaterContent());

        physicalPropertiesDTO.setPlasticityIndex(waterContentDto.getPlasticityIndex());
        physicalPropertiesDTO.setLiquidityIndex(waterContentDto.getLiquidityIndex());

        physicalPropertiesDTO.setBrokenStrBoychenkoConeImmersionDepthAverage(boychenkoConeDTO.getBrokenStrImmersionDepthAverage());
        physicalPropertiesDTO.setUndisturbedStrBoychenkoConeImmersionDepthAverage(boychenkoConeDTO.getUndisturbedStrImmersionDepthAverage());

        physicalPropertiesDTO.setIgnitionLossAverageMass(organicMatterDTO.getIgnitionLossAverageMass());
        physicalPropertiesDTO.setDecompositionDegree(organicMatterDTO.getDecompositionDegree());

        //TODO: добавить коррозию

        PhysicalPropertiesMethod.calculatePhysicalProperties(physicalPropertiesDTO);

        physicalPropertiesService.updateFromDTO(currentState.getSample(), physicalPropertiesDTO);
    }

    @FXML
    public void onShowPhysicalPropertiesButtonClick(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeSceneToModalWindow(event, PHYSICAL_PROPERTIES_RESULT_TABLE_VIEW.getPath(),
                applicationContext, stage, PHYSICAL_PROPERTIES.getTitle());
    }
    //endregion
}
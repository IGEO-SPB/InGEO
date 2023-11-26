package org.geoproject.ingeo.controllers.laborMethods.shear;

import org.geoproject.ingeo.controllers.laborMethods.AbstractLaborMethodController;
import org.geoproject.ingeo.dto.BoychenkoConeDTO;
import org.geoproject.ingeo.dto.DensityDTO;
import org.geoproject.ingeo.dto.PhysicalPropertiesDTO;
import org.geoproject.ingeo.dto.RingDensityDTO;
import org.geoproject.ingeo.dto.ShearDto;
import org.geoproject.ingeo.dto.WaterContentDTO;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.methods.labor.PhysicalPropertiesMethod;
import org.geoproject.ingeo.models.BoychenkoCone;
import org.geoproject.ingeo.models.Density;
import org.geoproject.ingeo.models.PhysicalProperties;
import org.geoproject.ingeo.models.RingDensity;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.Shear;
import org.geoproject.ingeo.models.WaterContent;
import org.geoproject.ingeo.services.classificators.PotService;
import org.geoproject.ingeo.services.classificators.RingService;
import org.geoproject.ingeo.services.classificators.WeighingBottleService;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.methodViews.BoychenkoConeService;
import org.geoproject.ingeo.services.methodViews.DensityService;
import org.geoproject.ingeo.services.methodViews.MethodViewService;
import org.geoproject.ingeo.services.methodViews.RingDensityService;
import org.geoproject.ingeo.services.methodViews.WaterContentService;
import org.geoproject.ingeo.services.tableViews.PhysicalPropertiesService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;
import static org.geoproject.ingeo.enums.StageTitleEnum.PHYSICAL_PROPERTIES;
import static org.geoproject.ingeo.enums.StageTitleEnum.SHEAR_DATA;
import static org.geoproject.ingeo.enums.ViewsEnum.PHYSICAL_PROPERTIES_RESULT_TABLE_VIEW;

@Log4j2
@Component
public class ShearWaterContentCutRingMethodController extends AbstractLaborMethodController<Shear, ShearDto> implements Initializable {

//    private static Integer shearPointNumber;
    private static Shear currentShear;

    private final DensityService densityService;
    private final BoychenkoConeService boychenkoConeService;
    private final WaterContentService waterContentService;
    private final RingDensityService ringDensityService;
    private final PhysicalPropertiesService physicalPropertiesService;

    private PhysicalProperties physicalProperties;

    //region Shear
    @FXML
    private TextField shearNaturalWaterContentWeighingBottleNumberFirstMeasurement;
    @FXML
    private TextField shearNaturalWaterContentWeighingBottleNumberSecondMeasurement;
    @FXML
    private TextField shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement;
    @FXML
    private TextField shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement;
    @FXML
    private TextField shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement;
    @FXML
    private TextField shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement;
    @FXML
    private TextField shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement;
    @FXML
    private TextField shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement;
    @FXML
    private TextField shearNaturalWaterContentWeighingBottleFirstMeasurement;
    @FXML
    private TextField shearNaturalWaterContentWeighingBottleSecondMeasurement;
    @FXML
    private TextField naturalShearAverageWaterContentFirstMeasurement;
    @FXML
    private TextField shearRingNumberFirstMeasurement;
    @FXML
    private TextField shearRingNumberSecondMeasurement;
    @FXML
    private TextField shearEmptyRingMassFirstMeasurement;
    @FXML
    private TextField shearEmptyRingMassSecondMeasurement;
    @FXML
    private TextField shearRingWithWetSoilMassFirstMeasurement;
    @FXML
    private TextField shearRingWithWetSoilMassSecondMeasurement;
    @FXML
    private TextField shearRingVolumeFirstMeasurement;
    @FXML
    private TextField shearRingVolumeSecondMeasurement;
    @FXML
    private TextField shearRingDensityFirstMeasurement;
    @FXML
    private TextField shearRingDensitySecondMeasurement;
    @FXML
    private TextField shearRingDrySoilDensityFirstMeasurement;
    @FXML
    private TextField shearRingDrySoilDensitySecondMeasurement;
    @FXML
    private TextField shearRingDensityAverageFirstMeasurement;
    @FXML
    private TextField shearRingDrySoilDensityAverageFirstMeasurement;

    @FXML
    private TextField additionalAverageWaterContentField;
    @FXML
    private TextField additionalRingDensityAverageField;
    @FXML
    private TextField additionalRingDrySoilDensityField;

    //endregion

    //region Density Fields

    // следующее поле перенесено в поля Физическкие свойства. Данные будут поступать оттуда.
//    //плотность частиц грунта
//    @FXML
//    private TextField averageDensity;

    private Density density;
    private DensityDTO densityDTO;
    //endregion

    //region Water Content Fields
    @FXML
    private TextField naturalWaterContentWeighingBottleNumberFirstMeasurement;
    @FXML
    private TextField naturalWaterContentWeighingBottleNumberSecondMeasurement;
    @FXML
    private TextField naturalWaterContentEmptyWeighingBottleMassFirstMeasurement;
    @FXML
    private TextField naturalWaterContentEmptyWeighingBottleMassSecondMeasurement;
    @FXML
    private TextField naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement;
    @FXML
    private TextField naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement;
    @FXML
    private TextField naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement;
    @FXML
    private TextField naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement;


    // следующие пять полей перенесены в поля Физическкие свойства. Данные будут поступать оттуда.
//    @FXML
//    private TextField naturalAverageWaterContent;
//    //влажность на границе текучести
//    @FXML
//    private TextField averageLiquidLimit;
//    //влажность на границе раската
//    @FXML
//    private TextField averagePlasticLimit;
//    //число пластичности
//    @FXML
//    private TextField plasticityIndex;
//    //показатель текучести, в другом блоке (на текущей схеме ВА в нижнем правом блоке, справа, верхняя строчка)
//    @FXML
//    private TextField liquidityIndex;

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


    //плотность частиц грунта
//    @FXML
//    private TextField particleDensity;

    // следующие три поля перенесены в поля Физическкие свойства. Данные будут поступать оттуда.
//    //коэффициент пористости
//    @FXML
//    private TextField voidRatio;
//    //полная влагоемкость
//    @FXML
//    private TextField fullWaterContent;
//    //коэффициент водонасыщения
//    @FXML
//    private TextField saturationRatio;

    // следующие два поля перенесены в поля Физическкие свойства. Данные будут поступать оттуда.
    //плотность грунта
//    @FXML
//    private TextField ringDensityAverage;
//    //плотность скелета грунта
//    @FXML
//    private TextField ringDrySoilDensity;

    private RingDensity ringDensity;
    private RingDensityDTO ringDensityDTO;
    //endregion

    //region PhysicalProperties Fields

    //влажность на границе текучести
    @FXML
    private TextField averageLiquidLimit;
    //влажность на границе раската
    @FXML
    private TextField averagePlasticLimit;
    //число пластичности
    @FXML
    private TextField plasticityIndex;

    //плотность частиц грунта
    @FXML
    private TextField averageDensity;

    @FXML
    private TextField naturalAverageWaterContent;
    //плотность грунта
    @FXML
    private TextField ringDensityAverage;
    //плотность скелета грунта
    @FXML
    private TextField ringDrySoilDensity;

    //показатель текучести, в другом блоке (на текущей схеме ВА в нижнем правом блоке, справа, верхняя строчка)
    @FXML
    private TextField liquidityIndex;
    //коэффициент пористости
    @FXML
    private TextField voidRatio;
    //полная влагоемкость
    @FXML
    private TextField fullWaterContent;
    //коэффициент водонасыщения
    @FXML
    private TextField saturationRatio;

    //endregion


    private PhysicalPropertiesDTO physicalPropertiesDTO;

    @FXML
    ChoiceBox<String> shearPointChoiceBox;

    protected Map<TextField, Property> allRingTextFieldMap;
    protected Map<TextField, Property> nonEditableRingTextFieldMap;

    protected Map<TextField, Property> allDensityTextFieldMap;
    protected Map<TextField, Property> nonEditableDensityTextFieldMap;

    protected Map<TextField, Property> allWaterContentTextFieldMap;
    protected Map<TextField, Property> nonEditableWaterContentTextFieldMap;

    protected Map<TextField, Property> allRingDensityTextFieldMap;
    protected Map<TextField, Property> nonEditableRingDensityTextFieldMap;

    protected Map<TextField, Property> allBoychenkoConeTextFieldMap;
    protected Map<TextField, Property> nonEditableBoychenkoConeTextFieldMap;

    protected Map<TextField, Property> allPhysicalPropertiesTextFieldMap;
    protected Map<TextField, Property> nonEditablePhysicalPropertiesTextFieldMap;

    protected List<Shear> shearPointsInCurrentSampleList;
    protected List<String> shearPointsNumberList;
//    protected List<Sample> samplesInCurrentSurveyPointList;

    protected ShearWaterContentCutRingMethodController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                                       MethodViewService<Shear, ShearDto> service, CurrentState currentState,
                                                       WeighingBottleService weighingBottleService, PotService potService,
                                                       RingService ringService,
                                                       DensityService densityService, BoychenkoConeService boychenkoConeService,
                                                       WaterContentService waterContentService, RingDensityService ringDensityService,
                                                       PhysicalPropertiesService physicalPropertiesService) {
        super(applicationContext, sampleService, service, currentState, weighingBottleService, potService, ringService);
        this.densityService = densityService;
        this.boychenkoConeService = boychenkoConeService;
        this.waterContentService = waterContentService;
        this.ringDensityService = ringDensityService;
        this.physicalPropertiesService = physicalPropertiesService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();

        setShearPointNumberChoiceBox();
    }

    @Override
    protected void setFirstSample() {
        List<Sample> byProject = sampleService.getByProject(currentState.getCurrentProject());
        Sample sample = byProject.get(ZERO_INDEX);

        currentState.setSample(sample);

        setFirstShearPoint();
    }

    @Override
    public void initMaps() {
        allRingTextFieldMap = new HashMap<>();
        nonEditableRingTextFieldMap = new HashMap<>();
        allDensityTextFieldMap = new HashMap<>();
        nonEditableDensityTextFieldMap = new HashMap<>();
        allTextFieldMap = new HashMap<>();
        nonEditableTextFieldMap = new HashMap<>();
        allWaterContentTextFieldMap = new HashMap<>();
        nonEditableWaterContentTextFieldMap = new HashMap<>();
        allRingDensityTextFieldMap = new HashMap<>();
        nonEditableRingDensityTextFieldMap = new HashMap<>();
        allBoychenkoConeTextFieldMap = new HashMap<>();
        nonEditableBoychenkoConeTextFieldMap = new HashMap<>();
        allPhysicalPropertiesTextFieldMap = new HashMap<>();
        nonEditablePhysicalPropertiesTextFieldMap = new HashMap<>();
    }

    @Override
    public void setTextFieldLists() {
        setShearTextFieldLists();

//        setDensityTextFieldLists();
        setBoychenkoConeTextFieldLists();
        setWaterContentTextFieldLists();
        setRingDensityTextFieldLists();
        setPhysicalPropertiesTextFieldLists();
    }

    private void setShearTextFieldLists() {
        allTextFieldMap.put(shearNaturalWaterContentWeighingBottleNumberFirstMeasurement, dto.shearNaturalWaterContentWeighingBottleNumberFirstMeasurementProperty());
        allTextFieldMap.put(shearNaturalWaterContentWeighingBottleNumberSecondMeasurement, dto.shearNaturalWaterContentWeighingBottleNumberSecondMeasurementProperty());
        allTextFieldMap.put(shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement, dto.shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurementProperty());
        allTextFieldMap.put(shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement, dto.shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurementProperty());
        allTextFieldMap.put(shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement, dto.shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurementProperty());
        allTextFieldMap.put(shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement, dto.shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurementProperty());
        allTextFieldMap.put(shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement, dto.shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurementProperty());
        allTextFieldMap.put(shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement, dto.shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurementProperty());
        allTextFieldMap.put(shearNaturalWaterContentWeighingBottleFirstMeasurement, dto.shearNaturalWaterContentWeighingBottleFirstMeasurementProperty());
        allTextFieldMap.put(shearNaturalWaterContentWeighingBottleSecondMeasurement, dto.shearNaturalWaterContentWeighingBottleSecondMeasurementProperty());
        allTextFieldMap.put(naturalShearAverageWaterContentFirstMeasurement, dto.naturalShearAverageWaterContentFirstMeasurementProperty());

        allTextFieldMap.put(additionalAverageWaterContentField, dto.naturalShearAverageWaterContentFirstMeasurementProperty());

        nonEditableTextFieldMap.put(shearNaturalWaterContentWeighingBottleFirstMeasurement, dto.shearNaturalWaterContentWeighingBottleFirstMeasurementProperty());
        nonEditableTextFieldMap.put(shearNaturalWaterContentWeighingBottleSecondMeasurement, dto.shearNaturalWaterContentWeighingBottleSecondMeasurementProperty());
        nonEditableTextFieldMap.put(naturalShearAverageWaterContentFirstMeasurement, dto.naturalShearAverageWaterContentFirstMeasurementProperty());

        nonEditableTextFieldMap.put(additionalAverageWaterContentField, dto.naturalShearAverageWaterContentFirstMeasurementProperty());

        allRingTextFieldMap.put(shearRingNumberFirstMeasurement, dto.shearRingNumberFirstMeasurementProperty());
        allRingTextFieldMap.put(shearRingNumberSecondMeasurement, dto.shearRingNumberSecondMeasurementProperty());
        allRingTextFieldMap.put(shearEmptyRingMassFirstMeasurement, dto.shearEmptyRingMassFirstMeasurementProperty());
        allRingTextFieldMap.put(shearEmptyRingMassSecondMeasurement, dto.shearEmptyRingMassSecondMeasurementProperty());
        allRingTextFieldMap.put(shearRingWithWetSoilMassFirstMeasurement, dto.shearRingWithWetSoilMassFirstMeasurementProperty());
        allRingTextFieldMap.put(shearRingWithWetSoilMassSecondMeasurement, dto.shearRingWithWetSoilMassSecondMeasurementProperty());
        allRingTextFieldMap.put(shearRingVolumeFirstMeasurement, dto.shearRingVolumeFirstMeasurementProperty());
        allRingTextFieldMap.put(shearRingVolumeSecondMeasurement, dto.shearRingVolumeSecondMeasurementProperty());
        allRingTextFieldMap.put(shearRingDensityFirstMeasurement, dto.shearRingDensityFirstMeasurementProperty());
        allRingTextFieldMap.put(shearRingDensitySecondMeasurement, dto.shearRingDensitySecondMeasurementProperty());
        allRingTextFieldMap.put(shearRingDrySoilDensityFirstMeasurement, dto.shearRingDrySoilDensityFirstMeasurementProperty());
        allRingTextFieldMap.put(shearRingDrySoilDensitySecondMeasurement, dto.shearRingDrySoilDensitySecondMeasurementProperty());
        allRingTextFieldMap.put(shearRingDensityAverageFirstMeasurement, dto.shearRingDensityAverageFirstMeasurementProperty());
        allRingTextFieldMap.put(shearRingDrySoilDensityAverageFirstMeasurement, dto.shearRingDrySoilDensityAverageFirstMeasurementProperty());

        allRingTextFieldMap.put(additionalRingDensityAverageField, dto.shearRingDensityAverageFirstMeasurementProperty());
        allRingTextFieldMap.put(additionalRingDrySoilDensityField, dto.shearRingDrySoilDensityAverageFirstMeasurementProperty());

        nonEditableRingTextFieldMap.put(shearRingDensityFirstMeasurement, dto.shearRingDensityFirstMeasurementProperty());
        nonEditableRingTextFieldMap.put(shearRingDensitySecondMeasurement, dto.shearRingDensitySecondMeasurementProperty());
        nonEditableRingTextFieldMap.put(shearRingDrySoilDensityFirstMeasurement, dto.shearRingDrySoilDensityFirstMeasurementProperty());
        nonEditableRingTextFieldMap.put(shearRingDrySoilDensitySecondMeasurement, dto.shearRingDrySoilDensitySecondMeasurementProperty());
        nonEditableRingTextFieldMap.put(shearRingDensityAverageFirstMeasurement, dto.shearRingDensityAverageFirstMeasurementProperty());
        nonEditableRingTextFieldMap.put(shearRingDrySoilDensityAverageFirstMeasurement, dto.shearRingDrySoilDensityAverageFirstMeasurementProperty());

        nonEditableRingTextFieldMap.put(additionalRingDensityAverageField, dto.shearRingDensityAverageFirstMeasurementProperty());
        nonEditableRingTextFieldMap.put(additionalRingDrySoilDensityField, dto.shearRingDrySoilDensityAverageFirstMeasurementProperty());


        JavaFXCommonMethods.setTextFieldProperties(allTextFieldMap, nonEditableTextFieldMap,
                weighingBottleService, null);

        JavaFXCommonMethods.setTextFieldProperties(allRingTextFieldMap, nonEditableRingTextFieldMap,
                ringService, null);
    }

    @Override
    public void setFieldBinding() {
        super.setFieldBinding();

        JavaFXCommonMethods.setFieldBinding(allRingTextFieldMap);

//        JavaFXCommonMethods.setFieldBinding(allDensityTextFieldMap);
        JavaFXCommonMethods.setFieldBinding(allWaterContentTextFieldMap);
        JavaFXCommonMethods.setFieldBinding(allRingDensityTextFieldMap);
        JavaFXCommonMethods.setFieldBinding(allBoychenkoConeTextFieldMap);
        JavaFXCommonMethods.setFieldBinding(allPhysicalPropertiesTextFieldMap);
    }

    @Override
    public void setDTO() {
        Sample currentSample = currentState.getSample();

        List<Shear> allBySample = service.getAllBySample(currentSample);

//        if(allBySample.isEmpty()) {
//            currentShear = new Shear();
//            currentShear.
//        }
        currentShear = allBySample.get(ZERO_INDEX);

        object = service.getBySampleAndNumber(currentSample, currentShear.getShearPointNumber());
        dto = service.getDto(object);

        density = densityService.getBySample(currentSample);
        waterContent = waterContentService.getBySample(currentSample);
        boychenkoCone = boychenkoConeService.getBySample(currentSample);
        ringDensity = ringDensityService.getBySample(currentSample);

        densityDTO = densityService.getDto(density);
        waterContentDto = waterContentService.getDto(waterContent);
        boychenkoConeDTO = boychenkoConeService.getDto(boychenkoCone);
        ringDensityDTO = ringDensityService.getDto(ringDensity);

        physicalProperties = physicalPropertiesService.getBySample(currentSample);
        physicalPropertiesDTO = physicalPropertiesService.getDto(physicalProperties);
    }

    public void setDTOs() {
        Sample currentSample = currentState.getSample();

        if(currentShear == null) object = null;
        else {
            object = service.getBySampleAndNumber(currentSample, currentShear.getShearPointNumber());
            dto = service.getDto(object);
        }

        density = densityService.getBySample(currentSample);
        waterContent = waterContentService.getBySample(currentSample);
        boychenkoCone = boychenkoConeService.getBySample(currentSample);
        ringDensity = ringDensityService.getBySample(currentSample);

        densityDTO = densityService.getDto(density);
        waterContentDto = waterContentService.getDto(waterContent);
        boychenkoConeDTO = boychenkoConeService.getDto(boychenkoCone);
        ringDensityDTO = ringDensityService.getDto(ringDensity);

        physicalProperties = physicalPropertiesService.getBySample(currentSample);
        physicalPropertiesDTO = physicalPropertiesService.getDto(physicalProperties);
    }

    private void setFirstShearPoint() {
        List<Shear> byProject = service.getByProject(currentState.getCurrentProject());
        if(!byProject.isEmpty()) currentShear = byProject.get(ZERO_INDEX);

    }


    //region Density Methods
    private void setDensityTextFieldLists() {
//        allDensityTextFieldMap.put(averageDensity, densityDTO.averageDensityProperty());

//        nonEditableDensityTextFieldMap.put(averageDensity, densityDTO.averageDensityProperty());

//        JavaFXCommonMethods.setTextFieldProperties(allDensityTextFieldMap, nonEditableDensityTextFieldMap,
//                null, this::updateEntity);
    }
    //endregion

    //region Water Content Methods
    private void setWaterContentTextFieldLists() {
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleNumberFirstMeasurement, waterContentDto.naturalWaterContentWeighingBottleNumberFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleNumberSecondMeasurement, waterContentDto.naturalWaterContentWeighingBottleNumberSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentEmptyWeighingBottleMassFirstMeasurement, waterContentDto.naturalWaterContentEmptyWeighingBottleMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentEmptyWeighingBottleMassSecondMeasurement, waterContentDto.naturalWaterContentEmptyWeighingBottleMassSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement, waterContentDto.naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement, waterContentDto.naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement, waterContentDto.naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurementProperty());
        allWaterContentTextFieldMap.put(naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement, waterContentDto.naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurementProperty());
//        allWaterContentTextFieldMap.put(naturalAverageWaterContent, waterContentDto.naturalAverageWaterContentProperty());
//        allWaterContentTextFieldMap.put(averageLiquidLimit, waterContentDto.averageLiquidLimitProperty());
//        allWaterContentTextFieldMap.put(averagePlasticLimit, waterContentDto.averagePlasticLimitProperty());
//        allWaterContentTextFieldMap.put(plasticityIndex, waterContentDto.plasticityIndexProperty());
//        allWaterContentTextFieldMap.put(liquidityIndex, waterContentDto.liquidityIndexProperty());

        nonEditableWaterContentTextFieldMap.put(naturalWaterContentEmptyWeighingBottleMassFirstMeasurement, waterContentDto.naturalWaterContentEmptyWeighingBottleMassFirstMeasurementProperty());
        nonEditableWaterContentTextFieldMap.put(naturalWaterContentEmptyWeighingBottleMassSecondMeasurement, waterContentDto.naturalWaterContentEmptyWeighingBottleMassSecondMeasurementProperty());
//        nonEditableWaterContentTextFieldMap.put(naturalAverageWaterContent, waterContentDto.naturalAverageWaterContentProperty());
//        nonEditableWaterContentTextFieldMap.put(averageLiquidLimit, waterContentDto.averageLiquidLimitProperty());
//        nonEditableWaterContentTextFieldMap.put(averagePlasticLimit, waterContentDto.averagePlasticLimitProperty());
//        nonEditableWaterContentTextFieldMap.put(plasticityIndex, waterContentDto.plasticityIndexProperty());
//        nonEditableWaterContentTextFieldMap.put(liquidityIndex, waterContentDto.liquidityIndexProperty());

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
//        allRingDensityTextFieldMap.put(ringDensityAverage, ringDensityDTO.ringDensityAverageProperty());
//        allRingDensityTextFieldMap.put(ringDrySoilDensity, ringDensityDTO.ringDrySoilDensityProperty());
//        allRingDensityTextFieldMap.put(voidRatio, ringDensityDTO.voidRatioProperty());
//        allRingDensityTextFieldMap.put(fullWaterContent, ringDensityDTO.fullWaterContentProperty());
//        allRingDensityTextFieldMap.put(saturationRatio, ringDensityDTO.saturationRatioProperty());

        nonEditableRingDensityTextFieldMap.put(ringEmptyMassFirstMeasurement, ringDensityDTO.emptyRingMassFirstMeasurementProperty());
        nonEditableRingDensityTextFieldMap.put(ringEmptyMassSecondMeasurement, ringDensityDTO.emptyRingMassSecondMeasurementProperty());
        nonEditableRingDensityTextFieldMap.put(ringVolumeFirstMeasurement, ringDensityDTO.ringVolumeFirstMeasurementProperty());
        nonEditableRingDensityTextFieldMap.put(ringVolumeSecondMeasurement, ringDensityDTO.ringVolumeSecondMeasurementProperty());
//        nonEditableRingDensityTextFieldMap.put(ringDensityAverage, ringDensityDTO.ringDensityAverageProperty());
//        nonEditableRingDensityTextFieldMap.put(ringDrySoilDensity, ringDensityDTO.ringDrySoilDensityProperty());
//        nonEditableRingDensityTextFieldMap.put(voidRatio, ringDensityDTO.voidRatioProperty());
//        nonEditableRingDensityTextFieldMap.put(fullWaterContent, ringDensityDTO.fullWaterContentProperty());
//        nonEditableRingDensityTextFieldMap.put(saturationRatio, ringDensityDTO.saturationRatioProperty());

        JavaFXCommonMethods.setTextFieldProperties(allRingDensityTextFieldMap, nonEditableRingDensityTextFieldMap,
                ringService, this::updateEntity);
    }
    //endregion

    // region Physical Properties Methods
    private void setPhysicalPropertiesTextFieldLists() {

//        averageLiquidLimit
//                averagePlasticLimit
//        plasticityIndex
//                averageDensity
//        naturalAverageWaterContent
//                ringDensityAverage
//        ringDrySoilDensity
//                liquidityIndex
//        voidRatio
//                fullWaterContent
//        saturationRatio

        allPhysicalPropertiesTextFieldMap.put(averageLiquidLimit, physicalPropertiesDTO.averageLiquidLimitProperty());
        allPhysicalPropertiesTextFieldMap.put(averagePlasticLimit, physicalPropertiesDTO.plasticLimitProperty());
        allPhysicalPropertiesTextFieldMap.put(plasticityIndex, physicalPropertiesDTO.plasticityIndexProperty());
        allPhysicalPropertiesTextFieldMap.put(averageDensity, physicalPropertiesDTO.averageDensityProperty());
        allPhysicalPropertiesTextFieldMap.put(naturalAverageWaterContent, physicalPropertiesDTO.naturalAverageWaterContentProperty());
        allPhysicalPropertiesTextFieldMap.put(ringDensityAverage, physicalPropertiesDTO.ringDensityAverageProperty());
        allPhysicalPropertiesTextFieldMap.put(ringDrySoilDensity, physicalPropertiesDTO.ringDrySoilDensityProperty());
        allPhysicalPropertiesTextFieldMap.put(liquidityIndex, physicalPropertiesDTO.liquidityIndexProperty());
        allPhysicalPropertiesTextFieldMap.put(voidRatio, physicalPropertiesDTO.voidRatioProperty());
        allPhysicalPropertiesTextFieldMap.put(fullWaterContent, physicalPropertiesDTO.fullWaterContentProperty());
        allPhysicalPropertiesTextFieldMap.put(saturationRatio, physicalPropertiesDTO.saturationRatioProperty());

        nonEditablePhysicalPropertiesTextFieldMap.put(averageLiquidLimit, physicalPropertiesDTO.averageLiquidLimitProperty());
        nonEditablePhysicalPropertiesTextFieldMap.put(averagePlasticLimit, physicalPropertiesDTO.plasticLimitProperty());
        nonEditablePhysicalPropertiesTextFieldMap.put(plasticityIndex, physicalPropertiesDTO.plasticityIndexProperty());
        nonEditablePhysicalPropertiesTextFieldMap.put(averageDensity, physicalPropertiesDTO.averageDensityProperty());
        nonEditablePhysicalPropertiesTextFieldMap.put(naturalAverageWaterContent, physicalPropertiesDTO.naturalAverageWaterContentProperty());
        nonEditablePhysicalPropertiesTextFieldMap.put(ringDensityAverage, physicalPropertiesDTO.ringDensityAverageProperty());
        nonEditablePhysicalPropertiesTextFieldMap.put(ringDrySoilDensity, physicalPropertiesDTO.ringDrySoilDensityProperty());
        nonEditablePhysicalPropertiesTextFieldMap.put(liquidityIndex, physicalPropertiesDTO.liquidityIndexProperty());
        nonEditablePhysicalPropertiesTextFieldMap.put(voidRatio, physicalPropertiesDTO.voidRatioProperty());
        nonEditablePhysicalPropertiesTextFieldMap.put(fullWaterContent, physicalPropertiesDTO.fullWaterContentProperty());
        nonEditablePhysicalPropertiesTextFieldMap.put(saturationRatio, physicalPropertiesDTO.saturationRatioProperty());

        JavaFXCommonMethods.setTextFieldProperties(allPhysicalPropertiesTextFieldMap, nonEditablePhysicalPropertiesTextFieldMap,
                null, this::updateEntity);
    }
    //endregion

    //region Common Methods
    @Override
    public void updateEntity() {
        calculate();
    }

    public void calculate() {
        densityService.calculate(currentState.getSample(), densityDTO);
        waterContentService.calculate(currentState.getSample(), waterContentDto);
        ringDensityService.calculate(currentState.getSample(), ringDensityDTO);
        boychenkoConeService.calculate(currentState.getSample(), boychenkoConeDTO);
    }
    //endregion

    @FXML
    public void onCalculateShearButtonClicked() {
        service.calculate(currentState.getSample(), dto);
    }

//    @FXML
//    public void onCalculatePhysicalPropertiesButtonClick() {
//        physicalPropertiesDTO.setNaturalAverageWaterContent(waterContentDto.getNaturalAverageWaterContent());
//        physicalPropertiesDTO.setAverageLiquidLimit(waterContentDto.getAverageLiquidLimit());
//        physicalPropertiesDTO.setPlasticLimit(waterContentDto.getAveragePlasticLimit());
//        physicalPropertiesDTO.setAverageDensity(densityDTO.getAverageDensity());
//        physicalPropertiesDTO.setRingDensityAverage(ringDensityDTO.getRingDensityAverage());
//        physicalPropertiesDTO.setRingDrySoilDensity(ringDensityDTO.getRingDrySoilDensity());
//        physicalPropertiesDTO.setVoidRatio(ringDensityDTO.getVoidRatio());
//
//        physicalPropertiesDTO.setSaturationRatio(ringDensityDTO.getSaturationRatio());
//        physicalPropertiesDTO.setFullWaterContent(ringDensityDTO.getFullWaterContent());
//
//        physicalPropertiesDTO.setPlasticityIndex(waterContentDto.getPlasticityIndex());
//        physicalPropertiesDTO.setLiquidityIndex(ringDensityDTO.getLiquidityIndex());
//
//        physicalPropertiesDTO.setBrokenStrBoychenkoConeImmersionDepthAverage(boychenkoConeDTO.getBrokenStrImmersionDepthAverage());
//        physicalPropertiesDTO.setUndisturbedStrBoychenkoConeImmersionDepthAverage(boychenkoConeDTO.getUndisturbedStrImmersionDepthAverage());
//
//        //TODO: добавить коррозию
//
//        PhysicalPropertiesMethod.calculatePhysicalProperties(physicalPropertiesDTO);
//
//        physicalPropertiesService.updateFromDTO(currentState.getSample(), physicalPropertiesDTO);
//    }

    @FXML
    public void onShowPhysicalPropertiesButtonClick(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeSceneToModalWindow(event, PHYSICAL_PROPERTIES_RESULT_TABLE_VIEW.getPath(),
                applicationContext, stage, PHYSICAL_PROPERTIES.getTitle());
    }

    //по сути кнопка по функционалу идентична "onCalculatePhysicalPropertiesButtonClick":
    @FXML
    public void onRestoreDataFromPhysicalPropertiesButtonClick() {
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
        physicalPropertiesDTO.setLiquidityIndex(ringDensityDTO.getLiquidityIndex());

        physicalPropertiesDTO.setBrokenStrBoychenkoConeImmersionDepthAverage(boychenkoConeDTO.getBrokenStrImmersionDepthAverage());
        physicalPropertiesDTO.setUndisturbedStrBoychenkoConeImmersionDepthAverage(boychenkoConeDTO.getUndisturbedStrImmersionDepthAverage());

        //TODO: добавить коррозию

        PhysicalPropertiesMethod.calculatePhysicalProperties(physicalPropertiesDTO);

        physicalPropertiesService.updateFromDTO(currentState.getSample(), physicalPropertiesDTO);

    }

    @FXML
    public void onSetPhysicalPropertiesFromShearButtonClick() {
        physicalPropertiesDTO.setNaturalAverageWaterContent(dto.getShearNaturalWaterContentWeighingBottleFirstMeasurement());
        physicalPropertiesDTO.setRingDensityAverage(dto.getShearRingDensityAverageFirstMeasurement());
        physicalPropertiesDTO.setRingDrySoilDensity(dto.getShearRingDrySoilDensityAverageFirstMeasurement());

        var physicalPropertiesLiquidityIndex = PhysicalPropertiesMethod.calculateLiquidityIndex(
                dto.getShearNaturalWaterContentWeighingBottleFirstMeasurement(),
                physicalPropertiesDTO.getPlasticLimit(),
                physicalPropertiesDTO.getPlasticityIndex()
        );

        physicalPropertiesDTO.setLiquidityIndex(physicalPropertiesLiquidityIndex);

        PhysicalPropertiesMethod.calculatePhysicalProperties(physicalPropertiesDTO);

        physicalPropertiesService.updateFromDTO(currentState.getSample(), physicalPropertiesDTO);

        setDTO();

        setTextFieldLists();

        setFieldBinding();
    }

    @FXML
    @Override
    public void setSampleChoiceBox() {
        shearPointsInCurrentSampleList = service.getByProject(currentState.getCurrentProject());
        sampleLaborNumberList = shearPointsInCurrentSampleList.stream().map(shear -> shear.getSample().getLaborNumber()).distinct().toList();
        var sampleList = FXCollections.observableArrayList(sampleLaborNumberList);
        sampleChoiceBox.getItems().addAll(sampleList);
        sampleChoiceBox.setValue(currentState.getSample().getLaborNumber());
    }

    @FXML
    public void setShearPointNumberChoiceBox() {

        shearPointsInCurrentSampleList = service.getAllBySample(currentState.getSample());
        shearPointsNumberList = shearPointsInCurrentSampleList.stream().map(shear -> String.valueOf(shear.getShearPointNumber())).toList();
        var shearPointList = FXCollections.observableArrayList(shearPointsNumberList);
        shearPointChoiceBox.setItems(shearPointList);
//        tableColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(laborNumberObservableList));

        shearPointChoiceBox.setValue(String.valueOf(shearPointList.get(ZERO_INDEX)));
    }

    @Override
    public void setSandCheckBox() {
    }

    @Override
    public void onChoiceBoxClicked(ActionEvent event) {
//        super.onChoiceBoxClicked(event);

        sampleChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue))
        {
            Sample sample = sampleService.getByLaborNumber(newValue);
            currentState.setSample(sample);

            System.out.println("And here current sample: " + sample.getLaborNumber() + " | " + newValue);

            var shears = service.getAllBySample(sample);
            currentShear = shears.get(ZERO_INDEX);

            setDTOs();
//            setDTO();
//            setTextFieldLists();

            object = service.getBySampleAndNumber(currentState.getSample(), currentShear.getShearPointNumber());
            dto = service.getDto(object);

//            setShearTextFieldLists();
            setTextFieldLists();
            setFieldBinding();

//            super.setFieldBinding();
//
//            JavaFXCommonMethods.setFieldBinding(allRingTextFieldMap);

            setShearPointNumberChoiceBox();
        }});
    }

    @Override
    public void onPreviousSampleButtonClick(ActionEvent event) {
//        super.onPreviousSampleButtonClick(event);

        int indexOfCurrentSample = sampleLaborNumberList.indexOf(currentState.getSample().getLaborNumber());

        int prevIndex;

        if (indexOfCurrentSample > 0) {
            prevIndex = indexOfCurrentSample - 1;
        } else {
            prevIndex = sampleLaborNumberList.size() - 1;
        }

        Sample nextSample = sampleService.getByLaborNumber(sampleLaborNumberList.get(prevIndex));

//        Sample nextSample = samplesInCurrentSurveyPointList.get(prevIndex);

        currentState.setSample(nextSample);
        sampleChoiceBox.setValue(currentState.getSample().getLaborNumber());

        var shears = service.getAllBySample(nextSample);
        currentShear = shears.get(ZERO_INDEX);

        setDTOs();

        object = service.getBySampleAndNumber(currentState.getSample(), currentShear.getShearPointNumber());
        dto = service.getDto(object);

        setTextFieldLists();
        setFieldBinding();

        setShearPointNumberChoiceBox();
    }

    @Override
    public void onNextSampleButtonClick(ActionEvent event) {
//        super.onNextSampleButtonClick(event);

        int indexOfCurrentSample = sampleLaborNumberList.indexOf(currentState.getSample().getLaborNumber());

        int nextIndex;

        if (indexOfCurrentSample < sampleLaborNumberList.size() - 1) {
            nextIndex = indexOfCurrentSample + 1;
        } else {
            nextIndex = 0;
        }

        Sample nextSample = sampleService.getByLaborNumber(sampleLaborNumberList.get(nextIndex));

//        Sample nextSample = samplesInCurrentSurveyPointList.get(prevIndex);

        currentState.setSample(nextSample);
        sampleChoiceBox.setValue(currentState.getSample().getLaborNumber());

        var shears = service.getAllBySample(nextSample);
        currentShear = shears.get(ZERO_INDEX);

        setDTOs();

        object = service.getBySampleAndNumber(currentState.getSample(), currentShear.getShearPointNumber());
        dto = service.getDto(object);

        setTextFieldLists();
        setFieldBinding();

        setShearPointNumberChoiceBox();
    }

    @FXML
    public void onShearPointChoiceBoxClicked(ActionEvent event) {
        shearPointChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                if (Objects.nonNull(newValue))
        {
            var shearPointNumber = Integer.valueOf(newValue);

            currentShear = service.getBySampleAndNumber(currentState.getSample(), shearPointNumber);

            shearPointNumber = currentShear.getShearPointNumber();

            object = service.getBySampleAndNumber(currentState.getSample(), shearPointNumber);
            dto = service.getDto(object);

            setShearTextFieldLists();

            super.setFieldBinding();

            JavaFXCommonMethods.setFieldBinding(allRingTextFieldMap);
        }});
    }

    @FXML
    public void onPreviousShearPointButtonClick(ActionEvent event) {
        log.info("Prev shear button clicked");

        int currentIndex = shearPointsNumberList.indexOf(String.valueOf(currentShear.getShearPointNumber()));

        int prevIndex;

        if (currentIndex > 0) {
            prevIndex = currentIndex - 1;
        } else {
            prevIndex = shearPointsInCurrentSampleList.size() - 1;
        }

        Shear nextShear = shearPointsInCurrentSampleList.get(prevIndex);

        currentShear = nextShear;

        shearPointChoiceBox.setValue(String.valueOf(nextShear.getShearPointNumber()));

        object = service.getBySampleAndNumber(currentState.getSample(), nextShear.getShearPointNumber());
        dto = service.getDto(object);

        setShearTextFieldLists();

        super.setFieldBinding();

        JavaFXCommonMethods.setFieldBinding(allRingTextFieldMap);
    }

    @FXML
    public void onNextShearPointButtonClick(ActionEvent event) {
        log.info("Next shear button clicked");

        int currentIndex = shearPointsNumberList.indexOf(String.valueOf(currentShear.getShearPointNumber()));

        int nextIndex;

        if (currentIndex < shearPointsInCurrentSampleList.size() - 1) {
            nextIndex = currentIndex + 1;
        } else {
            nextIndex = 0;
        }

        Shear nextShear = shearPointsInCurrentSampleList.get(nextIndex);

        currentShear = nextShear;

        shearPointChoiceBox.setValue(String.valueOf(nextShear.getShearPointNumber()));

        System.out.println("^^^^^^^^^^^^^^^^^^^^");
        System.out.println("sample: " + currentState.getSample().getLaborNumber());
        System.out.println("shear: " + nextShear.getShearPointNumber());

        object = service.getBySampleAndNumber(currentState.getSample(), nextShear.getShearPointNumber());
        dto = service.getDto(object);

        setShearTextFieldLists();

        super.setFieldBinding();

        JavaFXCommonMethods.setFieldBinding(allRingTextFieldMap);
    }

//    @FXML
//    public void onDensityCopyButtonClick() {
//        object.setParticleDensity(currentState.getSample().getAverageDensity());
//        dto.setParticleDensity(currentState.getSample().getAverageDensity());
//
//        service.update(object);
//    }
//
//    @FXML
//    public void onCalculateGranCompositionButtonClick() {
//        calculate();
//    }
//
//    @FXML
//    public void onShowGranCompositionButtonClick(ActionEvent event) throws IOException {
//        JavaFXCommonMethods.changeScene(event, GRAN_COMPOSITION_RESULT_TABLE_VIEW.getPath(), applicationContext,
//                GRAN_COMPOSITION_AREOMETRY.getTitle());
//    }

    @FXML
    public void onShearDensityAndWaterContentButtonClicked(ActionEvent event) throws IOException {
        log.info("Уже на экране таблицы ввода данных");
    }

    @FXML
    public void onEnterShearDataButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.SHEAR_DATA_VIEW.getPath(),
                applicationContext, SHEAR_DATA.getTitle());
    }

    @FXML
    public void onShearResultTableButtonClicked(ActionEvent event) throws IOException {

//        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_PARTIAL_RESULT_VIEW.getPath(),
//                applicationContext, WATER_EXTRACT_PARTIAL_RESULT.getTitle());
    }


}

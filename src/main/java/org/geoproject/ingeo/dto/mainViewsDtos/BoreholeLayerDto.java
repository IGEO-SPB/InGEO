package org.geoproject.ingeo.dto.mainViewsDtos;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.dto.classificators.kga.ColorDto;
import org.geoproject.ingeo.enums.dtoenums.BoreholeLayerDtoFieldsEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Описание слоев в колонке
 */

@Getter
@Setter
@RequiredArgsConstructor
public class BoreholeLayerDto {

    private Long id;
//    private String egeNumber;
//    private String shortName;
    /**
     * Использование JavaFX property требуется для маппинга на таблицу TableView.
     * За счет этого таблица автоматически отслеживает изменение поля, соответствующего
     * колонке в экземпляре DTO, соответствующем выделенной строке в таблице.
     */
    private StringProperty shortName = new SimpleStringProperty();


    public String getShortName() {
        return shortName.get();
    }

    public StringProperty shortNameProperty() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName.set(shortName);
    }

    private EgeDto egeDto;

    private Long surveyPointId;

    //глубина подошвы
//    private Float layerBottomDepth;

    private FloatProperty layerBottomDepth = new SimpleFloatProperty();

    public float getLayerBottomDepth() {
        return layerBottomDepth.get();
    }

    public FloatProperty layerBottomDepthProperty() {
        return layerBottomDepth;
    }

    public void setLayerBottomDepth(float layerBottomDepth) {
        this.layerBottomDepth.set(layerBottomDepth);
    }

    //    private Float absoluteTopMark;
//    private Float layerPower;
//    private Float layerTopDepth;
//    private Float firstLayerTop;

    private FloatProperty absoluteTopMark = new SimpleFloatProperty();
    private FloatProperty layerPower = new SimpleFloatProperty();
    private FloatProperty layerTopDepth = new SimpleFloatProperty();
    private FloatProperty firstLayerTop = new SimpleFloatProperty();

    public float getAbsoluteTopMark() {
        return absoluteTopMark.get();
    }

    public FloatProperty absoluteTopMarkProperty() {
        return absoluteTopMark;
    }

    public void setAbsoluteTopMark(float absoluteTopMark) {
        this.absoluteTopMark.set(absoluteTopMark);
    }

    public float getLayerPower() {
        return layerPower.get();
    }

    public FloatProperty layerPowerProperty() {
        return layerPower;
    }

    public void setLayerPower(float layerPower) {
        this.layerPower.set(layerPower);
    }

    public float getLayerTopDepth() {
        return layerTopDepth.get();
    }

    public FloatProperty layerTopDepthProperty() {
        return layerTopDepth;
    }

    public void setLayerTopDepth(float layerTopDepth) {
        this.layerTopDepth.set(layerTopDepth);
    }

    public float getFirstLayerTop() {
        return firstLayerTop.get();
    }

    public FloatProperty firstLayerTopProperty() {
        return firstLayerTop;
    }

    public void setFirstLayerTop(float firstLayerTop) {
        this.firstLayerTop.set(firstLayerTop);
    }

    private Float firstLayerBottom;
//    private String firstLayerType;
//    private Float secondLayerBottom;
    private FloatProperty secondLayerBottom = new SimpleFloatProperty();

    public float getSecondLayerBottom() {
        return secondLayerBottom.get();
    }

    public FloatProperty secondLayerBottomProperty() {
        return secondLayerBottom;
    }

    public void setSecondLayerBottom(float secondLayerBottom) {
        this.secondLayerBottom.set(secondLayerBottom);
    }

    //    private String secondLayerType;
//    private String hatchingNameCredoAutocad;

    //Штриховка, выбирается из классификатора
    private HatchingDto hatchingDto;

//    private SimpleObjectProperty<HatchingDto> hatchingDto = new SimpleObjectProperty<HatchingDto>();

//    public HatchingDto getHatchingDto() {
//        return hatchingDto.get();
//    }
//
//    public SimpleObjectProperty<HatchingDto> hatchingDtoProperty() {
//        return hatchingDto;
//    }
//
//    public void setHatchingDto(HatchingDto hatchingDto) {
//        this.hatchingDto.set(hatchingDto);
//    }

    //Цвет, выбирается из классификатора
    private ColorDto colorDto;

    private ConsistencyDto firstLayerConsistencyDto;
    private ConsistencyDto secondLayerConsistencyDto;

//    private String color;
    private Float waterDepth;
//    private Boolean isEditableFromEgeList;

    private Boolean isEditableFromEgeListBasic;

    private BooleanProperty isEditableFromEgeList;

//    private String descriptionCredoFormular;
//    private String descriptionCredoFormular;
    private StringProperty descriptionCredoFormular = new SimpleStringProperty();



//    private String descriptionKga;
    private StringProperty descriptionKga = new SimpleStringProperty();

    public String getDescriptionCredoFormular() {
        return descriptionCredoFormular.get();
    }

    public StringProperty descriptionCredoFormularProperty() {
        return descriptionCredoFormular;
    }

    public void setDescriptionCredoFormular(String descriptionCredoFormular) {
        this.descriptionCredoFormular.set(descriptionCredoFormular);
    }

    public String getDescriptionKga() {
        return descriptionKga.get();
    }

    public StringProperty descriptionKgaProperty() {
        return descriptionKga;
    }

    public void setDescriptionKga(String descriptionKga) {
        this.descriptionKga.set(descriptionKga);
    }


    //    number
//    egeNumber
//    soilType
//    layerBottomDepth
//    absoluteTopMark
//    layerPower
//    layerTopDepth
//    firstLayerTop
//    firstLayerBottom
//    firstLayerType
//    secondLayerBottom
//    secondLayerType
//    hatchingNameCredoAutocad
//    color
//    water
//    isEditableFromEgeList
//    descriptionCredoFormular
//    descriptionKga

    public void setFieldValue(BoreholeLayerDtoFieldsEnum field, Object value) {
        switch (field) {
//            case EGE_NUMBER:
//                egeNumber = (String) value;
//                break;
//            case SHORT_NAME:
//                shortName = (String) value;
//                break;
            case EGE:
                egeDto = (EgeDto) value;
                break;
            case LAYER_BOTTOM_DEPTH:
//                layerBottomDepth = (Float) value;
                setLayerBottomDepth((Float) value);
                break;
//            case ABSOLUTE_TOP_MARK:
//                absoluteTopMark = (Float) value;
//                break;
//            case LAYER_POWER:
//                layerPower = (Float) value;
//                break;
//            case LAYER_TOP_DEPTH:
//                layerTopDepth = (Float) value;
//                break;
//            case FIRST_LAYER_TOP:
//                firstLayerTop = (Float) value;
//                break;
            case FIRST_LAYER_BOTTOM:
                firstLayerBottom = (Float) value;
                break;
//            case FIRST_LAYER_TYPE:
//                firstLayerType = (String) value;
//                break;
//            case SECOND_LAYER_BOTTOM:
////                secondLayerBottom = (Float) value;
//                setSecondLayerBottom((Float) value);
//                break;
//            case SECOND_LAYER_TYPE:
//                secondLayerType = (String) value;
//                break;
//            case HATCHING_NAME_CREDO_AUTOCAD:
//                hatchingNameCredoAutocad = (String) value;
//                break;

            case HATCHING:
                hatchingDto = (HatchingDto) value;
                break;

            case FIRST_LAYER_CONSISTENCY:
                firstLayerConsistencyDto = (ConsistencyDto) value;
                break;

            case SECOND_LAYER_CONSISTENCY:
                secondLayerConsistencyDto = (ConsistencyDto) value;
                break;

            case COLOR:
                colorDto = (ColorDto) value;
                break;
            case WATER:
                waterDepth = (Float) value;
                break;
            case IS_EDITABLE_FROM_EGE_LIST:
                isEditableFromEgeListBasic = (Boolean) value;
                break;
            case DESCRIPTION_CREDO_FORMULAR:
                setDescriptionCredoFormular((String) value);
                break;
//            case DESCRIPTION_KGA:
//                descriptionKga = (String) value;
//                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoreholeLayerDto that = (BoreholeLayerDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
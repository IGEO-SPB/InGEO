package org.geoproject.ingeo.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JavaFXConstants {

    public static final Integer SINGLE_ROW_COUNT = 1;
    public static final Integer SINGLE_COLUMN_INDEX = 1;
    public static final Integer SECOND_INDEX = 2;

    public static final Integer SINGLE_CLICK_COUNT = 1;

    public static final Float ZERO_FLOAT_VALUE = 0F;

    public static final String IS_FLOAT_VALUE_PATTERN = "\\d+.0$";
    public static final String FLOAT_SPLIT_PATTERN = ".0$";
    public static final String FLOAT_ROUND_VIEW_PATTERN = "%.2f";

    public static final String TEXTFIELD_NUMERIC_PATTERN = "^\\d+[,.]?\\d*$";
    public static final String EMPLOYEE_FIELD_PATTERN = " | ";

    public static final Integer SCENE_WIDTH_800 = 800;
    public static final Integer SCENE_HEIGHT_500 = 500;

    public static final Integer SCENE_HEIGHT_600 = 600;

    public static final Integer SCENE_WIDTH_1200 = 1200;
    public static final Integer SCENE_HEIGHT_800 = 800;

    public static final String EMPLOYEE_CHOICE_BOX_VALUE = "Выбор сотрудника";
    public static final String CONSTRUCTION_TYPE_CHOICE_BOX_VALUE = "Выбор типа строительства";
    public static final String WATER_SAMPLE_PROPERTY_CHOICE_BOX_VALUE = "Выбрать";


    public static final String SURVEY_POINT_CHOICE_BOX = "surveyPointChoiceBox";
    public static final String SURVEY_POINT_NUMBER_CHOICE_BOX = "surveyPointNumberChoiceBox";

    // tableview

    public static final String LABOR_NUMBER_COLUMN = "laborNumber";
    public static final String SURVEY_POINT_NUMBER_COLUMN = "surveyPointNumber";
    public static final String DEPTH_COLUMN = "depth";
    public static final String DEPTH_FROM_COLUMN = "depthFrom";
    public static final String DEPTH_TO_COLUMN = "depthTo";
    public static final String SAMPLING_DATE_COLUMN = "samplingDate";

    public static final String GENESIS_COLUMN = "genesis";
    public static final String DESCRIPTION_KGA_COLUMN = "descriptionKga";
    public static final String GENESIS_DESCRIPTION_COLUMN = "genesisDescription";
    public static final String HATCHING_COLUMN = "hatching";
    public static final String CONSISTENCY_COLUMN = "consistency";
    public static final String IS_EDITABLE_FROM_EGE_LIST = "isEditableFromEgeList";

    public static final String ABSOLUTE_TOP_MARK_COLUMN = "absoluteTopMark";
    public static final String LAYER_POWER_COLUMN = "layerPower";
    public static final String LAYER_TOP_DEPTH_COLUMN = "layerTopDepth";
    public static final String FIRST_LAYER_TOP_COLUMN = "firstLayerTop";


    public static final String EGE_COLUMN = "ege";
    public static final String SHORT_NAME_COLUMN = "shortName";
    public static final String FIRST_LAYER_CONSISTENCY_COLUMN = "firstLayerConsistency";
    public static final String SECOND_LAYER_CONSISTENCY_COLUMN = "secondLayerConsistency";
    public static final String COLOR_COLUMN = "color";
    public static final String LAYER_BOTTOM_DEPTH_COLUMN = "layerBottomDepth";

    public static final String KD_COLUMN = "kd";
    public static final String IS_EXCLUDED = "isExcluded";

    public static final String NO_SAMPLES = "Нет образцов";
    public static final String YES = "Да";
    public static final String NO = "Нет";



}

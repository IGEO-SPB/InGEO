package org.geoproject.ingeo.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServiceConstants {
    public static final String ID_FIELD = "id";
    public static final String LABOR_NUMBER_FIELD = "laborNumber";
    public static final String SHEAR_POINT_NUMBER_FIELD = "shearPointNumber";
    public static final String CLT_NAME_FIELD = "cltName";

    public static final Boolean NOT_EDITABLE_FROM_EGE_LIST = false;

    public static final Boolean IS_SAND = true;
    public static final Boolean IS_NOT_SAND = false;

    public static final Integer ZERO_INDEX = 0;
    public static final Integer SINGLE_INDEX_POINT = 1;

    public static final Integer SINGLE_CODE_NUMBER = 1;

    public static final Boolean ENTITY_IS_NOT_ACTIVE = false;

    public static final Boolean ENTITY_IS_NOT_ARCHIVE = false;
    public static final Boolean ENTITY_IS_ARCHIVE = true;

    public static final Boolean WATER_SAMPLE_IS_NOT_BLOCKED = false;
    public static final Boolean WATER_SAMPLE_IS_BLOCKED = true;

    public static final String DATE_TIME_PATTERN = "dd/MM/yyyy";

    public static final Integer ZERO_LIST_SIZE = 0;

    public static final String SPACE_PATTERN = " ";
    public static final String COMMA_PATTERN = ", ";
    public static final String SOIL_SUBKIND_FIElD_PATTERN = "SS";
    public static final String SOIL_SUBKIND_ADJ_FIElD_PATTERN = "SSA";
    public static final String FX_ID_PREFIX_PATTERN = "#";
    public static final String VERTICAL_SPLITTER_PATTERN = " | ";

    public static final String WATER_FULL_PATTERN = "водонасыщенные с глубины %s м.";
    public static final String NOT_DEFINED_SOIL_SUBKIND_ADJ_PATTERN = "Не определено";
    public static final String SHORT_METER_PATTERN = " м.";

    public static final Long ZERO_ID = 0L;



    public static final String SCIENTIFIC_NOTATION_PATTERN = "-?\\d+(\\.\\d+)?[Ee]-?\\d+";

    public static final String SOIL_SUBKIND_ENTITY_NAME = "SoilSubkind";


    public static final String NO_PROJECTS_SERVICE_MESSAGE = "Проектов не существует";
    public static final String NO_SURVEY_POINTS_SERVICE_MESSAGE = "Точек исследования не существует";
    public static final String HAVE_TO_SAVE_CHOSEN_EGE_SERVICE_MESSAGE = "Сохраните выбранный ИГЭ";
    public static final String HAVE_TO_SAVE_CHOSEN_BOREHOLE_LAYER_SERVICE_MESSAGE = "Сохраните выбранный слой";
    public static final String ALL_SLOTS_IN_SOIL_SUBKIND_LIST_ARE_USED_SERVICE_MESSAGE = "Все слоты для типов группы заполнены. Удалите любое значение";
    public static final String ROW_IN_SOIL_SUBKIND_LIST_NOT_CHOSEN_SERVICE_MESSAGE = "Не выбрана строка в списке типов групп";

    public static final String CHOICE_BUTTON_TEXT = "Выбрать";
    public static final String ALERT_WINDOW_TITLE = "!!!";

    public static final Double COMBOBOX_COLUMN_PIXEL_GAP = 6D;
    public static final Double COMBOBOX_LAST_COLUMN_PIXEL_GAP = 22D;




}

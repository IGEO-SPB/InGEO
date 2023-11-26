package com.geoproject.igeo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewsEnum {

    BOREHOLE_LAYERS_DESCRIPTION_MAIN_VIEW("borehole-layers-description-main-view.fxml", "borehole layers description"),
    ALL_PROJECTS_VIEW("all-projects-view.fxml", "All projects"),
    FIELD_MODULE_MAIN_VIEW("field-module-main-view.fxml", "Field module"),
    LABOR_MODULE_MAIN_VIEW("labor-module-main-view.fxml", "Labor module"),
    CAMERAL_MODULE_MAIN_VIEW("cameral-module-main-view.fxml", "Cameral module"),
    EGE_LIST_VIEW("ege-list-view.fxml", "Ege list"),
    SOIL_KIND_CHOICE_VIEW("modalWindows/soil-kind-choice-view.fxml", "Soil kind choice"),
    LOGIN_VIEW("login-view.fxml", "Login view"),

    CONSTRUCTION_MESH_SUMMARY_VIEW("laborMethods/construction-mesh-summary-view.fxml", "construction mesh summary"),
    GRAN_COMPOSITION_RESULT_TABLE_VIEW("laborMethods/gran-composition-areometry-view.fxml", "gran composition areometry"),
    PHYSICAL_PROPERTIES_RESULT_TABLE_VIEW("laborMethods/physical-properties-summary-view.fxml", "physical properties summary"),

    WATER_CHEMISTRY_MAIN_VIEW("laborMethods/water_chemistry_main.fxml", "water chemistry main"),
    WATER_CHEMISTRY_DATA_VIEW("laborMethods/water_chemistry_data.fxml", "water chemistry data"),
    WATER_CHEMISTRY_RESULT_VIEW("laborMethods/water_chemistry_result.fxml", "water chemistry result"),
    WATER_CHEMISTRY_FINAL_RESULT_VIEW("laborMethods/water-chemistry-final-result-table.fxml", "water chemistry final result"),

    WATER_EXTRACT_CHOOSE_METHOD_VOLUME_VIEW("laborMethods/waterExtract/choose-water-extract-method-volume-view.fxml", "Выбор водной вытяжки"),
    WATER_EXTRACT_PARTIAL_DATA_VIEW("laborMethods/waterExtract/water-extract-partial-data-table-view.fxml", "Водная вытяжка. Ввод данных"),
    WATER_EXTRACT_FULL_DATA_VIEW("laborMethods/waterExtract/water-extract-full-data-table-view.fxml", "Полная водная вытяжка. Ввод данных"),
    WATER_EXTRACT_PARTIAL_RESULT_VIEW("laborMethods/waterExtract/water-extract-partial-result-table-view.fxml", "Химический состав водной вытяжки из грунтов"),
    WATER_EXTRACT_FULL_RESULT_VIEW("laborMethods/waterExtract/water-extract-full-result-table-view.fxml", "Химический состав полной водной вытяжки из грунтов"),

    SHEAR_DATA_VIEW("laborMethods/shear/shear-data-table-view.fxml", "Сдвиговые испытания. Ввод данных"),
    SHEAR_WATER_CONTENT_CUT_RING_VIEW("laborMethods/shear/shear-water-content-cut-ring-view.fxml", "Сдвиг - влажность и режущее кольцо"),

    WEIGHING_BOTTLES_VIEW("modalWindows/weighing-bottles-view.fxml", "weighing bottles view"),
    RINGS_VIEW("modalWindows/rings-view.fxml", "rings view"),
    POTS_VIEW("modalWindows/pots-view.fxml", "pots view"),

    CREATE_NEW_PROJECT_VIEW("modalWindows/create-project-view.fxml", "create project view"),
    EDIT_PROJECT_VIEW("modalWindows/edit-project-view.fxml", "edit project view"),

    DILUTION_FACTORS_VIEW("laborMethods/dilution-factors-view.fxml", "Коэффициенты разбавления"),
    DENSITY_WATER_CONTENT_METHOD_VIEW("laborMethods/density-water-content-method.fxml", "density water content method"),
    AREOMETRIC_METHOD_VIEW("laborMethods/areometric-method-view.fxml", "areometric method"),
    CONSTRUCTION_MESH_METHOD_VIEW("laborMethods/construction-mesh-method-view.fxml", "construction mesh method"),

    CONSTRUCTION_MESH_AREOMETRY_METHOD_VIEW("laborMethods/constructionMeshAreometry/method-view.fxml", "construction mesh method"),
    CONSTRUCTION_MESH_AREOMETRY_RESULT_VIEW("laborMethods/constructionMeshAreometry/summary-view.fxml", "construction mesh summary"),

    SOIL_CORROSION_INPUT("laborMethods/soilCorrosion/input.fxml", "soil corrosion input"),
    SOIL_CORROSION_RESULT("laborMethods/soilCorrosion/result.fxml", "soil corrosion result"),

    COMPRESSION_INPUT("laborMethods/compression/input.fxml", "compression input"),
    COMPRESSION_DEFORMATION("laborMethods/compression/deformation.fxml", "compression deformation"),
    COMPRESSION_POROSITY("laborMethods/compression/porosity.fxml", "compression porosity"),
    COMPRESSION_PATHS("laborMethods/compression/pathsModal.fxml", "modify paths for compression"),
    ;

    private final String path;
    private final String title;

    public String getPath() {
        return path;
    }
    public String getTitle() {
        return title;
    }
}
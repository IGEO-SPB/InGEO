package org.geoproject.ingeo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewsEnum {

    LOGIN_VIEW("login-view.fxml", "Login view"),

    BOREHOLE_LAYERS_DESCRIPTION_MAIN_VIEW("cameral/borehole-layers-description-main-view.fxml", "borehole layers description"),
    ALL_PROJECTS_VIEW("allProjects/all-projects-view.fxml", "All projects"),
    FIELD_MODULE_MAIN_VIEW("field/field-module-main-view.fxml", "Field module"),
    LABOR_MODULE_MAIN_VIEW("labor/labor-module-main-view.fxml", "Labor module"),
    CAMERAL_MODULE_MAIN_VIEW("cameral/cameral-module-main-view.fxml", "Cameral module"),
    EGE_LIST_VIEW("cameral/ege-list-view.fxml", "Ege list"),
    SOIL_KIND_CHOICE_VIEW("cameral/soil-kind-choice-view.fxml", "Soil kind choice"),

    WEIGHING_BOTTLES_VIEW("modalWindows/weighing-bottles-view.fxml", "weighing bottles view"),
    RINGS_VIEW("modalWindows/rings-view.fxml", "rings view"),
    POTS_VIEW("modalWindows/pots-view.fxml", "pots view"),

    CREATE_NEW_PROJECT_VIEW("allProjects/create-project-view.fxml", "create project view"),
    EDIT_PROJECT_VIEW("allProjects/edit-project-view.fxml", "edit project view"),

    DILUTION_FACTORS_VIEW("labor/waterChemistry/dilution-factors-view.fxml", "Коэффициенты разбавления"),
    WATER_CHEMISTRY_MAIN_VIEW("labor/waterChemistry/water_chemistry_main.fxml", "water chemistry main"),
    WATER_CHEMISTRY_DATA_VIEW("labor/waterChemistry/water_chemistry_data.fxml", "water chemistry data"),
    WATER_CHEMISTRY_RESULT_VIEW("labor/waterChemistry/water_chemistry_result.fxml", "water chemistry result"),
    WATER_CHEMISTRY_FINAL_RESULT_VIEW("labor/waterChemistry/water-chemistry-final-result-table.fxml", "water chemistry final result"),

    AREOMETRIC_METHOD_VIEW("labor/areometry/areometric-method-view.fxml", "areometric method"),

    CONSTRUCTION_MESH_METHOD_VIEW("labor/constructionMesh/construction-mesh-method-view.fxml", "construction mesh method"),
    CONSTRUCTION_MESH_SUMMARY_VIEW("labor/constructionMesh/construction-mesh-summary-view.fxml", "construction mesh summary"),

    CONSTRUCTION_MESH_AREOMETRY_METHOD_VIEW("labor/constructionMeshAreometry/method-view.fxml", "construction mesh method"),
    CONSTRUCTION_MESH_AREOMETRY_RESULT_VIEW("labor/constructionMeshAreometry/summary-view.fxml", "construction mesh summary"),

    DENSITY_WATER_CONTENT_METHOD_VIEW("labor/densityWaterContent/density-water-content-method.fxml", "density water content method"),
    PHYSICAL_PROPERTIES_RESULT_TABLE_VIEW("labor/densityWaterContent/physical-properties-summary-view.fxml", "physical properties summary"),

    GRAN_COMPOSITION_RESULT_TABLE_VIEW("labor/granCompositionAreometry/gran-composition-areometry-view.fxml", "gran composition areometry"),

    SHEAR_DATA_VIEW("labor/shear/shear-data-table-view.fxml", "Сдвиговые испытания. Ввод данных"),
    SHEAR_WATER_CONTENT_CUT_RING_VIEW("labor/shear/shear-water-content-cut-ring-view.fxml", "Сдвиг - влажность и режущее кольцо"),

    SOIL_CORROSION_INPUT("labor/soilCorrosion/input.fxml", "soil corrosion input"),
    SOIL_CORROSION_RESULT("labor/soilCorrosion/result.fxml", "soil corrosion result"),

    WATER_EXTRACT_CHOOSE_METHOD_VOLUME_VIEW("labor/waterExtract/choose-water-extract-method-volume-view.fxml", "Выбор водной вытяжки"),
    WATER_EXTRACT_PARTIAL_DATA_VIEW("labor/waterExtract/water-extract-partial-data-table-view.fxml", "Водная вытяжка. Ввод данных"),
    WATER_EXTRACT_FULL_DATA_VIEW("labor/waterExtract/water-extract-full-data-table-view.fxml", "Полная водная вытяжка. Ввод данных"),
    WATER_EXTRACT_PARTIAL_RESULT_VIEW("labor/waterExtract/water-extract-partial-result-table-view.fxml", "Химический состав водной вытяжки из грунтов"),
    WATER_EXTRACT_FULL_RESULT_VIEW("labor/waterExtract/water-extract-full-result-table-view.fxml", "Химический состав полной водной вытяжки из грунтов"),

    COMPRESSION_INPUT("labor/compression/input.fxml", "compression input"),
    COMPRESSION_DEFORMATION("labor/compression/deformation.fxml", "compression deformation"),
    COMPRESSION_POROSITY("labor/compression/porosity.fxml", "compression porosity"),
    COMPRESSION_PATHS("labor/compression/pathsModal.fxml", "modify paths for compression"),
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
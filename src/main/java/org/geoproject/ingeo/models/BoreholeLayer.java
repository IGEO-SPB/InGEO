package com.geoproject.igeo.models;

import jakarta.persistence.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Описание слоев в колонке
 */
@Entity
@Table(name = "borehole_layers_description_main")
@Getter
@Setter
@RequiredArgsConstructor
public class BoreholeLayer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //№ по порядку в рамках данной выработки
    @Column(name = "number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "point_id", referencedColumnName = "id")
    private SurveyPoint surveyPoint;

    @ManyToOne
    @JoinColumn(name = "ege_id", referencedColumnName = "id")
    private Ege ege;

    //глубина подошвы
    @Column(name = "layer_bottom_depth")
    private Float layerBottomDepth;

    //Абсолютная отметка кровли слоя (верх слоя). Для первого слоя - уровень поверхности земли (относительно уровня моря)
    @Column(name = "absolute_top_mark")
    private Float absoluteTopMark;

    //todo переделать на layerThickness
    //мощность слоя - разница между
    @Column(name = "layer_power")
    private Float layerPower;

    //глубина кровли слоя - глубина слоя от поверхности земли
    @Column(name = "layer_top_depth")
    private Float layerTopDepth;

    //Верх 1сл = Глубина кровли слоя
    @Column(name = "first_layer_top")
    private Float firstLayerTop;

    @Column(name = "first_layer_bottom")
    private Float firstLayerBottom;

    @Column(name = "first_layer_type")
    private String firstLayerType;

    @Column(name = "second_layer_bottom")
    private Float secondLayerBottom;

    @Column(name = "second_layer_type")
    private String secondLayerType;

    @Column(name = "hatching_name_credo_autocad")
    private String hatchingNameCredoAutocad;

    @Column(name = "color")
    private String color;

    @Column(name = "water")
    private Float water;

    @Column(name = "is_editable_from_ege_list")
    private Boolean isEditableFromEgeListBasic;

    @Transient
    private BooleanProperty isEditableFromEgeList;

    //Описание для credo и формуляра. Вводится вручную. В поле может копировать информация из descriptionKga
    @Column(name = "description_credo_formular")
    private String descriptionCredoFormular;

    //Описание по классификатору КГА. Вводится набором грунтов из классификатора
    @Column(name = "description_kga")
    private String descriptionKga;

    @PostLoad
    void fillTransients() {
        if (isEditableFromEgeListBasic != null) {
            this.isEditableFromEgeList = new SimpleBooleanProperty(isEditableFromEgeListBasic);
        } else {
            this.isEditableFromEgeList = new SimpleBooleanProperty();
        }
    }

    @Override
    public String toString() {
        return "BoreholeLayer{" +
                "id=" + id +
                ", number=" + number +
                ", surveyPoint=" + surveyPoint.getId() +
                ", isEditableFromEgeList=" + isEditableFromEgeList +
                '}';
    }
}

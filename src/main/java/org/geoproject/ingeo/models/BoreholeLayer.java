package org.geoproject.ingeo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.geoproject.ingeo.models.classificators.Consistency;
import org.geoproject.ingeo.models.classificators.Hatching;
import org.geoproject.ingeo.models.classificators.kga.Color;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.geoproject.ingeo.models.classificators.kga.SoilKind;

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

    //TODO: переделать название поля в таблице
    /**
     * Консистенция, выбирается из классификатора
     * ВА: TCNS (консистенция)
     */
    @ManyToOne
    @JoinColumn(name = "first_layer_consistency_id")
    private Consistency firstLayerConsistency;

    @Column(name = "second_layer_bottom")
    private Float secondLayerBottom;

    //TODO: переделать название поля в таблице
    /**
     * Консистенция, выбирается из классификатора
     * ВА: TCNS (консистенция)
     */
    @ManyToOne
    @JoinColumn(name = "second_layer_consistency_id")
    private Consistency secondLayerConsistency;

    //TODO: переделать название поля в таблице на hatching_id
    /**
     * Штриховка, выбирается из классификатора - класс Hatching
     * Ранее название поля - hatchingNameCredoAutocad
     * ВА: ACAD, для СREDO
     */
    @ManyToOne
    @JoinColumn(name = "hatching_id")
    private Hatching hatching;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "water")
    private Float waterDepth;

    @Column(name = "is_editable_from_ege_list")
    private Boolean isEditableFromEgeList;

//    @Transient
//    private BooleanProperty isEditableFromEgeList;

    //Описание для credo и формуляра. Вводится вручную. В поле может копировать информация из descriptionKga
    @Column(name = "description_credo_formular")
    private String descriptionCredoFormular;

    /**
     * Описание по классификатору КГА. Вводится набором грунтов из классификатора
     */
    @Column(name = "description_kga")
    private String descriptionKga;

    /**
     * COLG, цвет в CREDO (таблица ВА)
     * Из таблицы classif_hatching (класс Hatching)
     */
    @Column(name = "credo_color")
    private Integer credoColor;

    /**
     * PATT, штриховка в CREDO (таблица ВА)
     * Из таблицы classif_hatching (класс Hatching)
     */
    @Column(name = "hatching_credo")
    private String hatchingCredo;

    //для мягкого удаления
    @Column
    private Boolean isArchive;

//    @PostLoad
//    void fillTransients() {
//        if (isEditableFromEgeListBasic != null) {
//            this.isEditableFromEgeList = new SimpleBooleanProperty(isEditableFromEgeListBasic);
//        } else {
//            this.isEditableFromEgeList = new SimpleBooleanProperty();
//        }
//    }





    @Column(name = "SSA1")
    private Long SSA1;

    @Column(name = "SSA2")
    private Long SSA2;

    @Column(name = "SSA3")
    private Long SSA3;

    @Column(name = "SSA4")
    private Long SSA4;

    @Column(name = "SSA5")
    private Long SSA5;

    @Column(name = "SSA6")
    private Long SSA6;

    @Column(name = "SSA7")
    private Long SSA7;

    @Column(name = "SSA8")
    private Long SSA8;

    @Column(name = "SSA9")
    private Long SSA9;

    @Column(name = "SSA10")
    private Long SSA10;

    @Column(name = "SSA11")
    private Long SSA11;

    @Column(name = "SSA12")
    private Long SSA12;


    /**
     * ВА: SS1, для Формуляр
     */
    @Column(name = "SS1")
    private Long SS1;

    @Column(name = "SS2")
    private Long SS2;

    @Column(name = "SS3")
    private Long SS3;

    @Column(name = "SS4")
    private Long SS4;

    @Column(name = "SS5")
    private Long SS5;

    @Column(name = "SS6")
    private Long SS6;

    @Column(name = "SS7")
    private Long SS7;

    @Column(name = "SS8")
    private Long SS8;

    @Column(name = "SS9")
    private Long SS9;

    @Column(name = "SS10")
    private Long SS10;

    @Column(name = "SS11")
    private Long SS11;

    @Column(name = "SS12")
    private Long SS12;

    @Column(name = "SS13")
    private Long SS13;

    @Column(name = "SS14")
    private Long SS14;

    @Column(name = "SS15")
    private Long SS15;

    @Column(name = "SS16")
    private Long SS16;

    @Column(name = "SS17")
    private Long SS17;

    @Column(name = "SS18")
    private Long SS18;

    @Column(name = "SS19")
    private Long SS19;

    @Column(name = "SS20")
    private Long SS20;


    @ManyToOne
    @JoinColumn(name = "soil_class_id")
    private SoilClass soilClass;

    @ManyToOne
    @JoinColumn(name = "soil_kind_id")
    private SoilKind soilKind;

    @ManyToOne
    @JoinColumn(name = "soil_class_kind_group")
    private SoilClassKindGroup soilClassKindGroup;



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

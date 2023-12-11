package org.geoproject.ingeo.models;

import org.geoproject.ingeo.models.classificators.Consistency;
import org.geoproject.ingeo.models.classificators.Genesis;
import org.geoproject.ingeo.models.classificators.Hatching;
import org.geoproject.ingeo.models.classificators.kga.Color;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.geoproject.ingeo.models.classificators.kga.SoilKind;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ege")
@RequiredArgsConstructor
@Getter
@Setter
public class Ege {
//    ВА: для формуляр (назначение пока не ясно, еще куча полей с SS... и SSA...):

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    //Номер ИГЭ
    @Column(name = "number")
    private String number;

    //Следующие два поля просто цифры по порядку, не понятно, нужно ли это вообще:
    @Column(name = "code")
    private String code;

    @Column(name = "code_number")
    private int codeNumber;

    //описание почвы, вводится вручную
    //todo переназвать
    @Column(name = "short_name")
    private String shortName;

    //Связь с сущностью Genesis по ID, при этом в представлении надо выводить в двух колонках код генезиса и описание
    @ManyToOne
    @JoinColumn(name = "genesis_id", referencedColumnName = "id")
    private Genesis genesis;

    //Описание для credo и формуляра. Вводится вручную. В поле может копировать информация из descriptionKga
    @Column(name = "description_credo_formular")
    private String descriptionCredoFormular;

    //Описание по классификатору КГА. Вводится набором грунтов из классификатора
    @Column(name = "description_kga")
    private String descriptionKga;

    //Описание для камеральной задачи. Как правило, копируется из descriptionKga. Может быть ручной ввод.
    //Также в это поле должно попадать описание генезиса
    @Column(name = "description_for_organisation")
    private String descriptionForOrganisation;

    /**
     * Штриховка, выбирается из классификатора - класс Hatching
     * Ранее название поля - hatchingNameCredoAutocad
     * ВА: ACAD, для СREDO
     */
    @ManyToOne
    @JoinColumn(name = "hatching_id")
    private Hatching hatching;

    /**
     * Консистенция, выбирается из классификатора
     * ВА: TCNS (консистенция)
     */
    @ManyToOne
    @JoinColumn(name = "consistency_id")
    private Consistency consistency;





    // убрал поле - это "вид грунта - быстро". Перенес выпадающий список в окно создания описания КГА
    //ВА: SSA, enum - почва, пески, глинистые. От выбора зависят данные в меню "Вид грунта" и "Разновидность"
    //это то же, что класс SoilKind (сетится по кнопке в таблице на экране заполнения описания КГА)
//    @Column(name = "soil_kind_enum")
//    private String soilKindEnum;


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


//    @Column(name = "SS1")
//    private Integer SS1;

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

    @ManyToOne
    @JoinColumn(name = "color")
    private Color color;

    //  След.два поля назначение пока не ясно, связаны с формуляром:
    //ВА: GB_NMB, порядок следования слоев -для формуляра
    @Column(name = "GB_NMB")
    private Integer GB_NMB;

    //ВА: F_Opis, наименование грунта по классиф. Формуляр
    @Column(name = "F_Opis")
    private String F_Opis;

    //BA: с гл.хх м -насыщ водой
    @Column(name = "f_G")
    private Float waterDepth;

    @OneToMany(mappedBy = "ege")
    private List<BoreholeLayer> boreholeLayerList;

}

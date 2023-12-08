package org.geoproject.ingeo.models.classificators;

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
import org.geoproject.ingeo.models.Ege;

import java.util.List;

/**
 * Таблица со штриховками
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "classif_hatching")
public class Hatching {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * NAME_KRAT, наименование грунта краткое (таблица ВА)
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * GRN_KOD (таблица ВА)
     */
    @Column(name = "grn_code_string")
    private String grnCodeString;

    /**
     * GRN_KOD_NUM, преобр в число (таблица ВА)
     */
    @Column(name = "grn_code_int")
    private Integer grnCodeInt;

    /**
     * SHTRICH_NAME, название файла штриховки (таблица ВА)
     */
    @Column(name = "hatching_name")
    private String hatchingName;

    /**
     * ACAD, название штриховки в СREDO и в AutoCAD (таблица ВА)
     */
    @Column(name = "hatching_name_credo_autocad")
    private String hatchingNameCredoAutocad;

    /**
     * ONLY_CREDO, штриховка есть только в CREDO и в AutoCAD (таблица ВА)
     */
    @Column(name = "is_credo_autocad_hatching_only")
    private Boolean isCredoAutocadHatchingOnly;

    /**
     * COLG, цвет в CREDO (таблица ВА)
     */
    @Column(name = "credo_color")
    private Integer credoColor;

    /**
     * PATT, штриховка в CREDO (таблица ВА)
     */
    @Column(name = "hatching_credo")
    private String hatchingCredo;

    /**
     * IS_POVTOR, штриховка CREDO повторяется в этом списке- нужно для правильного запроса Q_SKV_GRN (таблица ВА)
     */
    @Column(name = "is_repeated")
    private Boolean isRepeated;

    /**
     * STR_Id, № штриховки-строка в этой тбл (таблица ВА)
     */
    @Column(name = "string_id")
    private Integer stringId;

    /**
     * R_SHTRIH, код штриховки в ROBUR (таблица ВА)
     */
    @Column(name = "hatching_code_robur")
    private Integer hatchingCodeRobur;

    /**
     * R_COLOR, цвет в ROBUR (таблица ВА)
     */
    @Column(name = "hatching_color_robur")
    private Integer hatchingColorRobur;

    @OneToMany
    @JoinColumn(name = "hatching_id")
    private List<Ege> egeList;
}

package org.geoproject.ingeo.models.classificators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "classif_soil_type")
public class SoilType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "grn_code_string")
    private String grnCodeString;

    @Column(name = "grn_code_int")
    private Integer grnCodeInt;

    @Column(name = "hatching_name")
    private String hatchingName;

    @Column(name = "hatching_name_credo_autocad")
    private String hatchingNameCredoAutocad;

    @Column(name = "is_credo_autocad_hatching_only")
    private Boolean isCredoAutocadHatchingOnly;

    @Column(name = "credo_color")
    private Integer credoColor;

    @Column(name = "hatching_credo")
    private String hatchingCredo;

    @Column(name = "is_repeated")
    private Boolean isRepeated;

    @Column(name = "string_id")
    private Integer stringId;

    @Column(name = "hatching_code_robur")
    private Integer hatchingCodeRobur;

    @Column(name = "hatching_color_robur")
    private Integer hatchingColorRobur;
}

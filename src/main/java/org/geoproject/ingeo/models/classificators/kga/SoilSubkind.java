package org.geoproject.ingeo.models.classificators.kga;

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


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "CL_SOIL_SUBKIND")
public class SoilSubkind {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CLT_ID")
    private Long cltId;

    @ManyToOne
    @JoinColumn(name = "SKGT_ID")
    private SoilKindGroupType soilKindGroupType;

    @Column(name = "SS_NAME")
    private String ssName;

    @Column(name = "SS_ENABLE")
    private Boolean ssEnable;

    @Column(name = "CLT_BORE_ID")
    private Long cltBoreId;

    @Column(name = "SS_DESCR")
    private String ssDescr;

    @Column(name = "SS_OTM")
    private Boolean ssOtm;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATOR")
    private String creator;

    @Column(name = "DATE_CR")
    private String dateCr;

    @Column(name = "ARCHIVER")
    private String archiver;

    @Column(name = "DATE_ARCH")
    private String dateArch;

    @Column(name = "SS_FC_R")
    private Integer ssFcR;

    @Column(name = "SS_FC_G")
    private Integer ssFcG;

    @Column(name = "SS_FC_B")
    private Integer ssFcB;

//    штриховка тисиз  из тбл Global_SGR:
    @Column(name = "ACAD")
    private String acad;
}

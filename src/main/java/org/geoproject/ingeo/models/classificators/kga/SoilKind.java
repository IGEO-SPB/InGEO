package org.geoproject.ingeo.models.classificators.kga;

import org.geoproject.ingeo.models.Ege;
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

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "CL_SOIL_KIND")
public class SoilKind {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SC_ID")
    private SoilClass soilClass;

    @Column(name = "CLT_ID")
    private String cltId;

    @Column(name = "SK_GROUP")
    private String skGroup;

    @Column(name = "SK_SUBGROUP1")
    private String skSubgroupOne;

    @Column(name = "SK_SUBGROUP2")
    private String skSubgroupTwo;

    @Column(name = "SK_TYPE1")
    private String skTypeOne;

    @Column(name = "SK_TYPE2")
    private String skTypeTwo;

    @Column(name = "SK_KIND")
    private String skKind;

    @Column(name = "SK_ENABLE")
    private Integer skEnable;

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

    @Column(name = "SK_BASE_ENABLE")
    private Integer skBaseEnable;

    @Column(name = "SK_DESCR")
    private String skDescr;

    @OneToMany
    @JoinColumn(name = "SK_ID")
    private List<SoilKindGroupType> soilKindGroupTypeList;

    @OneToMany
    @JoinColumn(name = "soil_class_id")
    private List<Ege> egeList;

}

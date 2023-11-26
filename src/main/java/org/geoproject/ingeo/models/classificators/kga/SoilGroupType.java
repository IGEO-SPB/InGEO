package com.geoproject.igeo.models.classificators.kga;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "CL_SOIL_GROUP_TYPE")
public class SoilGroupType {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GT_NAME")
    private String gtName;

    @Column(name = "No_pp")
    private Long noPp;

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

    @OneToMany
    @JoinColumn(name = "GT_ID")
    private List<SoilKindGroupType> soilKindGroupTypeList;

}

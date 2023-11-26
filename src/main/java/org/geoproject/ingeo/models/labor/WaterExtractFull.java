package org.geoproject.ingeo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "water_extract_full")
public class WaterExtractFull {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @Column(name = "depth_from")
    private Float depthFrom;

    @Column(name = "depth_to")
    private Float depthTo;

    @Column(name = "sampling_date")
    private LocalDate samplingDate;

    @Column(name = "is_archive")
    private Boolean isArchive;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "HCO3_1")
    private Float HCO3_1;

    @Column(name = "HCO3_2")
    private Float HCO3_2;

    @Column(name = "CO3_1")
    private Float CO3_1;

    @Column(name = "CO3_2")
    private Float CO3_2;

    @Column(name = "Cl_1")
    private Float Cl_1;

    @Column(name = "Cl_2")
    private Float Cl_2;

    @Column(name = "SO4_1")
    private Float SO4_1;

    @Column(name = "SO4_2")
    private Float SO4_2;

    @Column(name = "Ca_1")
    private Float Ca_1;

    @Column(name = "Ca_2")
    private Float Ca_2;

    @Column(name = "Mg_1")
    private Float Mg_1;

    @Column(name = "Mg_2")
    private Float Mg_2;

    @Column(name = "Dry_1")
    private Float Dry_1;

    @Column(name = "Dry_2")
    private Float Dry_2;

    @Column(name = "O2_1")
    private Float O2_1;

    @Column(name = "O2_2")
    private Float O2_2;

    @Column(name = "pH")
    private Float pH;

    @Column(name = "cl_coef")
    private Float clCoef;
}


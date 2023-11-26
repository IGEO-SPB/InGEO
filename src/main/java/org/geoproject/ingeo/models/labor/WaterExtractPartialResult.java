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
@Table(name = "water_extract_partial_result")
public class WaterExtractPartialResult {

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

    @Column(name = "pH")
    private Float pH;

    @Column(name = "Cl")
    private Float Cl;

    @Column(name = "NO3_txt")
    private Float NO3Txt;

    @Column(name = "Fe_txt")
    private Float FeTxt;

    @Column(name = "gum")
    private Float gum;

    @Column(name = "SO4")
    private Float SO4;

    @Column(name = "Cl_v")
    private Float Cl_v;

    @Column(name = "SO4_v")
    private Float SO4_v;

    @Column(name = "ClSO4")
    private Float ClSO4;
}

package org.geoproject.ingeo.models.labor;

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
import org.geoproject.ingeo.models.Sample;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "water_extract_full_result")
public class WaterExtractFullResult {

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

    @Column(name = "HCO3_v")
    private Float HCO3_v;

    @Column(name = "HCO3_eq")
    private Float HCO3_eq;

    @Column(name = "HCO3_proc")
    private Float HCO3_proc;

    @Column(name = "CO3_v")
    private Float CO3_v;

    @Column(name = "CO3_eq")
    private Float CO3_eq;

    @Column(name = "CO3_proc")
    private Float CO3_proc;

    @Column(name = "Cl_v")
    private Float Cl_v;

    @Column(name = "Cl_eq")
    private Float Cl_eq;

    @Column(name = "Cl_proc")
    private Float Cl_proc;

    @Column(name = "SO4_v")
    private Float SO4_v;

    @Column(name = "SO4_eq")
    private Float SO4_eq;

    @Column(name = "SO4_proc")
    private Float SO4_proc;

    @Column(name = "an_sum_eq")
    private Float anSumEq;

    @Column(name = "an_sum_proc")
    private Float anSumProc;

    @Column(name = "Ca_v")
    private Float Ca_v;

    @Column(name = "Ca_eq")
    private Float Ca_eq;

    @Column(name = "Ca_proc")
    private Float Ca_proc;

    @Column(name = "Mg_v")
    private Float Mg_v;

    @Column(name = "Mg_eq")
    private Float Mg_eq;

    @Column(name = "Mg_proc")
    private Float Mg_proc;

    @Column(name = "Na_v")
    private Float Na_v;

    @Column(name = "Na_eq")
    private Float Na_eq;

    @Column(name = "Na_proc")
    private Float Na_proc;

    @Column(name = "kat_sum_eq")
    private Float katSumEq;

    @Column(name = "kat_sum_proc")
    private Float katSumProc;

    @Column(name = "ion_sum")
    private Float ionSum;

    @Column(name = "Dry")
    private Float Dry;

    @Column(name = "O2")
    private Float O2;

    @Column(name = "pH")
    private Float pH;

    @Column(name = "gum")
    private Float gum;
}

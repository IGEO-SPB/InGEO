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
@Table(name = "water_extract_partial")
public class WaterExtractPartial {

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

    @Column(name = "Cl_1")
    private Float Cl_1;

    @Column(name = "Cl_2")
    private Float Cl_2;

    @Column(name = "NO3")
    private Float NO3;

    @Column(name = "Fe")
    private Float Fe;

    @Column(name = "gum_1")
    private Float gum_1;

    @Column(name = "gum_2")
    private Float gum_2;

    @Column(name = "SO4_1")
    private Float SO4_1;

    @Column(name = "SO4_2")
    private Float SO4_2;

    @Column(name = "cl_coef")
    private Float clCoef;
}

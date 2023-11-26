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

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "soil_corrosion_result")
public class SoilCorrosionResult {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @Column(name = "lab_number")
    private String labNumber;

    @Column(name = "uesg")
    private Float uesg;

    @Column(name = "corr_aggr_degree_uesg")
    private String corrAggrDegreeUesg;

    @Column(name = "pkt")
    private Float pkt;

    @Column(name = "corr_aggr_degree_pkt")
    private String corrAggrDegreePkt;
}

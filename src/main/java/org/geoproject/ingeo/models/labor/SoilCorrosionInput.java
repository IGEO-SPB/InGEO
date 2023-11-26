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

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "soil_corrosion_input")
public class SoilCorrosionInput {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @Column(name = "lab_number")
    private String labNumber;

    @Column(name = "current")
    private Float current;

    @Column(name = "voltage")
    private Float voltage;

    @Column(name = "resistance")
    private Float resistance;

    @Column(name = "cathodeCurrent")
    private Float cathodeCurrent;
}

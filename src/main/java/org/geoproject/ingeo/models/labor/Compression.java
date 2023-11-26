package org.geoproject.ingeo.models.labor;

import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.classificators.Ring;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "compression")
public class Compression {

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class CompressionItem {
        private Double pressure;
        private Double discharge;
        private Double deformation;
        private Double porosity;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ring_id", referencedColumnName = "id")
    private Ring ring;

    @Column(name = "porosity_coefficient")
    private Double porosityCoefficient;

    @Column(name = "lab_number")
    private String labNumber;

    @Embedded
    private CompressionItem item_00000;
    @Embedded
    private CompressionItem item_00125;
    @Embedded
    private CompressionItem item_00250;
    @Embedded
    private CompressionItem item_00500;
    @Embedded
    private CompressionItem item_00750;
    @Embedded
    private CompressionItem item_01000;
    @Embedded
    private CompressionItem item_02000;
    @Embedded
    private CompressionItem item_03000;
    @Embedded
    private CompressionItem item_04000;
    @Embedded
    private CompressionItem item_05000;
    @Embedded
    private CompressionItem item_06000;
    @Embedded
    private CompressionItem item_07000;
    @Embedded
    private CompressionItem item_08000;
    @Embedded
    private CompressionItem item_09000;
    @Embedded
    private CompressionItem item_10000;

}
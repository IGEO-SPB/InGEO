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

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "boychenko_cone")
public class BoychenkoCone {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @Column(name = "undisturbed_str_immersion_depth_first_measur")
    private Float undisturbedStrImmersionDepthFirstMeasur;

    @Column(name = "undisturbed_str_immersion_depth_second_measur")
    private Float undisturbedStrImmersionDepthSecondMeasur;

    @Column(name = "undisturbed_str_immersion_depth_third_measur")
    private Float undisturbedStrImmersionDepthThirdMeasur;

    @Column(name = "broken_str_immersion_depth_first_measur")
    private Float brokenStrImmersionDepthFirstMeasur;

    @Column(name = "broken_str_immersion_depth_second_measur")
    private Float brokenStrImmersionDepthSecondMeasur;

    @Column(name = "broken_str_immersion_depth_third_measur")
    private Float brokenStrImmersionDepthThirdMeasur;

    @Column(name = "undisturbed_str_immersion_depth_average")
    private Float undisturbedStrImmersionDepthAverage;

    @Column(name = "broken_str_immersion_depth_average")
    private Float brokenStrImmersionDepthAverage;
}

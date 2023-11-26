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

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "density")
public class Density {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @Column(name = "pycnometer_weight_with_dry_soil_first_measurement")
    private Float pycnometerWeightWithDrySoilFirstMeasurement;

    @Column(name = "empty_pycnometer_weight_first_measurement")
    private Float emptyPycnometerWeightFirstMeasurement;

    @Column(name = "pycnometer_weight_with_water_first_measurement")
    private Float pycnometerWeightWithWaterFirstMeasurement;

    @Column(name = "pycnometer_weight_with_soil_and_water_first_measurement")
    private Float pycnometerWeightWithSoilAndWaterFirstMeasurement;

    @Column(name = "dry_soil_weight_first_measurement")
    private Float drySoilWeightFirstMeasurement;

    @Column(name = "soil_volume_first_measurement")
    private Float soilVolumeFirstMeasurement;

    @Column(name = "soil_density_first_measurement")
    private Float soilDensityFirstMeasurement;

    @Column(name = "pycnometer_weight_with_dry_soil_second_measurement")
    private Float pycnometerWeightWithDrySoilSecondMeasurement;

    @Column(name = "empty_pycnometer_weight_second_measurement")
    private Float emptyPycnometerWeightSecondMeasurement;

    @Column(name = "pycnometer_weight_with_water_second_measurement")
    private Float pycnometerWeightWithWaterSecondMeasurement;

    @Column(name = "pycnometer_weight_with_soil_and_water_second_measurement")
    private Float pycnometerWeightWithSoilAndWaterSecondMeasurement;

    @Column(name = "dry_soil_weight_second_measurement")
    private Float drySoilWeightSecondMeasurement;

    @Column(name = "soil_volume_second_measurement")
    private Float soilVolumeSecondMeasurement;

    @Column(name = "soil_density_second_measurement")
    private Float soilDensitySecondMeasurement;

    //плотность частиц грунта
    @Column(name = "average_density")
    private Float averageDensity;
}

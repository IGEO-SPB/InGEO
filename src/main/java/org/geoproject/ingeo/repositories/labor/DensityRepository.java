package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.Density;
import org.geoproject.ingeo.models.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DensityRepository extends JpaRepository<Density, Long> {
    Optional<Density> findBySample(Sample sample);
}
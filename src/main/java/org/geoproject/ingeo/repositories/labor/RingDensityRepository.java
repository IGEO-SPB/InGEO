package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.RingDensity;
import org.geoproject.ingeo.models.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RingDensityRepository extends JpaRepository<RingDensity, Long> {
    Optional<RingDensity> findBySample(Sample sample);
}

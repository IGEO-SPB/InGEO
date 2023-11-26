package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.Areometry;
import org.geoproject.ingeo.models.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreometryRepository extends JpaRepository<Areometry, Long> {
    Optional<Areometry> findBySample(Sample sample);
}

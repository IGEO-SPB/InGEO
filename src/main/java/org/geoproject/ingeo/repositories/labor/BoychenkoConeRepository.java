package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.BoychenkoCone;
import org.geoproject.ingeo.models.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoychenkoConeRepository extends JpaRepository<BoychenkoCone, Long> {
    Optional<BoychenkoCone> findBySample(Sample sample);
}

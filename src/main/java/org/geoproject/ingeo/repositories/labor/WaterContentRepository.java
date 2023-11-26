package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.WaterContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaterContentRepository extends JpaRepository<WaterContent, Long> {

    Optional<WaterContent> findBySample(Sample sample);
}

package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.SoilCorrosionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SoilCorrosionResultRepository extends JpaRepository<SoilCorrosionResult, Long> {
    List<SoilCorrosionResult> findAllBySample(Sample sp);
    Optional<SoilCorrosionResult> findByLabNumber(String labNumber);
}

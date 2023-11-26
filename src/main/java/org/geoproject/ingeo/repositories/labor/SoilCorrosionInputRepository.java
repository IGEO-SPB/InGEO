package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.SoilCorrosionInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoilCorrosionInputRepository extends JpaRepository<SoilCorrosionInput, Long> {
    List<SoilCorrosionInput> findAllBySample(Sample sp);

    List<SoilCorrosionInput> findAllByLabNumber(String labNumber);
}

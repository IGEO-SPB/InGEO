package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.dto.methodDtos.OrganicMatterDTO;
import org.geoproject.ingeo.models.labor.OrganicMatter;
import org.geoproject.ingeo.models.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganicMatterRepository extends JpaRepository<OrganicMatter, OrganicMatterDTO> {
    Optional<OrganicMatter> findBySample(Sample sample);
}

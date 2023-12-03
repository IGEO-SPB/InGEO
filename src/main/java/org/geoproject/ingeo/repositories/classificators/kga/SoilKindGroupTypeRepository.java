package org.geoproject.ingeo.repositories.classificators.kga;

import org.geoproject.ingeo.models.classificators.kga.SoilKind;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoilKindGroupTypeRepository extends JpaRepository<SoilKindGroupType, Long> {
    List<SoilKindGroupType> findBySoilKind(SoilKind soilKind);
}

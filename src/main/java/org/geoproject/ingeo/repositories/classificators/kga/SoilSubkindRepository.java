package org.geoproject.ingeo.repositories.classificators.kga;

import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoilSubkindRepository extends JpaRepository<SoilSubkind, Long> {
    List<SoilSubkind> findAllBySoilKindGroupType(SoilKindGroupType soilKindGroupType);
}

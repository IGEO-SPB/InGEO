package org.geoproject.ingeo.repositories.classificators.kga;

import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoilClassKindGroupRepository extends JpaRepository<SoilClassKindGroup, Long> {

    List<SoilClassKindGroup> findBySoilClass(SoilClass soilClass, Sort sort);
}

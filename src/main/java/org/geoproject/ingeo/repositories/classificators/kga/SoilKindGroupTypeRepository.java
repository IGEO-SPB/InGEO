package com.geoproject.igeo.repositories.classificators.kga;

import com.geoproject.igeo.models.classificators.kga.SoilKindGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilKindGroupTypeRepository extends JpaRepository<SoilKindGroupType, Long> {
}

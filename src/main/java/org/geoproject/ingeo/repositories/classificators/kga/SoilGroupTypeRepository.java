package com.geoproject.igeo.repositories.classificators.kga;

import com.geoproject.igeo.models.classificators.kga.SoilGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilGroupTypeRepository extends JpaRepository<SoilGroupType, Long> {
}

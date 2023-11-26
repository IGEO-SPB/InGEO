package com.geoproject.igeo.repositories.classificators.kga;

import com.geoproject.igeo.models.classificators.kga.SoilSubkindAdj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilSubkindAdjRepository extends JpaRepository<SoilSubkindAdj, Long> {
}

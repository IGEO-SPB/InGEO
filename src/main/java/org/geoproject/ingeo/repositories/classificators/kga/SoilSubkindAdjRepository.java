package org.geoproject.ingeo.repositories.classificators.kga;

import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilSubkindAdjRepository extends JpaRepository<SoilSubkindAdj, Long> {
}

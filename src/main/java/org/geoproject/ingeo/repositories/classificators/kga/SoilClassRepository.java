package org.geoproject.ingeo.repositories.classificators.kga;

import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilClassRepository extends JpaRepository<SoilClass, Long> {
}

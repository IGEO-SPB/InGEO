package org.geoproject.ingeo.repositories.classificators;

import org.geoproject.ingeo.models.classificators.WaterGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterGroupRepository extends JpaRepository<WaterGroup, Long> {
}

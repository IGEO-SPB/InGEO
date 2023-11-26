package com.geoproject.igeo.repositories.classificators;

import com.geoproject.igeo.models.classificators.WaterGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterGroupRepository extends JpaRepository<WaterGroup, Long> {
}

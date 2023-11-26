package com.geoproject.igeo.repositories.classificators;

import com.geoproject.igeo.models.classificators.ConstructionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConstructionTypeRepository extends JpaRepository<ConstructionType, Long> {

    Optional<ConstructionType> findByType(String type);
}

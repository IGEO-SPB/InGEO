package org.geoproject.ingeo.repositories.classificators;

import org.geoproject.ingeo.models.classificators.ConstructionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConstructionTypeRepository extends JpaRepository<ConstructionType, Long> {

    Optional<ConstructionType> findByType(String type);
}

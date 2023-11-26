package org.geoproject.ingeo.repositories.classificators;

import org.geoproject.ingeo.models.classificators.PeatDecayDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeatDecayDegreeRepository extends JpaRepository<PeatDecayDegree, Integer> {
    Optional<PeatDecayDegree> findByP250(int p250);
}

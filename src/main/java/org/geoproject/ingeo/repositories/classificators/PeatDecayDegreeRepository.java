package com.geoproject.igeo.repositories.classificators;

import com.geoproject.igeo.models.classificators.PeatDecayDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeatDecayDegreeRepository extends JpaRepository<PeatDecayDegree, Integer> {
    Optional<PeatDecayDegree> findByP250(int p250);
}

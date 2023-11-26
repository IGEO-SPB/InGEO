package org.geoproject.ingeo.repositories.classificators;

import org.geoproject.ingeo.models.classificators.WeighingBottle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeighingBottleRepository extends JpaRepository<WeighingBottle, Integer> {

    Optional<WeighingBottle> findByNumber(String number);
}

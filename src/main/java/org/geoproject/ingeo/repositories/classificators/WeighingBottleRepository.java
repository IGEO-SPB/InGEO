package com.geoproject.igeo.repositories.classificators;

import com.geoproject.igeo.models.classificators.WeighingBottle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeighingBottleRepository extends JpaRepository<WeighingBottle, Integer> {

    Optional<WeighingBottle> findByNumber(String number);
}

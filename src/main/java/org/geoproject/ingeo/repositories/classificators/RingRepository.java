package org.geoproject.ingeo.repositories.classificators;

import org.geoproject.ingeo.models.classificators.Ring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RingRepository extends JpaRepository<Ring, Integer> {

    Optional<Ring> findByNumber(String number);

}

package com.geoproject.igeo.repositories.classificators;

import com.geoproject.igeo.models.classificators.Ring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RingRepository extends JpaRepository<Ring, Integer> {

    Optional<Ring> findByNumber(String number);

}

package com.geoproject.igeo.repositories.classificators;

import com.geoproject.igeo.models.classificators.Pot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PotRepository extends JpaRepository<Pot, Long> {

    Optional<Pot> findByNumber(String number);
}

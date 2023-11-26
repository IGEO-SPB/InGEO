package org.geoproject.ingeo.repositories.classificators;

import org.geoproject.ingeo.models.classificators.Pot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PotRepository extends JpaRepository<Pot, Long> {

    Optional<Pot> findByNumber(String number);
}

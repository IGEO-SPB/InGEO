package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.classificators.Genesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenesisRepository extends JpaRepository<Genesis, Integer> {

    Genesis findByCodeUni(String codeUni);

    Genesis findByGiId(int giId);
}

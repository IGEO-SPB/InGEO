package com.geoproject.igeo.repositories.classificators;

import com.geoproject.igeo.models.classificators.BoychenkoConeLiquidityConsistency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoychenkoConeLiquidityConsistencyRepository extends JpaRepository<BoychenkoConeLiquidityConsistency, Integer> {
    BoychenkoConeLiquidityConsistency findByBoychenkoConeImmersionDepth(float boychenkoConeImmersionDepth);
}

package com.geoproject.igeo.services.classificators;

import com.geoproject.igeo.models.classificators.BoychenkoConeLiquidityConsistency;

public interface BoychenkoConeLiquidityConsistencyService {

    BoychenkoConeLiquidityConsistency findByBoychenkoConeImmersionDepth(float boychenkoConeImmersionDepth);

    String defineCbLiquidityIndexName(float boychenkoConeImmersionDepth);

    }

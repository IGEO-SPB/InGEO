package org.geoproject.ingeo.services.classificators.impl;

import org.geoproject.ingeo.enums.laborenums.BoychenkoConeLiquidityConsistencyNameEnum;
import org.geoproject.ingeo.models.classificators.BoychenkoConeLiquidityConsistency;
import org.geoproject.ingeo.repositories.classificators.BoychenkoConeLiquidityConsistencyRepository;
import org.geoproject.ingeo.services.classificators.BoychenkoConeLiquidityConsistencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoychenkoConeLiquidityConsistencyImpl implements BoychenkoConeLiquidityConsistencyService {

    private final BoychenkoConeLiquidityConsistencyRepository repository;

    @Override
    public BoychenkoConeLiquidityConsistency findByBoychenkoConeImmersionDepth(float boychenkoConeImmersionDepth) {
        return repository.findByBoychenkoConeImmersionDepth(boychenkoConeImmersionDepth);
    }

    public String defineCbLiquidityIndexName(float boychenkoConeImmersionDepth) {
        if (boychenkoConeImmersionDepth <= 1) {
            return BoychenkoConeLiquidityConsistencyNameEnum.VERY_STIFF.getValue();
        } else if (boychenkoConeImmersionDepth > 1 && boychenkoConeImmersionDepth <= 4) {
            return BoychenkoConeLiquidityConsistencyNameEnum.STIFF.getValue();
        } else if (boychenkoConeImmersionDepth > 4 && boychenkoConeImmersionDepth <= 7.4) {
            return BoychenkoConeLiquidityConsistencyNameEnum.FIRM_STIFF.getValue();
        } else if (boychenkoConeImmersionDepth > 7.4 && boychenkoConeImmersionDepth <= 11.2) {
            return BoychenkoConeLiquidityConsistencyNameEnum.FIRM.getValue();
        } else if (boychenkoConeImmersionDepth > 11.2 && boychenkoConeImmersionDepth <= 15) {
            return BoychenkoConeLiquidityConsistencyNameEnum.VERY_FIRM.getValue();
        } else if (boychenkoConeImmersionDepth > 15 && boychenkoConeImmersionDepth <= 22) {
            return BoychenkoConeLiquidityConsistencyNameEnum.SOFT_FIRM.getValue();
        } else if (boychenkoConeImmersionDepth > 22 && boychenkoConeImmersionDepth <= 48) {
            return BoychenkoConeLiquidityConsistencyNameEnum.VERY_SOFT.getValue();
        } else {
//            throw new SomeException();
            return null;
        }
    }
}

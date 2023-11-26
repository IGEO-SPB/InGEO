package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.classificators.SurveyPointsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyPointsTypesRepository extends JpaRepository<SurveyPointsType, Integer> {

    SurveyPointsType findBySurveyTypeShortName(String surveyTypeShortName);
}

package com.geoproject.igeo.repositories;

import com.geoproject.igeo.models.BoreholeLayer;
import com.geoproject.igeo.models.SurveyPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoreHoleLayerMainRepository extends JpaRepository<BoreholeLayer, Long> {

    List<BoreholeLayer> findBySurveyPoint(SurveyPoint surveyPoint);

}

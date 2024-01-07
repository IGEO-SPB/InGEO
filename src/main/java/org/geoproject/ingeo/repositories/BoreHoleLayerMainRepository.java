package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.BoreholeLayer;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.SurveyPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoreHoleLayerMainRepository extends JpaRepository<BoreholeLayer, Long> {

    List<BoreholeLayer> findBySurveyPointIdAndIsArchiveFalse(Long surveyPointId);

    List<BoreholeLayer> findBySurveyPointProjectAndIsArchiveFalse(Project project);
}

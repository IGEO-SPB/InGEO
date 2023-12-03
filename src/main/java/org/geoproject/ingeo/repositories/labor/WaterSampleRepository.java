package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WaterSampleRepository extends JpaRepository<WaterSample, Long> {

    List<WaterSample> findBySurveyPoint(SurveyPoint surveyPoint);

    Optional<WaterSample> findByLaborNumber(String laborNumber);

    List<WaterSample> findAllByLaborNumberIn(List<String> dtoLaborNumbers);

    List<WaterSample> findAllBySurveyPoint(SurveyPoint surveyPoint);

    Boolean existsByLaborNumberAndSurveyPointProject(String laborNumber, Project project);

    @Query("""
            SELECT ws FROM WaterSample ws
            LEFT JOIN ws.surveyPoint sp
            WHERE sp.project = :currentProject
            """)
    List<WaterSample> findAllByProject(@Param("currentProject") Project currentProject);

    @Query("""
            SELECT ws FROM WaterSample ws
            LEFT JOIN ws.surveyPoint sp
            WHERE ws.laborNumber = :laborNumber
            AND sp.project.id = :projectId
            """)
    WaterSample findByLaborNumberAndProjectId(@Param("laborNumber") String laborNumber,
                                              @Param("projectId") Long projectId);

    WaterSample findByLaborNumberAndSurveyPoint(String laborNumber, SurveyPoint surveyPoint);
}

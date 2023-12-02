package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.labor.WaterSampleResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterSampleResultRepository extends JpaRepository<WaterSampleResult, Long> {

    List<WaterSampleResult> findAllBySurveyPointIdIn(List<Long> surveyPointsIds);

    @Query("""
            SELECT wsr FROM WaterSampleResult wsr
            LEFT JOIN SurveyPoint sp ON wsr.surveyPoint = sp
            WHERE sp.project = :currentProject
            """)
    List<WaterSampleResult> findAllByProject(@Param("currentProject") Project currentProject);

    Boolean existsByLaborNumberAndSurveyPointProject(String laborNumber, Project project);
}

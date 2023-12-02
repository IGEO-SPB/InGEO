package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SamplesRepository extends JpaRepository<Sample, Long> {

    List<Sample> findBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber);

    Optional<Sample> findByLaborNumber(String laborNumber);

    @Query("SELECT s FROM Sample s " +
            "WHERE s.surveyPoint.project = :project")
    List<Sample> findByProject(@Param("project") Project project);

    @Query("SELECT s FROM Sample s " +
            "WHERE s.surveyPoint.pointNumber like :surveyPointNumber " +
            "AND s.laborNumber like :laborNumber")
    Optional<Sample> findBySurveyPointNumberAndLaborNumber(@Param("surveyPointNumber") String surveyPointNumber, @Param("laborNumber") String laborNumber);

    @Query("SELECT s FROM Sample s " +
            "WHERE s.surveyPoint.project = :project " +
            "AND s.laborNumber like :laborNumber")
    Optional<Sample> findByProjectIdAndLaborNumber(@Param("project") Project project,
                                                   @Param("laborNumber") String laborNumber);
}

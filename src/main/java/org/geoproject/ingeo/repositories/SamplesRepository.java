package com.geoproject.igeo.repositories;

import com.geoproject.igeo.models.Project;
import com.geoproject.igeo.models.Sample;
import com.geoproject.igeo.models.SurveyPoint;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SamplesRepository extends JpaRepository<Sample, Long> {

    List<Sample> findBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber);

    Optional<Sample> findByLaborNumber(String laborNumber);

    @Query("SELECT s FROM Sample s " +
            "WHERE s.surveyPoint.project = :project")
    List<Sample> findByProject(Project project);

    @Query("SELECT s FROM Sample s " +
            "WHERE s.surveyPoint.pointNumber like :surveyPointNumber " +
            "AND s.laborNumber like :laborNumber")
    Optional<Sample> findBySurveyPointNumberAndLaborNumber(String surveyPointNumber, String laborNumber);

    @Query("SELECT s FROM Sample s " +
            "WHERE s.surveyPoint.project = :project " +
            "AND s.laborNumber like :laborNumber")
    Optional<Sample> findByProjectIdAndLaborNumber(Project project, String laborNumber);
}

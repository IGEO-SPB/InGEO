package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.WaterExtractPartialResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WaterExtractPartialResultRepository extends JpaRepository<WaterExtractPartialResult, Long> {

    @Query("SELECT w FROM WaterExtractPartialResult w " +
            "WHERE w.sample = :sample " +
            "AND w.isArchive = false")
    Optional<WaterExtractPartialResult> findBySampleAndNotArchive(Sample sample);

    @Query("SELECT w FROM WaterExtractPartialResult w " +
            "WHERE w.sample.surveyPoint.project.id = :projectId")
    List<WaterExtractPartialResult> findAllByProjectId(Long projectId);

    @Query("SELECT w FROM WaterExtractPartialResult w " +
            "WHERE w.sample.surveyPoint.project.id = :projectId " +
            "AND w.isArchive = false")
    List<WaterExtractPartialResult> findAllByProjectIdAndIsNotArchive(Long projectId);

    @Query("SELECT w FROM WaterExtractPartialResult w " +
            "WHERE w.sample.surveyPoint.pointNumber like :surveyPointNumber " +
            "AND w.sample.laborNumber like :laborNumber " +
            "AND w.isArchive = false")
    Optional<WaterExtractPartialResult> findBySurveyPointNumberAndLaborNumberAndIsNotArchive(String surveyPointNumber, String laborNumber);
}

package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.WaterExtractFullResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WaterExtractFullResultRepository extends JpaRepository<WaterExtractFullResult, Long> {

    @Query("SELECT w FROM WaterExtractFullResult w " +
            "WHERE w.sample = :sample " +
            "AND w.isArchive = false")
    Optional<WaterExtractFullResult> findBySampleAndNotArchive(Sample sample);

    @Query("SELECT w FROM WaterExtractFullResult w " +
            "WHERE w.sample.surveyPoint.project.id = :projectId")
    List<WaterExtractFullResult> findAllByProjectId(Long projectId);

    @Query("SELECT w FROM WaterExtractFullResult w " +
            "WHERE w.sample.surveyPoint.project.id = :projectId " +
            "AND w.isArchive = false")
    List<WaterExtractFullResult> findAllByProjectIdAndIsNotArchive(Long projectId);

    @Query("SELECT w FROM WaterExtractFullResult w " +
            "WHERE w.sample.surveyPoint.pointNumber like :surveyPointNumber " +
            "AND w.sample.laborNumber like :laborNumber " +
            "AND w.isArchive = false")
    Optional<WaterExtractFullResult> findBySurveyPointNumberAndLaborNumberAndIsNotArchive(String surveyPointNumber, String laborNumber);

}

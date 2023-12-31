package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.WaterExtractFull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WaterExtractFullRepository extends JpaRepository<WaterExtractFull, Long> {

    @Query("SELECT w FROM WaterExtractFull w " +
            "WHERE w.sample.surveyPoint.project.id = :projectId " +
            "AND w.isArchive = false")
    List<WaterExtractFull> findAllByProjectIdAndIsNotArchive(@Param(value = "projectId") Long projectId);

    @Query("SELECT w FROM WaterExtractFull w " +
            "WHERE w.sample.surveyPoint.pointNumber like :surveyPointNumber " +
            "AND w.sample.laborNumber like :laborNumber " +
            "AND w.isArchive = false")
    Optional<WaterExtractFull> findBySurveyPointNumberAndLaborNumberAndIsNotArchive(@Param(value = "surveyPointNumber") String surveyPointNumber,
                                                                                    @Param(value = "laborNumber") String laborNumber);

    @Query("SELECT w FROM WaterExtractFull w " +
            "WHERE w.sample = :sample " +
            "AND w.isArchive = false")
    Optional<WaterExtractFull> findBySampleAndNotArchive(@Param(value = "sample") Sample sample);
}

package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.WaterExtractPartial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WaterExtractPartialRepository extends JpaRepository<WaterExtractPartial, Long> {

    @Query("SELECT w FROM WaterExtractPartial w " +
            "WHERE w.sample.surveyPoint.project.id = :projectId " +
            "AND w.isArchive = false")
    List<WaterExtractPartial> findAllByProjectIdAndIsNotArchive(@Param("projectId") Long projectId);

    @Query("SELECT w FROM WaterExtractPartial w " +
            "WHERE w.sample.surveyPoint.pointNumber like :surveyPointNumber " +
            "AND w.sample.laborNumber like :laborNumber " +
            "AND w.isArchive = false")
    Optional<WaterExtractPartial> findBySurveyPointNumberAndLaborNumberAndIsNotArchive(@Param("surveyPointNumber") String surveyPointNumber,
                                                                                       @Param("laborNumber") String laborNumber);

    @Query("SELECT w FROM WaterExtractPartial w " +
            "WHERE w.sample = :sample " +
            "AND w.isArchive = false")
    Optional<WaterExtractPartial> findBySampleAndNotArchive(@Param("sample") Sample sample);
}

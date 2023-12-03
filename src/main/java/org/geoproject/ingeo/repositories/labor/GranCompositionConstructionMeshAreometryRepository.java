package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.GranCompositionConstructionMeshAreometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GranCompositionConstructionMeshAreometryRepository extends JpaRepository<GranCompositionConstructionMeshAreometry, Long> {

    Optional<GranCompositionConstructionMeshAreometry> findBySampleId(Long id);

    //    @Query(nativeQuery = true, value = "SELECT g.*, s.point_id FROM gran_composition_areometry g left join " +
//            "sample s ON g.sample_id = s.id " +
//            "WHERE s.point_id = :surveyPointId ORDER BY s.labor_number")
    @Query("SELECT g FROM GranCompositionConstructionMeshAreometry g " +
            "WHERE g.sample.surveyPoint.id = :surveyPointId")
    List<GranCompositionConstructionMeshAreometry> findBySurveyPoint(@Param("surveyPointId") Long surveyPointId);

    @Query("SELECT g FROM GranCompositionConstructionMeshAreometry g " +
            "WHERE g.sample.laborNumber IN (:dtoLaborNumbers)")
    List<GranCompositionConstructionMeshAreometry> findAllByLaborNumberIn(@Param("dtoLaborNumbers") List<String> dtoLaborNumbers);
}
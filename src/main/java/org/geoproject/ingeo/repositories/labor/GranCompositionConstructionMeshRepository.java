package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.GranCompositionConstructionMesh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GranCompositionConstructionMeshRepository extends JpaRepository<GranCompositionConstructionMesh, Long> {

    Optional<GranCompositionConstructionMesh> findBySampleId(Long id);

//    @Query(nativeQuery = true, value = "SELECT g.*, s.point_id FROM gran_composition_areometry g left join " +
//            "sample s ON g.sample_id = s.id " +
//            "WHERE s.point_id = :surveyPointId ORDER BY s.labor_number")
    @Query("SELECT g FROM GranCompositionConstructionMesh g " +
            "WHERE g.sample.surveyPoint.id = :surveyPointId")
    List<GranCompositionConstructionMesh> findBySurveyPoint(Long surveyPointId);

    List<GranCompositionConstructionMesh> findAllByIdIn(List<String> dtoLaborNumbers);
}

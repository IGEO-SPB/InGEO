package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.ConstructionMeshAreometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConstructionMeshAreometryRepository extends JpaRepository<ConstructionMeshAreometry, Long> {

    Optional<ConstructionMeshAreometry> findBySampleId(Long id);

    Optional<ConstructionMeshAreometry> findByLaborNumber(String laborNumber);

    @Query(nativeQuery = true, value = "SELECT cm.*, s.point_id FROM construction_mesh_areometry cm left join " +
            "sample s ON cm.labor_number = s.labor_number " +
            "WHERE s.point_id = :surveyPointId ORDER BY cm.labor_number")
    List<ConstructionMeshAreometry> findBySurveyPoint(@Param("surveyPointId") Long surveyPointId);
}

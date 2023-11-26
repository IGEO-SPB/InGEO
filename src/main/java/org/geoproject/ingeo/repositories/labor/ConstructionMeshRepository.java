package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.ConstructionMesh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConstructionMeshRepository extends JpaRepository<ConstructionMesh, Long> {

    Optional<ConstructionMesh> findBySampleId(Long id);

    Optional<ConstructionMesh> findByLaborNumber(String laborNumber);

    @Query(nativeQuery = true, value = "SELECT cm.*, s.point_id FROM construction_mesh cm left join " +
            "sample s ON cm.labor_number = s.labor_number " +
            "WHERE s.point_id = :surveyPointId ORDER BY cm.labor_number")
    List<ConstructionMesh> findBySurveyPoint(Long surveyPointId);
}

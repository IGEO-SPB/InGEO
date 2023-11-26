package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.GranCompositionAreometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GranCompositionAreometryRepository extends JpaRepository<GranCompositionAreometry, Long> {

    Optional<GranCompositionAreometry> findBySampleId(Long id);

//    @Query(nativeQuery = true, value = "SELECT g.*, s.point_id FROM gran_composition_areometry g left join " +
//            "sample s ON g.sample_id = s.id " +
//            "WHERE s.point_id = :surveyPointId ORDER BY s.labor_number")
    @Query("SELECT g FROM GranCompositionAreometry g " +
            "WHERE g.sample.surveyPoint.id = :surveyPointId")
    List<GranCompositionAreometry> findBySurveyPoint(Long surveyPointId);

    List<GranCompositionAreometry> findAllByIdIn(List<String> dtoLaborNumbers);
}

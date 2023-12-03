package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.PhysicalProperties;
import org.geoproject.ingeo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhysicalPropertiesRepository extends JpaRepository<PhysicalProperties, Long> {

    Optional<PhysicalProperties> findBySampleId(Long id);

    PhysicalProperties findByLaborNumber(String laborNumber);

    @Query(nativeQuery = true, value = "SELECT p.*, s.point_id FROM physical_properties p left join " +
            "sample s ON p.labor_number = s.labor_number " +
            "WHERE s.point_id = :surveyPointId ORDER BY p.labor_number")
    List<PhysicalProperties> findBySurveyPointId(@Param("surveyPointId") Long surveyPointId);

    @Query("SELECT p FROM PhysicalProperties p left join " +
            "Sample s ON p.laborNumber = s.laborNumber left join " +
            "SurveyPoint sp ON s.surveyPoint = sp left join " +
            "Project pr ON sp.project = pr " +
            "WHERE pr = :project " +
//            "AND p.isArchive = false " +
            "ORDER BY p.laborNumber")
    List<PhysicalProperties> findByProject( @Param("project") Project project);
}

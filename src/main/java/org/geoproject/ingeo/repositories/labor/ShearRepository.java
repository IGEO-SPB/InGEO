package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.Shear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShearRepository extends JpaRepository<Shear, Long> {

    @Query("SELECT s FROM Shear s " +
            "WHERE s.sample.surveyPoint.project.id = :projectId " +
            "AND s.isArchive = false")
    List<Shear> findAllByProjectIdAndIsNotArchive(Long projectId);

    @Query("SELECT s FROM Shear s " +
            "WHERE s.sample = :sample " +
            "AND s.shearPointNumber = :number " +
            "AND s.isArchive = false")
    Optional<Shear> findBySampleAndShearPointNumber(Sample sample, Integer number);

    @Query("SELECT s FROM Shear s " +
            "WHERE s.sample.surveyPoint.project = :currentProject " +
            "AND s.isArchive = false " +
            "ORDER BY s.shearPointNumber ASC")
    List<Shear> findByProjectSortByShearPointNumber(Project currentProject);

    List<Shear> findBySampleOrderByShearPointNumber(Sample sample);
}

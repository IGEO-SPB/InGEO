package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.Compression;
import org.geoproject.ingeo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompressionRepository extends JpaRepository<Compression, Long> {
    Optional<Compression> findByLabNumber(String labNumber);

    @Query("""
    SELECT c FROM Compression c
    LEFT JOIN c.sample s
    LEFT JOIN s.surveyPoint sp
    WHERE sp.project = :currentProject
    """)
    List<Compression> findByProject(Project currentProject);
}

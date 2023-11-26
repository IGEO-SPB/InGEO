package org.geoproject.ingeo.repositories.labor;

import org.geoproject.ingeo.models.labor.CompressionPaths;
import org.geoproject.ingeo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompressionPathsRepository extends JpaRepository<CompressionPaths, Long> {
    CompressionPaths getByProject(Project proj);
}

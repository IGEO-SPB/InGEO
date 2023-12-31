package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EgeRepository extends JpaRepository<Ege, Long> {

    List<Ege> findByProject(Project project);

    Ege findByEgeNumberAndProject(String number, Project project);
}

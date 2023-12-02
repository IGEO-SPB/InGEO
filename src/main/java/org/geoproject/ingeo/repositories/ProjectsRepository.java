package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.Project;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {
    Project findByContractNumber(String contractNumber);

    List<Project> findAllByIsArchiveFalse(Sort sort);
}

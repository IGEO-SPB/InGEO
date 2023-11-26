package org.geoproject.ingeo.repositories;

import org.geoproject.ingeo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {
    Project findByContractNumber(String contractNumber);
}

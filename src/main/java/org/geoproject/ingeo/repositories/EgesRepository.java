package com.geoproject.igeo.repositories;

import com.geoproject.igeo.models.Ege;
import com.geoproject.igeo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EgesRepository extends JpaRepository<Ege, Long> {

    List<Ege> findByProject(Project project);

    Ege findByNumberAndProject(String number, Project project);
}

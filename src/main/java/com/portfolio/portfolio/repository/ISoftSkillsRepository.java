package com.portfolio.portfolio.repository;

import com.portfolio.portfolio.entity.SoftSkills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoftSkillsRepository extends JpaRepository<SoftSkills, Integer>{
    Optional<SoftSkills> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}

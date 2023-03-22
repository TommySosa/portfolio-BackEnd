package com.portfolio.portfolio.repository;

import com.portfolio.portfolio.entity.HardSkills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHardSkillRepository extends JpaRepository<HardSkills, Integer>{
    Optional<HardSkills> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}

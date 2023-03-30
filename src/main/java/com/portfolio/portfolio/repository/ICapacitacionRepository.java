package com.portfolio.portfolio.repository;

import com.portfolio.portfolio.entity.Capacitacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICapacitacionRepository extends JpaRepository<Capacitacion, Integer>{
    public Optional<Capacitacion> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}

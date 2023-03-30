package com.portfolio.portfolio.service;

import com.portfolio.portfolio.entity.Capacitacion;
import com.portfolio.portfolio.repository.ICapacitacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CapacitacionService {
    @Autowired
    ICapacitacionRepository rCapacitacion;
    
    public List<Capacitacion> list(){
        return rCapacitacion.findAll();
    }
    
    public Optional<Capacitacion> getOne(int id) {
        return rCapacitacion.findById(id);
    }
    
    public Optional<Capacitacion> getByNombre(String nombre) {
        return rCapacitacion.findByNombre(nombre);
    }
    
    public void save(Capacitacion capacitacion) {
        rCapacitacion.save(capacitacion);
    }
    
    public void delete(int id) {
        rCapacitacion.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return rCapacitacion.existsById(id);
    }
    
    public boolean existsByNombre(String nombre) {
        return rCapacitacion.existsByNombre(nombre);
    }
}

package com.portfolio.portfolio.service;

import com.portfolio.portfolio.entity.SoftSkills;
import com.portfolio.portfolio.repository.ISoftSkillsRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SoftSkillService {
    @Autowired
    ISoftSkillsRepository rSoft;
    
    public List<SoftSkills> list() {
        return rSoft.findAll();
    }
    
    public Optional<SoftSkills> getOne(int id) {
        return rSoft.findById(id);
    }
    
    public Optional<SoftSkills> getByNombre(String nombre) {
        return rSoft.findByNombre(nombre);
    }
    
    public void save(SoftSkills soft) {
        rSoft.save(soft);
    }
    
    public void delete(int id) {
        rSoft.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rSoft.existsById(id);
    }
    
    public boolean existsByNombre(String nombre) {
        return rSoft.existsByNombre(nombre);
    }
}

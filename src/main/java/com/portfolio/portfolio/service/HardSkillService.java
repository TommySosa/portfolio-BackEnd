package com.portfolio.portfolio.service;

import com.portfolio.portfolio.entity.HardSkills;
import com.portfolio.portfolio.repository.IHardSkillRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class HardSkillService {
    @Autowired
    IHardSkillRepository rHard;
    
    public List<HardSkills> list() {
        return rHard.findAll();
    }
    
    public Optional<HardSkills> getOne(int id) {
        return rHard.findById(id);
    }
    
    public Optional<HardSkills> getByNombre(String nombre) {
        return rHard.findByNombre(nombre);
    }
    
    public void save(HardSkills hard) {
        rHard.save(hard);
    }
    
    public void delete(int id) {
        rHard.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHard.existsById(id);
    }
    
    public boolean existsByNombre(String nombre) {
        return rHard.existsByNombre(nombre);
    }
}

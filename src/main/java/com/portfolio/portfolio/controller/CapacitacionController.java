package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.Dto.DtoCapacitacion;
import com.portfolio.portfolio.entity.Capacitacion;
import com.portfolio.portfolio.security.controller.Mensaje;
import com.portfolio.portfolio.service.CapacitacionService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/capacitacion")
//@CrossOrigin(origins = {"http://localhost:4200/", "https://tomassosa-df065.web.app/"})
@CrossOrigin(origins =  "https://tomassosa-df065.web.app")
public class CapacitacionController {
    @Autowired
    CapacitacionService sCapacitacion;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Capacitacion>> list() {
        List<Capacitacion> list = sCapacitacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Capacitacion> getById(@PathVariable("id") int id) {
        if (!sCapacitacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Capacitacion capacitacion = sCapacitacion.getOne(id).get();
        return new ResponseEntity(capacitacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delet (@PathVariable ("id") int id) {
        if(!sCapacitacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        sCapacitacion.delete(id);
        return new ResponseEntity(new Mensaje("Capacitacion eliminada"), HttpStatus.OK);
        
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoCapacitacion dtoeduc) {
        if (StringUtils.isBlank(dtoeduc.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
            
        }
        if (sCapacitacion.existsByNombre(dtoeduc.getNombre())) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        Capacitacion capacitacion = new Capacitacion(
                dtoeduc.getNombre(), dtoeduc.getPeriodo(), dtoeduc.getImg()
        );
        
        sCapacitacion.save(capacitacion);
        
        return new ResponseEntity(new Mensaje("Capacitación creada"), HttpStatus.OK);
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") int id, @RequestBody DtoCapacitacion dtoeduc) {
        
        if (!sCapacitacion.existsById(id)) {
           return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
           
        }
        if (sCapacitacion.existsByNombre(dtoeduc.getNombre()) && sCapacitacion.getByNombre(dtoeduc.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"),HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(dtoeduc.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Capacitacion capacitacion = sCapacitacion.getOne(id).get();
        
        capacitacion.setNombre(dtoeduc.getNombre());
        capacitacion.setPeriodo(dtoeduc.getPeriodo());
        capacitacion.setImg(dtoeduc.getImg());
        
        sCapacitacion.save(capacitacion);
        
        return new ResponseEntity(new Mensaje("Capacitacion actualizada"), HttpStatus.OK);
    }
}

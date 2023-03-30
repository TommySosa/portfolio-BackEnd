package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.Dto.DtoProyectos;
import com.portfolio.portfolio.entity.Proyectos;
import com.portfolio.portfolio.security.controller.Mensaje;
import com.portfolio.portfolio.service.ProyectosService;
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
@RequestMapping("/proyectos")
//@CrossOrigin(origins = {"http://localhost:4200/", "https://tomassosa-df065.web.app/"})
@CrossOrigin(origins =  "https://tomassosa-df065.web.app/")
public class ProyectosController {
    @Autowired
    ProyectosService sProyectos;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list() {
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id) {
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = sProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delet (@PathVariable ("id") int id) {
        if(!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        sProyectos.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
        
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyectos dtoproy) {
        if (StringUtils.isBlank(dtoproy.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
            
        }
        if (sProyectos.existsByNombre(dtoproy.getNombre())) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        Proyectos proyectos = new Proyectos(
                dtoproy.getNombre(), dtoproy.getDescripcion(), dtoproy.getLinkUrl(), dtoproy.getGithubUrl(), dtoproy.getImg()
        );
        
        sProyectos.save(proyectos);
        
        return new ResponseEntity(new Mensaje("Proyecto creado"), HttpStatus.OK);
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") int id, @RequestBody DtoProyectos dtoproy) {
        
        if (!sProyectos.existsById(id)) {
           return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
           
        }
        if (sProyectos.existsByNombre(dtoproy.getNombre()) && sProyectos.getByNombre(dtoproy.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"),HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(dtoproy.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = sProyectos.getOne(id).get();
        
        proyectos.setNombre(dtoproy.getNombre());
        proyectos.setDescripcion(dtoproy.getDescripcion());
        proyectos.setLinkUrl(dtoproy.getLinkUrl());
        proyectos.setGithubUrl(dtoproy.getGithubUrl());
        proyectos.setImg(dtoproy.getImg());
        
        sProyectos.save(proyectos);
        
        return new ResponseEntity(new Mensaje("Proyectos actualizado"), HttpStatus.OK);
    }
}

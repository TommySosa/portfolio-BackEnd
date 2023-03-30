package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.Dto.DtoSoftSkills;
import com.portfolio.portfolio.entity.SoftSkills;
import com.portfolio.portfolio.security.controller.Mensaje;
import com.portfolio.portfolio.service.SoftSkillService;
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
@CrossOrigin(origins = {"http://localhost:4200/", "https://tomassosa-df065.web.app/"})
//@CrossOrigin(origins =  "https://tomassosa-df065.web.app/")
@RequestMapping("/softskills")
public class SoftSkillsController {
    
    @Autowired
    SoftSkillService sSoft;
    
    @GetMapping("/lista")
   public ResponseEntity<List<SoftSkills>> list() {
        List<SoftSkills> list = sSoft.list();

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoSoftSkills dtosoft) {
        if (StringUtils.isBlank(dtosoft.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (sSoft.existsByNombre(dtosoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        SoftSkills softskills = new SoftSkills(dtosoft.getNombre(), dtosoft.getPorcentaje());
        sSoft.save(softskills);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoSoftSkills dtosoft) {
        //validamos si existe el ID
        if (!sSoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("El  ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de experiencias
        if (sSoft.existsByNombre(dtosoft.getNombre()) && sSoft.getByNombre(dtosoft.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        //no puede estar vacio
        if (StringUtils.isBlank(dtosoft.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        SoftSkills softskills = sSoft.getOne(id).get();
        softskills.setNombre(dtosoft.getNombre());
        softskills.setPorcentaje(dtosoft.getPorcentaje());

        sSoft.save(softskills);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //validamos si existe el ID
        if (!sSoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("El  ID no existe"), HttpStatus.BAD_REQUEST);
        }

        sSoft.delete(id);

        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<SoftSkills> getById(@PathVariable("id") int id) {
        if (!sSoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        SoftSkills softskills = sSoft.getOne(id).get();
        return new ResponseEntity(softskills, HttpStatus.OK);
    }
}

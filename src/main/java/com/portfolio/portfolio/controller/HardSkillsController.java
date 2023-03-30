package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.Dto.DtoHardSkills;
import com.portfolio.portfolio.entity.HardSkills;
import com.portfolio.portfolio.security.controller.Mensaje;
import com.portfolio.portfolio.service.HardSkillService;
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
//@CrossOrigin(origins = {"http://localhost:4200/", "https://tomassosa-df065.web.app/"})
@CrossOrigin(origins =  "https://tomassosa-df065.web.app/")
@RequestMapping("/hardskills")
public class HardSkillsController {

    @Autowired
    HardSkillService sHard;

    @GetMapping("/lista")
    public ResponseEntity<List<HardSkills>> list() {
        List<HardSkills> list = sHard.list();

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHardSkills dtohard) {
        if (StringUtils.isBlank(dtohard.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (sHard.existsByNombre(dtohard.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        HardSkills hardskills = new HardSkills(dtohard.getNombre(), dtohard.getPorcentaje(), dtohard.getImg());
        sHard.save(hardskills);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHardSkills dtohard) {
        //validamos si existe el ID
        if (!sHard.existsById(id)) {
            return new ResponseEntity(new Mensaje("El  ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de experiencias
        if (sHard.existsByNombre(dtohard.getNombre()) && sHard.getByNombre(dtohard.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        //no puede estar vacio
        if (StringUtils.isBlank(dtohard.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        HardSkills hardskills = sHard.getOne(id).get();
        hardskills.setNombre(dtohard.getNombre());
        hardskills.setPorcentaje(dtohard.getPorcentaje());
        hardskills.setImg(dtohard.getImg());

        sHard.save(hardskills);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //validamos si existe el ID
        if (!sHard.existsById(id)) {
            return new ResponseEntity(new Mensaje("El  ID no existe"), HttpStatus.BAD_REQUEST);
        }

        sHard.delete(id);

        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<HardSkills> getById(@PathVariable("id") int id) {
        if (!sHard.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        HardSkills hardskills = sHard.getOne(id).get();
        return new ResponseEntity(hardskills, HttpStatus.OK);
    }
}

package com.portfolio.portfolio.Dto;

import javax.validation.constraints.NotBlank;


public class DtoCapacitacion {
    @NotBlank
    private String nombre;
    @NotBlank
    private String periodo;
    @NotBlank
    private String img;

    public DtoCapacitacion() {
    }

    public DtoCapacitacion(String nombre, String periodo, String img) {
        this.nombre = nombre;
        this.periodo = periodo;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
}

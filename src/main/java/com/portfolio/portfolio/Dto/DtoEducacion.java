package com.portfolio.portfolio.Dto;

import javax.validation.constraints.NotBlank;


public class DtoEducacion {
    
    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;
    private String img;

    public DtoEducacion() {
    }

    public DtoEducacion(String nombreE, String descripcionE, String img) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }
    
    
}

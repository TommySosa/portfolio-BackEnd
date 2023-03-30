package com.portfolio.portfolio.Dto;

import javax.validation.constraints.NotBlank;


public class DtoProyectos {
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    private String linkUrl;
    private String githubUrl;
    @NotBlank
    private String img;

    public DtoProyectos() {
    }

    public DtoProyectos(String nombre, String descripcion, String linkUrl, String githubUrl, String img) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.linkUrl = linkUrl;
        this.githubUrl = githubUrl;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
    
    
}

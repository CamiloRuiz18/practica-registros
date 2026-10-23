package com.registros.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuarios{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_usuario")
    private Integer id;

    private String nombre;
    private String correo;
    private String contrasena;
    private String telefono;
    private String proveedor;
    private String rol;
    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creado_en;

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    @JsonIgnore
    public String getContrasena(){
        return contrasena;
    }
    
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getProveedor(){
        return proveedor;
    }

    public void setProveedor(String proveedor){
        this.proveedor = proveedor;
    }

    public String getRol(){
        return rol;
    }

    public void setRol(String rol){
        this.rol = rol;
    }

    public LocalDateTime getCreado_en(){
        return creado_en;
    }

    public void setCreado_en(LocalDateTime creado_en){
        this.creado_en = creado_en;
    }

}


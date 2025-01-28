package com.pruebaDani.demoDani;

import lombok.Data;

@Data
public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;

    public boolean esElMismo(Estudiante estudiante){
    return this.id==estudiante.getId() && this.nombre.equals(estudiante.getNombre()) && this.apellido.equals(estudiante.getApellido()) && this.edad==estudiante.getEdad();
    }

    public boolean esMismoID(int id){
        return this.id==id;
    }

    public boolean esMismoNombre( String nombre){
        return this.nombre.equals(nombre);
    }
}


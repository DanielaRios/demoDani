package com.pruebaDani.demoDani;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EstudiantesService implements InterfaceEstudiantesService{

    ArrayList<Estudiante> estudiantes = new ArrayList<>();

   @Override
   public ArrayList<Estudiante> getTodosLosEstudiantes(){
    return this.estudiantes;
   }

   @Override
   public ArrayList<Estudiante> getPorNombreEstudiantes(String nombre){
       ArrayList<Estudiante> estudiantesPorNombre = new ArrayList<>();
       for (Estudiante estudiante: estudiantes) {
           if (estudiante.esMismoNombre(nombre)) {
               estudiantesPorNombre.add(estudiante);
           }
       }
       return estudiantesPorNombre;
   }
}

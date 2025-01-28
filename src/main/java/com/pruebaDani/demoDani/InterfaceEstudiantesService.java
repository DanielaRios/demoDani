package com.pruebaDani.demoDani;

import java.util.ArrayList;

public interface InterfaceEstudiantesService {

    public ArrayList<Estudiante> getTodosLosEstudiantes();
    public ArrayList<Estudiante> getPorNombreEstudiantes( String nombre);
}

package com.pruebaDani.demoDani;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*PRIMEROS PASOS*/

@RestController //especifica que la clase es un controlador
@RequestMapping
public class EstudiantesController {

    ArrayList<Estudiante> estudiantes = new ArrayList<>();
    @Autowired
    private EstudiantesService estudiantesService;

    public Estudiante buscarEstudiante(Estudiante estudiante) {
        int pos = 0;
        Estudiante estudianteEncontrado = null;
        while (pos < estudiantes.size() && estudianteEncontrado == null) {
            if (estudiantes.get(pos).esElMismo(estudiante)) {
                estudianteEncontrado = estudiantes.get(pos);
            }
            pos++;
        }
        return estudianteEncontrado;
    }

    @GetMapping("/estudiantes/{id}")
    public ResponseEntity<Estudiante> getEstudiante(@PathVariable("id") int id){
        System.out.println("Id: " + id);
        Estudiante estudianteEncontrado=null;
        ResponseEntity<Estudiante> respuesta;
        int pos=0;
        while(pos<estudiantes.size() && estudianteEncontrado==null ){
            if (estudiantes.get(pos).esMismoID(id)){
                estudianteEncontrado= estudiantes.get(pos);
            }
            pos++;
        }
        if( estudianteEncontrado!=null){
            respuesta = new ResponseEntity<>(estudianteEncontrado, HttpStatus.OK);
        }else{
            respuesta = ResponseEntity.notFound().build(); // alternativa de devolver un status - "build()" instancia ResponseEntity por eso no se coloca "new"
        }

        return respuesta;
    }

    @GetMapping("/estudiantes")
    public ResponseEntity<ArrayList<Estudiante>> getEstudiantes(@RequestParam( value = "nombre", required = false) String nombre){
        ArrayList<Estudiante> estudiantesPorNombre= new ArrayList<>();
        ResponseEntity<ArrayList<Estudiante>> respuesta;
        if (nombre==null){
         respuesta = new ResponseEntity<>(estudiantesService.getTodosLosEstudiantes(), HttpStatus.OK);
        }else{
            estudiantesPorNombre = estudiantesService.getPorNombreEstudiantes(nombre);
            if(estudiantesPorNombre.isEmpty()){
                respuesta = ResponseEntity.notFound().build();
            }else{
                respuesta = new ResponseEntity<>(estudiantesPorNombre, HttpStatus.OK);
            }
        }
        return respuesta;
    }

    /*
    @GetMapping("/estudiantes")
    public ResponseEntity<ArrayList<Estudiante>> getEstudiantes() {
        return new ResponseEntity<ArrayList<Estudiante>>(estudiantes, HttpStatus.OK);
    }
    */


    @PostMapping("/estudiantes")
    public ResponseEntity<String> existeEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante estudianteEncontrado = this.buscarEstudiante(estudiante);
        ResponseEntity<String> respuesta;
        if (estudianteEncontrado != null) {
            respuesta = new ResponseEntity<>("El usuario ya existe", HttpStatus.BAD_REQUEST);
        } else {
            respuesta = new ResponseEntity<>("Se creo correctamente", HttpStatus.CREATED);
            estudiantes.add(estudiante);
        }
        return respuesta;
    }

    @DeleteMapping("/estudiantes")
    public ResponseEntity<String> eliminarEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante estudianteEncontrado = this.buscarEstudiante(estudiante);
        ResponseEntity<String> respuesta;
        if (estudianteEncontrado != null) {
            estudiantes.remove(estudianteEncontrado);
            respuesta = new ResponseEntity<>("El estudiante se elimino", HttpStatus.OK);
        } else {
            respuesta = new ResponseEntity<>("NO EXISTE EL ESTUDIANTE", HttpStatus.NOT_FOUND);
        }
        return respuesta;
    }
}




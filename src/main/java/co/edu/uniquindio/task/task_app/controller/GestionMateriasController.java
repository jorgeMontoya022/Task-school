package co.edu.uniquindio.task.task_app.controller;

import java.util.List;

import co.edu.uniquindio.task.task_app.model.Materia;

public class GestionMateriasController extends CoreController {

    public GestionMateriasController(){
        super();
    }

    public List<Materia> getMateriasUsuario(String correo) {
        return ModelFactory.getMateriasUsuario(correo);
    }

    public boolean agregarMateria(Materia materia, String correo) {
       return ModelFactory.agregarMateria(materia, correo);
    }

    public boolean verificarMateria(String nombreMateria, String correo) {
        return ModelFactory.verificarMateria(nombreMateria, correo);
    }
    
}

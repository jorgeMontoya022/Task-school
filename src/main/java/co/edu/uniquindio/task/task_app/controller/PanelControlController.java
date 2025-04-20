package co.edu.uniquindio.task.task_app.controller;

import java.util.List;

import co.edu.uniquindio.task.task_app.model.Tarea;

public class PanelControlController extends CoreController{

    public PanelControlController(){
        super();
    }

    public List<Tarea> getTareas(String correo) {
        return ModelFactory.getTareas(correo);
        
    }

    public boolean agregarTarea(Tarea tarea, String correo) {
        return ModelFactory.agregarTarea(tarea, correo);
   
    }

    public int contarTareasPendientes(String correo) {
        return ModelFactory.contarTareasPendientes(correo);
    }

    public int contarTareasProximas(String correo) {
       return ModelFactory.contarTareasProximas(correo);
    }

    public boolean actualizarTarea(Tarea tareaSeleccionada, Tarea tarea, String correo) {
       return ModelFactory.actualizarTarea(tareaSeleccionada, tarea, correo);
    }
    
}

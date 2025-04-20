package co.edu.uniquindio.task.task_app.factory;

import java.util.List;

import co.edu.uniquindio.task.task_app.model.Materia;
import co.edu.uniquindio.task.task_app.model.Tarea;
import co.edu.uniquindio.task.task_app.model.TaskScholar;
import co.edu.uniquindio.task.task_app.model.Usuario;
import co.edu.uniquindio.task.task_app.utils.TaskSchoolUtils;

public class ModelFactory {

    TaskScholar taskScholar;

    private static ModelFactory modelFactory;

    private ModelFactory(){
        if(taskScholar == null) {
            initializeData();
        }
    }

    private void initializeData() {
        taskScholar = TaskSchoolUtils.initializeData();
    }

    public static ModelFactory getInstance() {
        if (modelFactory == null) {

            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    public Usuario validarAcceso(String correo, String contraseña) {
        try {
            return taskScholar.validarAcceso(correo, contraseña);
        } catch (Exception e) {
    
            System.err.println("Error al validar acceso: " + e.getMessage());
            return null;
        }
    }


    public List<Tarea> getTareas(String correo) {
       return taskScholar.getTareasUsuario(correo);
    }

    public boolean agregarTarea(Tarea tarea, String correo) {
        return taskScholar.agregarTarea(tarea, correo);
    }


    public List<Materia> getMateriasUsuario(String correo) {
        return taskScholar.getMateriasUsuario(correo);
    }

    public boolean agregarMateria(Materia materia, String correo) {
        return taskScholar.agregarMateria(materia, correo);

    }

    public boolean verificarMateria(String nombreMateria, String correo) {
        return taskScholar.verificarMateria(nombreMateria, correo);
    }

    public boolean registrarUsuario(Usuario usuario) {
       return taskScholar.registrarUsuario(usuario);
    }

    public boolean verificarUsuarioExiste(String correo) {
       return taskScholar.verificarUsuarioExiste(correo);
    }

    public int contarTareasPendientes(String correo) {
       return taskScholar.contarTareasPendientes(correo);
    }

    public int contarTareasProximas(String correo) {
       return taskScholar.contarTareasProximas(correo);
    }
    

    
}

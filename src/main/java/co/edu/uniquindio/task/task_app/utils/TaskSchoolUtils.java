package co.edu.uniquindio.task.task_app.utils;

import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.uniquindio.task.task_app.model.EstadoTarea;
import co.edu.uniquindio.task.task_app.model.Materia;
import co.edu.uniquindio.task.task_app.model.Tarea;
import co.edu.uniquindio.task.task_app.model.TaskScholar;
import co.edu.uniquindio.task.task_app.model.Usuario;

public class TaskSchoolUtils {
    
    public static TaskScholar initializeData(){

        // ----- Materia 1 -----
        Materia materia1 = new Materia(
            "Estructura de datos", 
            "Robinson Arias", 
            "Miércoles 7-9am - Viernes 7-9am", 
            0);

        // Tareas asociadas a materia1
        Tarea tarea1 = new Tarea(
            materia1,
            "Mapa conceptual de colas", 
            "Mapa conceptual", 
            LocalDate.of(2025, 4, 20),
            EstadoTarea.PENDIENTE);
        
        Tarea tarea2 = new Tarea(
            materia1, 
            "Realizar aplicación con javaFX", 
            "Aplicación fx", 
            LocalDate.of(2025, 4, 18),
            EstadoTarea.COMPLETADA);
        
        materia1.agregarTarea(tarea1); // Agrega la tarea a la cola
        materia1.agregarTarea(tarea2);

        //----Materia 2 ----
        Materia materia2 = new Materia(
            "Infraestructura Comp",
            "Jorge Orlando",
            "Lunes 9-11am - Jueves 11-1pm",
            0
        );

        Tarea tarea3 = new Tarea(
            materia2, 
            "Realizar un laboratorio sobre linux", 
            "Laboratorio 10", 
            LocalDate.of(2025, 4, 17),
            EstadoTarea.PENDIENTE);

        materia2.agregarTarea(tarea3);

        // ----- Usuario 1 -----
        Usuario usuario1 = new Usuario(
            "Juanes Esteban Montoya", 
            "jorgew.montoyat@uqvirtual.edu.co", 
            "maloh", 
            "Universidad del Quindío", 
            "Ing. Sistemas");

        ArrayList<Materia> materiasUsuario1 = new ArrayList<>();
        materiasUsuario1.add(materia1);
        materiasUsuario1.add(materia2);
        usuario1.setListaMaterias(materiasUsuario1);
        
    

        // ----- Usuario 2 -----
        Usuario usuario2 = new Usuario(
            "Jorge William Yepes", 
            "jorgetoro708@gmail.com", 
            "trululu", 
            "Universidad del Quindío", 
            "Ing. Civil");

        // ----- Usuario 3 -----
        Usuario usuario3 = new Usuario(
            "Samuel Castaño", 
            "samuel@gmail.com", 
            "samuel", 
            "Uniquindío", 
            "Ginecología");

        // ----- TaskScholar -----
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(usuario1);
        listaUsuarios.add(usuario2);
        listaUsuarios.add(usuario3);

        TaskScholar taskScholar = new TaskScholar();
        taskScholar.setListaUsuarios(listaUsuarios);

        return taskScholar;
    }
}

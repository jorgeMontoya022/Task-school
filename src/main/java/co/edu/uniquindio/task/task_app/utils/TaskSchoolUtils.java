package co.edu.uniquindio.task.task_app.utils;

import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.uniquindio.task.task_app.model.Materia;
import co.edu.uniquindio.task.task_app.model.Tarea;
import co.edu.uniquindio.task.task_app.model.TaskScholar;
import co.edu.uniquindio.task.task_app.model.Usuario;

public class TaskSchoolUtils {
    
    public static TaskScholar initializeData(){


        Materia materia = new Materia(
            "Estructura de datos", 
            "Robinson Arias", 
            "Miercoles 7-9am - Viernes 7-9am", 
            1);

        Tarea tarea1 = new Tarea(
            materia,
            "Mapa conceptual de colas", 
            "Mapa conceptual", 
            LocalDate.of(2025, 4, 20));
            tarea1.setMateria(materia);

        Usuario usuario1 = new Usuario(
            "Juanes Esteban Montoya", 
            "jorgew.montoyat@uqvirtual.edu.co", 
            "maloh", 
            "Universidad del quindío", 
            "Ing.Sistemas");
            
            //lista tareas para el usuario1
            ArrayList<Materia> listaTareas = new ArrayList<>();
            listaTareas.add(materia);
            usuario1.setListaMaterias(listaTareas);

            //-------------------------------------------------------------------------------------------

            Usuario usuario2 = new Usuario(
            "Jorge William Yepes", 
            "jorgetoro708@gmail.com", 
            "trululu", 
            "Universidad del quindío", 
            "Ing.Civil");

            ArrayList<Usuario> listaUsuarios = new ArrayList<>();
            listaUsuarios.add(usuario1);
            listaUsuarios.add(usuario2);

            TaskScholar taskScholar = new TaskScholar();
            taskScholar.setListaUsuarios(listaUsuarios);

            return taskScholar;
    }
}

package co.edu.uniquindio.task.task_app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

import co.edu.uniquindio.task.task_app.priorityQueue.ColaPrioridad;

public class Materia {
    private String nombreMateria;
    private String profesor;
    private String horario;
    private int tareasPendientes;
    private ColaPrioridad<Tarea> colaTareas;
   
    public Materia(String nombreMateria, String profesor, String horario, int tareasPendientes) {
        this.nombreMateria = nombreMateria;
        this.profesor = profesor;
        this.horario = horario;
        this.tareasPendientes = tareasPendientes;
        this.colaTareas = new ColaPrioridad<>(Comparator.comparing(Tarea:: getFechaEntrega));
       
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getTareasPendientes() {
        return tareasPendientes;
    }

    public void setTareasPendientes(int tareasPendientes) {
        this.tareasPendientes = tareasPendientes;
    }

    public ColaPrioridad<Tarea> getColaTareas() {
        return colaTareas;
    }

    public void setColaTareas(ColaPrioridad<Tarea> colaTareas) {
        this.colaTareas = colaTareas;
    }

    public void agregarTarea(Tarea tarea) {
        colaTareas.add(tarea);
        tareasPendientes++;
    }

   
}

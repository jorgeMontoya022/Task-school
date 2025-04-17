package co.edu.uniquindio.task.task_app.model;

import java.time.LocalDate;

public class Tarea {
    private Materia materia;
    private String descripción;
    private String titulo;
    private LocalDate fechaEntrega;
    private EstadoTarea estadoTarea;

    public Tarea(Materia materia, String descripción, String titulo, LocalDate fechaEntrega) {
        this.materia = materia;
        this.descripción = descripción;
        this.titulo = titulo;
        this.fechaEntrega = fechaEntrega;
        this.estadoTarea = estadoTarea.PENDIENTE;
    }

    public Tarea() {

    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public EstadoTarea getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(EstadoTarea estadoTarea) {
        this.estadoTarea = estadoTarea;
    }
    
    

}

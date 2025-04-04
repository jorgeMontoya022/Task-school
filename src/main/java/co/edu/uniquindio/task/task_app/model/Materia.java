package co.edu.uniquindio.task.task_app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Materia {
    private String nombreMateria;
    private String profesor;
    private String horario;
    private int tareasPendientes;
    private String colorHex; // Color para la barra superior



    public Materia(String nombreMateria, String profesor, String horario, int tareasPendientes, String colorHex) {
        this.nombreMateria = nombreMateria;
        this.profesor = profesor;
        this.horario = horario;
        this.tareasPendientes = tareasPendientes;
        this.colorHex = colorHex;
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

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }
    
    // Getters y setters
   
}

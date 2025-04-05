package co.edu.uniquindio.task.task_app.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombreCompleto;
    private String correo;
    private String contrasenia;
    private String institucion;
    private String carrera;
    private List<Materia> listaMaterias;


    public Usuario(String nombreCompleto, String correo, String contrasenia, String institucion, String carrera) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.institucion = institucion;
        this.carrera = carrera;
        this.listaMaterias = new ArrayList<>();
    }


    public String getNombreCompleto() {
        return nombreCompleto;
    }


    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }


    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getContrasenia() {
        return contrasenia;
    }


    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }


    public String getInstitucion() {
        return institucion;
    }


    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }


    public String getCarrera() {
        return carrera;
    }


    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }


    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }


    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }


    @Override
    public String toString() {
        return "Usuario [nombreCompleto=" + nombreCompleto + ", institucion=" + institucion + ", carrera=" + carrera
                + "]";
    }

    
}

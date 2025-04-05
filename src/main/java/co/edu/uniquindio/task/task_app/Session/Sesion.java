package co.edu.uniquindio.task.task_app.Session;

import co.edu.uniquindio.task.task_app.model.Usuario;

public class Sesion {
    private Usuario usuario;
    private static Sesion INSTANCE;

    private Sesion() {

    }

    public static Sesion getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new Sesion();
        }
        return INSTANCE;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void closeSesion() {
        this.usuario = null;
    }
    
}

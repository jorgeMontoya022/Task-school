package co.edu.uniquindio.task.task_app.model;

import java.util.ArrayList;
import java.util.List;

public class TaskScholar {
    private List<Usuario> listaUsuarios;

    public TaskScholar(){
        this.listaUsuarios = new ArrayList<>();
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public Usuario validarAcceso(String correo, String contraseña) throws Exception {
        for(Usuario usuario: getListaUsuarios()) {
            if (verificarUsuarioExiste(correo)){
                if(usuario.getCorreo().equals(correo) && usuario.getContrasenia().equals(contraseña)){
                    return usuario;
                }
            } else {
                throw new Exception("El usuario no existe");
            }
        }
        throw new Exception("Validaciones incorrectas");
    }

    
    private boolean verificarUsuarioExiste(String correo) {
        for(Usuario usuario: getListaUsuarios()) {
            if (usuario.getCorreo().equals(correo)){
                return true;
            }
        }
        return false;
    }
}

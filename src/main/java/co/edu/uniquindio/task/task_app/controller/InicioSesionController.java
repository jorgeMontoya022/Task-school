package co.edu.uniquindio.task.task_app.controller;

import co.edu.uniquindio.task.task_app.Session.Sesion;
import co.edu.uniquindio.task.task_app.model.Usuario;

public class InicioSesionController extends CoreController {

    public InicioSesionController() {
        super();
    }

    public Usuario validarAcceso(String correo, String contraseña) {
        return ModelFactory.validarAcceso(correo, contraseña);
    }

    public void guardarSesion(Usuario usuarioValidado) {
        Sesion.getInstance().setUsuario(usuarioValidado);
    }
    
}

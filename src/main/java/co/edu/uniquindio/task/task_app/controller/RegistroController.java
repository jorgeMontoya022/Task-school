package co.edu.uniquindio.task.task_app.controller;

import co.edu.uniquindio.task.task_app.model.Usuario;

public class RegistroController extends CoreController{

    public boolean registrarUsuario(Usuario usuario) {
       return ModelFactory.registrarUsuario(usuario);
    }

    public boolean verificarUsuarioExiste(String correo) {
      return ModelFactory.verificarUsuarioExiste(correo);
    }
    
}

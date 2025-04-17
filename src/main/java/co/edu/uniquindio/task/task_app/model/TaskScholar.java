package co.edu.uniquindio.task.task_app.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import co.edu.uniquindio.task.task_app.priorityQueue.ColaPrioridad;

public class TaskScholar {
    private List<Usuario> listaUsuarios;

    public TaskScholar() {
        this.listaUsuarios = new ArrayList<>();
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario validarAcceso(String correo, String contraseña) throws Exception {
        for (Usuario usuario : getListaUsuarios()) {
            if (verificarUsuarioExiste(correo)) {
                if (usuario.getCorreo().equals(correo) && usuario.getContrasenia().equals(contraseña)) {
                    return usuario;
                }
            } else {
                throw new Exception("El usuario no existe");
            }
        }
        throw new Exception("Validaciones incorrectas");
    }

    private boolean verificarUsuarioExiste(String correo) {
        for (Usuario usuario : getListaUsuarios()) {
            if (usuario.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    public List<Tarea> getTareasUsuario(String correo) {
        Usuario usuario = buscarUsuario(correo);
        if (usuario == null) {
            return new ArrayList<>();
        }

        List<Materia> listaMaterias = usuario.getListaMaterias();
        List<Tarea> todasLasTareas = new ArrayList<>();

        for (Materia materia : listaMaterias) {
            // Extraer todas las tareas de la cola de prioridad y añadirlas a nuestra lista
            ColaPrioridad<Tarea> colaPrioridad = materia.getColaTareas();
            if (colaPrioridad != null) {
                // Usar el método toList() para convertir la cola a lista
                todasLasTareas.addAll(colaPrioridad.toList());
            }
        }

        // Ordenar la lista combinada por fecha de entrega
        todasLasTareas.sort(Comparator.comparing(Tarea::getFechaEntrega));

        return todasLasTareas;
    }

    private Usuario buscarUsuario(String correo) {
        for (Usuario usuario : getListaUsuarios()) {
            if (usuario.getCorreo().equals(correo)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Agrega una nueva tarea a una materia específica de un usuario.
     *
     * @param tarea Tarea a agregar
     * @return true si la tarea se agregó correctamente, false en caso contrario
     */
    public boolean agregarTarea(Tarea tarea, String correo) {
        // Buscar el usuario
        Usuario usuario = buscarUsuario(correo);
        if (usuario == null) {
            return false;
        }

        Materia materiaAsignada = tarea.getMateria();

        // Buscar el usuario que tiene esta materia
        for (Materia materia : usuario.getListaMaterias()) {
            if (materia.getNombreMateria().equals(materiaAsignada.getNombreMateria())) {
                // Asegurarse de que la tarea tenga la materia correcta
                tarea.setMateria(materia);
                // Agregar la tarea a la materia
                materia.agregarTarea(tarea);
                return true;
            }
        }

        // Si no se encontró la materia, retornar false
        return false;
    }
}

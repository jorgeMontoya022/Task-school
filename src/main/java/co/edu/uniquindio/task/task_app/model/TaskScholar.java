package co.edu.uniquindio.task.task_app.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
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

    public boolean verificarUsuarioExiste(String correo) {
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

    public List<Materia> getMateriasUsuario(String correo) {
        Usuario usuario = buscarUsuario(correo);
        return usuario.getListaMaterias();
    }

    public boolean agregarMateria(Materia materia, String correo) {
        Usuario usuario = buscarUsuario(correo);

        Materia materiaEncontrada = buscarMateria(materia.getNombreMateria(), usuario);

        if (materiaEncontrada != null) {
            return false;
        } else {
            usuario.getListaMaterias().add(materia);
            return true;
        }

    }

    private Materia buscarMateria(String nombreMateria, Usuario usuario) {
        for (Materia materia : usuario.getListaMaterias()) {
            if (materia.getNombreMateria().equalsIgnoreCase(nombreMateria)) {
                return materia;
            }
        }
        return null;
    }

    public boolean verificarMateria(String nombreMateria, String correo) {
        Usuario usuario = buscarUsuario(correo);

        for (Materia materia : usuario.getListaMaterias()) {
            if (materia.getNombreMateria().equalsIgnoreCase(nombreMateria)) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarUsuario(Usuario usuario) {
        Usuario usuarioEncontrado = buscarUsuario(usuario.getCorreo());

        if (usuarioEncontrado != null) {
            return false;
        } else {
            listaUsuarios.add(usuario);
            return true;
        }
    }

    /**
     * @param correo
     * @return
     */
    public int contarTareasPendientes(String correo) {
        Usuario usuario = buscarUsuario(correo);
        int tareasPendientes = 0;

        for (Materia materia : usuario.getListaMaterias()) {
            for (Tarea tarea : materia.getColaTareas().getElementos()) {
                if (tarea.getEstadoTarea() == EstadoTarea.PENDIENTE) {
                    tareasPendientes++;
                }
            }

        }

        return tareasPendientes;
    }

    public int contarTareasCompletadas(String correo) {
        Usuario usuario = buscarUsuario(correo);

        int tareasCompletadas = 0;

        for (Materia materia : usuario.getListaMaterias()) {
            for (Tarea tarea : materia.getColaTareas().getElementos()) {
                if (tarea.getEstadoTarea() == EstadoTarea.COMPLETADA) {
                    tareasCompletadas++;
                }
            }
        }
        return tareasCompletadas;
    }

    public int contarTareasProximas(String correo) {
        Usuario usuario = buscarUsuario(correo);
        int tareasProximas = 0;

        if (usuario != null) {
            for (Materia materia : usuario.getListaMaterias()) {
                for (Tarea tarea : materia.getColaTareas().getElementos()) {
                    if (tarea.getEstadoTarea() == EstadoTarea.PENDIENTE) {
                        long diasRestantes = java.time.temporal.ChronoUnit.DAYS.between(
                                java.time.LocalDate.now(), tarea.getFechaEntrega());

                        if (diasRestantes <= 5) { // Alta o Media prioridad
                            tareasProximas++;
                        }
                    }
                }
            }
        }

        return tareasProximas;
    }

    public boolean eliminarMateria(Materia materiaSeleccionada, String correo) {
        Usuario usuario = buscarUsuario(correo);

        Iterator<Materia> iterator = usuario.getListaMaterias().iterator();

        while (iterator.hasNext()) {
            Materia materia = iterator.next();
            if (materia.equals(materiaSeleccionada)) {
                iterator.remove(); 
                return true;
            }
        }

        return false;
    }



    public boolean actualizarTarea(Tarea tareaActual, Tarea tareaEditada, String correo) {
        Usuario usuario = buscarUsuario(correo);
        if (usuario == null) return false;
    
        for (Materia materia : usuario.getListaMaterias()) {
            ColaPrioridad<Tarea> cola = materia.getColaTareas();
    
            // Buscar la tarea actual en la cola
            for (Tarea tarea : cola.getElementos()) {
                if (tarea.getTitulo().equals(tareaActual.getTitulo())) {
    
                    EstadoTarea estadoAnterior = tarea.getEstadoTarea();
                    EstadoTarea nuevoEstado = tareaEditada.getEstadoTarea();
    
                    // Actualizar la tarea con los nuevos valores
                    tarea.setTitulo(tareaEditada.getTitulo());
                    tarea.setDescripción(tareaEditada.getDescripción());
                    tarea.setFechaEntrega(tareaEditada.getFechaEntrega());
                    tarea.setEstadoTarea(nuevoEstado);
    
                    // Si el estado cambió de PENDIENTE a COMPLETADA
                    if (estadoAnterior == EstadoTarea.PENDIENTE && nuevoEstado == EstadoTarea.COMPLETADA) {
                        materia.setTareasPendientes(materia.getTareasPendientes() - 1);
                    }
    
                    // Si el estado cambió de COMPLETADA a PENDIENTE
                    if (estadoAnterior == EstadoTarea.COMPLETADA && nuevoEstado == EstadoTarea.PENDIENTE) {
                        materia.setTareasPendientes(materia.getTareasPendientes() + 1);
                    }
    
                    return true; // Tarea actualizada correctamente
                }
            }
        }
    
        return false; // No se encontró la tarea
    }
    

}

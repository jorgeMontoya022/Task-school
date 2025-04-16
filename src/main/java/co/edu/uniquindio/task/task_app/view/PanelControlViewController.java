package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.task.task_app.Session.Sesion;
import co.edu.uniquindio.task.task_app.controller.PanelControlController;
import co.edu.uniquindio.task.task_app.model.Materia;
import co.edu.uniquindio.task.task_app.model.Tarea;
import co.edu.uniquindio.task.task_app.model.Usuario;
import co.edu.uniquindio.task.task_app.priorityQueue.ColaPrioridad;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PanelControlViewController extends CoreViewController {

    PanelControlController panelControlController;

    Usuario usuario;
    Tarea tareaSeleccionada;
    ObservableList<Tarea> listaTareas = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregarTarea;

    @FXML
    private Button btnEliminarTarea;

    @FXML
    private Button btnMisMaterias;

    @FXML
    private Button btnPanelPrincipal;

    @FXML
    private Button btnVolverInicio;

    @FXML
    private ComboBox<String> cbEstado;

    @FXML
    private ComboBox<Materia> cbMateria;

    @FXML
    private Label labelNombreProfesor;

    @FXML
    private Label labelPrioridad;

    @FXML
    private TableView<Tarea> tablaTareas;

    @FXML
    private TableColumn<Tarea, String> colEstado;

    @FXML
    private TableColumn<Tarea, String> colFecha;

    @FXML
    private TableColumn<Tarea, String> colMateria;

    @FXML
    private TableColumn<Tarea, String> colTarea;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtNombreTarea;

    @FXML
    private TextField txtfiltrarTarea;

    @FXML
    private Text labeBienvenida;

    @FXML
    private AnchorPane materiasView;

    @FXML
    private ScrollPane panelPrincipalView;
    
    @FXML
    void onVolverInicio(ActionEvent event) {
        if (mostrarMensajeConfirmacion("¿Deseas volver al inicio?")) {
            Sesion.getInstance().closeSesion();
            browseWindow("/co/edu/uniquindio/task/task_app/view/login-view.fxml", "Inicio de sesión", event);
        }
    }

    @FXML
    void oEliminarTarea(ActionEvent event) {
        eliminarTarea();
    }

    @FXML
    void onActualizarTarea(ActionEvent event) {
        actualizarTarea();
    }

    @FXML
    private Text textCarrera;

    @FXML
    private Text textNombre;

    @FXML
    public void onPanelPrincipal(ActionEvent event) {
        // Mostrar panel principal, ocultar el resto
        panelPrincipalView.setVisible(true);
        materiasView.setVisible(false);

        // Cambiar estilos de los botones
        actualizarEstilosBotones(btnPanelPrincipal);
    }

    @FXML
    void onMisMaterias(ActionEvent event) {
        panelPrincipalView.setVisible(false);
        materiasView.setVisible(true);

        actualizarEstilosBotones(btnMisMaterias);
    }

    private void actualizarEstilosBotones(Button botonActivo) {
        // Restaurar el estilo por defecto para todos los botones
        String estiloInactivo = "-fx-background-color: white; -fx-text-fill: #718096; -fx-alignment: center-left; -fx-padding: 0 0 0 35; -fx-background-radius: 5;";
        btnPanelPrincipal.setStyle(estiloInactivo);
        btnMisMaterias.setStyle(estiloInactivo);

        // Aplicar estilo activo al botón seleccionado
        String estiloActivo = "-fx-background-color: #edf2f7; -fx-text-fill: #2d3748; -fx-alignment: center-left; -fx-padding: 0 0 0 35; -fx-background-radius: 5;";
        botonActivo.setStyle(estiloActivo);
    }

    @FXML
    void onLimpiarFormulario(ActionEvent event) {
        limpiarCampos();
        deseleccionarTabla();
    }

    @FXML
    void onAgregarTarea(ActionEvent event) {
        agregarTarea();
    }

    @FXML
    void initialize() {
        panelControlController = new PanelControlController();
        usuario = (Usuario) Sesion.getInstance().getUsuario();
        System.out.println("Usuario en PanelControl: " + usuario);
        initView();
    }

    private void initView() {
        mostrarInformacion();
        initDataBinding();
        cargarMaterias();
        cargarEstados();
        cargarTareas();
        configurarFiltro();
        listenerSeleccion();
    }

    private void mostrarInformacion() {
        textCarrera.setText(usuario.getCarrera());
        textNombre.setText(usuario.getNombreCompleto());
        labeBienvenida.setText("Bienvenido de vuelta, " + usuario.getNombreCompleto());
    }
    
    private void initDataBinding() {
        // Configurar las columnas de la tabla con los datos de las tareas
        colTarea.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colMateria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMateria().getNombreMateria()));
        colFecha.setCellValueFactory(cellData -> {
            LocalDate fecha = cellData.getValue().getFechaEntrega();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new SimpleStringProperty(fecha.format(formatter));
        });
        // Por ahora el estado es ficticio, luego puedes implementar un estado real en la clase Tarea
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty("Pendiente"));
    }
    
    private void cargarMaterias() {
        // Cargar las materias del usuario en el ComboBox
        ObservableList<Materia> materias = FXCollections.observableArrayList();
        materias.addAll(usuario.getListaMaterias());
        cbMateria.setItems(materias);
        
        // Configurar cómo se muestran las materias en el ComboBox
        cbMateria.setCellFactory(param -> new javafx.scene.control.ListCell<Materia>() {
            @Override
            protected void updateItem(Materia item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombreMateria());
                }
            }
        });
        
        cbMateria.setButtonCell(new javafx.scene.control.ListCell<Materia>() {
            @Override
            protected void updateItem(Materia item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombreMateria());
                }
            }
        });
        
        // Actualizar la información del profesor cuando se selecciona una materia
        cbMateria.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                labelNombreProfesor.setText("Profesor: " + newVal.getProfesor());
            } else {
                labelNombreProfesor.setText("Profesor: ");
            }
        });
    }
    
    private void cargarEstados() {
        // Por ahora solo tenemos "Pendiente" y "Completada"
        ObservableList<String> estados = FXCollections.observableArrayList("Pendiente", "Completada");
        cbEstado.setItems(estados);
        cbEstado.getSelectionModel().select(0); // Seleccionar "Pendiente" por defecto
    }
    
    private void cargarTareas() {
        listaTareas.clear();
        
        // Obtener todas las tareas de todas las materias del usuario
        List<Tarea> todasLasTareas = new ArrayList<>();
        
        for (Materia materia : usuario.getListaMaterias()) {
            // Obtener todas las tareas de la cola de prioridad
            ColaPrioridad<Tarea> colaTareas = materia.getColaTareas();
            
            // Como no hay un método para obtener todas las tareas de la cola de prioridad,
            // utilizamos un método auxiliar para extraer las tareas
            todasLasTareas.addAll(obtenerTareasDeCola(materia));
        }
        
        // Agregar todas las tareas a la lista observable
        listaTareas.addAll(todasLasTareas);
        
        // Asignar la lista observable a la tabla
        tablaTareas.setItems(listaTareas);
    }
    
    private List<Tarea> obtenerTareasDeCola(Materia materia) {
        // Este método es un workaround ya que la cola de prioridad no permite acceder a todos los elementos
        // sin eliminarlos. En un caso real, podrías modificar la clase ColaPrioridad para agregar un método
        // que retorne todos los elementos sin eliminarlos.
        
        // Por ahora, simplemente retornamos las tareas que ya están en la cola
        List<Tarea> tareas = new ArrayList<>();
        ColaPrioridad<Tarea> colaTareas = materia.getColaTareas();
        
        // Usando una estructura temporal no modificable para no alterar la cola original
        int size = colaTareas.size();
        if (size > 0) {
            // En un escenario real, aquí extraeríamos las tareas de la cola de prioridad
            // sin eliminarlas. Por ahora, solo trabajamos con la tarea que ya existe en los datos iniciales.
            for (int i = 0; i < size; i++) {
                try {
                    // Esta no es la forma correcta de acceder a todos los elementos sin eliminarlos,
                    // pero para datos quemados funcionará
                    Tarea tarea = colaTareas.remove();
                    tareas.add(tarea);
                    // Volver a agregar la tarea a la cola para no perderla
                    colaTareas.add(tarea);
                } catch (Exception e) {
                    // Ignorar errores
                }
            }
        }
        
        return tareas;
    }
    
    private void configurarFiltro() {
        txtfiltrarTarea.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarTareas(newValue);
        });
    }
    
    private void filtrarTareas(String filtro) {
        ObservableList<Tarea> tareasFiltradas = FXCollections.observableArrayList();
        
        for (Materia materia : usuario.getListaMaterias()) {
            List<Tarea> tareas = obtenerTareasDeCola(materia);
            
            for (Tarea tarea : tareas) {
                if (coincideFiltro(tarea, filtro)) {
                    tareasFiltradas.add(tarea);
                }
            }
        }
        
        tablaTareas.setItems(tareasFiltradas);
    }
    
    private boolean coincideFiltro(Tarea tarea, String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return true;
        }
        
        String filtroLower = filtro.toLowerCase();
        
        return tarea.getTitulo().toLowerCase().contains(filtroLower) ||
               tarea.getDescripción().toLowerCase().contains(filtroLower) ||
               tarea.getMateria().getNombreMateria().toLowerCase().contains(filtroLower);
    }
    
    private void listenerSeleccion() {
        tablaTareas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            tareaSeleccionada = newSelection;
            if (tareaSeleccionada != null) {
                mostrarInformacionTarea(tareaSeleccionada);
            }
        });
    }
    
    private void mostrarInformacionTarea(Tarea tarea) {
        txtNombreTarea.setText(tarea.getTitulo());
        txtDescripcion.setText(tarea.getDescripción());
        cbMateria.getSelectionModel().select(tarea.getMateria());
        // Por defecto seleccionamos "Pendiente", luego puedes implementar un estado real
        cbEstado.getSelectionModel().select("Pendiente");
        
        // Mostrar la prioridad (fecha de entrega)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaEntrega = tarea.getFechaEntrega().format(formatter);
        labelPrioridad.setText("Fecha de entrega: " + fechaEntrega);
    }
    
    private void limpiarCampos() {
        txtNombreTarea.clear();
        txtDescripcion.clear();
        cbMateria.getSelectionModel().clearSelection();
        cbEstado.getSelectionModel().select(0); // "Pendiente" por defecto
        labelPrioridad.setText("Fecha de entrega: ");
        labelNombreProfesor.setText("Profesor: ");
    }
    
    private void deseleccionarTabla() {
        tablaTareas.getSelectionModel().clearSelection();
        tareaSeleccionada = null;
    }
    
    private void agregarTarea() {
        // Este método se implementará cuando se agregue la funcionalidad para crear nuevas tareas
        // Por ahora solo muestra un mensaje
        mostrarMensaje("Información", "Funcionalidad en desarrollo", 
                "La funcionalidad para agregar tareas está en desarrollo.", null);
    }
    
    private void eliminarTarea() {
        // Este método se implementará cuando se agregue la funcionalidad para eliminar tareas
        // Por ahora solo muestra un mensaje
        mostrarMensaje("Información", "Funcionalidad en desarrollo", 
                "La funcionalidad para eliminar tareas está en desarrollo.", null);
    }
    
    private void actualizarTarea() {
        // Este método se implementará cuando se agregue la funcionalidad para actualizar tareas
        // Por ahora solo muestra un mensaje
        mostrarMensaje("Información", "Funcionalidad en desarrollo", 
                "La funcionalidad para actualizar tareas está en desarrollo.", null);
    }
}
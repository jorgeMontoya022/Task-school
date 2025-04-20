package co.edu.uniquindio.task.task_app.view;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.task.task_app.Session.Sesion;
import co.edu.uniquindio.task.task_app.controller.GestionMateriasController;
import co.edu.uniquindio.task.task_app.model.Materia;
import co.edu.uniquindio.task.task_app.model.Usuario;
import co.edu.uniquindio.task.task_app.view.observer.EventType;
import co.edu.uniquindio.task.task_app.view.observer.ObserverManagement;
import co.edu.uniquindio.task.task_app.view.observer.ObserverView;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionMateriasViewController extends CoreViewController implements ObserverView {

  GestionMateriasController gestionMateriasController;

  ObservableList<Materia> listaMaterias = FXCollections.observableArrayList();

  Materia materiaSeleccionada;

  Usuario usuario;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button btnAgregar;

  @FXML
  private Button btnEliminar;

  @FXML
  private Button btnLimpiar;

  @FXML
  private TableView<Materia> tableMaterias;

  @FXML
  private TableColumn<Materia, String> tcHorario;

  @FXML
  private TableColumn<Materia, String> tcNombre;

  @FXML
  private TableColumn<Materia, String> tcProfesor;

  @FXML
  private TableColumn<Materia, String> tcTareas;

  @FXML
  private TextField txtHorario;

  @FXML
  private TextField txtNombreMateria;

  @FXML
  private TextField txtProfesor;

  @FXML
  void onAgregar(ActionEvent event) {
    agregarMateria();

  }

  @FXML
  void onEliminar(ActionEvent event) {
    eliminarMateria();

  }

  @FXML
  void onLimpiar(ActionEvent event) {
    limpiarCampos();
    deseleccionar();

  }

  private void deseleccionar() {
    tableMaterias.getSelectionModel().clearSelection();
    materiaSeleccionada = null;
  }

  private void limpiarCampos() {
    txtProfesor.clear();
    txtHorario.clear();
    txtNombreMateria.clear();

  }

  @FXML
  void initialize() {
    gestionMateriasController = new GestionMateriasController();
    usuario = (Usuario) Sesion.getInstance().getUsuario();
    System.out.println("Usuario en gestion de materias " + usuario);
    initView();
    ObserverManagement.getInstance().addObserver(EventType.TAREA, this);

  }

  private void initView() {
    initDataBinding();
    getMaterias();
    tableMaterias.getItems().clear();
    tableMaterias.setItems(listaMaterias);
    listenerSelection();
  }

  private void listenerSelection() {
    tableMaterias.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      materiaSeleccionada = newSelection;
      mostrarInformacion(materiaSeleccionada);
    });

  }

  private void mostrarInformacion(Materia materiaSeleccionada2) {
    if (materiaSeleccionada != null) {
      txtNombreMateria.setText(materiaSeleccionada.getNombreMateria());
      txtHorario.setText(materiaSeleccionada.getHorario());
      txtProfesor.setText(materiaSeleccionada.getProfesor());
    }
  }

  private void getMaterias() {
    String correo = usuario.getCorreo();
    listaMaterias.clear();
    listaMaterias.addAll(gestionMateriasController.getMateriasUsuario(correo));
  }

  private void initDataBinding() {
    tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreMateria()));
    tcProfesor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProfesor()));
    tcHorario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHorario()));
    tcTareas.setCellValueFactory(
        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTareasPendientes())));

  }

  private void agregarMateria() {
    Materia materia = buildMateria();
    if (materia == null) {
      mostrarMensaje("Error", "Datos invalidos", "No se pudo crear la materia", Alert.AlertType.ERROR);
      return;

    }

    if (validarDatos(materia)) {
      if (gestionMateriasController.agregarMateria(materia, usuario.getCorreo())) {
        listaMaterias.add(materia);
        ObserverManagement.getInstance().notifyObservers(EventType.MATERIA);
        mostrarMensaje("Notificación", "Materia agregada", "La materia ha sido agregada con éxito",
            Alert.AlertType.INFORMATION);
        limpiarCampos();

      } else {
        mostrarMensaje("Error", "Materia no agregada", "La materia no pudo ser agregada", Alert.AlertType.ERROR);

      }
    }

  }

  private void eliminarMateria() {
    if(materiaSeleccionada != null) {
      if(mostrarMensajeConfirmacion("¿Está seguro de eliminar la materia seleccionada?")) {
        if(gestionMateriasController.eliminarMateria(materiaSeleccionada, usuario.getCorreo())) {
          listaMaterias.remove(materiaSeleccionada);
          ObserverManagement.getInstance().notifyObservers(EventType.MATERIA);
          deseleccionar();
          limpiarCampos();
          mostrarMensaje("Notificación", "Materia eliminada", 
          "La materia ha sido eliminada con éxito", Alert.AlertType.INFORMATION);
        }else {
          mostrarMensaje("Error", "Materia no eliminada", 
          "La materia no pudo ser eliminada", Alert.AlertType.ERROR);
        }
      }
    } else {
      mostrarMensaje("Advertencia", "Materia no seleccionada", 
      "Por favor, seleccione una materia para eliminar", Alert.AlertType.WARNING);
    }

  }

  private boolean validarDatos(Materia materia) {
    String mensaje = "";
    if (materia.getNombreMateria().isEmpty()) {
      mensaje += "El nombre de la materia es requerido. \n";
    }

    if (materia.getHorario().isEmpty()) {
      mensaje += "El horario de su materia es requerido.\n";
    }
    if (materia.getProfesor().isEmpty()) {
      mensaje += "El nombre del profesor es requerido.\n";
    }

    if (gestionMateriasController.verificarMateria(materia.getNombreMateria(), usuario.getCorreo())) {
      mensaje += "Ya tienes esta materia";
    }

    if (!mensaje.isEmpty()) {
      mostrarMensaje("Notificación de validación", "Datos no validos", mensaje, Alert.AlertType.WARNING);
      return false;
    }
    return true;
  }

  private Materia buildMateria() {
    String materia = txtNombreMateria.getText().trim();
    String horario = txtHorario.getText().trim();
    String profesor = txtProfesor.getText().trim();
    return new Materia(materia, profesor, horario, 0);

  }

  @Override
  public void updateView(EventType eventType) {
    if (eventType == EventType.TAREA) {
      getMaterias();
      tableMaterias.refresh();
    }

  }

}

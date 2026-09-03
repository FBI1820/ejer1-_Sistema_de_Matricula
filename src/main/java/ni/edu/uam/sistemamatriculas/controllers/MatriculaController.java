package ni.edu.uam.sistemamatriculas.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import ni.edu.uam.sistemamatriculas.models.Estudiante;
import ni.edu.uam.sistemamatriculas.AlertaUtil;

import java.time.LocalDate;

public class MatriculaController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField pwContrasena;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private ComboBox<String> cbDepartamento;

    @FXML private ListView<String> lvcursos;
    @FXML private ListView<String> lvHorarios;

    @FXML private RadioButton rbPresencial;
    @FXML private RadioButton rbVirtual;
    @FXML private ToggleGroup grpModalidad;

    @FXML private CheckBox chkAceptarNormas;

    @FXML private TableView<Estudiante> tblEstudiantes;
    @FXML private TableColumn<Estudiante, String> colNombre;
    @FXML private TableColumn<Estudiante, String> colApellido;
    @FXML private TableColumn<Estudiante, String> colDepartamento;
    @FXML private TableColumn<Estudiante, String> colCurso;
    @FXML private TableColumn<Estudiante, String> colHorario;
    @FXML private TableColumn<Estudiante, Boolean> colModalidad;
    @FXML private TableColumn<Estudiante, LocalDate> colFechaNacimiento;

    private final ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
    private Estudiante estudianteSeleccionado = null;

    @FXML
    public void initialize() {
        cbDepartamento.getItems().addAll("Managua", "Matagalpa", "Jinotega", "León", "Granada");

        // Cargar ítems
        lvcursos.getItems().addAll(
                "Programación Java",
                "Excel Avanzado",
                "Redes CISCO",
                "Diseño Gráfico"
        );

        lvHorarios.getItems().addAll(
                "[08:00 - 10:00 AM] Lunes y Miércoles",
                "[10:00 - 12:00 PM] Martes y Jueves",
                "[01:00 - 05:00 PM] Viernes",
                "[08:00 - 12:00 PM] Sábados"
        );

        // Formato CheckBox para ListViews
        lvcursos.setCellFactory(CheckBoxListCell.forListView(item -> {
            javafx.beans.property.BooleanProperty prop = new javafx.beans.property.SimpleBooleanProperty(
                    lvcursos.getSelectionModel().getSelectedItems().contains(item)
            );
            prop.addListener((obs, wasSelected, isSelected) -> {
                if (isSelected) lvcursos.getSelectionModel().select(item);
                else lvcursos.getSelectionModel().clearSelection(lvcursos.getItems().indexOf(item));
            });
            return prop;
        }));

        lvHorarios.setCellFactory(CheckBoxListCell.forListView(item -> {
            javafx.beans.property.BooleanProperty prop = new javafx.beans.property.SimpleBooleanProperty(
                    lvHorarios.getSelectionModel().getSelectedItems().contains(item)
            );
            prop.addListener((obs, wasSelected, isSelected) -> {
                if (isSelected) lvHorarios.getSelectionModel().select(item);
                else lvHorarios.getSelectionModel().clearSelection(lvHorarios.getItems().indexOf(item));
            });
            return prop;
        }));

        // Mapeo de columnas de la tabla
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        colModalidad.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

        tblEstudiantes.setItems(listaEstudiantes);

        // Listener para cargar datos al seleccionar fila
        tblEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                estudianteSeleccionado = newSel;
                cargarFormulario(newSel);
            }
        });
    }

    private void cargarFormulario(Estudiante e) {
        txtNombre.setText(e.getNombre());
        txtApellido.setText(e.getApellido());
        txtUsuario.setText(e.getUsuario());
        pwContrasena.setText(e.getContrasena());
        cbDepartamento.setValue(e.getDepartamento());
        dpFechaNacimiento.setValue(e.getFechaNacimiento());

        lvcursos.getSelectionModel().select(e.getCurso());
        lvHorarios.getSelectionModel().select(e.getHorario());

        if (Boolean.TRUE.equals(e.getModalidad())) {
            rbPresencial.setSelected(true);
        } else {
            rbVirtual.setSelected(true);
        }

        chkAceptarNormas.setSelected(true);
    }

    @FXML
    protected void onActionGuardar() {
        if (!chkAceptarNormas.isSelected()) return;

        // Construcción del objeto utilizando Lombok Builder
        Estudiante nuevo = Estudiante.builder()
                .nombre(txtNombre.getText())
                .apellido(txtApellido.getText())
                .usuario(txtUsuario.getText())
                .contrasena(pwContrasena.getText())
                .departamento(cbDepartamento.getValue())
                .curso(lvcursos.getSelectionModel().getSelectedItem())
                .Horario(lvHorarios.getSelectionModel().getSelectedItem())
                .modalidad(rbPresencial.isSelected())
                .fechaNacimiento(dpFechaNacimiento.getValue())
                .build();

        listaEstudiantes.add(nuevo);
        limpiar();
    }

    @FXML
    protected void onActionActualizar() {
        if (estudianteSeleccionado == null) return;

        estudianteSeleccionado.setNombre(txtNombre.getText());
        estudianteSeleccionado.setApellido(txtApellido.getText());
        estudianteSeleccionado.setUsuario(txtUsuario.getText());
        estudianteSeleccionado.setContrasena(pwContrasena.getText());
        estudianteSeleccionado.setDepartamento(cbDepartamento.getValue());
        estudianteSeleccionado.setCurso(lvcursos.getSelectionModel().getSelectedItem());
        estudianteSeleccionado.setHorario(lvHorarios.getSelectionModel().getSelectedItem());
        estudianteSeleccionado.setModalidad(rbPresencial.isSelected());
        estudianteSeleccionado.setFechaNacimiento(dpFechaNacimiento.getValue());

        tblEstudiantes.refresh();
        limpiar();
    }

    @FXML
    protected void onActionEliminar() {
        if (estudianteSeleccionado == null) return;
        listaEstudiantes.remove(estudianteSeleccionado);
        limpiar();
    }

    @FXML
    protected void onActionLimpiar() {
        limpiar();
    }

    public void limpiar() {
        txtNombre.clear();
        txtApellido.clear();
        txtUsuario.clear();
        pwContrasena.clear();
        cbDepartamento.getSelectionModel().clearSelection();

        lvcursos.getSelectionModel().clearSelection();
        lvHorarios.getSelectionModel().clearSelection();

        dpFechaNacimiento.setValue(null);

        if (grpModalidad.getSelectedToggle() != null) {
            grpModalidad.getSelectedToggle().setSelected(false);
        }

        chkAceptarNormas.setSelected(false);
        tblEstudiantes.getSelectionModel().clearSelection();
        estudianteSeleccionado = null;
    }
}
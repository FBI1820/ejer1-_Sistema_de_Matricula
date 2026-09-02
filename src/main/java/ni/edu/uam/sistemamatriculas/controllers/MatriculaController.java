package ni.edu.uam.sistemamatriculas.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ni.edu.uam.sistemamatriculas.dao.EstudianteDao;
import ni.edu.uam.sistemamatriculas.models.Estudiante;

import java.time.LocalDate;

public class MatriculaController {
    EstudianteDao lista;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField pwContrasena;

    @FXML
    private ComboBox cbDepartamento;

    @FXML
    private ListView lvcursos;

    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    private RadioButton rbModalidad;

    @FXML
    private CheckBox chbHorario;


    public void initialize(){
        cbDepartamento.getItems().addAll("Managua", "Matagalpa", "Jinotega");

    }

    public void leerDatos() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String usuario = txtUsuario.getText();
        String contrasena = pwContrasena.getText();
        String departamento = (String) cbDepartamento.getValue();
        LocalDate fechaNacimiento = dpFechaNacimiento.getValue();
        Boolean modalidad = rbModalidad.isSelected();
        Boolean horario = chbHorario.isSelected();


    }

    public void limpiar(){
        txtNombre.clear();
        txtApellido.clear();
        txtUsuario.clear();
        pwContrasena.clear();
        cbDepartamento.getSelectionModel().clearSelection();
        lvcursos.getSelectionModel().clearSelection();
        dpFechaNacimiento.setValue(null);
    }










}

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

    @FXML
    protected void onActionGuardar() {
        if (!validar()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campos vacíos", "Por favor llene todos los campos.");
            return;
        }
        else
        {
            leerDatos();
            limpiar();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso", "El estudiante ha sido registrado correctamente.");
        }


    }

    @FXML
    protected void onActionLimpiar(){
        limpiar();
    }


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
        if (validarContrasena()) {
            Estudiante estudiante = new Estudiante(nombre, apellido, usuario, contrasena, departamento, fechaNacimiento, null, modalidad, horario);
            lista.agregar(estudiante);
            limpiar();
        }


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

    public Boolean validarContrasena(){
        if(pwContrasena.getText().length() < 8){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Contraseña inválida");
            alert.setContentText("La contraseña debe tener al menos 8 caracteres");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public Boolean validar() {
        return !txtUsuario.getText().trim().isEmpty() &&
                !txtNombre.getText().trim().isEmpty() &&
                !txtApellido.getText().trim().isEmpty() &&
                !pwContrasena.getText().trim().isEmpty() &&
                cbDepartamento.getValue() != null &&
                dpFechaNacimiento.getValue() != null;

    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }












}

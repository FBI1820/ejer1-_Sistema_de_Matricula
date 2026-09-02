package ni.edu.uam.sistemamatriculas.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasena;
    private String departamento;
    private LocalDate fechaNacimiento;
    private String curso;
    private Boolean modalidad;
    private Boolean Horario;

}

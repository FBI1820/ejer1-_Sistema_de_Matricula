package ni.edu.uam.sistemamatriculas.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Data
@Builder
public class Estudiante {
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasena;
    private String departamento;
    private LocalDate fechaNacimiento;
    private String curso;
    private Boolean modalidad;
    private String Horario;

}

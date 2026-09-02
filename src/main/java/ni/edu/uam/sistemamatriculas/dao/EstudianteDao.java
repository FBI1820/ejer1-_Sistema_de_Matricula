package ni.edu.uam.sistemamatriculas.dao;

import lombok.AllArgsConstructor;
import ni.edu.uam.sistemamatriculas.models.Estudiante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EstudianteDao {
    private List<Estudiante> estudiantes;

    public EstudianteDao() {
        this.estudiantes = new ArrayList<>();
    }

    public void agregar(Estudiante entidad) {
        estudiantes.add(entidad);

    }


    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }


}

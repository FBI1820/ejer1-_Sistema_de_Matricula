module ni.edu.uam.sistemamatriculas {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.sistemamatriculas to javafx.fxml;
    exports ni.edu.uam.sistemamatriculas;
}
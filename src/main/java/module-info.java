module ni.edu.uam.sistemamatriculas {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.edu.uam.sistemamatriculas to javafx.fxml;
    exports ni.edu.uam.sistemamatriculas;
}
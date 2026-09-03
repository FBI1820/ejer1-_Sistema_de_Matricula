module ni.edu.uam.sistemamatriculas {
    requires javafx.controls;
    requires javafx.fxml;


    requires static lombok;

    opens ni.edu.uam.sistemamatriculas to javafx.fxml;
    exports ni.edu.uam.sistemamatriculas;

    opens ni.edu.uam.sistemamatriculas.controllers to javafx.fxml;
    exports ni.edu.uam.sistemamatriculas.controllers;

    opens ni.edu.uam.sistemamatriculas.models to javafx.base;
    exports ni.edu.uam.sistemamatriculas.models;
}
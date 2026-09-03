package ni.edu.uam.sistemamatriculas;

import javafx.scene.control.Alert;

public class AlertaUtil {
        public static void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
            Alert alert = new Alert(tipo);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        }
    }


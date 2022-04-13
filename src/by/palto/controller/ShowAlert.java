package by.palto.controller;

import javafx.scene.control.Alert;

final class ShowAlert {

    private ShowAlert() {

    }


    static void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    static void showSuccessfulAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
        alert.showAndWait();
    }
}

module ru.suharik.handwrittennumberrecognizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.suharik.handwrittennumberrecognizer to javafx.fxml;
    exports ru.suharik.handwrittennumberrecognizer;
}
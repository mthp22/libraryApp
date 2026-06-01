module com.lm {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.lm to javafx.fxml;
    exports com.lm;
}

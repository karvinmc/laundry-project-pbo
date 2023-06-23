module com.example.projectpbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.projectpbo to javafx.fxml;
    exports com.example.projectpbo;
    opens com.example.projectpbo.beans to javafx.fxml;
    exports com.example.projectpbo.beans;
    exports com.example.projectpbo.controllers.login;
    opens com.example.projectpbo.controllers.login to javafx.fxml;
    exports com.example.projectpbo.controllers.main;
    opens com.example.projectpbo.controllers.main to javafx.fxml;
}
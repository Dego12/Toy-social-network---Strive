module Console {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens Console to javafx.fxml;
    opens repository to javafx.fxml;
    opens domain to javafx.fxml, javafx.graphics, javafx.base;
    opens service to javafx.fxml;
    opens UI to javafx.fxml;
    opens validators to javafx.fxml;



    exports Console;
}
module pl.com.dragon {
    requires javafx.fxml;
    requires javafx.controls;
   // requires javax.mail.api;
    requires java.mail;
    requires java.desktop;

    opens pl.com.dragon to javafx.fxml;
    opens pl.com.dragon.controllers to javafx.fxml, javafx.graphics;

    exports pl.com.dragon.controllers to javafx.fxml, javafx.graphics;
    exports pl.com.dragon to javafx.fxml, javafx.graphics;
}
module com.example.tap_intellij {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tap_intellij to javafx.fxml;
    // opens com.example.tap_intellij.Componentes to javafx.base;
    opens com.example.tap_intellij.Modelos to javafx.base;
    exports com.example.tap_intellij;

    requires java.sql;
    // requires mysql.connector.j;
    requires mariadb.java.client;

    requires org.kordamp.bootstrapfx.core;
}
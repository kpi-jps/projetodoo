module com.vettodos {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;
    requires jbcrypt;
    
    opens com.vettodos.application.view to javafx.fxml;
    opens com.vettodos.application.controller to javafx.fxml;
    

    exports com.vettodos.application.view;
    exports com.vettodos.application.controller;

}

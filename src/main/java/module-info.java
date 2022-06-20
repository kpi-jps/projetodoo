module com.vettodos {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;
    requires jbcrypt;
    
    opens com.vettodos.application.view to javafx.fxml;
    opens com.vettodos.application.controller to javafx.fxml;
    opens com.vettodos.model.domain.entities.individuo to javafx.base;
    opens com.vettodos.model.domain.entities.animal to javafx.base;
    opens com.vettodos.model.domain.entities.produto to javafx.base;
    opens com.vettodos.model.domain.entities.registro_estoque to javafx.base;
    opens com.vettodos.model.domain.entities.atendimento to javafx.base;
    

    exports com.vettodos.application.view;
    exports com.vettodos.application.controller;

}

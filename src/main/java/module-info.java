module com.vettodos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    

    //opens com.projetodoo.vettodos.model.domain.usecases.animal to jbcrypt;
    opens com.vettodos to javafx.fxml;
    exports com.vettodos;
    //exports com.projetodoo.vettodos.model.domain.usecases.util;
}

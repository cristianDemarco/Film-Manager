module com.example.filmmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.filmmanager to javafx.fxml;
    exports com.example.filmmanager;
    exports com.example.filmmanager.Controllers;
    opens com.example.filmmanager.Controllers to javafx.fxml;
}
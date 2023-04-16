module com.example.snakeladderbyram {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeladderbyram to javafx.fxml;
    exports com.example.snakeladderbyram;
}
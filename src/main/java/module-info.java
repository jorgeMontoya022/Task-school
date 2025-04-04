module co.edu.uniquindio.task.task_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.task.task_app to javafx.fxml;
    exports co.edu.uniquindio.task.task_app;
}
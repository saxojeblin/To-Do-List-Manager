module rubio.COP3330.assignment4part2.main {
    requires javafx.fxml;
    requires javafx.controls;

    opens ucf.assignments to javafx.fxml;
    exports ucf.assignments;
}
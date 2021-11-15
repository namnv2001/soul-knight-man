module master.soulknight {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens master.soulknight to javafx.fxml;
    exports master.soulknight;
}
module master.soulknight {
    requires javafx.controls;
    requires javafx.fxml;


    opens master.soulknight to javafx.fxml;
    exports master.soulknight;
}
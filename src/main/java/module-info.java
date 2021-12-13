module master.soulknight {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;

    opens master.soulknight to javafx.fxml;
    exports master.soulknight;
}
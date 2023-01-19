module com.forpus.forpus_inventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    //requires eu.hansolo.tilesfx;

    opens com.forpus.forpus_inventory to javafx.fxml;
    exports com.forpus.forpus_inventory;
    opens com.forpus.forpus_inventory.module;
    opens com.forpus.forpus_inventory.persistence.entity;
}
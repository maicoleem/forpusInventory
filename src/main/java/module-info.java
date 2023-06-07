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
    requires java.desktop;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires jasperreports;
    //requires eu.hansolo.tilesfx;

    opens com.forpus.forpus_inventory to javafx.fxml;
    opens com.forpus.forpus_inventory.controller to javafx.fxml;
    opens com.forpus.styles to javafx.fxml;
    opens com.forpus.jasper_report to javafx.fxml;
    opens com.forpus.forpus_inventory.persistence.entity;
    opens com.forpus.forpus_inventory.domain.services;

    exports com.forpus.forpus_inventory;
    exports com.forpus.forpus_inventory.controller;
    exports com.forpus.forpus_inventory.persistence.entity;
    exports com.forpus.forpus_inventory.domain.repository;




}
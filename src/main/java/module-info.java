module com.forpus.forpusinventory {
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;

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

    opens com.forpus.forpusinventory to javafx.fxml;
    opens com.forpus.forpusinventory.controller to javafx.fxml;
    opens com.forpus.styles to javafx.fxml;
    opens com.forpus.jasper_report to javafx.fxml;
    opens com.forpus.forpusinventory.persistence.entity;
    opens com.forpus.forpusinventory.domain.services;

    exports com.forpus.forpusinventory.controller;
    exports com.forpus.forpusinventory.persistence.entity;
    exports com.forpus.forpusinventory.domain.repository;
    exports com.forpus.forpusinventory to javafx.graphics;

}
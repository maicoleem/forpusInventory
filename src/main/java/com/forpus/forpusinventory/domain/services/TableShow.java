package com.forpus.forpusinventory.domain.services;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableShow {

    private final StringProperty c1 = new SimpleStringProperty(this, "c1");
    private final StringProperty c2 = new SimpleStringProperty(this, "c2");
    private final StringProperty c3 = new SimpleStringProperty(this, "c3");
    private final StringProperty c4 = new SimpleStringProperty(this, "c4");
    private final StringProperty c5 = new SimpleStringProperty(this, "c5");
    private final StringProperty c6 = new SimpleStringProperty(this, "c6");
    private final StringProperty c7 = new SimpleStringProperty(this, "c7");
    private final StringProperty c8 = new SimpleStringProperty(this, "c8");
    private final StringProperty c9 = new SimpleStringProperty(this, "c9");

    public String getC1() {
        return c1.get();
    }

    public StringProperty c1Property() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1.set(c1);
    }

    public String getC2() {
        return c2.get();
    }

    public StringProperty c2Property() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2.set(c2);
    }

    public String getC3() {
        return c3.get();
    }

    public StringProperty c3Property() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3.set(c3);
    }

    public String getC4() {
        return c4.get();
    }

    public StringProperty c4Property() {
        return c4;
    }

    public void setC4(String c4) {
        this.c4.set(c4);
    }

    public String getC5() {
        return c5.get();
    }

    public StringProperty c5Property() {
        return c5;
    }

    public void setC5(String c5) {
        this.c5.set(c5);
    }

    public String getC6() {
        return c6.get();
    }

    public StringProperty c6Property() {
        return c6;
    }

    public void setC6(String c6) {
        this.c6.set(c6);
    }

    public String getC7() {
        return c7.get();
    }

    public StringProperty c7Property() {
        return c7;
    }

    public void setC7(String c7) {
        this.c7.set(c7);
    }

    public String getC8() {
        return c8.get();
    }

    public StringProperty c8Property() {
        return c8;
    }

    public void setC8(String c8) {
        this.c8.set(c8);
    }

    public String getC9() {
        return c9.get();
    }

    public StringProperty c9Property() {
        return c9;
    }

    public void setC9(String c9) {
        this.c9.set(c9);
    }
}
